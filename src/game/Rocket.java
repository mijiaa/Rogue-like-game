package game;

import edu.monash.fit2099.engine.Item;

public class Rocket extends Item {
    public Rocket(String name, char displayChar) {
        super(name, displayChar);
        newInventoryItem(name,displayChar);
    }
}
