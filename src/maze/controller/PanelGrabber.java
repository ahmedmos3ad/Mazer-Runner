package maze.controller;

import java.util.ArrayList;

public class PanelGrabber implements Subject {

	private ArrayList<Observer> observers;
	private int healthvar;
	private int NoOfCoins;
	private int score;
	
	public PanelGrabber() {
		observers = new ArrayList<Observer>();
	}
	
	@Override
	public void register(Observer newObserver) {
		observers.add(newObserver);
		
	}

	@Override
	public void unregister(Observer deleteObserver) {
		
		int observerIndex=observers.indexOf(deleteObserver);
		observers.remove(observerIndex);
		
	}

	@Override
	public void notifyObserver() {
		for(Observer observer: observers)
		{
			observer.update(getNoOfCoins(), getHealthvar(), score);
		}
		
	}

	public void setNoOfCoins(int newNoOfCoins)
	{
		this.NoOfCoins=newNoOfCoins;
		notifyObserver();	
	}
	
	public void sethealthvar(int newHealthVar)
	{
		this.setHealthvar(newHealthVar);
		notifyObserver();	
	}
	
	public void setScore(int newScore)
	{
		this.score=newScore;
		notifyObserver();	
	}

	/**
	 * @return the healthvar
	 */
	public int getHealthvar() {
		return healthvar;
	}

	/**
	 * @param healthvar the healthvar to set
	 */
	public void setHealthvar(int healthvar) {
		this.healthvar = healthvar;
	}

	/**
	 * @return the noOfCoins
	 */
	public int getNoOfCoins() {
		return NoOfCoins;
	}

}
