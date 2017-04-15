package Maps;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

public class RectangleComp extends JComponent
{
  Rectangle rect;

  public RectangleComp(int h, int w,int x, int y)
  {
    rect  = new Rectangle(h, w, x, y);
  }

  public void paintComponent(Graphics g)
  {
      Graphics2D g2 = (Graphics2D) g;
      g2.draw(rect);
  } 
}
