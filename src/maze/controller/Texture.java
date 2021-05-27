package maze.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Texture {
	public static BufferedImage[] player;
	public BufferedImage spritesheet;
	
	public Texture() {
		try {
			spritesheet = ImageIO.read(getClass().getResource("/sprites/spritesheet.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		player = new BufferedImage[5];
		player[0] = getSprite(0,0);
		player[1] = getSprite(16,0);
		player[2] = getSprite(0,0);
		player[3] = getSprite(32,0);
		
	}
	
	public BufferedImage getSprite(int xx, int yy) {
		return spritesheet.getSubimage(xx, yy, 16, 16);
	}

}
