package game;

import edu.monash.fit2099.engine.*;

public class Pool extends Ground {
    /**
     * Constructor.
     *{@inheritDoc}
     */
    public Pool() {
        super('*');
    }

    /**
     *{@inheritDoc}
     * Do not let an actor walk aon water
     * @return false
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public boolean blocksThrownObjects() {
        return true;
    }

    /**
     * {@inheritDoc}
     * This method add RefillWaterPistol action to the allowable actions
     * @return actions
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {

        Actions actions = new Actions();
        Action refill = new RefillWaterPistol();
        actions.add(refill);

        return actions;
    }
}
