package maze.controller;

import maze.view.GUI;
import maze.view.GameOverGUI;

public class BombFactory implements Factory {
	
	GameOverGUI GO=new GameOverGUI();
	private PanelGrabber panelGrabber=new PanelGrabber();
	private PanelObserver panelObserver=new PanelObserver(panelGrabber);
	
	@Override
	public void reduceHealth(boolean type) {
		if(type)
		{
			panelGrabber.sethealthvar(GUI.healthvar-50);
			//GUI.healthvar-=50;
			GUI.healthvar=panelGrabber.getHealthvar();
		}
		else
		{
			panelGrabber.sethealthvar(GUI.healthvar-25);
			GUI.healthvar=panelGrabber.getHealthvar();
			//GUI.healthvar-=25;
		}
		GUI.healthAmount.setText(String.valueOf(GUI.healthvar));
		if(GUI.healthvar<=0)
		{
			GUI.score+=((GUI.score*0.2)/(GUI.getK()*0.2));
			GUI.scoreAmount.setText(String.valueOf(GUI.score));
			GUI.getT().stop();
			GO.Show();
			//GameOverGUI.Show();
		}
	}
}