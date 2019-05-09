package game;

import edu.monash.fit2099.engine.*;

/**
 *New action that allow player to give Rocket plans to Q
 */
public class GivePlans extends Action {

    private Actor subject_Q;
    private Item item1;
    private Item item2;

    /**
     * Constructor.
     *
     * @param subject represent Q
     * @param item1 represent Rocket plans
     * @param item2 represent Rocket body
     */

    public GivePlans (Actor subject, Item item1, Item item2) {
        ;
        this.subject_Q = subject;
        this.item1 = item1;
        this.item2 = item2;
    }

    /**
     * {@inheritDoc}
     *
     * If player has rocket plans, give them to Q.
     * This will cause the plans to be removed and replaced by the rocket body.
     * Q will then be removed from the game
     *
     * Perform the Action.
     */

    public String execute(Actor actor, GameMap map) {
        System.out.println(actor.getInventory());
        actor.removeItemFromInventory(item1);
        subject_Q.removeItemFromInventory(item2);
        actor.addItemToInventory(item2);
        map.removeActor(subject_Q);
        return "Q left with a cheery wave, player obtained "+ item2.toString();

    }

    /**
     *{@inheritDoc}
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " give Rocket Plans to " + subject_Q;
    }

    /**
     *{@inheritDoc}
     * @return hotKey as 10 as a string on menu
     */
    @Override
    public String hotKey() {
        return "10";
    }
}
