import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface IGameElement
{
	public boolean WantKeyEvents();
	
	public boolean WantMouseEvents();
	
	public void KeyPressed(KeyEvent e);
	
	public void KeyReleased(KeyEvent e);
	
	public void mouseReleased(MouseEvent e);
	
	public void mousePressed(MouseEvent e);
	
	public void mouseExited(MouseEvent e);
	
	public void mouseEntered(MouseEvent e);
	
	public void mouseMoved(MouseEvent e);
	
	public void mouseDragged(MouseEvent e);
	
	public void Destroy();
	
	public void Step();
	
	public void CollideWith(GameElementAdapter element);
	
	public boolean Contains(GameElementAdapter element);
}
