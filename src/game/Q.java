package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.List;

public class Q extends Actor {
    private Actor player;
    public Q (String name, Actor player){
        super(name,'Q',2,50);
        this.player = player;
        addBehaviour(new WanderAround());
//        Actions action = new Actions();
//        action.add(new Talk(this,player));
//        addBehaviour(new GivePlans(player) );
//        addBehaviour(new Talk (player) );

    }

    private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();

    private void addBehaviour(ActionFactory behaviour) {
        actionFactories.add(behaviour);
    }

    @Override
    public Action playTurn(Actions actions, GameMap map, Display display) {
        for (ActionFactory factory : actionFactories) {
            Action action = factory.getAction(this, map);
            if(action != null)
                return action;
        }

        return super.playTurn(actions,  map,  display);
    }

}
