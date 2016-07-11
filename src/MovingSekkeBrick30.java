public class MovingSekkeBrick30 extends SekkeBrick30
{
	private int	maxLimit	=50;
	private int	X0 , Y0;
	private int	direction;
	private int	state		=0;	// SAHMI mode
									
	public MovingSekkeBrick30(int x , int y)
	{
		super(x , y);
		destroyedScore=30;
		
		X0=getX();
		Y0=getY();
		direction=1;// go Right
	}
	
	private int F(int x)
	{
		return (int) (-.01*(x-X0)*(x-X0)+Y0);
	}
	
	protected void goNextPoint()
	{
		if (state==0)
		{
			// a SAHMI
			setXY(getX()+direction , F(getX()));
			if (getX()>X0+maxLimit)
				direction=-1; // go Left
			if (getX()<X0-maxLimit)
				direction=1; // go Right
		}
		else
			super.goNextPoint();
	}
	
	public void CollideWith(GameElementAdapter element)
	{
		if ((element instanceof Bullet)||(element instanceof GBall))
		{
			state=1; // Not SAHMI mode
			// super.CollideWith(element);
		}
		super.CollideWith(element);
	}
	
}
