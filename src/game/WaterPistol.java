package game;

import edu.monash.fit2099.engine.Item;

/**
 * Class representing a WaterPistol type item
 * Inherited from Item class to have its properties and methods
 */
public class WaterPistol extends Item {

    /***
     *
     * @param name
     * @param displayChar
     * WaterPistol object created has 2 default skills, UNLOADED and SHOOT
     */
    public WaterPistol(String name, char displayChar) {
        super(name, displayChar);
        this.addSkill(ItemSkills.UNLOADED);
        this.addSkill(ItemSkills.SHOOT);
    }
}
