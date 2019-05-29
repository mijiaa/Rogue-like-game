package game;

import edu.monash.fit2099.engine.*;
import game.BuildRocket;
import game.GetOxygenTank;

/**
 * Class representing an OxygenDispenser
 */
public class OxygenDispenser extends Ground {
    GameMap map ;
    /**
     * Constructor.
     *{@inheritDoc}
     */
    public OxygenDispenser() {
        super('O');
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
     * This method add GetOxygenTank action to actor's allowable action
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = new Actions();
        Action getOxygenTankAction = new GetOxygenTank();
        actions.add(getOxygenTankAction);

        return actions;
    }
}

