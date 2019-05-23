package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

import java.util.List;

public class RefillWaterPistol extends Action {

    public RefillWaterPistol(){}

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

    @Override
    public String menuDescription(Actor actor) {

        return "Refill water Pistol";
    }

    @Override
    public String hotKey() {

        return "13";
    }

}
