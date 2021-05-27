package maze.controller;

public class PanelObserver implements Observer {

	private int NoOfCoins;
	private int	healthvar;
	private int score;
	
	private static int observerIDTracker=0;
	private int observerID;
	
	private Subject panelGrabber;
	
	public PanelObserver(Subject panelGrabber) {
		this.panelGrabber=panelGrabber;
		this.observerID=++observerIDTracker;
		panelGrabber.register(this);
	}
	
	
	@Override
	public void update(int NoOfCoins, int healthvar, int score) {
		this.NoOfCoins=NoOfCoins;
		this.healthvar=healthvar;
		this.score=score;
		
	}

}
