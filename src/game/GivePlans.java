//package game;
//
//import edu.monash.fit2099.engine.Action;
//import edu.monash.fit2099.engine.Actor;
//import edu.monash.fit2099.engine.GameMap;
//import edu.monash.fit2099.engine.Location;
//
//import java.util.Random;
//
//public class GivePlans extends Action {
//
//    private Actor actor;
//    private Actor subject;
//    private Random rand = new Random();
//
//    public GivePlans (Actor actor, Actor subject) {
//        this.actor = actor;
//        this.subject = subject;
//    }
//
//    public String execute(Actor actor, GameMap map) {
//        actor.removeItemFromInventory("Rocket Body");
//        subject.removeItemFromInventory("Rocket Plan");
//        subject.addItemToInventory("Rocket Body");
//}
