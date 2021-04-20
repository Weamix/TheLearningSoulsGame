package lsg.consumables.food;

import lsg.consumables.Consumable;

import static lsg.characters.Character.LIFE_STAT_STRING;

public class Food extends Consumable {
    public Food(String name, int capacity) {
        super(name, capacity, LIFE_STAT_STRING);
    }
}
