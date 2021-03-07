package lsg;

import lsg.characters.Hero;
import lsg.characters.Monster;
import lsg.weapons.ShotGun;
import lsg.weapons.Sword;
import lsg.weapons.Weapon;

public class LearningSoulsGame {
    public static void main(String[] args) {
        Hero h = new Hero();
        h.printStats();
        /*System.out.println(h); //appel toString par défaut
        Hero h2 = new Hero("Marionapator");
        h2.printStats();
        for (int i=0;i<=5;i++){
            Monster m = new Monster();
            m.printStats();
        }*/

        System.out.print("----------------------------------------------------------------------------------------\n");
        Weapon sword = new Sword();
        while(h.getStamina() > 0){
            h.printStats();
            h.setWeapon(sword);
            int damage = h.attack();
            System.out.println("attaque avec "+ sword.getName() +" (min:"+ sword.getMinDamage()+ " max: " + sword.getMaxDamage() + " stam:" + sword.getStamCost() + " dur:"+ sword.getDurability()+") > "+ damage);
        }
        if (h.getStamina() == 0){
            h.printStats();
        }

        System.out.print("----------------------------------------------------------------------------------------\n");

        Monster m3 = new Monster();
        while(m3.getStamina() > 0){
            m3.printStats();
            m3.setWeapon(sword);
            int damage = m3.attack();
            System.out.println("attaque avec "+ sword.getName() +" (min:"+ sword.getMinDamage()+ " max: " + sword.getMaxDamage() + " stam:" + sword.getStamCost() + " dur:"+ sword.getDurability()+") > "+ damage);
        }
        if (m3.getStamina() == 0){
            m3.printStats();
        }

        System.out.print("----------------------------------------------------------------------------------------\n");

        Hero rick = new Hero("Rick");
        Monster zombie = new Monster("Zombie");
        Weapon shotgun = new ShotGun();
        rick.setWeapon(shotgun);
        while(zombie.isAlive() && rick.getStamina() > 0){
            rick.printStats();
            zombie.printStats();
            System.out.println("!!!" +rick.getName()+" attack " +zombie.getName()+" with " +rick.getWeapon().getName()+" (" +rick.attack()+") !!! -> Effective DMG:000" +zombie.getHitWith(rick.attack())+"PV");
        }
        if (!zombie.isAlive()){
            rick.printStats();
            zombie.printStats();
        }
    }
}
