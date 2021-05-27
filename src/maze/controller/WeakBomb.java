package maze.controller;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;

public class WeakBomb extends Rectangle implements Bombs{
private static final long serialVersionUID = 1L;

	public WeakBomb(int x, int y) {
		setBounds(x,y,32,32);
	}

	public void render (Graphics g) {
		/*g.setColor(new Color(33,0,127));*/
		Color Transperant = new Color(0.0f, 0.0f, 0.0f, 0.0f);
    	g.setColor(Transperant);
		g.fillRect(x, y, width, height);
		ImageIcon icon=new ImageIcon("SmallBomb.png");
	    Image img=icon.getImage();
	    ImageObserver observer=null;
		g.drawImage(img, x, y, observer);
	}

}
