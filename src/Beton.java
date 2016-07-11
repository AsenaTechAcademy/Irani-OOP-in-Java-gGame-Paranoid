public class Beton extends GameElementAdapter
{
	public static final int	Beton_20_20	=0;
	public static final int	Beton_20_40	=1;
	public static final int	Beton_20_60	=2;
	public static final int	Beton_40_20	=3;
	public static final int	Beton_60_20	=4;
	
	public Beton(int x , int y , int BetonType)
	{
		super((getBetonType(BetonType)) , x , y);
		this.destroyedScore=this.getWidth()*this.getHeight()/400;;
	}
	
	private static String getBetonType(int type)
	{
		if (type==Beton_20_20)
			return "Images/Beton-20-20.jpg";
		else if (type==Beton_20_40)
			return "Images/Beton-20-40.jpg";
		else if (type==Beton_20_60)
			return "Images/Beton-20-60.jpg";
		else if (type==Beton_40_20)
			return "Images/Beton-40-20.jpg";
		else if (type==Beton_60_20)
			return "Images/Beton-60-20.jpg";
		return "";
	}
	
	public void CollideWith(GameElementAdapter him)
	{
		if (him instanceof Bullet)
		{
			GGame.IncreaseScore(this.destroyedScore);
			this.Destroy();
			SoundStore.get().Play(Sounds.Beton);
			return;
		}
	}
	
}