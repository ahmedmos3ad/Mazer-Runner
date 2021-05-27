package maze.controller;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


import maze.model.Enemy;
import maze.model.Gift;

public class Level {
	public int width;
	public int height;
	
	public Tile[][] tiles;
	public List<Tree> trees;
	public List<StrongBomb> strongbombs;
	public List<WeakBomb> weakbombs;
	public List<Gift> gifts;
	public List<Enemy> enemies;
	public List<FinishLine> finishLine;
	public List<Health> health;
	public List<Ammo> ammo;
	public Level(String path) {
		gifts = new ArrayList<>();
		enemies = new ArrayList<>();
		finishLine = new ArrayList<>();
		weakbombs = new ArrayList<>();
		strongbombs = new ArrayList<>();
		ammo = new ArrayList<>();
		health = new ArrayList<>();
		trees = new ArrayList<>();
		finishLine = new ArrayList<>();
		try {
			BufferedImage map =  ImageIO.read(getClass().getResource(path));
			this.width = map.getWidth();
			this.height = map.getHeight();
			int[] pixels = new int [width*height];
			tiles = new Tile[width][height];
			map.getRGB(0, 0, width, height, pixels, 0, width);
	
			for (int xx=0; xx < width; xx++) {
				for(int yy=0; yy < height; yy++) {
					int val = pixels[xx + (yy*width)];
					
					if(val == 0xFF000000) {
						//Tile
						tiles[xx][yy] = new Tile(xx*32, yy*32);
					}else if(val == 0xFFA0A0A0) {
						trees.add(new Tree(xx*32, yy*32));
					}
					else if(val == 0xFF0000FF) {
						//Player
						Game.player.x = xx*32;
						Game.player.y = yy*32;
				}else if(val == 0xFFFF0000) {
					//Enemy
					enemies.add(new Enemy(xx*32,yy*32));
			}
				else if(val == 0xFF00FF90) {
					finishLine.add(new FinishLine(xx*32,yy*32));
	
				}else if(val == 0xFF7F006E) {
					weakbombs.add(new WeakBomb(xx*32, yy*32));
				}else if(val == 0xFF297C00) {
					strongbombs.add(new StrongBomb(xx*32, yy*32));
				}else if(val == 0xFF00FFFF) {
					health.add(new Health(xx*32, yy*32));
				}else if(val == 0xFF00137F) {
					ammo.add(new Ammo(xx*32, yy*32));
				}
						else {
				gifts.add(new Gift(xx*32,yy*32));
				
			}
					}
				}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void tick() {
		for (int i=0 ;i<enemies.size();i++) {
		enemies.get(i).tick();
		}
}	
	
	public void render (Graphics g) {
		for (int x=0; x<width; x++) {
			for (int y=0; y<height; y++) {
				if(tiles[x][y] != null) tiles[x][y].render(g);
			}
		}
		for (int i=0 ;i<gifts.size();i++) {
			gifts.get(i).render(g);
			
		}
		for (int i=0 ;i<enemies.size();i++) {
			enemies.get(i).render(g);
	}
		for (int i=0 ;i<strongbombs.size();i++) {
			strongbombs.get(i).render(g);
	}
		for (int i=0 ;i<weakbombs.size();i++) {
			weakbombs.get(i).render(g);
	}
		for (int i=0 ;i<health.size();i++) {
			health.get(i).render(g);
	}
		for (int i=0 ;i<trees.size();i++) {
			trees.get(i).render(g);
	}
		for (int i=0 ;i<ammo.size();i++) {
			ammo.get(i).render(g);
	}
		for (int i=0 ;i<finishLine.size();i++) {
			finishLine.get(i).render(g);
	}
}}
	