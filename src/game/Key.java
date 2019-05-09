package game;

/**
 * Class representing a key type item
 * Inherited from Item class to have its properties and methods
 */
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.Item;

public class Key extends Item{
    public Key(String name, char displayChar){
        super(name, displayChar);
    }

    /***
     *
     * @param name
     * @param displayChar
     * @return a key that has the drop action, suitable for placing in an inventory.
     */
    public static Key newInventoryItem(String name, char displayChar)
    {
        Key key = new Key(name, displayChar);
        key.allowableActions.clear();
        key.allowableActions.add(new DropItemAction(key));
        return key;
    }
}
