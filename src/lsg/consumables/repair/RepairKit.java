package lsg.consumables.repair;

import lsg.consumables.Consumable;
import lsg.weapons.Weapon;

public class RepairKit extends Consumable {
    public RepairKit() {
        super("Repair Kit", 10, Weapon.DURABILITY_STAT_STRING);
    }

    @Override
    public int use(){
        int capacity = 0;
        if(getCapacity()>0){
            capacity=1;
        }
        setCapacity(getCapacity()-capacity);
        return capacity;
    }
}
