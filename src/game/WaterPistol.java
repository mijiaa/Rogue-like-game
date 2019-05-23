package game;

import edu.monash.fit2099.engine.Item;

public class WaterPistol extends Item {

    public WaterPistol(String name, char displayChar) {
        super(name, displayChar);
        this.addSkill(ItemSkills.UNLOADED);
        this.addSkill(ItemSkills.SHOOT);
    }
}
