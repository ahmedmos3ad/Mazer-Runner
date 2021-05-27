package maze.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;


import maze.controller.Game;


public class GUI {
	 
	private static Timer t;
	@SuppressWarnings("unused")
	private Game game;
	private static JLabel label;
	private static int k=0;
	public static JFrame frame = new JFrame();
	public static JLabel timeElapsed=new JLabel("");
	public static JLabel healthAmount=new JLabel("100");
	public static JLabel ammoAmount=new JLabel("8");
	public static int healthvar=100;
	public static int score=0;
	public static int ammo=8;
	public static JLabel scoreAmount=new JLabel("0");
	public void FireUp() {
		Game game = new Game();
		Box theBox = Box.createHorizontalBox();
		setLabel(new JLabel("Coins:"));
		JLabel Space=new JLabel("             ");
		JLabel Space2=new JLabel("             ");
		JLabel Space3=new JLabel("             ");
		JLabel Space4=new JLabel("             ");
		JLabel time=new JLabel("Time:");
		JLabel health=new JLabel("Health:");
		JLabel score=new JLabel("Score:");
		JLabel Ammo=new JLabel("Ammo:");
		setT(new Timer(1000,new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				getTimeElapsed().setText(String.valueOf(getK()));
				setK(getK() + 1);
			}
			
		}));
		getT().start();
		getLabel().setFont(getLabel().getFont().deriveFont(30f));
		time.setFont(time.getFont().deriveFont(30f));
		healthAmount.setFont(healthAmount.getFont().deriveFont(30f));
		health.setFont(health.getFont().deriveFont(30f));
		scoreAmount.setFont(healthAmount.getFont().deriveFont(30f));
		score.setFont(health.getFont().deriveFont(30f));
		ammoAmount.setFont(healthAmount.getFont().deriveFont(30f));
		Ammo.setFont(health.getFont().deriveFont(30f));
		getTimeElapsed().setFont(getTimeElapsed().getFont().deriveFont(30f));
		/*JTextArea score=new JTextArea("Hi");
		/*score.setEditable(false);*/
		/*score.setFont(score.getFont().deriveFont(30f));*/
		theBox.add(getLabel());
		/*theBox.add(score);*/
		theBox.add(Space);
		theBox.add(time);
		theBox.add(getTimeElapsed());
		theBox.add(Space2);
		theBox.add(health);
		theBox.add(healthAmount);
		theBox.add(Space4);
		theBox.add(Ammo);
		theBox.add(ammoAmount);
		theBox.add(Space3);
		theBox.add(score);
		theBox.add(scoreAmount);
		getFrame().setTitle(Game.TITLE);
		getFrame().getContentPane().add(game);
		getFrame().getContentPane().add(theBox,BorderLayout.SOUTH);
		getFrame().setResizable(false);
		getFrame().pack();
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().setLocationRelativeTo(null);
		getFrame().setVisible(true);
		game.start();
	}
	public void setGame(Game game) {
		this.game = game;
	}
	/**
	 * @return the label
	 */
	public static JLabel getLabel() {
		return label;
	}
	/**
	 * @param label the label to set
	 */
	public static void setLabel(JLabel label) {
		GUI.label = label;
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
	@SuppressWarnings("static-access")
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	/**
	 * @return the k
	 */
	public static int getK() {
		return k;
	}
	/**
	 * @param k the k to set
	 */
	public static void setK(int k) {
		GUI.k = k;
	}
	/**
	 * @return the timeElapsed
	 */
	public JLabel getTimeElapsed() {
		return timeElapsed;
	}
	/**
	 * @param timeElapsed the timeElapsed to set
	 */
	@SuppressWarnings("static-access")
	public void setTimeElapsed(JLabel timeElapsed) {
		this.timeElapsed = timeElapsed;
	}
	/**
	 * @return the t
	 */
	public static Timer getT() {
		return t;
	}
	/**
	 * @param t the t to set
	 */
	@SuppressWarnings("static-access")
	public void setT(Timer t) {
		this.t = t;
	}
}
