package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.MoveActorAction;

/**
 * Class representing a OxygenTank type item
 * Inherited from Item class to have its properties and methods
 */
public class OxygenTank extends Item {
    int oxygen_point = 10;

    public OxygenTank(String name, char displayChar) {
        super(name, displayChar);
        this.addSkill(ItemSkills.BREATH);
    }

    public int oxygen_depletion(){
        System.out.println(oxygen_point);
        if (oxygen_point == 0){
            return -1;
        }
        return oxygen_point - 1;
    }

//    public Action check_oxygen_points(int point){
//        if (point<0){
//            return new MoveActorAction();
//        }
//        return null;
//
//    }
}
