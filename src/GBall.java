public class GBall extends GameMovableElementAdapter
{
	
	private int		movingDegree;	// Degree of Movement
	private int		dx;			// speed of Movement
	private int		x0 , y0;		// first x,y , set if direction change
	private double	tana;			// tan(Degree) , no need but its better
									
	public GBall(int x , int y)
	{
		super("Images/GRedBall.gif" , x , y);
		setmovingDegree(225);
		AfterCollision(); // set x0,y0,tana
	}
	
	private void AfterCollision()
	{
		x0=getX();
		y0=getY();
		tana=Math.tan(Math.toRadians(movingDegree));
	}
	
	public void Reset()
	{
		// set to first status
		this.setXY(365 , 540);
		setmovingDegree(225);
		AfterCollision();
	}
	
	public void Step()
	{
		// no need to super.Step(); because this Movement is very different
		calcNextPoint();
	}
	
	private void calcNextPoint()
	{
		int x2=getX()+dx;
		int y2=(int) (tana*x2+(y0-tana*x0));
		setXY(x2 , y2);
	}
	
	private void setmovingDegree(int degree)
	{
		SoundStore.get().Play(Sounds.Ball);
		
		movingDegree=(degree+(int) (6*Math.random()-3))%360;
		// Optimize dx
		if (movingDegree<0)
			movingDegree=355;
		
		if (movingDegree>=178&&movingDegree<=180)
			movingDegree=175;
		else if (movingDegree>=180&&movingDegree<=183)
			movingDegree=185;
		else if (movingDegree<=3&&movingDegree>=0)
			movingDegree=5;
		else if (movingDegree>=357&&movingDegree<=0)
			movingDegree=355;
		else if (movingDegree>=75&&movingDegree<=90)
			movingDegree=75;
		else if (movingDegree>=90&&movingDegree<=105)
			movingDegree=105;
		else if (movingDegree>=255&&movingDegree<=270)
			movingDegree=255;
		else if (movingDegree>=270&&movingDegree<=285)
			movingDegree=285;
		
		// calc dx based on Degree
		if (movingDegree>=0&&movingDegree<=90)
			dx=(int) (-0.06818182*movingDegree+8.06);
		else if (movingDegree>=90&&movingDegree<=180)
			dx=(int) (0.07142857*movingDegree-4.78571429);
		else if (movingDegree>=180&&movingDegree<=270)
			dx=(int) (-0.06818182*movingDegree+20.34090909);
		else if (movingDegree>=270&&movingDegree<=360)
			dx=(int) (0.06818182*movingDegree-16.47727273);
		
		// calc Direction
		if ((movingDegree>0&&movingDegree<90)||(movingDegree>270&&movingDegree<360))
			dx=Math.abs(dx);
		if ((movingDegree>90&&movingDegree<270))
			dx=-1*Math.abs(dx);
	}
	
	protected void CollideLeft(GameElementAdapter him)
	{
		// Region 7
		if (movingDegree>90&&movingDegree<180)
			setmovingDegree(180-movingDegree);
		else if (movingDegree>180&&movingDegree<270)
			setmovingDegree(360+180-movingDegree);
		setXY((him.getX()+him.getWidth()+1) , getY());
		AfterCollision();
	}
	
	protected void CollideLeftBorder()
	{
		if (movingDegree>90&&movingDegree<180)
			setmovingDegree(180-movingDegree);
		else if (movingDegree>180&&movingDegree<270)
			setmovingDegree(360+180-movingDegree);
		AfterCollision();
	}
	
	protected void CollideRight(GameElementAdapter him)
	{
		// Region 5
		if (movingDegree<90)
			setmovingDegree(180-movingDegree);
		else if (movingDegree>180)
			setmovingDegree(360+180-movingDegree);
		this.setXY(him.getX()-this.getWidth()-1 , this.getY());
		AfterCollision();
	}
	
	protected void CollideRightBorder()
	{
		if (movingDegree<90)
			setmovingDegree(180-movingDegree);
		else if (movingDegree>180)
			setmovingDegree(360+180-movingDegree);
		AfterCollision();
	}
	
	protected void CollideUp(GameElementAdapter him)
	{
		// Region 8
		setmovingDegree(360-movingDegree);
		setXY(getX() , him.getY()+him.getHeight()+1);
		AfterCollision();
	}
	
	protected void CollideUpBorder()
	{
		setmovingDegree(360-movingDegree);
		AfterCollision();
	}
	
	protected void CollideDown(GameElementAdapter him)
	{
		// Region 6
		setmovingDegree(360-movingDegree);
		this.setXY(getX() , him.getY()-this.getHeight()-1);
		AfterCollision();
	}
	
	protected void CollideDownBorder()
	{
		GGame.DecreaseLive();
	}
	
	protected void CollideCorner1(GameElementAdapter him)
	{
		// Corner 1
		setmovingDegree(225+(int) (20*Math.random()-10));
		this.setXY(him.getX()-this.getWidth()-1 , him.getY()-this.getHeight()-1);
		AfterCollision();
	}
	
	protected void CollideCorner2(GameElementAdapter him)
	{
		// Corner 2
		setmovingDegree(315+(int) (20*Math.random()-10));
		this.setXY(him.getX()+him.getWidth()+1 , him.getY()-this.getHeight()-1);
		AfterCollision();
	}
	
	protected void CollideCorner3(GameElementAdapter him)
	{
		// Corner 3
		setmovingDegree(45+(int) (20*Math.random()-10));
		this.setXY(him.getX()+him.getWidth()+1 , him.getY()+him.getHeight()+1);
		AfterCollision();
	}
	
	protected void CollideCorner4(GameElementAdapter him)
	{
		// Corner 4
		setmovingDegree(135+(int) (20*Math.random()-10));
		this.setXY(him.getX()-this.getWidth()-1 , him.getY()+him.getHeight()+1);
		AfterCollision();
	}
	
	private boolean CollideWithGBar(GBar him)
	{
		// New Region 1
		if ((this.getX()>=him.getX()-this.getWidth()/2)
				&&((this.getX()+this.getWidth()/2)<=(him.getX()+him.getWidth()/2)))
		{
			int dis=him.getX()+him.getWidth()/2-(this.getX()+this.getWidth()/2);
			setmovingDegree(263-dis);
			this.setXY(getX() , him.getY()-this.getHeight()-1);
			AfterCollision();
			return true;
		}
		
		// New Region 2
		if (((this.getX()-this.getWidth()/2)>=(him.getX()+him.getWidth()/2))
				&&((this.getX()+this.getWidth())<=(him.getX()+him.getWidth()+this.getWidth()/2)))
		{
			int dis=(this.getX()+this.getWidth()/2)-(him.getX()+him.getWidth()/2);
			setmovingDegree(280+dis);
			this.setXY(getX() , him.getY()-this.getHeight()-1);
			AfterCollision();
			return true;
		}
		
		
		return false;
	}
	
	public void CollideWith(GameElementAdapter him)
	{
		
		if (him instanceof Bullet)
			return;
		
		// Collision with GBar
		if ((him instanceof GBar)&&(him.getY()>this.getY())
				&&(this.getX()>=him.getX()-this.getWidth()/2)
				&&((this.getX()+this.getWidth())<=(him.getX()+him.getWidth()+this.getWidth()/2)))
		{
			if (CollideWithGBar((GBar) him))
				return;
		}
		
		if ((this.getX()<him.getX())&&(this.getY()<him.getY()))
		{
			// Region 1
			int ddx=this.getX()+this.getWidth()-him.getX();
			int ddy=this.getY()+this.getHeight()-him.getY();
			if (ddx>ddy)
				CollideDown(him);
			if (ddx<ddy)
				CollideRight(him);
			if (ddx==ddy)
				CollideCorner1(him);
			return;
		}
		
		if (((this.getX()+this.getWidth())>(him.getX()+him.getWidth()))&&(this.getY()<him.getY()))
		{
			// Region 2
			int ddx=him.getX()+him.getWidth()-this.getX();
			int ddy=this.getY()+this.getHeight()-him.getY();
			if (ddx>ddy)
				CollideDown(him);
			if (ddx<ddy)
				CollideLeft(him);
			if (ddx==ddy)
				CollideCorner2(him);
			return;
		}
		
		if (((this.getX()+this.getWidth())>(him.getX()+him.getWidth()))
				&&((this.getY()+this.getHeight())>(him.getY()+him.getHeight())))
		{
			// Region 3
			int ddx=him.getX()+him.getWidth()-this.getX();
			int ddy=him.getY()+him.getHeight()-this.getY();
			if (ddx>ddy)
				CollideUp(him);
			if (ddx<ddy)
				CollideLeft(him);
			if (ddx==ddy)
				CollideCorner3(him);
			return;
		}
		
		if ((this.getX()<him.getX())&&((this.getY()+this.getHeight())>(him.getY()+him.getHeight())))
		{
			// Region 4
			int ddx=this.getX()+this.getWidth()-him.getX();
			int ddy=him.getY()+him.getHeight()-this.getY();
			if (ddx>ddy)
				CollideUp(him);
			if (ddx<ddy)
				CollideRight(him);
			if (ddx==ddy)
				CollideCorner4(him);
			return;
		}
		
		if (this.getX()<him.getX())
		{
			// Region 5
			CollideRight(him);
			return;
		}
		if ((this.getY())<him.getY())
		{
			// Region 6
			CollideDown(him);
			return;
		}
		if ((this.getX()+this.getWidth())>(him.getX()+him.getWidth()))
		{
			// Region 7
			CollideLeft(him);
			return;
		}
		if ((this.getY()+this.getHeight())>(him.getY()+him.getHeight()))
		{
			// Region 8
			CollideUp(him);
			return;
		}
	}// End of Collide With
}
