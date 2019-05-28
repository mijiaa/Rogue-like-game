package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.List;

public class FinalBoss extends Actor {
    Actor player;
    public FinalBoss (String name, Actor player){
        super(name,'Y',3,100);
        this.player = player;
//        addBehaviour(new WanderAround());
        this.addSkill(ItemSkills.EXOSKELETON);
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
