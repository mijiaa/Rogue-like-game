package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.List;

public class FinalBoss extends Actor {
    Actor player;
    public FinalBoss (String name, Actor player){
        super(name,'Y',3,5);
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

    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = super.getAllowableActions(otherActor,direction,map);
        Boolean hasGun = false;
        Action shoot = new ShootWater(this);
        System.out.println(this.hasSkill(ItemSkills.EXOSKELETON));
        if(this.hasSkill(ItemSkills.EXOSKELETON)){
            actions.clear();
        }

        List<Item> items_list = otherActor.getInventory();
        if(otherActor.getClass() == ExtendedPlayer.class ){
            for (Item item : items_list) {
                if (item.hasSkill(ItemSkills.SHOOT)) {
                    hasGun = true;
                }
            }
        }

        if (hasGun) {
            actions.add(shoot);
        }


        return actions;
    }


}
