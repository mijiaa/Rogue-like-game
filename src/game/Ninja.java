package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;

public class Ninja extends Actor {
	Display printResult = new Display();
	
	// Ninja have 50 hitpoints and are always represented with a N
	public Ninja(String name, Actor player) {
		super(name, 'N', 7, 50);		
	}
}
