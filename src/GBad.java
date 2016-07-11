public class GBad extends GameMovableElementAdapter
{
	
	private static String	Images[]	= { "Images/Bad1.gif", "Images/Bad2.gif",
			"Images/Bad3.gif", "Images/Bad4.gif", "Images/Bad5.gif" };
	private int				cImage		=0;
	private long			waitTime	=70;
	private long			lastTime	=0;
	
	public GBad(int x , int y)
	{
		super(Images[0] , x , y);
		destroyedScore=100;
		
		setSpeedY(2);
		setSpeedX(0);
		setDownDirection();
		StopMoving();
	}
	
	private void nextImage()
	{
		cImage=(cImage+1)%5;
		ChangeImage("Images/Bad"+(cImage+1)+".gif");
	}
	
	public void Step()
	{
		super.Step();
		if (lastTime+waitTime<System.currentTimeMillis())
		{
			this.nextImage();
			lastTime=System.currentTimeMillis();
		}
	}
	
	public void CollideWith(GameElementAdapter element)
	{
		if ((element instanceof GBall)||(element instanceof Bullet))
		{
			GGame.IncreaseScore(destroyedScore);
			StartMoving();
			SoundStore.get().Play(Sounds.Dog);
			return;
		}
		if (element instanceof GBar)
		{
			GGame.DecreaseLive();
			this.Destroy();
			return;
		}
		// to collide with other elements
		element.Destroy();
	}
	
	protected void CollideDownBorder()
	{
		super.CollideDownBorder();
		this.Destroy();
	}
}
