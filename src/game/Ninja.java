package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.AttackAction;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Player;
import edu.monash.fit2099.engine.SkipTurnAction;
import edu.monash.fit2099.engine.World;


public class Ninja extends Actor {
	private Actor playerObj;
	//private GameMap mapObj;
	//private Display dispObj;
	private int count = 0;
	private int determineStun = 0;
	private int[] playerOriLocation;
	private int[] playerLocationAfterInput;
	private int[] supposeStun;
	
	// Ninja have 50 hitpoints and are always represented with a N
	public Ninja(String name, Actor player) {
	//public Ninja(String name, PlayerActor player) {	
		super(name, 'N', 2, 50);
		playerObj = player;
	}
	

	public void setOriLocation(GameMap map) {
		playerOriLocation = playerLocation(map);
		//System.out.println("playerOriLocation: x=" + playerOriLocation[0] +"y=" + playerOriLocation[1]);
	}
	
	public Action playTurn(Actions actions, GameMap map, Display display) {
		//mapObj = map;
		//dispObj = display;
		
		//if (count == 1) {
			//Stunt(map,display);
			//count -= 1;
		//}
		System.out.println("determineStun=" + determineStun);
		
					
		int[] playerLocation = playerLocation(map); //calling playerLocation method to retrieved player location
		//System.out.println(playerLocation[0]);
		//System.out.println(playerLocation[1]);
		if(count == 0) {
			playerLocationAfterInput=playerLocation;
			System.out.println("playerOriLocation: x=" + playerOriLocation[0] +",y=" + playerOriLocation[1]);
			System.out.println("playerLocationAfterInput: x=" + playerLocationAfterInput[0] +",y=" + playerLocationAfterInput[1]);
			count = count + 1;
		}
		else{
			playerOriLocation = playerLocationAfterInput;
			playerLocationAfterInput = playerLocation;
			System.out.println("playerOriLocation: x=" + playerOriLocation[0] +",y=" + playerOriLocation[1]);
			System.out.println("playerLocationAfterInput: x=" + playerLocationAfterInput[0] +",y=" + playerLocationAfterInput[1]);
		}
		
		//System.out.println("determineStun " + determineStun);
		if (determineStun == 1) {
			supposeStun = playerOriLocation;
			System.out.println("supposeStun: x=" + supposeStun[0] +",y=" + supposeStun[1]);
			Stunt(map,determineStun);
			determineStun += 1;
			//System.out.println("playerOriLocation: x=" + playerOriLocation[0] +",y=" + playerOriLocation[1]);
			//System.out.println("playerLocationAfterInput: x=" + playerLocationAfterInput[0] +",y=" + playerLocationAfterInput[1]);
		}
		else if (determineStun == 3){
			playerOriLocation = supposeStun;
			Stunt(map,determineStun);
			determineStun -= 4;
		}
			
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
				///////////////////////stun here///////////////////////////
				determineStun += 1;
				//Stunt(map,display);
				///////////////////////stun here///////////////////////////
				
					
				
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
					//System.out.println("naruto movement");
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
				///////////////////////stun here///////////////////////////
				//count += 1;
				///////////////////////stun here///////////////////////////
				
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
	
	/*
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		if(count == 0) {
		    Actions act = super.getAllowableActions(otherActor,direction,map);
		    //Actions act2 = super.getAllowableActions(otherActor,direction,map);
		    //act.add(act2);
			//act.clear();
		    Action skipTurnAction = new SkipTurnAction();
			Actions skipTurn = new Actions(skipTurnAction);
			Display display = new Display(); //
			playerObj.playTurn(skipTurn, map, display);
			System.out.println("Player stunned");
			return act;
		}
		
		return new Actions(new AttackAction(otherActor, this));
	        		
	}
	*/
	
	

  
	public void Stunt(GameMap map,int i) {
		//Action skipTurnAction = new SkipTurnAction();
		//Actions skipTurn = new Actions(skipTurnAction);
		
		if(i == 1) {
			System.out.println("Player stunned");
			
			System.out.println("/////////Inside Stunt/////////");
			System.out.println("playerOriLocation: x=" + playerOriLocation[0] +",y=" + playerOriLocation[1]);
			System.out.println("playerLocationAfterInput: x=" + playerLocationAfterInput[0] +",y=" + playerLocationAfterInput[1]);
			System.out.println("/////////Inside Stunt/////////");
			
			Location oriLocationRef = map.at(playerOriLocation[0],playerOriLocation[1]);
			map.moveActor(playerObj,oriLocationRef);
		}
		else if(i == 3){
			System.out.println("Player stunned second time");
			playerOriLocation = supposeStun;
			
			System.out.println("/////////Inside Second Stunt/////////");
			System.out.println("playerOriLocation: x=" + playerOriLocation[0] +",y=" + playerOriLocation[1]);
			System.out.println("playerLocationAfterInput: x=" + playerLocationAfterInput[0] +",y=" + playerLocationAfterInput[1]);
			System.out.println("/////////Inside Second Stunt/////////");
			
			Location oriLocationRef = map.at(playerOriLocation[0],playerOriLocation[1]);
			map.moveActor(playerObj,oriLocationRef);
			//map.removeActor(playerObj);
			//map.addActor(playerObj, playerOriLocation[0], playerOriLocation[1]);
		}
		
		
	}
	
	
		
}

	
   
