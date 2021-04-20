package lsg.consumables;

import lsg.consumables.drinks.Coffee;
import lsg.consumables.drinks.Whisky;
import lsg.consumables.drinks.Wine;
import lsg.consumables.food.American;
import lsg.consumables.food.Hamburger;

import java.util.HashSet;
import java.util.Iterator;

public class MenuBestOfV3 extends HashSet<Consumable> {

    public MenuBestOfV3() {
        add(new Hamburger());
        add(new Wine());
        add(new American());
        add(new Coffee());
        add(new Whisky());
    }

    @Override
    public String toString() {
        StringBuilder m = new StringBuilder("MenuBestOfV3 :\n");
        Iterator<Consumable> i=iterator();
        int compteur = 1;
        while(i.hasNext()) {
            m.append(compteur).append(" : ").append(i.next().toString()).append("\n");
            compteur++;
        }
        return m.toString();
    }

    public static void main(String args[]){
        MenuBestOfV3 menu = new MenuBestOfV3();
        System.out.println(menu.toString());
    }
}
