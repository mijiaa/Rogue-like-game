package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.SkipTurnAction;
import edu.monash.fit2099.engine.World;

public class ExtendedWorld extends World {

	public ExtendedWorld(Display display) {
		super(display);
		// TODO Auto-generated constructor stub
	}
	
	public void run() {
		if(player == null)
			throw new IllegalStateException();
		
		while (stillRunning()) {
			GameMap playersMap = actorLocations.locationOf(player).map();
			playersMap.draw(display);
			
			//for (Actor actor : actorLocations) {
			for (Actor actor : actorLocations) {
				if((playersMap.locationOf(actor).map()).equals(playersMap)) {
					processActorTurn(actor);
				}
				//processActorTurn(actor);
			}
			
			
		}
		display.println(endGameMessage());
	}
	
	
	protected void processActorTurn(Actor actor) {
		Location here = actorLocations.locationOf(actor);
		GameMap map = here.map();

		Actions actions = new Actions();
		for (Item item : actor.getInventory()) {
			actions.add(item.getAllowableActions());
		}

		for (Exit exit : here.getExits()) {
			Location destination = exit.getDestination();
			if (actorLocations.isAnActorAt(destination)) {
				Actor adjacentActor = actorLocations.actorAt(destination);
				actions.add(adjacentActor.getAllowableActions(actor, exit.getName(), map));
			} else {
				Ground adjacentGround = map.groundAt(destination);
				actions.add(adjacentGround.allowableActions(actor, destination, exit.getName()));
				actions.add(adjacentGround.getMoveAction(actor, destination, exit.getName(), exit.getHotKey()));
			}
		}

		for (Item item : here.getItems()) {
			actions.add(item.getAllowableActions());
		}
		
		if(actor.toString() == "Player") {
			QuitGameAction quitGame = new QuitGameAction();
			quitGame.addActorLocations(actorLocations);
			actions.add(quitGame);
		}
		/*
		QuitGameAction quitGame = new QuitGameAction();
		quitGame.addActorLocations(actorLocations);
		actions.add(quitGame);
		*/
		actions.add(new SkipTurnAction());
		
		/*
		QuitGameAction quitGame = new QuitGameAction();
		quitGame.addActorLocations(actorLocations);
		actions.add(quitGame);
		*/
		//actions.add(new QuitGameAction());
		
		Action action = actor.playTurn(actions, map, display);
		String result = action.execute(actor, map);
		display.println(result);
	}
	
	
}
