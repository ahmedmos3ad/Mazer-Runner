package maze.view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import maze.controller.Game;

public class GameOverGUI {
	public JFrame frame=new JFrame();
		public void Show() {
			GUI.frame.setVisible(false);
			JLabel ScoreLabel=new JLabel("300");
			Box theBox = Box.createHorizontalBox();
			BufferedImage myPicture = null;
			try {
				myPicture = ImageIO.read(new File("GameOver.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			theBox.add(picLabel);
			theBox.add(ScoreLabel);
			frame.setTitle(Game.TITLE);
			frame.getContentPane().add(picLabel);
			frame.setResizable(false);
			frame.pack();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		}
}
