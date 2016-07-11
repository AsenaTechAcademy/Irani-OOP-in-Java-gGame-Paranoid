public class LifeBrick extends MultiLifeBrick
{
	public LifeBrick(int x , int y)
	{
		super("Images/LifeBrick5.jpg" , x , y , 5);
		destroyedScore=100;
		
		setNextImage("Images/LifeBrick1.jpg");
		setNextImage("Images/LifeBrick2.jpg");
		setNextImage("Images/LifeBrick3.jpg");
		setNextImage("Images/LifeBrick4.jpg");
		setNextImage("Images/LifeBrick5.jpg");
	}
	
	public void CollideWith(GameElementAdapter element)
	{
		if (element instanceof GBar)
		{
			GGame.IncreaseLive();
			GGame.IncreaseScore(destroyedScore);
			this.Destroy();
			return;
		}
		
		super.CollideWith(element);
	}
	
}
