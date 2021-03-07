package lsg;

import lsg.characters.Hero;
import lsg.characters.Monster;
import lsg.weapons.Sword;
import lsg.weapons.Weapon;

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
        for (int i=0;i<=5;i++){
            Weapon sword = new Sword();
            h.printStats();
            h.setWeapon(sword);
            int damage = h.attack();
            System.out.println("attaque avec "+ h.getName() +" (min:"+ sword.getMinDamage()+ " max: " + sword.getMaxDamage() + " stam:" + sword.getStamCost() + " dur:"+ sword.getDurability()+") > "+ damage);
        }

        System.out.print("----------------------------------------------------------------------------------------\n");

        Monster m3 = new Monster();
        for (int i=0;i<=5;i++){
            Weapon sword = new Sword();
            m3.printStats();
            m3.setWeapon(sword);
            int damage = m3.attack();
            System.out.println("attaque avec "+ h.getName() +" (min:"+ sword.getMinDamage()+ " max: " + sword.getMaxDamage() + " stam:" + sword.getStamCost() + " dur:"+ sword.getDurability()+") > "+ damage);
        }
    }
}
