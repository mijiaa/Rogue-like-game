package game;

import edu.monash.fit2099.engine.Item;

/**
 * Class representing a SpaceSuit type item
 * Inherited from Item class to have its properties and methods
 */
public class SpaceSuit extends Item {
    /***
     *
     * @param name represents the name of the SpaceSuit
     * @param displayChar represents the display character of SpaceSuit on map
     *
     */
    public SpaceSuit(String name, char displayChar) {
        super(name, displayChar);
        this.addSkill(ItemSkills.WALKONMOON);
    }
}
