package game;

import edu.monash.fit2099.engine.*;



public class GetOxygenTank extends Action {

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

        OxygenTank oxygenTank = new OxygenTank("Oxygen Tank",'T');
        Location location = map.locationOf(actor);
        if (location.getItems().isEmpty()){
            location.addItem(oxygenTank);
            return "Oxygen tank produced";
        }

//        map.addItem(oxygenTank,map.locationOf(actor).x(),map.locationOf(actor).y());

        else { return "No Action ";}
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
     * @return hotKey as 0 as a string on menu
     */
    @Override
    public String hotKey() {
        return "0";
    }
}

