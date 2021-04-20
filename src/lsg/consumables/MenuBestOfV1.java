package lsg.consumables;

import lsg.consumables.drinks.Coffee;
import lsg.consumables.drinks.Whisky;
import lsg.consumables.drinks.Wine;
import lsg.consumables.food.American;
import lsg.consumables.food.Hamburger;

public class MenuBestOfV1 {
    private Consumable[] menu = new Consumable[]{new Hamburger(),new Wine(),new American(), new Coffee(), new Whisky()};

    @Override
    public String toString() {
        StringBuilder m = new StringBuilder("MenuBestOfV1 :\n");
        for (int i=1; i<=menu.length;i++){
            m.append(i).append(" : ").append(menu[i - 1].toString()).append("\n");
        }
        return m.toString();
    }

    public static void main(String args[]){
        MenuBestOfV1 menu = new MenuBestOfV1();
        System.out.println(menu.toString());
    }
}
