package lsg.consumables.drinks;

import lsg.consumables.Consumable;
import static lsg.characters.Character.STAM_STAT_STRING;

public class Drink extends Consumable {

    public Drink(String name, int capacity) {
        super(name, capacity, STAM_STAT_STRING);
    }
}
