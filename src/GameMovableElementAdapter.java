public abstract class GameMovableElementAdapter extends GameElementAdapter
{
	private int		pX , pY;	// Previous X,Y
	private int		speedX;	// Speed in X direction (Right or Left)
	private int		speedY;	// Speed in Y direction (Up or Down)
	private boolean	ismoving;	// Object is moving or static
								
								
	public GameMovableElementAdapter(String ref , int x , int y)
	{
		super(ref , x , y);
		this.speedX=0;
		this.speedY=0;
		this.ismoving=false;
		this.pX=getX();
		this.pY=getY();
		
		SetLimits(0 , GameConstants.Game_Width-this.getWidth() , 0 ,
				GameConstants.Game_Height-this.getHeight());
	}
	
	public void Step()
	{
		super.Step(); // now it is empty
		goNextPoint();
	}
	
	
	protected void goNextPoint()
	{
		// if you have EQUATION of MOVEMENT
		// have to override this method and put equation of movement here
		if (ismoving)
		{
			pX=getX();
			pY=getY();
			setXY(getX()+speedX , getY()+speedY);
		}
	}
	
	protected void goBack()
	{
		setXY(pX , pY);
	}
	
	public void setXY(int x , int y)
	{
		if (x<this.getminX())
			CollideLeftBorder();
		else if (x>this.getmaxX())
			CollideRightBorder();
		else if (y<this.getminY())
			CollideUpBorder();
		else if (y>this.getmaxY())
			CollideDownBorder();
		else
			super.setXY(x , y);
	}
	
	protected void CollideLeftBorder()
	{
		// you can do something in child classes
	}
	
	protected void CollideRightBorder()
	{
		// you can do something in child classes
	}
	
	protected void CollideUpBorder()
	{
		// you can do something in child classes
	}
	
	protected void CollideDownBorder()
	{
		// you can do something in child classes
	}
	
	protected void setRightDirection()
	{
		speedX=Math.abs(speedX);
	}
	
	protected void setLeftDirection()
	{
		speedX=-1*Math.abs(speedX);
	}
	
	protected void setUpDirection()
	{
		speedY=-1*Math.abs(speedY);
	}
	
	protected void setDownDirection()
	{
		speedY=Math.abs(speedY);
	}
	
	protected boolean isMoving()
	{
		return ismoving;
	}
	
	protected void StopMoving()
	{
		ismoving=false;
	}
	
	protected void StartMoving()
	{
		ismoving=true;
	}
	
	protected void setSpeedX(int newSpeedX)
	{
		speedX=Math.abs(newSpeedX);
	}
	
	protected void setSpeedY(int newSpeedY)
	{
		speedY=Math.abs(newSpeedY);
	}
	
	protected int getSpeedX()
	{
		return speedX;
	}
	
	protected int getSpeedY()
	{
		return speedY;
	}
	
}
