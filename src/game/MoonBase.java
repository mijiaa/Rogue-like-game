package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.*;

public class MoonBase {
	private GameMap earthMap;
	private RocketToEarth rocketAtMoon = new RocketToEarth("Rocket", '~');
	
	public MoonBase(Actor player,GameMap earth){
		earthMap = earth;
		World moonWorld = new World(new Display());
		
		FancyGroundFactory MoonGroundFactory = new FancyGroundFactory(new Floor(), new Wall(),new Door(),new RocketPad());
		GameMap moonMap;
		
		List<String> secondMap = Arrays.asList(
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
		
		moonMap = new GameMap(MoonGroundFactory, secondMap);
		moonWorld.addMap(moonMap);
		
		moonWorld.addPlayer(player, moonMap, 10,22);
		moonMap.addItem(rocketAtMoon, 22, 10);
		
		//adding grunts to game map, grunts has key
		Grunt grunt = new Grunt("Mongo", player); 
		moonMap.addActor(grunt, 4, 8);
		//grunt.addItemToInventory(key);

		Grunt grunt2 = new Grunt("Norbert", player); 
		moonMap.addActor(grunt2,  10, 3);
		//grunt2.addItemToInventory(key);

		//adding goon to game map, goon has key
		Goon goon = new Goon("Robert",player);
		moonMap.addActor(goon, 0, 10);
		//goon.addItemToInventory(key);

		//adding ninja to game map
		Ninja ninja = new Ninja("Naruto",player);
		moonMap.addActor(ninja, 15, 8);
		ninja.setOriLocation(moonMap);
		//ninja.addItemToInventory(key);


		moonWorld.run();
	}
}