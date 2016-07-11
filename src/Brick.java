public class Brick extends GameElementAdapter
{
	public static final int	Brick_20_20	=0;
	public static final int	Brick_20_40	=1;
	public static final int	Brick_20_60	=2;
	public static final int	Brick_40_20	=3;
	public static final int	Brick_60_20	=4;
	
	public Brick(int x , int y , int BrickType)
	{
		super((getBrickType(BrickType)) , x , y);
		this.destroyedScore=this.getWidth()*this.getHeight()/400;
	}
	
	private static String getBrickType(int type)
	{
		if (type==Brick_20_20)
			return "Images/Brick-20-20.jpg";
		else if (type==Brick_20_40)
			return "Images/Brick-20-40.jpg";
		else if (type==Brick_20_60)
			return "Images/Brick-20-60.jpg";
		else if (type==Brick_40_20)
			return "Images/Brick-40-20.jpg";
		else if (type==Brick_60_20)
			return "Images/Brick-60-20.jpg";
		return "";
	}
	
	public void CollideWith(GameElementAdapter him)
	{
		if ((him instanceof GBall)||(him instanceof Bullet))
		{
			this.Destroy();
			GGame.IncreaseScore(this.destroyedScore);
			SoundStore.get().Play(Sounds.Brick);
			return;
		}
	}
	
}
