package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.List;

public class MiniBoss extends Actor{

    // Grunts have 50 hitpoints and are always represented with a g
    public MiniBoss(String name,Actor player) {
        super(name, 'D',2, 25);;
        addBehaviour(new StayStill());
    }

    private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();

    private void addBehaviour(ActionFactory behaviour) {
        actionFactories.add(behaviour);
    }

    @Override
    public Action playTurn(Actions actions, GameMap map, Display display) {
//        System.out.println(this.getInventory());
        for (ActionFactory factory : actionFactories) {
            Action action = factory.getAction(this, map);
            System.out.println(action);
            if(action != null)
                return action;
        }



        return super.playTurn(actions,  map,  display);
    }

}
