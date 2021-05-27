package maze.controller;

import java.util.ArrayList;

public class Caretaker {
	ArrayList<Memento> savedMazes=new ArrayList<Memento>();
	
	public void addMemento(Memento m)
	{
		savedMazes.add(m);
	}
	public Memento getMemento(int index) {
		return savedMazes.get(index);
	}
}