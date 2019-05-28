package game;


import edu.monash.fit2099.engine.Item;
/**
 * Class representing a OxygenTank type item
 * Inherited from Item class to have its properties and methods
 */
public class OxygenTank extends Item {


    public OxygenTank(String name, char displayChar) {
        super(name, displayChar);
        this.addSkill(ItemSkills.BREATH);
    }
}
