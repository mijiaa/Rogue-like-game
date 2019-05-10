package game;

import edu.monash.fit2099.engine.*;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 *New behaviour that allow actor to wander around map
 */
public class WanderAround implements ActionFactory {
    public WanderAround(){}

    /**
     * {@inheritDoc}
     *
     * This method let actor moves randomly on the game map
     * by giving the actor a random direction each turn
     * @return new instantiated MoveActorAction
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);

        //get a list of the 4 Exits
        List<Exit> lst_exit = here.getExits() ;

        //get a random number from of 0 to 3
        int randomNum = ThreadLocalRandom.current().nextInt(0, lst_exit.size());

        //the number will then be used as an index to access lst_exit and get a random exit
        Exit exit = lst_exit.get(randomNum);

        //get the destination of the chosen exit
        Location destination = exit.getDestination();

        //check if the exit is valid, if yes, move actor, else get a new destination
        //exit is only valid when actor can pass through, and does not contain any other actor
        while (!destination.canActorEnter(actor) || destination.containsActor()) {
            randomNum = ThreadLocalRandom.current().nextInt(0, lst_exit.size());
            exit = lst_exit.get(randomNum);
            destination = exit.getDestination();
        }
        return new MoveActorAction(destination, exit.getName());
    }
}
