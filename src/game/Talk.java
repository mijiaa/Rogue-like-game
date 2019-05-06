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

        Location loc_player = map.locationOf(actor);
        Location loc_sub = map.locationOf(subject);
        List<Item> player_items = actor.getInventory();
        int near = 1;

        if (distance(loc_player,loc_sub) == near){

            for(int i = 0 ; i<player_items.size() ; i++) {
                //& player_items.get(i).toString() == "Rocket Plan"

                if (player_items.get(i).getClass() == game.Rocket.class){
                    exchange(player_items.get(i),map);
                    System.out.println("Hand them over, I don’t have all day!");
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

    private void exchange(Item item,GameMap map){
        actor.addItemToInventory(item);
        subject.removeItemFromInventory(item);

        System.out.println("Q exchange Rocket body with Rocket Plans with Player");
        System.out.println("Q left with a cheery wave");
        map.removeActor(subject);
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

