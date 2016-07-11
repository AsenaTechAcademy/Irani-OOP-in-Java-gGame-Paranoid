public class SekkeBrick10 extends GameElementAdapter
{
	
	public SekkeBrick10(int x , int y)
	{
		super("Images/SekkeBrick10.jpg" , x , y);
		destroyedScore=10;
	}
	
	public void CollideWith(GameElementAdapter element)
	{
		if ((element instanceof GBall)||(element instanceof Bullet))
		{
			GGame.IncreaseScore(destroyedScore);
			this.Destroy();
			return;
		}
	}
	
}
