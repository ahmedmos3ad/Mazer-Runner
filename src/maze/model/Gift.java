package maze.model;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class Gift extends Rectangle{
	private static final long serialVersionUID = 1L;
	ImageIcon icon=new ImageIcon("Coin_24px.png");
	private Image img=icon.getImage();


	public Gift(int x, int y) {
		this.setBounds(x+10,y+8,8,8);
	}

	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(x, y, width, height);
		ImageObserver observer = null;
		g.drawImage(img, x-6, y-6, observer);
	}
}
