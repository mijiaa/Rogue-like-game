package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.List;

public class MiniBoss extends Actor{
    Actor player;
    // Grunts have 50 hitpoints and are always represented with a g
    public MiniBoss(String name,Actor player) {
        super(name, 'D',2, 10);;
        this.player = player;
        addBehaviour(new StayStill());
    }

    private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();

    private void addBehaviour(ActionFactory behaviour) {
        actionFactories.add(behaviour);
    }

    @Override
    public Action playTurn(Actions actions, GameMap map, Display display) {
        actions.clear();
        Location loc_actor = map.locationOf(this);
        Location loc_player = map.locationOf(player);
        int count = 0;
        if (distance(loc_actor,loc_player) == 1 & count == 0){
            actionFactories.clear();
//
//            actions.add(new DropItemAction(this.getInventory().get(0)));
//            actions.add(new AttackAction(this,player));
//            actions.add(new DropItemAction(this.getInventory().get(0)));
            count += 1;
        }

//        if (super.isConscious()) {
        for (ActionFactory factory : actionFactories) {
            Action action = factory.getAction(this, map);
            System.out.println(factory.getAction(this, map));
//            if (action != null)
            if (action instanceof DropItemAction ) {
                System.out.println("DROP");
                if (!super.isConscious()) {
                    return action;
                }
            }else{
                return action;}
//            }
        }
//        }
//        else{
//            System.out.println("hi");
//            return new DropItemAction(this.getInventory().get(0));
//        }
        return super.playTurn(actions,  map,  display);

    }

    private int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }
}
