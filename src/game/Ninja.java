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
	private int secondStun = 0;
	private int thirdStun = 0;
	//private int stunExecute = 0;
		
	// Ninja have 50 hitpoints and are always represented with a N
	public Ninja(String name, Actor player) {
	//public Ninja(String name, PlayerActor player) {	
		super(name, 'N', 2, 50);
		playerObj = player;
	}
	
	public void setOriLocation(GameMap map) {
		playerOriLocation = locationCoordinate(map,playerObj);
		//System.out.println("playerOriLocation: x=" + playerOriLocation[0] +"y=" + playerOriLocation[1]);
	}
	
	
	
	public Action playTurn(Actions actions, GameMap map, Display display) {	
		//mapObj = map;
		//dispObj = display;
		
		//if (count == 1) {
			//Stunt(map,display);
			//count -= 1;
		//}		
		
				
		//////////////////////////////////Code for stunt starts here//////////////////////////////////
		System.out.println("Before detected,determineStun= " + determineStun);
		
		int actionSize = actions.size();
				
		int[] playerLocation = locationCoordinate(map,playerObj); //calling playerLocation method to retrieved player location
		
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
		if (determineStun == 1 & thirdStun != 2) {
			Random rand = new Random();
			int prob = rand.nextInt(2);
			System.out.println("prob= " + prob);
			
			if(prob == 1) {
				secondStun += 1;
				supposeStun = playerOriLocation;
				System.out.println("supposeStun: x=" + supposeStun[0] +",y=" + supposeStun[1]);
				Stunt(map,1);
				System.out.println("Inside 1st Stun,determineStun= " + determineStun);
				//secondStun += 1;
				determineStun += 1;
				thirdStun += 1;
			}			
			else {
				determineStun = 0;
			}
			/*
			secondStun += 1;
			supposeStun = playerOriLocation;
			System.out.println("supposeStun: x=" + supposeStun[0] +",y=" + supposeStun[1]);
			Stunt(map,1);
			System.out.println("Inside 1st Stun,determineStun= " + determineStun);
			//secondStun += 1;
			determineStun += 1;
			thirdStun += 1;
			*/
			
			//System.out.println("playerOriLocation: x=" + playerOriLocation[0] +",y=" + playerOriLocation[1]);
			//System.out.println("playerLocationAfterInput: x=" + playerLocationAfterInput[0] +",y=" + playerLocationAfterInput[1]);
		}
		else if (thirdStun == 2) {
			thirdStun = 0;
			determineStun = 0;
		}
		else if (secondStun  > 0){
			playerOriLocation = supposeStun;
			Stunt(map,2);
			System.out.println("Inside 2nd Stun,determineStun= " + determineStun);
			//determineStun = 0;
			secondStun = 0;
			thirdStun += 1;
		}		
		//////////////////////////////////Code for stunt starts here//////////////////////////////////
		
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
				/*
				if(stunExecute == 0){
					Random rand = new Random();
					int prob = rand.nextInt(2);
					if(prob == 1) {
						determineStun += 1;
						stunExecute += 1;
					}
				}
				else {
					determineStun += 1;
					stunExecute -= 1;
				}
				*/
				determineStun += 1;	
				System.out.println("Inside detected if statement,determineStun= : " + determineStun);
				///////////////////////stun here///////////////////////////
				
				//if player is located above the Ninja location
				if (playerLocation[1] <= yNinjaCoordinate) {
					
					for(int i=0;i<actionSize;i++) {
						if(actions.get(i).menuDescription(this).equalsIgnoreCase("Naruto moves South")) {
							return actions.get(i);
						}
					}
				}
				
				//if player is located below the Ninja location
				if (playerLocation[1] >= yNinjaCoordinate) {
					
					for(int i=0;i<actionSize;i++) {
						if(actions.get(i).menuDescription(this).equalsIgnoreCase("Naruto moves North")) {
							return actions.get(i);
						}
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
				/*
				if(stunExecute == 0){
					Random rand = new Random();
					int prob = rand.nextInt(2);
					if(prob == 1) {
						determineStun += 1;
						stunExecute += 1;
					}
				}
				else {
					determineStun += 1;
					stunExecute -= 1;
				}
				*/
				determineStun += 1;	
				System.out.println("Inside detected if statement,determineStun= : " + determineStun);
				///////////////////////stun here///////////////////////////
				
				//if player is located to the left of the Ninja location
				if (playerLocation[0] <= xNinjaCoordinate) {
					
					for(int i=0;i<actionSize;i++) {
						if(actions.get(i).menuDescription(this).equalsIgnoreCase("Naruto moves East")) {
							return actions.get(i);
						}
					}
				}
				
				//if player is located to the right of the Ninja location
				if (playerLocation[0] >= xNinjaCoordinate) {
					
					for(int i=0;i<actionSize;i++) {
						if(actions.get(i).menuDescription(this).equalsIgnoreCase("Naruto moves West")) {
							return actions.get(i);
						}
					}
				}
			}
		}
		
		return actions.get(actions.size()-1); //do nothing if the player is not within the detected range by Ninja
		
	}

	//method to return the player location in the list format of [x-Coordinate,y-Coordinate]
	public int[] locationCoordinate(GameMap gamemap,Actor actorObj) {
		
		Location locationRef = gamemap.locationOf(actorObj);
		int xCoordinate = locationRef.x();
		int yCoordinate = locationRef.y();
		
		int[] coordinate = {xCoordinate,yCoordinate};
		
		return coordinate;	
	}
	
	public void Stunt(GameMap map,int i) {
		//Action skipTurnAction = new SkipTurnAction();
		//Actions skipTurn = new Actions(skipTurnAction);
		
		if(i == 1) {
			System.out.println("Player stunned");
			
			System.out.println("/////////Inside Stunt/////////");
			System.out.println("playerOriLocation: x=" + playerOriLocation[0] +",y=" + playerOriLocation[1]);
			System.out.println("playerLocationAfterInput: x=" + playerLocationAfterInput[0] +",y=" + playerLocationAfterInput[1]);
			System.out.println("/////////Inside Stunt/////////");
			
			//ori
			//Location oriLocationRef = map.at(playerOriLocation[0],playerOriLocation[1]);
			//map.moveActor(playerObj,oriLocationRef);
			//ori
			
			map.removeActor(playerObj);
			map.addActor(playerObj, playerOriLocation[0], playerOriLocation[1]);
		}
		else{
			System.out.println("Player stunned second time");
			//playerOriLocation = supposeStun;
			
			System.out.println("/////////Inside Second Stunt/////////");
			System.out.println("playerOriLocation: x=" + playerOriLocation[0] +",y=" + playerOriLocation[1]);
			System.out.println("playerLocationAfterInput: x=" + playerLocationAfterInput[0] +",y=" + playerLocationAfterInput[1]);
			System.out.println("/////////Inside Second Stunt/////////");
			
			//ori
			//Location oriLocationRef = map.at(playerOriLocation[0],playerOriLocation[1]);
			//map.moveActor(playerObj,oriLocationRef);
			//ori 
			
			map.removeActor(playerObj);
			map.addActor(playerObj, playerOriLocation[0], playerOriLocation[1]);
		}
		
		
	}
	
  

}

