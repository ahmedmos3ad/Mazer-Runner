package maze.controller;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class Tree extends Rectangle implements Walls{
private static final long serialVersionUID = 1L;

	public Tree(int x, int y) {
		setBounds(x,y,32,32);
	}
	
	@Override
	public void render (Graphics g) {
		ImageIcon icon=new ImageIcon("Trees.png");
	    Image img=icon.getImage();
		Color Transperant = new Color(0.0f, 0.0f, 0.0f, 0.0f);
    	g.setColor(Transperant);
    	g.fillRect(x, y, width, height);
    	ImageObserver observer=null;
		g.drawImage(img, x, y, observer);
	}
	
}
