package game;

import edu.monash.fit2099.engine.*;

import java.util.List;
import java.util.Random;


/**
 * New Action class to allow player to shoot water at Final boss
 */
public class ShootWater extends Action {

    private Actor boss;
    private Item w_pistol;
    private static final int PROB_HIT_MISS = 7;

    /**
     * Constructor.
     *
     * @param boss represents Final Boss object
     */
    public ShootWater(Actor boss) {
        this.boss = boss;
    }

    /**
     * {@inheritDoc}
     *
     * Shooting water from a full water pistol onto Yugo Maxx has a 0.7 probability of destroying his
     * exoskeleton, making him vulnerable.
     * It empties the water pistol whether it hits or misses.
     *
     * Perform the Action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Boolean pistol_loaded = false;
        List<Item> items_list = actor.getInventory();

        Random rand = new Random();
        //generate a random number in the range of 0-9
        int prob = rand.nextInt(10);

        //checks if player has pistol and whether it is loaded
        for (Item item : items_list) {
            if (item.hasSkill(ItemSkills.LOADED)) {
                w_pistol = item;
                pistol_loaded = true;
            }
        }

        // if random number generated lower than the probability,
        // Final boss's EXOSKELETON is removed
        // Pistol is emptied
        if (prob <= PROB_HIT_MISS  && pistol_loaded) {
            boss.removeSkill(ItemSkills.EXOSKELETON);
            w_pistol.removeSkill(ItemSkills.LOADED);
            w_pistol.addSkill(ItemSkills.UNLOADED);
            return "pew pew, Exoskeleton Destroyed";
        }
        // if random number generated higher than the probability,,
        // Player missed, pistol is emptied
        else if (prob > PROB_HIT_MISS && pistol_loaded) {
            w_pistol.removeSkill(ItemSkills.LOADED);
            w_pistol.addSkill(ItemSkills.UNLOADED);
            return "You missed, pistol is now emptied";


        } else {
            return "Pistol is not loaded";
        }
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Shoot Water";
    }

    /**
     *{@inheritDoc}
     * @return hotKey as S as a string on menu
     */
    @Override
    public String hotKey() {
        return "S";
    }
}

