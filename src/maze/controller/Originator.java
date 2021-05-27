package maze.controller;

import java.awt.Color;
import java.awt.Shape;
import java.util.ArrayList;

import maze.model.Gift;


public class Originator {
	private ArrayList<Gift> gift = new ArrayList<Gift>();
	private ArrayList<WeakBomb> weakbomb = new ArrayList<WeakBomb>();
	private ArrayList<StrongBomb> StrongBomb = new ArrayList<StrongBomb>();
	private ArrayList<Tree> tree = new ArrayList<Tree>();
	private int score;
	private int Ammo;
	private int health;
	
	public void set(ArrayList<Gift> gift,ArrayList<WeakBomb> weakbomb,ArrayList<StrongBomb> StrongBomb,ArrayList<Tree> tree,int score,int Ammo,int health)
	{
		this.gift=gift;
		this.weakbomb=weakbomb;
		this.StrongBomb=StrongBomb;
		this.tree=tree;
		this.score=score;
		this.Ammo=Ammo;
		this.health=health;
	
	}
	public Memento StoreInMemento() {
		return new Memento(gift,weakbomb,StrongBomb,tree,score,Ammo,health);	
	}
	public ArrayList<Gift> RestoreGiftFromMemento(Memento memento){
		gift=memento.getGift();
		return gift;
	}
	public ArrayList<WeakBomb> RestoreWeakBombFromMemento(Memento memento){
		weakbomb=memento.getWeakBomb();
		return weakbomb;
	}
	public ArrayList<StrongBomb> RestoreStrongBombFromMemento(Memento memento){
		StrongBomb=memento.getStrongBomb();
		return StrongBomb;
	}
	public ArrayList<Tree> RestoreTreeFromMemento(Memento memento){
		tree=memento.getTree();
		return tree;
	}
	public int RestoreScoreFromMemento(Memento memento){
		score=memento.getScore();
		return score;
	}
	public int RestoreAmmoFromMemento(Memento memento){
		Ammo=memento.getAmmo();
		return Ammo;
	}
	public int RestoreHealthFromMemento(Memento memento){
		health=memento.getHealth();
		return health;
	}
}
