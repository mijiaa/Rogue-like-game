package game;

import edu.monash.fit2099.engine.*;

import java.util.Random;

public class GivePlans extends Action {

    private Actor actor;
    private Actor subject;
    private Random rand = new Random();
    private Item item1;
    private Item item2;

    public GivePlans (Actor actor, Actor subject, Item item1, Item item2) {
        this.actor = actor;
        this.subject = subject;
        this.item1 = item1;
        this.item2 = item2;
    }

    public String execute(Actor actor, GameMap map) {
        actor.removeItemFromInventory(item1);
        subject.removeItemFromInventory(item2);
        actor.addItemToInventory(item2);
        map.removeActor(subject);
        System.out.println(actor.getInventory());
        return "Q left with a cheery wave, player obtained Rocket Body";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " give Rocket Plans to " + subject;
    }

    @Override
    public String hotKey() {
        return "9";
    }
}
