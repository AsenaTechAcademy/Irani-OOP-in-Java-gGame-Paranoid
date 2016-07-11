import java.applet.Applet;
import java.applet.AudioClip;
import java.util.HashMap;

public class SoundStore
{
	private static SoundStore	single			=new SoundStore();
	private static String		SoundFiles[]	= { Sounds.Ball, Sounds.Beton, Sounds.Brick,
			Sounds.Fire, Sounds.Score, Sounds.Dog };
	
	public static SoundStore get()
	{
		return single;
	}
	
	private HashMap	sounds;
	
	public void LoadSounds()
	{
		sounds=new HashMap();
		for (int i=0 ; i<SoundFiles.length ; i++)
		{
			AudioClip clip=Applet.newAudioClip(this.getClass().getResource(SoundFiles[i]));
			sounds.put(SoundFiles[i] , clip);
		}
	}
	
	public void Play(String ref)
	{
		if (sounds.get(ref)!=null)
			((AudioClip) sounds.get(ref)).play();
	}
	
}// End of Class