import java.awt.event.KeyEvent;

public class GBar extends GameMovableElementAdapter
{
	// to firing from both Right and Left sequentially
	private boolean	isRightFire	=true;
	
	public GBar(int x , int y)
	{
		super("Images/Bar2-150-15.jpg" , x , y);
		setSpeedX(12);
		setSpeedY(0);
	}
	
	public void Reset()
	{
		this.setXY(300 , 560); // set to first status
	}
	
	// need to get Key Events
	public boolean WantKeyEvents()
	{
		return true;
	}
	
	public void KeyReleased(KeyEvent e)
	{
		if (e.getKeyCode()==KeyEvent.VK_LEFT||e.getKeyCode()==KeyEvent.VK_RIGHT)
			StopMoving();
	}
	
	public void KeyPressed(KeyEvent e)
	{
		
		if (e.getKeyCode()==KeyEvent.VK_SPACE)
			Fire();
		
		if (e.getKeyCode()==KeyEvent.VK_LEFT)
		{
			StartMoving();
			setLeftDirection();
		}
		if (e.getKeyCode()==KeyEvent.VK_RIGHT)
		{
			StartMoving();
			setRightDirection();
		}
	}
	
	private void Fire()
	{
		if (GGame.getTotalFires()>0)
		{
			// Create new Bullet and shoot up from Right and Left
			Bullet b=new Bullet(0 , 0);
			if (isRightFire)
				b.setXY(this.getX()+this.getWidth()-b.getWidth() , this.getY()-b.getHeight()-1);
			else
				b.setXY(this.getX() , this.getY()-b.getHeight()-1);
			isRightFire=!isRightFire;
			
			GGame.addNewEntity(b);
			GGame.DecreaseFires(1);
			SoundStore.get().Play(Sounds.Fire);
		}
	}
}
