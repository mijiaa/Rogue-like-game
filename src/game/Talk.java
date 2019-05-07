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
        List<Item> player_items = actor.getInventory();
        for(int i = 0 ; i<player_items.size() ; i++) {
            if (player_items.get(i).getClass() == game.Rocket.class & player_items.get(i).toString() == "Rocket Plan"){
                return "Hand them over, I don’t have all day!";

                } else {
                    return "I can give you something that will help, but I’m going to need the plans.";
                }
            }

        return null;
    }


    @Override
    public String menuDescription(Actor actor) {
        return actor + " talks to " + subject;
    }

    @Override
    public String hotKey() {
        return "10";
    }
}

