package game;

import edu.monash.fit2099.engine.*;
import game.BuildRocket;
import game.GetOxygenTank;

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
     *This method check if player has Rocket Body and RocketEngine
     *and add new BuildRocket action to player
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = new Actions();
        Action getOxygenTankAction = new GetOxygenTank();
        actions.add(getOxygenTankAction);

        return actions;
    }
}

