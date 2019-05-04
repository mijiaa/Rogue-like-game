package game;

import edu.monash.fit2099.engine.*;

public class StayStill implements ActionFactory {

    public StayStill(){}

    @Override
    public Action getAction(Actor actor, GameMap map) {

        Location here = map.locationOf(actor);
        map.removeActor(actor);
        return new MoveActorAction(here, "no where");

    }
}
