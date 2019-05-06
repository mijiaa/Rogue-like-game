package game;

import edu.monash.fit2099.engine.*;

import java.util.List;

import java.util.concurrent.ThreadLocalRandom;

public class WanderAround implements ActionFactory {
    public WanderAround(){}

    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);
        List<Exit> lst_exit = here.getExits() ;
        int randomNum = ThreadLocalRandom.current().nextInt(0, lst_exit.size());
        Exit exit = lst_exit.get(randomNum);
        Location destination = exit.getDestination();

        if (!destination.canActorEnter(actor) & !map.isAnActorAt(destination)) {
            randomNum = ThreadLocalRandom.current().nextInt(0, lst_exit.size());
            exit = lst_exit.get(randomNum);
            destination = exit.getDestination();
        }


        return new MoveActorAction(destination, exit.getName());
    }


}
