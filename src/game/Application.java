package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.*;

public class Application {

	public static void main(String[] args) {
		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Floor(), new Wall(),new Door(),new RocketPad(), new OxygenDispenser());
		GameMap gameMap;

		List<String> map = Arrays.asList(
				"..O....................",
				"....#####....######....",
				"....#...#....#....#....",
				"....#...|....|....#....",
				"....#####....######....",
				".......................",
				".......................",
				".......................",
				".......................",
				"....................##.",
				"....................#.X");
		gameMap = new GameMap(groundFactory, map);
		world.addMap(gameMap);

		//Declaring instances for new Rocket objects and key
		Rocket rocketPlan = new Rocket("Rocket Plan",'-');
		Rocket rocketEng = new Rocket ("Rocket Engine" ,'<');
		Rocket rocketBody = new Rocket("Rocket Body" , '>');
		Key k = new Key ("Key", 'K');
		Key key = k. newInventoryItem("Key",'K');
		Item rocketEngine = rocketEng.newInventoryItem("Rocket Engine",'<');
		SpaceSuit s_suit = new SpaceSuit("Space Suit",'S');


		gameMap.addItem(s_suit,19,10);


		Actor player = new Player("Player", '@', 1, 100);
		world.addPlayer(player, gameMap, 0,0);
				
		//adding grunts to game map, grunts has key
		Grunt grunt = new Grunt("Mongo", player); 
		gameMap.addActor(grunt, 4, 8);
		grunt.addItemToInventory(key);

		Grunt grunt2 = new Grunt("Norbert", player); 
		gameMap.addActor(grunt2,  10, 3);
		grunt2.addItemToInventory(key);

		//adding goon to game map, goon has key
		Goon goon = new Goon("Robert",player);
		gameMap.addActor(goon, 0, 10);
		goon.addItemToInventory(key);

		//adding ninja to game map
		Ninja ninja = new Ninja("Naruto",player);
		gameMap.addActor(ninja, 15, 8);
		ninja.setOriLocation(gameMap);
		ninja.addItemToInventory(key);

		//adding MiniBoss inside a locked room, MiniBoss has rocket engine
		MiniBoss miniBoss = new MiniBoss("Doctor Maybe" , player);
		gameMap.addActor(miniBoss, 15,2);
		miniBoss.addItemToInventory(rocketEngine);

		//adding Q to game map, Q has rocket body
		Q q = new Q("Q" , player);
		gameMap.addActor(q ,11, 6);
		q.addItemToInventory(rocketBody);

		//adding rocket plan inside a locked room
		gameMap.addItem(rocketPlan,6,2);

		world.run();
	}

}
