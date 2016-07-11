public class SekkeBrick30 extends GameMovableElementAdapter
{
	public SekkeBrick30(int x , int y)
	{
		super("Images/SekkeBrick30.jpg" , x , y);
		destroyedScore=30;
		
		setSpeedY(2);
		setSpeedX(0);
		setDownDirection();
		StopMoving();
	}
	
	public void CollideWith(GameElementAdapter element)
	{
		if ((element instanceof Bullet)||(element instanceof GBall))
		{
			this.StartMoving();
			return;
		}
		if (element instanceof GBar)
		{
			GGame.IncreaseScore(destroyedScore);
			this.Destroy();
			return;
		}
		// to collide with other elements
		this.goBack();
		this.StopMoving();
		
	}
	
	protected void CollideDownBorder()
	{
		super.CollideDownBorder();
		this.Destroy();
	}
	
}
