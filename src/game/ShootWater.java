package game;

import edu.monash.fit2099.engine.*;

import java.util.List;
import java.util.Random;

public class ShootWater extends Action {

    Actor boss;
    Item w_pistol;

    public ShootWater(Actor boss) {
        this.boss = boss;

    }

    /**
     * {@inheritDoc}
     *
     * Player places both Rocket Body and Rocket Engine on Rocket Pad
     * and build and obtain a Rocket
     *
     * Perform the Action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Boolean loaded = false;
        List<Item> items_list = actor.getInventory();
        Random rand = new Random();
        int prob = rand.nextInt(10);

        for (Item item : items_list) {
            if (item.hasSkill(ItemSkills.LOADED)) {
                w_pistol = item;
                loaded = true;
            }
        }
        if (prob <= 7 && loaded) {
            boss.removeSkill(ItemSkills.EXOSKELETON);
            w_pistol.removeSkill(ItemSkills.LOADED);
            w_pistol.addSkill(ItemSkills.UNLOADED);
            return "pew pew, Exoskeleton Destroyed";
        } else if (prob > 7 && loaded) {
            w_pistol.removeSkill(ItemSkills.LOADED);
            w_pistol.addSkill(ItemSkills.UNLOADED);
            return "You missed";
        } else {
            return "Gun not loaded";
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
     * @return hotKey as 11 as a string on menu
     */
    @Override
    public String hotKey() {
        return "S";
    }
}

