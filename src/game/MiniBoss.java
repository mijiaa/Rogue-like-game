package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class representing new character MiniBoss
 */
public class MiniBoss extends Actor{
    private Actor player;
    private StayStill stayStill = new StayStill();

    /**
     * Constructor.
     *{@inheritDoc}
     * @param name Name to call Goon in the UI
     * @param player Character to represent Goon in the UI
     *
     * MiniBoss have 25 hitpoints and are always represented with a D
     * MiniBoss have a StayStill behaviour that retrict him from moving at all
     */
    public MiniBoss(String name,Actor player) {
        super(name, 'D',3, 25);
        this.player = player;
        addBehaviour(stayStill);
    }

    private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();

    private void addBehaviour(ActionFactory behaviour) {
        actionFactories.add(behaviour);
    }


    /**
     *{@inheritDoc}
     *
     * MiniBoss have half the damage of a Grunt
     * @return a freshly-instantiated IntrinsicWeapon.
     */
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(3, "punches");
    }

    /**
     *{@inheritDoc}
     * This method return an action randomly for MiniBoss from the actions list if player is near him.
     * Only allow MiniBoss to perform AttackAction
     */
    @Override
    public Action playTurn(Actions actions, GameMap map, Display display) {
        int index = actionFactories.indexOf(stayStill);

        //get the location of player and MiniBoss
        Location loc_actor = map.locationOf(this);
        Location loc_player = map.locationOf(player);

        //generating a random number and use it as an index to get the next action from actions list
        int randomNum = ThreadLocalRandom.current().nextInt(0, actions.size());
        Action currentAction = actions.get(randomNum);

        //if player is 1 step away from MiniBoss,
        //call returnValidAction to verify currentAction
        if (near(distance(loc_actor,loc_player))){
            return returnValidAction(currentAction);
        }
        else {

            //return StayStill behaviour when player is not around MiniBoss
            return actionFactories.get(index).getAction(this,map);
        }
    }

    /**
     *
     * This method calculates the distance of two locations
     * @param a current location of actor
     * @param b current location of player
     * @return the distance as integer
     */
    private int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }

    /**
     * This method checks if player is 1 step away of the actor(consider as "near")
     * @param distance is the distance of actor and player
     * @return true if condition is met
     */
    private boolean near(int distance) { return distance == 1;}

    /**
     * This method skip MoveActorAction, SkipTurnAction, DropItemAction of the actor
     * only allows AttackAction
     * @param currentAction Action that the actor will carry out next
     * @return new instantiated AttackAction
     */
    private Action returnValidAction(Action currentAction){
        if (currentAction.getClass() == MoveActorAction.class || currentAction.getClass() == SkipTurnAction.class
                || currentAction.getClass() == DropItemAction.class) {
            return new AttackAction(this,player);
        } else {
            return currentAction;
        }
    }

}
