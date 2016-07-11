public abstract class MultiLifeBrick extends GameMovableElementAdapter
{
	private int		Lifes;
	private String	Images[];
	private int		cImages;
	
	
	public MultiLifeBrick(String FirstImage , int x , int y , int lifes)
	{
		super(FirstImage , x , y);
		
		setSpeedX(0);
		setSpeedY(3);
		setDownDirection();
		StopMoving();
		
		Lifes=lifes;
		Images=new String[10];
		cImages=0;
	}
	
	protected void setNextImage(String strImage)
	{
		Images[cImages++]=strImage;
	}
	
	protected void LifeIncrease()
	{
		if (Lifes>=2)
		{
			Lifes--;
			this.ChangeImage(Images[Lifes-1]);
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
			if (Lifes==1)
			{
				this.StartMoving();
				return;
			}
			this.LifeIncrease();
			return;
		}
		// to collide with other elements
		this.goBack();
		this.StopMoving();
	}
	
}
