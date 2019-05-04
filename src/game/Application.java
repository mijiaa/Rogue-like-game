package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.*;

public class Application {

	public static void main(String[] args) {
		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Floor(), new Wall(),new Door());
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
				".......................");
		gameMap = new GameMap(groundFactory, map);
		world.addMap(gameMap);

		Rocket rocketPlan = new Rocket("Rocket Plan",'-');
		Rocket rocketEngine = new Rocket ("Rocket Engine" ,'<');
		Rocket rocketBody = new Rocket("Rocket Body" , '>');
		Key key = new Key ("key", 'K');

//		Actor player = new Player("Player", '@', 1, 100);
//		world.addPlayer(player, gameMap, 2, 2);
		Actor player = new Player("Player", '@', 1, 100);
		world.addPlayer(player, gameMap, 4, 2);

		
//		Grunt grunt = new Grunt("Mongo", player);
//		gameMap.addActor(grunt, 0, 0);
//		Grunt grunt2 = new Grunt("Norbert", player);
//		gameMap.addActor(grunt2,  10, 10);


		Goon goon = new Goon("Robert",player);
		gameMap.addActor(goon, 0, 0);
		goon.addItemToInventory(key);


		MiniBoss miniBoss = new MiniBoss("Doctor Maybe" , player);
		world.addPlayer(miniBoss, gameMap, 2,15);
		miniBoss.addItemToInventory(rocketEngine);


		Q q =new Q("Q",player);
		world.addPlayer(q,gameMap,4,1);
		q.addItemToInventory(rocketBody);

		gameMap.addItem(key,4,5);
		gameMap.addItem(rocketPlan,6,2);

		Actions action = new Actions(new Talk(player, q));
		action.add(action);
		world.run();
	}
}
