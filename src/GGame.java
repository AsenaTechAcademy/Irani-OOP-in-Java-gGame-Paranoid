import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

// GGames.Paraniod
// by GholamAli Irani, University of Bonab
public class GGame
{
	private static PlayerInfo			currentPlayer;
	private static LevelStatus			Levelstatus	=LevelStatus.LevelNotStarted;
	
	private JFrame						frmMain;
	private BufferStrategy				buffer;
	Graphics							graphics;
	
	private static GameElementAdapter	Entities[]	=new GameElementAdapter[1000];
	private static int					cEntities	=0;
	
	public GGame()
	{
		GraphicsEnvironment genv=GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice device=genv.getDefaultScreenDevice();
		GraphicsConfiguration gc=device.getDefaultConfiguration();
		
		frmMain=new JFrame(gc);
		frmMain.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmMain.setUndecorated(true);
		frmMain.setResizable(false);
		frmMain.setLayout(null);
		frmMain.setIgnoreRepaint(true);
		
		device.setFullScreenWindow(frmMain);
		device.setDisplayMode(new DisplayMode(GameConstants.Game_Width , GameConstants.Game_Height ,
				32 , 0));
		
		frmMain.createBufferStrategy(2);
		buffer=frmMain.getBufferStrategy();
		
		frmMain.requestFocus();
		frmMain.addKeyListener(new KeyHandler());
		frmMain.addMouseMotionListener(new MouseMotionHandler());
		frmMain.addMouseListener(new MouseHandler());
		
		
	}// end of default constructor
	
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Other Methods
	public void setPlayerInfo(PlayerInfo p)
	{
		currentPlayer=new PlayerInfo(p);
	}
	
	public PlayerInfo getPlayerInfo()
	{
		return currentPlayer;
	}
	
	public static void IncreaseFires(int fires)
	{
		currentPlayer.setFires(currentPlayer.getFires()+fires);
	}
	
	public static void DecreaseFires(int fires)
	{
		currentPlayer.setFires(currentPlayer.getFires()-fires);
	}
	
	public static int getTotalFires()
	{
		return currentPlayer.getFires();
	}
	
	public static void IncreaseScore(int score)
	{
		currentPlayer.setScore(currentPlayer.getScore()+score);
		if (score>0)
			SoundStore.get().Play(Sounds.Score);
	}
	
	public static void IncreaseLive()
	{
		currentPlayer.setLives(currentPlayer.getLives()+1);
	}
	
	public static void DecreaseLive()
	{
		if (currentPlayer.getLives()==1)
			Levelstatus=LevelStatus.GameOver;
		else
		{
			currentPlayer.setLives(currentPlayer.getLives()-1);
			Levelstatus=LevelStatus.LevelStop_LiveLoss;
			((GBar) Entities[1]).Reset(); // Reset Bar to first status
			((GBall) Entities[2]).Reset(); // Reset Ball to first status
		}
	}
	
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Add Entities
	public void setNextEntity(GameElementAdapter element)
	{
		element.setID(cEntities);
		Entities[cEntities++]=element;
	}
	
	public static void addNewEntity(GameElementAdapter element)
	{
		element.setID(cEntities);
		Entities[cEntities++]=element;
		// Maybe we do something in the future
	}
	
	public void clearEntities()
	{
		cEntities=0;
	}
	
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Game Start
	public void Game_Start()
	{
		Levelstatus=LevelStatus.LevelNotStarted;
		currentPlayer.setStartTime(System.currentTimeMillis());
	}
	
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Game Loop
	public LevelStatus Game_Loop()
	{
		
		while (true)
		{
			graphics=buffer.getDrawGraphics();
			
			if (cEntities==3)
				Levelstatus=LevelStatus.LevelWined;
			
			// Step of All Entities
			if (Levelstatus==LevelStatus.LevelRunning)
			{
				for (int i=0 ; i<cEntities ; i++)
					Entities[i].Step();
				CheckCollisions();
				currentPlayer.setCurrentTime((System.currentTimeMillis()-currentPlayer
						.getStartTime())/1000);
			}
			
			// Draw ALL current entities in any condition of game
			for (int i=0 ; i<cEntities ; i++)
				Entities[i].draw(graphics);
			
			// Message of Game
			LevelMessages.ShowLevelMessages(currentPlayer , graphics , Levelstatus);
			
			buffer.show();
			graphics.dispose();
			
			if ((Levelstatus==LevelStatus.waitNextLevel)||(Levelstatus==LevelStatus.waitGameOver))
				return Levelstatus;
		}// End of While(true)
	}
	
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Collisions
	private void CheckCollisions()
	{
		// Check collision of all moving elements with static elements
		for (int mm=1 ; mm<cEntities ; mm++)
		{
			for (int i=mm+1 ; i<cEntities ; i++)
			{
				GameElementAdapter me=(GameElementAdapter) Entities[mm];
				GameElementAdapter him=(GameElementAdapter) Entities[i];
				
				if (me.Contains(him))
				{
					me.CollideWith(him);
					him.CollideWith(me);
				}
			}
		}
	} // end of method check collisions
	
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Death Notify
	public static void NotifyDeath(GameElementAdapter element)
	{
		Entities[cEntities-1].setID(element.getID());
		Entities[element.getID()]=Entities[--cEntities];
	}
	
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Mouse Adapter
	private class MouseMotionHandler extends MouseMotionAdapter
	{
		public void mouseMoved(MouseEvent e)
		{
			// Say to all entities
		}
	}
	
	private class MouseHandler extends MouseAdapter
	{
		public void mouseClicked(MouseEvent e)
		{
			// Say to all entities
		}
	}
	
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ key Adapter
	private class KeyHandler extends KeyAdapter
	{
		public void keyPressed(KeyEvent e)
		{
			if (Levelstatus==LevelStatus.LevelRunning)
			{
				// say to all elements that a key Pressed
				for (int i=0 ; i<cEntities ; i++)
					if (Entities[i].WantKeyEvents())
						Entities[i].KeyPressed(e);
				return;
			}
		}
		
		public void keyReleased(KeyEvent e)
		{
			if (e.getKeyCode()==KeyEvent.VK_ESCAPE)
			{
				if (Levelstatus==LevelStatus.LevelStop_Player)
					Levelstatus=LevelStatus.waitGameOver;
				if (Levelstatus==LevelStatus.LevelRunning)
					Levelstatus=LevelStatus.LevelStop_Player;
				return;
			}
			if (e.getKeyCode()==KeyEvent.VK_CONTROL)
			{
				if (Levelstatus==LevelStatus.GameOver)
					Levelstatus=LevelStatus.waitGameOver;
				if ((Levelstatus==LevelStatus.LevelWined))
					Levelstatus=LevelStatus.waitNextLevel;
				if (Levelstatus==LevelStatus.LevelNotStarted
						||Levelstatus==LevelStatus.LevelStop_LiveLoss
						||Levelstatus==LevelStatus.LevelStop_Player)
					Levelstatus=LevelStatus.LevelRunning;
				return;
			}
			
			if ((Levelstatus==LevelStatus.LevelRunning)
					||(Levelstatus==LevelStatus.LevelStop_LiveLoss))
			{
				// say to all elements that a key Released
				for (int i=0 ; i<cEntities ; i++)
					if (Entities[i].WantKeyEvents())
						Entities[i].KeyReleased(e);
			}
		}
		
	}// End of Key Adapter
	
}// end of class GGame



