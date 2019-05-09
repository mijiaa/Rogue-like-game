package game;

import edu.monash.fit2099.engine.*;

import java.util.List;
import java.util.Random;

/**
 * New Action that lets player talks to Q
 */
public class Talk extends Action {
    private Actor subject;

    /**
     * Constructor.
     *
     * @param subject represent Q
     */
    public Talk( Actor subject) {
        this.subject = subject;
    }

    /**
     * {@inheritDoc}
     *
     * If player talks to Q without having rocket plans,
     * Q replies “I can give you something that will help, but I’m going to need the plans.”
     * If player talks to Q and have rocket plans,
     * Q replies “Hand them over, I don’t have all day!”
     *
     * Perform the Action.
     *
     * @return Q's replies as String
     */

    public String execute(Actor actor, GameMap map) {
        List<Item> player_items = actor.getInventory();
        for(int i = 0 ; i<player_items.size() ; i++) {
            if (player_items.get(i).getClass() == game.Rocket.class & player_items.get(i).toString().equals( "Rocket Plan")) {
                return "Hand them over, I don’t have all day!";
            }
        }

        return "I can give you something that will help, but I’m going to need the plans.";
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " talks to " + subject;
    }

    /**
     *{@inheritDoc}
     * @return hotKey as 9 as a string on menu
     */
    @Override
    public String hotKey() {
        return "9";
    }
}

