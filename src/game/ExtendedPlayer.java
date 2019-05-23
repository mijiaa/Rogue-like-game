package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.List;

public class ExtendedPlayer extends Player {

    private ArrayList<Item> oxy_tanks = new ArrayList<>();
    private int oxygen_point =10;
    private Boolean moon= false;

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


    }


    public Action playTurn(Actions actions, GameMap map, Display display) {

        List<Item> items = this.getInventory();

        System.out.println(oxy_tanks);

        for (Item item : items) {
            if (item.hasSkill(ItemSkills.BREATH)) {
                System.out.println(item);
                oxy_tanks.add(item);
                this.removeItemFromInventory(item);
            }
        }
        if (moon){
            oxygen_point = oxygen_depletion();
            System.out.println(oxygen_point);
        }

        return showMenu(actions, display);
    }

    public int oxygen_depletion(){
        if (oxygen_point <= 0){
            oxy_tanks.remove(oxy_tanks.get(0));
            // MoveActorLocation
            if (!oxy_tanks.isEmpty()){
                return 10;
            }
        }
        return oxygen_point - 1;
    }

    public void atMoon(boolean moon) {
        this.moon = moon;
    }

}
