package game;

import edu.monash.fit2099.engine.*;


import java.util.List;
import java.util.Random;

public class Talk extends Action {

    private Actor actor;
    private Actor subject;
    private Random rand = new Random();

    public Talk(Actor actor, Actor subject) {
        this.actor = actor;
        this.subject = subject;
    }

    public String execute(Actor actor, GameMap map) {
        System.out.println("TALK");
        Location loc_player = map.locationOf(actor);
        Location loc_sub = map.locationOf(subject);
        List<Item> player_items = subject.getInventory();


        if (distance(loc_player,loc_sub) == 1){
            for(int i =0 ; i<player_items.size() ; i++) {
                if (player_items.get(i).getClass() == Rocket.class & player_items.get(i).toString() == "Rocket Plan"){
                    exchange(player_items.get(i));
                    return "Hand them over, I don’t have all day!";

                } else {
                    return "I can give you something that will help, but I’m going to need the plans.";

                }
            }

        }
        return null;
    }

    private int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }

    private void exchange(Item item){
        actor.removeItemFromInventory(item);
        subject.removeItemFromInventory(item);
        subject.addItemToInventory(item);
        System.out.println("Q exchange Rocket body and Rocket Plans with Player");
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " talks " + subject;
    }

    @Override
    public String hotKey() {
        return "a";
    }
}

