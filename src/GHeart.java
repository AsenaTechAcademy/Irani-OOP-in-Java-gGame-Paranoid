public class GHeart extends GameMovableElementAdapter
{
	
	private static String	Images[]	= { "Images/Heart1.gif", "Images/Heart2.gif",
			"Images/Heart3.gif", "Images/Heart4.gif", "Images/Heart5.gif", "Images/Heart6.gif" };
	private int				cImage		=0;
	private long			waitTime	=50;
	private long			lastTime	=0;
	
	public GHeart(int x , int y)
	{
		super(Images[0] , x , y);
		destroyedScore=100;
		
		setSpeedX(0);
		setSpeedY(3);
		setDownDirection();
		StopMoving();
	}
	
	private void nextImage()
	{
		cImage=(cImage+1)%6;
		ChangeImage("Images/Heart"+(cImage+1)+".gif");
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
	
	protected void CollideDownBorder()
	{
		super.CollideDownBorder();
		this.Destroy();
	}
	
	public void CollideWith(GameElementAdapter element)
	{
		if ((element instanceof GBall)||(element instanceof Bullet))
		{
			this.StartMoving();
			return;
		}
		if (element instanceof GBar)
		{
			GGame.IncreaseLive();
			GGame.IncreaseScore(destroyedScore);
			this.Destroy();
			return;
		}
		
		// to collide with other elements
		this.goBack();
		this.StopMoving();
	}
}
