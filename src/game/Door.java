package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import java.util.List;

/**
 * Class representing a Door
 */
public class Door extends Ground {

    /**
     * Constructor.
     *{@inheritDoc}
     */
    public Door () { super('|'); }

    /**
     * {@inheritDoc}
     *Only passable if condition are met.
     *Conditions : actor has a key item
     *
     * @return true if condition is met
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        if (actor.hasSkill(ItemSkills.UNLOCK)){
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean blocksThrownObjects() {
        return true;
    }
}
