package maze.model;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;

import maze.controller.BombFactory;
import maze.controller.FinishLine;
import maze.controller.Game;
import maze.controller.Level;
import maze.controller.Texture;
import maze.view.FinishLineGUI;
import maze.view.GUI;
import maze.view.GameOverGUI;

public class Player extends Rectangle{
	private static final long serialVersionUID = 1L;
	private static int NoOfCoins=0;
	public boolean right,left,up,down;
	private int speed = 4;
	private int time = 0, targetTime = 10;
	public int imageIndex = 0;
	private int lastDir = 1;
	private int n=1;
	
	private static Player player=null;	
	@SuppressWarnings("unused")
	private GUI gui=new GUI();
	BombFactory factory=new BombFactory();
	private static FinishLineGUI FL=new FinishLineGUI();
 	GameOverGUI GO=new GameOverGUI();

 	  File coins = new File("Coins.wav");
 	  File ammopick = new File("AmmoPick.wav");
 	  File health = new File("health.wav");
 	  File smallbomb = new File("SmallBomb.wav");
 	  File bigbomb = new File("bigbomb.wav");
 	  
	private Player(int x,int y) {
		setBounds(x,y,32,32);
	}
	public void tick(){
		if (right && canMove(x+getSpeed(),y)) {
			x+=getSpeed();
		    lastDir=1;
		}
		if (left && canMove(x-getSpeed(),y)) { 
			x-=getSpeed();
			lastDir=-1;
	}
		if (up && canMove(x,y-getSpeed())) {
			y-=getSpeed();
			lastDir=2;
		}
		if (down && canMove(x,y+getSpeed())) {
			y+=getSpeed();
			lastDir=3;
		}
		
		Level level = Game.level; 
		for(int i=0; i<level.gifts.size();i++) {
			if (this.intersects(level.gifts.get(i))) {
				level.gifts.remove(i);
				Game.PlaySound(coins);
				setNoOfCoins(getNoOfCoins() + 1);
				GUI.score+=30;
				GUI.scoreAmount.setText(String.valueOf(GUI.score));
				GUI.getLabel().setText("Coins:"+String.valueOf(getNoOfCoins()));
				break;
			}	
		}
		for(int i=0; i<level.ammo.size();i++) {
			if (this.intersects(level.ammo.get(i))) {
				level.ammo.remove(i);
				 Game.PlaySound(ammopick);
				GUI.ammo++;
				GUI.ammoAmount.setText(String.valueOf(GUI.ammo));
				break;
			}	
		}
		for(int i=0; i<level.health.size();i++) {
			if (this.intersects(level.health.get(i))) {
				level.health.remove(i);
				 Game.PlaySound(health);
				GUI.score+=250;
				GUI.scoreAmount.setText(String.valueOf(GUI.score));
				if (GUI.healthvar<100)
					GUI.healthvar+=25;
				GUI.healthAmount.setText(String.valueOf(GUI.healthvar));
				break;
			}	
		}
		for(int i=0; i<level.weakbombs.size();i++) {
			if (this.intersects(level.weakbombs.get(i))) {
				GUI.score-=50;
				if (GUI.score<0)
					GUI.score=0;
				GUI.scoreAmount.setText(String.valueOf(GUI.score));
				level.weakbombs.remove(i);
				Game.PlaySound(smallbomb);
				factory.reduceHealth(false);
				break;
			}	
		}
		for(int i=0; i<level.strongbombs.size();i++) {
			if (this.intersects(level.strongbombs.get(i))) {
				GUI.score-=100;
				if (GUI.score<0)
					GUI.score=0;
				GUI.scoreAmount.setText(String.valueOf(GUI.score));
				level.strongbombs.remove(i);
				Game.PlaySound(bigbomb);
				factory.reduceHealth(true);
				break;
			}	
		}
		if(level.gifts.size() ==0) { 
			Game.player = player.getPlayer();
			Game.level = new Level("/maps/map2.png");
			return;
		}
				
		for (int i=0;i<Game.level.enemies.size();i++) {
			Enemy en = Game.level.enemies.get(i);
			if (en.intersects(this)) {
				//GameOverGUI.Show();
				for (int scoreloop=0;scoreloop<n;scoreloop++)
				{
					GUI.score+=(GUI.score*0.2/(GUI.getK()*0.2));
					GUI.scoreAmount.setText(String.valueOf(GUI.score));
				}
				GO.Show();
				n=0;
				GUI.getT().stop();
				break;
		}}
			
		
		//for(int i=0;i<Game.level.finishLine.size();i++) {
			FinishLine fl = Game.level.finishLine.get(0);
			if(fl.intersects(this)) {
				GUI.score+=((GUI.score*0.2)/(GUI.getK()*0.2));
				GUI.scoreAmount.setText(String.valueOf(GUI.score));
				GUI.getT().stop();
				getFL().Show();}
			//break;	
		//}
	    time++;
	    if(time==targetTime) {
	    	time = 0;
	    	imageIndex++;
	    }
	    
		
	}
	
	private boolean canMove(int nextx, int nexty){             //for collision with walls
		Rectangle bounds = new Rectangle(nextx,nexty,width,height);
		Level level = Game.level;
		
		for(int xx=0; xx<level.tiles.length; xx++) {
			for(int yy=0; yy<level.tiles[0].length; yy++) {
				if(level.tiles[xx][yy] != null) {
					if(bounds.intersects(level.tiles[xx][yy])) {
						return false;
					}
				}
				for(int i=0; i<level.trees.size();i++) {
					if (level.trees.get(i)!=null) {
						if(bounds.intersects(level.trees.get(i)))
							return false;
					}
				}	
			}
		}
	return true;
	}
	
	public void render(Graphics g) {
		if(lastDir == 1) g.drawImage(Texture.player[imageIndex%2], x, y, width, height, null);
		else if(lastDir == -1) g.drawImage(Texture.player[imageIndex%2], x+32, y, -width, height, null);
		else if(lastDir == 	2) g.drawImage(Texture.player[(imageIndex%2)+2], x, y, width, height, null);
		else g.drawImage(Texture.player[(imageIndex%2)+2], x, y+32, width, -height, null);
	     
	}
	
	public static Player getPlayer()
	{
		if (player==null)
			player=new Player(640,480);
		return player;
	}

	/**
	 * @return the speed
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/**
	 * @return the noOfCoins
	 */
	public static int getNoOfCoins() {
		return NoOfCoins;
	}

	/**
	 * @param noOfCoins the noOfCoins to set
	 */
	public static void setNoOfCoins(int noOfCoins) {
		NoOfCoins = noOfCoins;
	}
	/**
	 * @return the fL
	 */
	public static FinishLineGUI getFL() {
		return FL;
	}
	/**
	 * @param fL the fL to set
	 */
	public void setFL(FinishLineGUI fL) {
		FL = fL;
	}
	
	
	
	

}
