public class PlayerInfo
{
	private String	PlayerName;
	private int		currentLevel;
	private int		Lives;
	private int		Score;
	private int		Fires;
	private long	TotalTime	=0;
	private long	StartTime	=0;
	private long	CurrentTime	=0;
	
	public PlayerInfo()
	{
		TotalTime=0;
		StartTime=0;
		CurrentTime=0;
	}
	
	public PlayerInfo(PlayerInfo p)
	{
		this.PlayerName=p.PlayerName;
		this.currentLevel=p.currentLevel;
		this.TotalTime=p.TotalTime;
		this.Score=p.Score;
		this.Fires=p.Fires;
		this.Lives=p.Lives;
	}
	
	
	public int getCurrentLevel()
	{
		return currentLevel;
	}
	
	public void setCurrentLevel(int currentLevel)
	{
		this.currentLevel=currentLevel;
	}
	
	public String getPlayerName()
	{
		return PlayerName;
	}
	
	public void setPlayerName(String playerName)
	{
		PlayerName=playerName;
	}
	
	public long getTotalTime()
	{
		return TotalTime;
	}
	
	public void setTotalTime(long totalTime)
	{
		TotalTime=totalTime;
	}
	
	public long getStartTime()
	{
		return StartTime;
	}
	
	public void setStartTime(long startTime)
	{
		StartTime=startTime;
	}
	
	public long getCurrentTime()
	{
		return CurrentTime;
	}
	
	public void setCurrentTime(long currentTime)
	{
		CurrentTime=currentTime;
	}
	
	
	
	public int getLives()
	{
		return Lives;
	}
	
	public void setLives(int lives)
	{
		Lives=lives;
	}
	
	public int getScore()
	{
		return Score;
	}
	
	public void setScore(int score)
	{
		Score=score;
	}
	
	public int getFires()
	{
		return Fires;
	}
	
	public void setFires(int fires)
	{
		Fires=fires;
	}
	
}
