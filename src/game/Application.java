package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.*;

public class Application {

	public static void main(String[] args) {
		ExtendedWorld world = new ExtendedWorld(new Display());
		
		GameMap gameMap;
		GameMap moonMap;
		RocketPad rocketPlatform = new RocketPad();
		FancyGroundFactory groundFactory = new FancyGroundFactory(new Floor(), new Wall(),new Door(),rocketPlatform, new OxygenDispenser(),new Pool());
		
		
		List<String> map = Arrays.asList(
				"..O....................",
				"....#####....######....",
				"....#...#....#....#....",
				"....#...|....|....#....",
				"....#####....######....",
				".......................",
				"......................*",
				".......................",
				".......................",
				"....................##.",
				"....................#.X");
		
		List<String> moonmap = Arrays.asList(
				".......................",
				".......................",
				".......................",
				".......................",
				".......................",
				".......................",
				".......................",
				".......................",
				".......................",
				".......................",
				".......................");
		
		gameMap = new GameMap(groundFactory, map);
		moonMap = new GameMap(groundFactory, moonmap);
		//rocketPlatform.setMap(gameMap, moonMap);
		world.addMap(gameMap);
		world.addMap(moonMap);
		rocketPlatform.setMap(gameMap, moonMap);
		
		
		//Declaring instances for new Rocket objects and key
		Rocket rocketPlan = new Rocket("Rocket Plan",'-');
		Rocket rocketEng = new Rocket ("Rocket Engine" ,'<');
		Rocket rocketBody = new Rocket("Rocket Body" , '>');
		Key k = new Key ("Key", 'K');
		Key key = k. newInventoryItem("Key",'K');
		Item rocketEngine = rocketEng.newInventoryItem("Rocket Engine",'<');
		SpaceSuit s_suit = new SpaceSuit("Space Suit",'S');
		WaterPistol pistol = new WaterPistol("Water Pistol", 'P');
		OxygenTank oxygenTank = new OxygenTank("Oxygen Tank",'T');



//		gameMap.addItem(s_suit,19,10);


		Actor player = new ExtendedPlayer("Player", '@', 1, 100);
		//world.addPlayer(player, gameMap, 0,0);
		player.addItemToInventory(rocketPlan);
		player.addItemToInventory(rocketEng);
		player.addItemToInventory(rocketBody);
		player.addItemToInventory(oxygenTank);
		//player.addItemToInventory(oxygenTank);
		player.addItemToInventory(s_suit);

		
		//world.addPlayer(player, gameMap, 8,22);
		world.addPlayer(player, gameMap, 8,22);
						
		
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
		//gameMap.addActor(goon, 22, 7); //test when player is knocked out
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
