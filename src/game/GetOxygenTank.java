package game;

import edu.monash.fit2099.engine.*;

import java.util.List;

public class GetOxygenTank extends Action {
    private Integer turn;
    private Display printResult = new Display();
    /**
     * {@inheritDoc}
     *
     *
     *
     * Perform the Action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
//        setTurn(turn);
        Item oxygenTank = new OxygenTank("Oxygen Tank",'T');
        Location location = map.locationOf(actor);

        if (location.getItems().isEmpty()){
            location.addItem(oxygenTank);}

//        map.addItem(oxygenTank,map.locationOf(actor).x(),map.locationOf(actor).y());

        return "producing oxygen tank ...";
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Press Button Oxygen Dispenser";
    }

    /**
     *{@inheritDoc}
     * @return hotKey as 12 as a string on menu
     */
    @Override
    public String hotKey() {
        return "12";
    }

    public void setTurn(int turn){
        this.turn = turn;
    }

    public int getTurn(){return turn;}
}

