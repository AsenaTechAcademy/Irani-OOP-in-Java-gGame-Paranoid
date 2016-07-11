import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class LevelMessages
{
	public static void ShowLevelMessages(PlayerInfo currentPlayer , Graphics graphics ,
			LevelStatus Levelstatus)
	{
		ShowScoreBoard(currentPlayer , graphics , Levelstatus);
		ShowLevelNotStarted(currentPlayer , graphics , Levelstatus);
		ShowLossLive(currentPlayer , graphics , Levelstatus);
		ShowGameOver(currentPlayer , graphics , Levelstatus);
		ShowLevelWinned(currentPlayer , graphics , Levelstatus);
		ShowMenu(currentPlayer , graphics , Levelstatus);
	}
	
	private static void ShowMenu(PlayerInfo currentPlayer , Graphics graphics ,
			LevelStatus Levelstatus)
	{
		if (Levelstatus==LevelStatus.LevelStop_Player)
		{
			graphics.setColor(Color.RED);
			graphics.fillRect(150 , 380 , 500 , 80);
			graphics.setColor(Color.BLUE);
			graphics.setFont(new Font("Arial" , Font.BOLD , 28));
			graphics.drawString("Game Stop" , 320 , 405);
			
			graphics.setColor(Color.WHITE);
			graphics.setFont(new Font("Tahoma" , Font.BOLD , 12));
			graphics.drawString("press  ESC to exit game" , 310 , 440);
			graphics.drawString("press  Ctrl  to resume game" , 310 , 453);
		}
	}
	
	private static void ShowScoreBoard(PlayerInfo currentPlayer , Graphics graphics ,
			LevelStatus Levelstatus)
	{
		graphics.setColor(Color.BLACK);
		graphics.setFont(new Font("Tahoma" , Font.BOLD , 12));
		graphics.drawString("Level: "+currentPlayer.getCurrentLevel() , 20 , 590);
		graphics.drawString("Lives: "+currentPlayer.getLives() , 100 , 590);
		graphics.drawString("Score: "+currentPlayer.getScore() , 180 , 590);
		graphics.drawString("Fires: "+currentPlayer.getFires() , 260 , 590);
		graphics.drawString("Time: "+currentPlayer.getCurrentTime() , 340 , 590);
		graphics.drawString("Salaam  "+currentPlayer.getPlayerName()+", " , 420 , 590);
	}
	
	private static void ShowLevelNotStarted(PlayerInfo currentPlayer , Graphics graphics ,
			LevelStatus Levelstatus)
	{
		if (Levelstatus==LevelStatus.LevelNotStarted)
		{
			
			graphics.setColor(Color.RED);
			graphics.fillRect(150 , 380 , 500 , 80);
			graphics.setColor(Color.BLUE);
			graphics.setFont(new Font("Arial" , Font.BOLD , 28));
			graphics.drawString("Welcome to GGames.Paraniod" , 190 , 405);
			
			graphics.setColor(Color.WHITE);
			graphics.setFont(new Font("Tahoma" , Font.BOLD , 12));
			graphics.drawString("Salaam "+currentPlayer.getPlayerName()
					+", Press CTRL to start the game" , 200 , 440);
		}
	}
	
	
	private static void ShowGameOver(PlayerInfo currentPlayer , Graphics graphics ,
			LevelStatus Levelstatus)
	{
		if (Levelstatus==LevelStatus.GameOver)
		{
			graphics.setColor(Color.RED);
			graphics.fillRect(150 , 380 , 500 , 80);
			graphics.setColor(Color.BLUE);
			graphics.setFont(new Font("Arial" , Font.BOLD , 28));
			graphics.drawString("Game Over, Looser !" , 230 , 405);
			
			graphics.setColor(Color.WHITE);
			graphics.setFont(new Font("Tahoma" , Font.BOLD , 12));
			graphics.drawString("Press Ctrl to exit" , 310 , 440);
		}
	}
	
	private static void ShowLossLive(PlayerInfo currentPlayer , Graphics graphics ,
			LevelStatus Levelstatus)
	{
		if (Levelstatus==LevelStatus.LevelStop_LiveLoss)
		{
			graphics.setColor(Color.RED);
			graphics.fillRect(150 , 380 , 500 , 80);
			graphics.setColor(Color.BLUE);
			graphics.setFont(new Font("Arial" , Font.BOLD , 28));
			graphics.drawString("Be careful !!" , 300 , 405);
			
			graphics.setColor(Color.WHITE);
			graphics.setFont(new Font("Tahoma" , Font.BOLD , 12));
			graphics.drawString("Press Ctrl to continue" , 310 , 440);
		}
	}
	
	private static void ShowLevelWinned(PlayerInfo currentPlayer , Graphics graphics ,
			LevelStatus Levelstatus)
	{
		if (Levelstatus==LevelStatus.LevelWined)
		{
			graphics.setColor(Color.RED);
			graphics.fillRect(150 , 380 , 500 , 80);
			graphics.setColor(Color.BLUE);
			graphics.setFont(new Font("Arial" , Font.BOLD , 28));
			graphics.drawString("OK, Level Complete" , 250 , 405);
			
			graphics.setColor(Color.WHITE);
			graphics.setFont(new Font("Tahoma" , Font.BOLD , 12));
			graphics.drawString("Press Ctrl to next level" , 310 , 440);
		}
	}
	
}
