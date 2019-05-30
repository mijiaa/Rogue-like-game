package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.List;


/**
 * New class to represent the final boss.
 */
public class FinalBoss extends Actor {
    Actor player;

    /**
     * Constructor.
     *{@inheritDoc}
     * @param name Name to call Final Boss in the UI
     * @param player Character to represent Final bos in the UI
     *
     * Final boss has 50 hitPoints and are always represented with a Y
     * Final boss is wearing an EXOSKELECTON skill that makes him invulnerable to damage
     *
     */
    public FinalBoss (String name, Actor player){
        super(name,'Y',3,10);
        this.player = player;

        this.addSkill(ItemSkills.EXOSKELETON);

        //Final boss has a skill WIN that allows player to win after defeated
        this.addSkill(ItemSkills.WIN);
    }
    private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();

    /**
     *{@inheritDoc}
     */
    @Override
    public Action playTurn(Actions actions, GameMap map, Display display) {

        for (ActionFactory factory : actionFactories) {
            Action action = factory.getAction(this, map);
            if(action != null)
                return action;
        }
        return super.playTurn(actions,  map,  display);
    }

    /**
     *{@inheritDoc}
     * This method add ShootWater action to player's allowable actions
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = super.getAllowableActions(otherActor,direction,map);
        Boolean hasPistol = false;
        Action shoot = new ShootWater(this);

        // If final boss is wearing an exoskeleton, player cannot attack it but removing player's actions
        if(this.hasSkill(ItemSkills.EXOSKELETON)){
            actions.clear();
        }

        List<Item> items_list = otherActor.getInventory();
        // This loops checks if player has a water pistol
        if(otherActor.getClass() == ExtendedPlayer.class ){
            for (Item item : items_list) {
                if (item.hasSkill(ItemSkills.SHOOT)) {
                    hasPistol = true;
                }
            }
        }

        // If yes, add new ShootWater action to player
        if (hasPistol) {
            actions.add(shoot);
        }

        return actions;
    }
}
