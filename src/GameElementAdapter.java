import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class GameElementAdapter implements IGameElement
{
	private int		X;
	private int		Y;
	private int		Width;
	private int		Height;
	private Sprite	sprite;			// the Image of object
										
	// if this object destroy, how many scores do object increase
	protected int	destroyedScore	=0;
	
	// limitations of object in the Screen
	private int		minX;
	private int		maxX;
	private int		minY;
	private int		maxY;
	
	private int		ID;				// the index of element in Entity Array
										
	public GameElementAdapter(String ref , int x , int y)
	{
		// Load the Image from ImageFile (just for one time)
		this.sprite=SpriteStore.get().getSprite(ref);
		
		// Width and Height of any class get from it's Image
		setWidth(sprite.getWidth());
		setHeight(sprite.getHeight());
		
		// Default limitations of class in the screen
		minX=0;
		maxX=GameConstants.Game_Width-getWidth();
		minY=0;
		maxY=GameConstants.Game_Height-getHeight();
		
		setXY(x , y);
	}
	
	public void setID(int id)
	{
		if (id>=0)
			this.ID=id;
	}
	
	public void ChangeImage(String ref)
	{
		sprite=SpriteStore.get().getSprite(ref);
		setWidth(sprite.getWidth());
		setHeight(sprite.getHeight());
	}
	
	
	public void draw(Graphics g)
	{
		sprite.draw(g , X , Y);
	}
	
	protected void SetLimits(int minX , int maxX , int minY , int maxY)
	{
		this.minX=minX;
		this.maxX=maxX;
		this.minY=minY;
		this.maxY=maxY;
	}
	
	public int getmaxX()
	{
		return this.maxX;
	}
	
	public int getminX()
	{
		return this.minX;
	}
	
	public int getmaxY()
	{
		return this.maxY;
	}
	
	public int getminY()
	{
		return this.minY;
	}
	
	public int getID()
	{
		return ID;
	}
	
	private Rectangle getRectangle()
	{
		return new Rectangle(X , Y , Width , Height);
	}
	
	private void setHeight(int height)
	{
		if (height>0&&height<=GameConstants.Game_Height)
			Height=height;
	}
	
	public int getHeight()
	{
		return Height;
	}
	
	
	private void setWidth(int width)
	{
		if (width>0&&width<=GameConstants.Game_Width)
			Width=width;
	}
	
	public int getWidth()
	{
		return Width;
	}
	
	public void setXY(int x , int y)
	{
		if (y>=minY&&y<=maxY&&x>=minX&&x<=maxX)
		{
			X=x;
			Y=y;
		}
	}
	
	public int getX()
	{
		return X;
	}
	
	public int getY()
	{
		return Y;
	}
	
	@Override
	public boolean Contains(GameElementAdapter element)
	{
		return this.getRectangle().intersects(element.getRectangle());
	}
	
	@Override
	public void CollideWith(GameElementAdapter element)
	{
		// should override in child classes
	}
	
	@Override
	public void Step()
	{
		// do something in in child classed
	}
	
	@Override
	public boolean WantKeyEvents()
	{
		return false;
	}
	
	@Override
	public boolean WantMouseEvents()
	{
		return false;
	}
	
	@Override
	public void KeyPressed(KeyEvent e)
	{
		// should override in child classes
	}
	
	@Override
	public void KeyReleased(KeyEvent e)
	{
		// should override in child classes
	}
	
	@Override
	public void mouseReleased(MouseEvent e)
	{
		// should override in child classes
	}
	
	@Override
	public void mousePressed(MouseEvent e)
	{
		// should override in child classes
	}
	
	@Override
	public void mouseExited(MouseEvent e)
	{
		// should override in child classes
	}
	
	@Override
	public void mouseEntered(MouseEvent e)
	{
		// should override in child classes
	}
	
	@Override
	public void mouseMoved(MouseEvent e)
	{
		// should override in child classes
	}
	
	@Override
	public void mouseDragged(MouseEvent e)
	{
		// should override in child classes
	}
	
	@Override
	public void Destroy()
	{
		GGame.NotifyDeath(this);
	}
	
}
