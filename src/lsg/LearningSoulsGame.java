package lsg;

import lsg.characters.Hero;
import lsg.characters.Monster;
import lsg.weapons.Sword;

public class LearningSoulsGame {
    public static void main(String[] args) {
        Hero h = new Hero();
        h.printStats();
        System.out.println(h); //appel toString par défaut
        Hero h2 = new Hero("Marionapator");
        h2.printStats();
        for (int i=0;i<=5;i++){
            Monster m = new Monster();
            m.printStats();
        }
        System.out.print("----------------------------------------------------------------------------------------\n");

        Sword s = new Sword();
        for (int i=0;i<=5;i++){
            h.printStats();
            int damage = h.attackWith(s);
            System.out.println("attaque avec "+ h.getName() +" (min:"+ s.getMinDamage()+ " max: " + s.getMaxDamage() + " stam:" + s.getStamCost() + " dur:"+ s.getDurability()+") > "+ damage);
        }
    }
}
