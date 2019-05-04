package game;

import edu.monash.fit2099.engine.Item;

public class Key extends Item {
    public Key(String name, char displayChar){
        super(name, displayChar);

        newInventoryItem(name,displayChar);
    }

}
