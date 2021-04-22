package lsg.consumables;

import lsg.consumables.drinks.Coffee;
import lsg.consumables.drinks.Whisky;
import lsg.consumables.drinks.Wine;
import lsg.consumables.food.American;
import lsg.consumables.food.Hamburger;
import lsg.consumables.repair.RepairKit;

import java.util.Iterator;
import java.util.LinkedHashSet;

public class MenuBestOfV4 extends LinkedHashSet<Consumable> {

    public MenuBestOfV4() {
        add(new Hamburger());
        add(new Wine());
        add(new American());
        add(new Coffee());
        add(new Whisky());
        add(new RepairKit());
    }

    @Override
    public String toString() {
        StringBuilder m = new StringBuilder("MenuBestOfV4 :\n");
        Iterator<Consumable> i=iterator();
        int compteur = 1;
        while(i.hasNext()) {
            m.append(compteur).append(" : ").append(i.next().toString()).append("\n");
            compteur++;
        }
        return m.toString();
    }

    public static void main(String[] args){
        MenuBestOfV4 menu = new MenuBestOfV4();
        System.out.println(menu.toString());
    }
}
