public class Bullet extends GameMovableElementAdapter
{
	
	public Bullet(int x , int y)
	{
		super("Images/Bullet01.gif" , x , y);
		
		this.setSpeedX(0);
		this.setSpeedY(3);
		this.setUpDirection();
		this.StartMoving();
	}
	
	protected void CollideUpBorder()
	{
		this.Destroy();
	}
	
	public void CollideWith(GameElementAdapter element)
	{
		this.Destroy();
	}
	
}
