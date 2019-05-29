package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

import java.util.List;

/**
 * New Action that lets player refills water pisotl
 */
public class RefillWaterPistol extends Action {

    public RefillWaterPistol(){}


    /**
     * {@inheritDoc}
     *
     * If pistol is empty, refill water by adding skill LOADED to water pistol
     * Perform the Action.
     *
     * @return string
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        List<Item> items  = actor.getInventory();

        for (Item item: items){
            if (item.hasSkill(ItemSkills.UNLOADED)){
                item.removeSkill(ItemSkills.UNLOADED);
                item.addSkill(ItemSkills.LOADED);
                System.out.println(item.getClass());
                return "Your pistol is refilled";
            }
        }
        return "You need a pistol";
    }

    /**
     *{@inheritDoc}
     * @return String show on menu
     */
    @Override
    public String menuDescription(Actor actor) {

        return "Refill water Pistol";
    }

    /**
     *{@inheritDoc}
     * @return hotKey as R as a string on menu
     */
    @Override
    public String hotKey() {

        return "R";
    }

}
