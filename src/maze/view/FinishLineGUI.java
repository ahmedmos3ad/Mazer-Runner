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

public class FinishLineGUI {
	private JFrame frame=new JFrame();
		
		public  void Show() {
			//GUI.frame.setVisible(false);
			JLabel ScoreLabel=new JLabel("500");
			Box theBox = Box.createHorizontalBox();
			BufferedImage myPicture = null;
			try {
				myPicture = ImageIO.read(new File("YouWin.jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			theBox.add(picLabel);
			theBox.add(ScoreLabel);
			getFrame().setTitle(Game.TITLE);
			getFrame().getContentPane().add(picLabel);
			getFrame().setResizable(false);
			getFrame().pack();
			getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			getFrame().setLocationRelativeTo(null);
			getFrame().setVisible(true);
		}

		/**
		 * @return the frame
		 */
		public JFrame getFrame() {
			return frame;
		}

		/**
		 * @param frame the frame to set
		 */
		public void setFrame(JFrame frame) {
			this.frame = frame;
		}
}
