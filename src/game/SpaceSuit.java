package game;

import edu.monash.fit2099.engine.Item;

/**
 * Class representing a SpaceSuit type item
 * Inherited from Item class to have its properties and methods
 */
public class SpaceSuit extends Item {
    /***
     *
     * @param name
     * @param displayChar
     *
     */
    public SpaceSuit(String name, char displayChar) {
        super(name, displayChar);
        this.addSkill(ItemSkills.WALKONMOON);
    }
}
