import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class SpriteStore
{
	/** The single instance of this class */
	private static SpriteStore	single	=new SpriteStore();
	
	public static SpriteStore get()
	{
		return single;
	}
	
	private HashMap	sprites	=new HashMap();
	
	public Sprite getSprite(String ref)
	{
		// if is exist in HashMap
		if (sprites.get(ref)!=null)
			return (Sprite) sprites.get(ref);
		
		// if not exist
		BufferedImage sourceImage=null;
		try
		{
			URL url=this.getClass().getClassLoader().getResource(ref);
			sourceImage=ImageIO.read(url);
		}
		catch (IOException e)
		{
			// Show Error
		}
		
		// create an accelerated image of the right size to store our sprite in
		GraphicsConfiguration gc=GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice().getDefaultConfiguration();
		Image image=gc.createCompatibleImage(sourceImage.getWidth() , sourceImage.getHeight() ,
				Transparency.BITMASK);
		// draw our source image into the accelerated image
		image.getGraphics().drawImage(sourceImage , 0 , 0 , null);
		// create a sprite, add it the cache then return it
		Sprite sprite=new Sprite(image);
		sprites.put(ref , sprite);
		
		return sprite;
	}
	
}