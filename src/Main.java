public class Main
{
	public static void main(String[] args)
	{
		int curLevel=1;
		LevelStatus gamestatus=LevelStatus.waitNextLevel;
		
		SoundStore.get().LoadSounds();
		
		GGame game=new GGame();
		while (gamestatus==LevelStatus.waitNextLevel&&curLevel<=Levels.FinalLevel)
		{
			Levels.SetLevel(game , curLevel++);
			
			game.Game_Start();
			gamestatus=game.Game_Loop();
			
			if (gamestatus==LevelStatus.waitGameOver)
				System.exit(0);
		}
		
		if (gamestatus==LevelStatus.waitNextLevel&&curLevel==Levels.FinalLevel+1)
			System.out.print("You Complete this GAME...");
		
		System.exit(0);
		
	}// end of main
}
