package game;

import java.util.Random;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

/**
 * Class representing new character Ninja
 */
public class Ninja extends Actor {
	private Actor playerObj;
	private int count = 0;
	private int determineStun = 0;
	private int[] playerOriLocation;
	private int[] playerLocationAfterInput;
	private int[] supposeStun;
	private int secondStun = 0;
	private int thirdStun = 0;


	public Ninja(String name, Actor player) {
		/**
		 * Constructor.
		 *{@inheritDoc}
		 * @param name Name to call Ninja in the UI
		 * @param player Character to represent Ninja in the UI
		 *
		 * Ninja have 50 hitpoints and are always represented with a N
		 * Ninja have move one step away behaviour and a 50% chance of stunning player for 2 rounds when they detected player is within 5 squares of them
		 */

		super(name, 'N', 4, 50);
		playerObj = player;
	}
	
	/**
	 * This method saves the player starting position 
	 * @param map map that contains all the actor locations
	 */
	public void setOriLocation(GameMap map) {
		playerOriLocation = locationCoordinate(map,playerObj);
	}
	
	/**
	 * This method allows Ninja to detect whether player is within 5 squares of them 
	 * If player is within 5 squares of them,ninja will move one step away and have a 50% chance to stun player for 2 rounds
	 */
	@Override
	public Action playTurn(Actions actions, GameMap map, Display display) {	
		
		//////////////////////////////////Code for stunt starts here//////////////////////////////////
			
		int actionSize = actions.size(); //getting the size of allowable actions of ninja
				
		int[] playerLocation = locationCoordinate(map,playerObj); //calling playerLocation method to retrieved player location
		
		//Tracking player movement 
		//In the first round set player current position to global variable named playerLocationAfterInput
		if(count == 0) {
			playerLocationAfterInput=playerLocation;
			count = count + 1;
			
		}
		//After the first round,set the previous position to playerOriLocation and current position to playerLocationAfterInput
		else{
			playerOriLocation = playerLocationAfterInput;
			playerLocationAfterInput = playerLocation;
		}
		
		//Stun player if the stun indicator for first round is not zero and stun counter is not more than 2 times
		if (determineStun == 1 && thirdStun != 2) {
			//generate random numbers in the range of 0 to 1(50% chances to execute stun code)
			Random rand = new Random();
			int prob = rand.nextInt(2);
			
			if(prob == 1) {
				secondStun += 1; //make second stun indicator non zero to execute stun code for second round 
				supposeStun = playerOriLocation; //record the player stunned position for second stun to be performed	
				Stunt(map,1);				
				determineStun += 1; 
				thirdStun += 1; //recording how many rounds has player been stunned,to prevent third consecutive stun
			}			
			else {
				determineStun = 0; //if no stunt is executed due to the 50% luck,make the stun indicator back to zero(Indicate no stun to be performed)
			}
		}
		//allow stun code to be executed 2 rounds after second stun(prevent player from being stunned for 3 consecutive rounds)
		else if (thirdStun == 2) {
			thirdStun = 0;
			determineStun = 0;
		}
		//stun player for second round if stun indicator for second round is not zero
		else if (secondStun  > 0){
			playerOriLocation = supposeStun;
			Stunt(map,2);			
			secondStun = 0;
			thirdStun += 1;
		}		
		//////////////////////////////////Code for stunt ends here//////////////////////////////////
		
		//getting the ninja's current position 
		int xNinjaCoordinate = map.locationOf(this).x();
		int yNinjaCoordinate = map.locationOf(this).y();
		
		//if player's location x-Coordinate is equivalent to the x-Coordinate of Ninja(Vertical Movement)
		if (playerLocation[0] == xNinjaCoordinate){
			//calculating the y-Coordinate range that the Ninja can detect player
			int yDetectedUpper = yNinjaCoordinate - 5;
			int yDetectedLower = yNinjaCoordinate + 5;
						
			//if player location is within the detected range by Ninja
			if( yDetectedUpper <= playerLocation[1] && playerLocation[1] <= yDetectedLower) {

				determineStun += 1;	//adding one to the stun indicator to allow player to be stunned for the next round
							
				//if player is located above the Ninja location
				if (playerLocation[1] <= yNinjaCoordinate) {
					//move ninja one step away from player in the direction of South
					for(int i=0;i<actionSize;i++) {
						//if(actions.get(i).menuDescription(this).equalsIgnoreCase("Naruto moves South")) {
						if(actions.get(i).hotKey().toString() == "2") {
							return actions.get(i);
						}
					}
				}
				
				//if player is located below the Ninja location
				if (playerLocation[1] >= yNinjaCoordinate) {
					//move ninja one step away from player in the direction of North
					for(int i=0;i<actionSize;i++) {
						//if(actions.get(i).menuDescription(this).equalsIgnoreCase("Naruto moves North")) {
						if(actions.get(i).hotKey().toString() == "8") {
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

				determineStun += 1;	//adding one to the stun indicator to allow player to be stunned for the next round
							
				//if player is located to the left of the Ninja location
				if (playerLocation[0] <= xNinjaCoordinate) {
					//move ninja one step away from player in the direction of East
					for(int i=0;i<actionSize;i++) {
						//if(actions.get(i).menuDescription(this).equalsIgnoreCase("Naruto moves East")) {
						if(actions.get(i).hotKey().toString() == "6") {
							return actions.get(i);
						}
					}
				}
				
				//if player is located to the right of the Ninja location
				if (playerLocation[0] >= xNinjaCoordinate) {
					//move ninja one step away from player in the direction of West
					for(int i=0;i<actionSize;i++) {
						//if(actions.get(i).menuDescription(this).equalsIgnoreCase("Naruto moves West")) {
						if(actions.get(i).hotKey().toString() == "4") {
							//System.out.println(actions.get(i).hotKey().toString());
							return actions.get(i);
						}
					}
				}
			}
		}
		
		return actions.get(actions.size()-1); //do nothing if the player is not within the detected range by Ninja or Ninja is blocked by wall or edges of map
		
	}
	
	/**
	 * This method return the actor location
	 * @param gamemap map that contains all the actor locations
	 * @param actorObj actor object 
	 * @return the x and y coordinate of actor in the list format of [x-Coordinate,y-Coordinate]
	 */
	//method to return the actor location in the list format of [x-Coordinate,y-Coordinate]
	public int[] locationCoordinate(GameMap gamemap,Actor actorObj) {
		
		Location locationRef = gamemap.locationOf(actorObj); //getting the location reference of actor
		int xCoordinate = locationRef.x();
		int yCoordinate = locationRef.y();
		
		int[] coordinate = {xCoordinate,yCoordinate};
		
		return coordinate;	
	}
	
	/**
	 * This method will stun player by removing player from the current position and
	 * add the player back to the original position where the player was stunned
	 * @param map map that contains all the actor locations
	 * @param i indicator to determine the stun round
	 */
	public void Stunt(GameMap map,int i) {
		//if it is the first round of stun		
		if(i == 1) {
			System.out.println("Player stunned.No movement has been made by player.");			
			map.removeActor(playerObj);
			map.addActor(playerObj, playerOriLocation[0], playerOriLocation[1]);
		}
		//code for second round of stun
		else{
			System.out.println("Player stunned for second round.No movement has been made by player.");			
			map.removeActor(playerObj);
			map.addActor(playerObj, playerOriLocation[0], playerOriLocation[1]);
		}		
	}
}

