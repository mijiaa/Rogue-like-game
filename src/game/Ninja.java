package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Player;


public class Ninja extends Actor {
	Actor playerObj;
	Stunt stunPlayer = new Stunt();
	//SkipTurnAction stunPlayer = new Stunt();
		
	// Ninja have 50 hitpoints and are always represented with a N
	public Ninja(String name, Actor player) {
		super(name, 'N', 7, 50);
		playerObj = player;
	}
	
	public Action playTurn(Actions actions, GameMap map, Display display) {
		
		int[] playerLocation = playerLocation(map); //calling playerLocation method to retrieved player location
		
		//map.at(4, 1); //return location reference
		//System.out.println(map.groundAt(map.at(2,11)).canActorEnter(this)); //check whether is it wall
		int xNinjaCoordinate = map.locationOf(this).x();
		int yNinjaCoordinate = map.locationOf(this).y();
		
		//if player location x-Coordinate is equivalent to the x-Coordinate of Ninja(Vertical Movement)
		if (playerLocation[0] == xNinjaCoordinate){
			//calculating the y-Coordinate range that the Ninja can detect player
			int yDetectedUpper = yNinjaCoordinate - 5;
			int yDetectedLower = yNinjaCoordinate + 5;
			
			//if player location is within the detected range by Ninja
			if( yDetectedUpper <= playerLocation[1] && playerLocation[1] <= yDetectedLower) {
				//stunPlayer

				//if player is located above the Ninja location
				if (playerLocation[1] <= yNinjaCoordinate) {
					//ensuring the location of next move is within the valid value
					if (yNinjaCoordinate+1 <= 10) {
						//ensuring the location of next move is valid(not wall)
						if (map.groundAt(map.at(xNinjaCoordinate,yNinjaCoordinate+1)).canActorEnter(this)) {
							return actions.get(2); //move South if player is within 5 spaces away from Ninja in the direction of North 
						}
						else {
							return actions.get(3); //do nothing if location of next move is invalid(blocked by wall)
						}
					}
					else {
						return actions.get(3); //do nothing if the next moved location is invalid
					}
				}
				
				//if player is located below the Ninja location
				if (playerLocation[1] >= yNinjaCoordinate) {
					//ensuring the location of next move is within the valid value
					if (yNinjaCoordinate-1 >= 0) {
						//ensuring the location of next move is valid(not wall)
						if (map.groundAt(map.at(xNinjaCoordinate,yNinjaCoordinate-1)).canActorEnter(this)) {
							return actions.get(0); //move North if player is within 5 spaces away from Ninja in the direction of South
						}
						else {
							return actions.get(3); //do nothing if location of next move is invalid(blocked by wall)
						}
					}
					else {
						return actions.get(3); //do nothing if the next moved location is invalid
					}
				}
			}
		} 
		
		//if player location y-Coordinate is equivalent to the y-Coordinate of Ninja(Horizontal Movement)
		if (playerLocation[1] == yNinjaCoordinate){
			//calculating the x-Coordinate range that the Ninja can detect player
			int xDetectedLeft = xNinjaCoordinate - 5;
			int xDetectedRight = xNinjaCoordinate + 5;
			
			//if player location is within the detected range by Ninja
			if( xDetectedLeft <= playerLocation[0] && playerLocation[0] <= xDetectedRight) {
				//if player is located to the left of the Ninja location
				if (playerLocation[0] <= xNinjaCoordinate) {
					//ensuring the location of next move is within the valid value
					if (xNinjaCoordinate+1 <= 22) {
						//ensuring the location of next move is valid(not wall)
						if (map.groundAt(map.at(xNinjaCoordinate+1,yNinjaCoordinate)).canActorEnter(this)) {
							return actions.get(1); //move East if player is within 5 spaces away from Ninja in the direction of West 
						}
						else {
							return actions.get(3); //do nothing if location of next move is invalid(blocked by wall)
						}
					}
					else {
						return actions.get(3); //do nothing if the next moved location is invalid
					}
				}
				
				//if player is located to the right of the Ninja location
				if (playerLocation[0] >= xNinjaCoordinate) {
					//ensuring the location of next move is within the valid value
					if (xNinjaCoordinate-1 >= 0) {
						//ensuring the location of next move is valid(not wall)
						if (map.groundAt(map.at(xNinjaCoordinate-1,yNinjaCoordinate)).canActorEnter(this)) {
							return actions.get(3); //move West if player is within 5 spaces away from Ninja in the direction of East
						}
						else {
							return actions.get(3); //do nothing if location of next move is invalid(blocked by wall)
						}
					}
					else {
						return actions.get(3); //do nothing if the next moved location is invalid
					}
				}
			}
		}
		
		return actions.get(4); //do nothing if the player is not within the detected range by Ninja
	}

	//method to return the player location in the list format of [x-Coordinate,y-Coordinate]
	public int[] playerLocation(GameMap gamemap) {
		
		Location locationRef = gamemap.locationOf(playerObj);
		int xCoordinate = locationRef.x();
		int yCoordinate = locationRef.y();
		
		int[] coordinate = {xCoordinate,yCoordinate};
		
		return coordinate;	
	}
}

