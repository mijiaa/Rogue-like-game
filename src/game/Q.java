package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.List;

public class Q extends Actor {
    private Actor player;
    Rocket rocketPlan = new Rocket("Rocket Plan",'-');
    Rocket rocketBody = new Rocket("Rocket Body" , '>');

    public Q (String name, Actor player){
        super(name,'Q',10,20);
        this.player = player;
//        addBehaviour(new WanderAround());

    }

    private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();

    private void addBehaviour(ActionFactory behaviour) {
        actionFactories.add(behaviour);
    }

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

    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions act = super.getAllowableActions(otherActor,direction,map);
        Action talk = new Talk(player,this);
        Action give = new GivePlans(player, this ,rocketPlan, rocketBody);
        act.clear();
        if(otherActor instanceof  Player){
            act.add(talk);
            act.add(give);
        }

        return act;
    }
}
