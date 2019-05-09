package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MiniBoss extends Actor{
    private Actor player;
    private StayStill stayStill = new StayStill();

    public MiniBoss(String name,Actor player) {
        super(name, 'D',3, 25);;
        this.player = player;
        addBehaviour(stayStill);
    }

    private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();

    private void addBehaviour(ActionFactory behaviour) {
        actionFactories.add(behaviour);
    }

    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(5, "punches");
    }

    @Override
    public Action playTurn(Actions actions, GameMap map, Display display) {
        System.out.println(player.getInventory());
        int index = actionFactories.indexOf(stayStill);
        Location loc_actor = map.locationOf(this);
        Location loc_player = map.locationOf(player);
        int randomNum = ThreadLocalRandom.current().nextInt(0, actions.size());
        Action currentAction = actions.get(randomNum);

        if (near(distance(loc_actor,loc_player))){
            return returnValidAction(currentAction);
        }
        else {
            return actionFactories.get(index).getAction(this,map);
        }
    }

    private int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }

    private boolean near(int distance) {
        if (distance == 1)
            return true;
        return false;}

    private Action returnValidAction(Action currentAction){
        if (currentAction.getClass() == MoveActorAction.class || currentAction.getClass() == SkipTurnAction.class
                || currentAction.getClass() == DropItemAction.class) {
            return new AttackAction(this,player);
        } else {
            return currentAction;
        }
    }

}
