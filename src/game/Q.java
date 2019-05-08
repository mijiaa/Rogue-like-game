package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.List;

public class Q extends Actor {
    private Actor player;
    private Item rocketPlan;
    private Rocket rocketBody = new Rocket("Rocket Body" , '>');

    public Q (String name, Actor player){
        super(name,'Q',2,100);
        this.player = player;
        addBehaviour(new WanderAround());
    }

    private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();

    private void addBehaviour(ActionFactory behaviour) {
        actionFactories.add(behaviour);
    }

    @Override
    public Action playTurn(Actions actions, GameMap map, Display display) {
        //System.out.println(player.getInventory());
        for (ActionFactory factory : actionFactories) {
            Action action = factory.getAction(this, map);
            if(action != null){
                return action;
            }
        }
        return super.playTurn(actions,  map,  display);
    }

    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = super.getAllowableActions(otherActor,direction,map);
        List<Item> player_items = player.getInventory();

        for(int i = 0 ; i<player_items.size() ; i++) {
            if (player_items.get(i).getClass() == Rocket.class & player_items.get(i).toString() == "Rocket Plan") {
                rocketPlan = player_items.get(i);
            }
        }

        Action talk = new Talk(player,this);
        Action give = new GivePlans(player, this ,rocketPlan, rocketBody);
        actions.clear();

        if(otherActor.getClass() ==Player.class ){
            if (checkItem(player_items)) {
                actions.add(talk);
                actions.add(give);
            }
            else {
                actions.add(talk);
            }
        }
        return actions;
    }

    private Boolean checkItem(List items){ if (items.contains(rocketPlan)) return true; return false;}


}
