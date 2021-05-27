package maze.controller;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import maze.model.Player;
import maze.view.FinishLineGUI;
import maze.view.GUI;
import maze.view.GameOverGUI;

public class Game extends Canvas implements Runnable,KeyListener{
	private static final long serialVersionUID = 1L;

	private boolean isRunning =false;
	public static final int WIDTH = 1280,HEIGHT = 800;
	public static final String TITLE = "Maze-Runner";
	private Thread thread;
	public static Player player;
	public static Level level;
	private GUI gui=new GUI();
	private FinishLineGUI FL=new FinishLineGUI();
	private GameOverGUI GO=new GameOverGUI();
	private PanelGrabber panelGrabber=new PanelGrabber();
	private PanelObserver panelObserver=new PanelObserver(panelGrabber);
	File Remove = new File("Remove.wav");
	
	public Game(){
			Dimension dimension = new Dimension(Game.WIDTH,Game.HEIGHT);
			setPreferredSize(dimension);
			setMinimumSize(dimension);
			setMaximumSize(dimension);
			addKeyListener(this);
			new Texture();
			player = Player.getPlayer();
			level = new Level("/maps/map2.png");
	}
	public synchronized void start() {
		if(isRunning) return;
		isRunning = true;
		thread = new Thread(this); 
		thread.start();
	}
	
    public synchronized void stop() {
    	if(!isRunning) return;
		isRunning = false;
		try {
		thread.join();
		} catch (InterruptedException e) {
		e.printStackTrace();
        }
    }
    
	private void tick() {
		player.tick();
		level.tick();
		
	}
	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs==null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		player.render(g);
		level.render(g);
		g.dispose();
		bs.show();
	}
	
	@Override
	public void run() {
		requestFocus();
		@SuppressWarnings("unused")
		int fps = 0;
		double timer = System.currentTimeMillis();
		long lastTime = System.nanoTime();
		double targetTick = 60.0;
		double delta = 0;
		double ns = 1000000000/targetTick;
		
		while (isRunning){
			long now = System.nanoTime();
			delta+=(now-lastTime) / ns;
			lastTime = now;
			
			while(delta >= 1) {
				tick();
				render();           //render/fps inside is better than outside, but lower in turns.
				fps++;
				delta--;
			}
			if(System.currentTimeMillis() - timer >= 1000) {
				//System.out.println(fps);
				fps=0;
				timer+=1000;
			}
		}
		stop();
	}
	
	int xx=0,yy=0;
	Player p;
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) player.right=true;
		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) player.left=true;
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) player.up=true;
		if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) player.down=true;
		if(e.getKeyCode()== KeyEvent.VK_SPACE) {
			if (GUI.ammo>0) {
				GUI.ammo--;
				PlaySound(Remove);
				GUI.ammoAmount.setText(String.valueOf(GUI.ammo));
				player.grow(20,20);
				for(int i=0; i<level.weakbombs.size();i++) {
					if (player.intersects(level.weakbombs.get(i))) {
						level.weakbombs.remove(i);
						break;
					}	
				}
				for(int i=0; i<level.strongbombs.size();i++) {
					if (player.intersects(level.strongbombs.get(i))) {
						level.strongbombs.remove(i);
						break;
					}	
				}
				for(int i=0; i<level.trees.size();i++) {
					if (player.intersects(level.trees.get(i))) {
						level.trees.remove(i);
						break;
					}	
				}
				player.grow(-20,-20);	
		}}
		if(e.getKeyCode()==KeyEvent.VK_ENTER) 
		{
			Player.getFL().getFrame().setVisible(false);
			Game.player = player.getPlayer();
			Game.level = new Level("/maps/map2.png");
			GUI.getT().start();
			panelGrabber.setNoOfCoins(0);
			Player.setNoOfCoins(panelGrabber.getNoOfCoins());
			GUI.getLabel().setText("Coins:"+String.valueOf(0));
			GUI.setK(0);
			GUI.timeElapsed.setText("0");
			panelGrabber.sethealthvar(100);
			GUI.healthvar=panelGrabber.getHealthvar();
			GUI.healthAmount.setText(String.valueOf(panelGrabber.getHealthvar()));
			GUI.scoreAmount.setText("0");
			GUI.ammoAmount.setText("8");
			GUI.ammo=8;
			GUI.score=0;
		}}
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) player.right=false;
		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) player.left=false;
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) player.up=false;
		if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) player.down=false;
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}
	
	/*private void destroy(int nextx, int nexty){             
		Rectangle bounds = new Rectangle(nextx,nexty,p.width,p.height);
		Level level = Game.level;
		for(int xx=0; xx<level.trees.length; xx++) {
			for(int yy=0; yy<level.trees[0].length; yy++) {
				if (level.trees[xx][yy]!=null) 
					if(bounds.intersects(level.trees[xx][yy]))
						(level.trees[xx][yy])=null;
			}
		}
		
	}*/
	/**
	 * @return the gui
	 */
	public GUI getGui() {
		return gui;
	}
	/**
	 * @param gui the gui to set
	 */
	public void setGui(GUI gui) {
		this.gui = gui;
	}
	
	public static void PlaySound(File Sound) {
	    try {
	      Clip clip = AudioSystem.getClip();
	      clip.open(AudioSystem.getAudioInputStream(Sound));
	      clip.start();
	      
	      Thread.sleep(clip.getMicrosecondLength() / 15000L);
	    }
	    catch (Exception localException) {}
	  }
}
