package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.*;

public class Application {

	public static void main(String[] args) {
		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Floor(), new Wall(),new Door(),new RocketPad());
		GameMap gameMap;

		List<String> map = Arrays.asList(
				".......................",
				"....#####....######....",
				"....#...#....#....#....",
				"....#...|....|....#....",
				"....#####....######....",
				".......................",
				".......................",
				".......................",
				".......................",
				".......................",
				"......................%");
		gameMap = new GameMap(groundFactory, map);
		world.addMap(gameMap);


		Rocket rocketPlan = new Rocket("Rocket Plan",'-');
		Rocket rocketEng = new Rocket ("Rocket Engine" ,'<');
		Rocket rocketBody = new Rocket("Rocket Body" , '>');
		Key key = new Key ("key", 'K');

		Item rocketEngine = rocketEng.newInventoryItem("Rocket Engine",'<');





		Actor player = new Player("Player", '@', 1, 100);
		player.addItemToInventory(rocketPlan);
		player.addItemToInventory(rocketEng);
		player.addItemToInventory(rocketBody);
		
       // player.addItemToInventory(rocketPlan); //player.addItemToInventory(key);
		//world.addPlayer(player, gameMap, 5, 	1);
		//world.addPlayer(player, gameMap, 9,16 ); //testing for ninja
		world.addPlayer(player, gameMap, 9,22 );
		
//		Grunt grunt = new Grunt("Mongo", player);
//		gameMap.addActor(grunt, 0, 0);
//		Grunt grunt2 = new Grunt("Norbert", player);
//		gameMap.addActor(grunt2,  10, 10);


		Goon goon = new Goon("Robert",player);
		gameMap.addActor(goon, 0, 0);
		goon.addItemToInventory(key);

		Ninja ninja = new Ninja("Naruto",player);
		gameMap.addActor(ninja, 15, 8);
		ninja.setOriLocation(gameMap);

		MiniBoss miniBoss = new MiniBoss("Doctor Maybe" , player);
		gameMap.addActor(miniBoss, 15,2);
		miniBoss.addItemToInventory(rocketEngine);

		Q q = new Q("Q" , player);
		gameMap.addActor(q ,1, 4);
		q.addItemToInventory(rocketBody);

//		gameMap.addItem(key,4,5);
		gameMap.addItem(rocketPlan,6,2);


		world.run();
	}

}
