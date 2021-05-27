package maze.controller;

import java.util.ArrayList;

import maze.model.Gift;

public class Memento {
	private ArrayList<Gift> gift = new ArrayList<Gift>();
	private ArrayList<WeakBomb> weakbomb = new ArrayList<WeakBomb>();
	private ArrayList<StrongBomb> StrongBomb = new ArrayList<StrongBomb>();
	private ArrayList<Tree> tree = new ArrayList<Tree>();
	private int score;
	private int Ammo;
	private int health;
	public Memento(ArrayList<Gift> gift,ArrayList<WeakBomb> weakbomb,ArrayList<StrongBomb> StrongBomb,ArrayList<Tree> tree,int score,int Ammo,int health)
	{
		this.gift=gift;
		this.weakbomb=weakbomb;
		this.StrongBomb=StrongBomb;
		this.tree=tree;
		this.score=score;
		this.Ammo=Ammo;
		this.health=health;
	
	}
	public ArrayList<Gift> getGift() {
		return gift;
	}
	public ArrayList<WeakBomb> getWeakBomb() {
		return weakbomb;
	}
	public ArrayList<StrongBomb> getStrongBomb() {
		return StrongBomb;
	}
	public ArrayList<Tree> getTree() {
		return tree;
	}
	public int getScore() {
		return score;
	}
	public int getHealth() {
		return health;
	}
	public int getAmmo() {
		return Ammo;
	}
}
