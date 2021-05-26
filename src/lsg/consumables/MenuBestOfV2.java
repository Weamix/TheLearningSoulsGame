package lsg.consumables;

import lsg.consumables.drinks.Coffee;
import lsg.consumables.drinks.Whisky;
import lsg.consumables.drinks.Wine;
import lsg.consumables.food.American;
import lsg.consumables.food.Hamburger;

import java.util.HashSet;
import java.util.Iterator;

public class MenuBestOfV2 {
    private HashSet<Consumable> menu =new HashSet();

    public MenuBestOfV2() {
        menu.add(new Hamburger());
        menu.add(new Wine());
        menu.add(new American());
        menu.add(new Coffee());
        menu.add(new Whisky());
    }

    @Override
    public String toString() {
        StringBuilder m = new StringBuilder("MenuBestOfV2 :\n");
        Iterator<Consumable> i=menu.iterator();
        int compteur = 1;
        while(i.hasNext()) {
            m.append(compteur).append(" : ").append(i.next().toString()).append("\n");
            compteur++;
        }
        return m.toString();
    }

    public static void main(String[] args){
        MenuBestOfV2 menu = new MenuBestOfV2();
        System.out.println(menu.toString());
    }
}
