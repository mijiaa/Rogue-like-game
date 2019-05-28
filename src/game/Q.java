package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Class representing new character Q
 */
public class Q extends Actor {
    private Actor player;
    private Item rocketPlan;
    private Rocket rocketBody = new Rocket("Rocket Body" , '>');

    /**
     * Constructor.
     *{@inheritDoc}
     * @param name Name to call Q in the UI
     * @param player Character to represent Q in the UI
     *
     * Q have 100 hitPoints and are always represented with a Q
     * Q have a WanderAround behaviour that move him randomly around the map
     */
    public Q (String name, Actor player){
        super(name,'Q',2,100);
        this.player = player;
        addBehaviour(new WanderAround());
    }

    private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();

    private void addBehaviour(ActionFactory behaviour) {
        actionFactories.add(behaviour);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public Action playTurn(Actions actions, GameMap map, Display display) {

        for (ActionFactory factory : actionFactories) {
            Action action = factory.getAction(this, map);
            if(action != null){
                return action;
            }
        }
        return super.playTurn(actions,  map,  display);
    }

    /**
     *{@inheritDoc}
     *
     * This method add new Talk and GivePlans actions to player's allowable actions.
     * If player is holding valid Rocket Plans, add both Talk and GivePlans to player's allowable actions
     * else, add only Talk action
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = super.getAllowableActions(otherActor,direction,map);
        List<Item> player_items = player.getInventory();

        //This for loop assign valid Rocket Plan object to attribute rocketPlan
        for(int i = 0 ; i<player_items.size() ; i++) {
            //Rocket Plan is only considered valid if it passes the getClass, and to.String() comparison
            if (player_items.get(i).getClass() == Rocket.class & player_items.get(i).toString().equals("Rocket Plan")) {
                rocketPlan = player_items.get(i);
            }
        }

        Action talk = new Talk(this);
        Action give = new GivePlans(this ,rocketPlan, rocketBody);
        actions.clear();

        if(otherActor.getClass() ==ExtendedPlayer.class ){
            if (checkItemList(player_items)) {
                actions.add(talk);
                actions.add(give);
            }
            else {
                actions.add(talk);
            }
        }
        return actions;
    }

    /**
     * This method check if player has rocket plan in the inventory
     * @param items player's items list
     * @return true if condition is met
     */
    private Boolean checkItemList(List items){return items.contains(rocketPlan);}


}
