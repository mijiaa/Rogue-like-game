package game;

import edu.monash.fit2099.engine.*;

/**
 *New behaviour that stops movements of Actor
 */
public class StayStill implements ActionFactory {

    public StayStill(){}

    /**
     * {@inheritDoc}
     *
     * This method skip the actor's turn, retrict it from perfroming any action
     * @return new instantiated SkipTurnAction
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        return new SkipTurnAction();

    }
}
