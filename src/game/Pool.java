package game;

import edu.monash.fit2099.engine.*;

public class Pool extends Ground {
    /**
     * Constructor.
     *{@inheritDoc}
     */
    public Pool() {
        super('P');
    }

    /**
     *{@inheritDoc}
     * Do not let an actor enter
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
     *This method check if player has Gun
     *and add water Bullet for player
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {

        Actions actions = new Actions();
        Action refill = new RefillWaterPistol();
        actions.add(refill);

        return actions;
    }
}
