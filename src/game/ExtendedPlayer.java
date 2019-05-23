package game;

import edu.monash.fit2099.engine.*;

import java.util.List;

public class ExtendedPlayer extends Player {

    private List<OxygenTank> oxy_tanks;
    /**
     * Constructor.
     *
     * @param name Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param priority How early in the turn the player can act
     * @param hitPoints Player's starting number of hitpoints
     */
    public ExtendedPlayer(String name, char displayChar, int priority, int hitPoints) {
        super(name, displayChar, priority, hitPoints);

        List<Item> items = this.getInventory();

        for (Item item:items){
            if (item.hasSkill(ItemSkills.BREATH)){
                oxy_tanks.add( (OxygenTank) item);
            }
        }
    }


    public Action playTurn(Actions actions, GameMap map, Display display) {

        for (OxygenTank oxy_t : oxy_tanks){
            oxy_t.oxygen_depletion();
        }

        return showMenu(actions, display);
    }

}
