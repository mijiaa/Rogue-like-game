package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;

import java.util.ArrayList;
import java.util.List;


public class Door extends Ground {
    public Door () { super('|');
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        List<Item> item = actor.getInventory();

        for (int i =0; i<item.size(); i++){
            if (item.get(i).getClass() == game.Key.class) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean blocksThrownObjects() {
        return true;
    }

}
