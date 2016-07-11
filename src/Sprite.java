import java.awt.Graphics;
import java.awt.Image;

public class Sprite
{
	/** The image to be drawn for this sprite */
	private Image	image;
	
	// Create a new sprite based on an image
	public Sprite(Image image)
	{
		this.image=image;
	}
	
	public int getWidth()
	{
		return image.getWidth(null);
	}
	
	public int getHeight()
	{
		return image.getHeight(null);
	}
	
	public void draw(Graphics g , int x , int y)
	{
		g.drawImage(image , x , y , null);
	}
}