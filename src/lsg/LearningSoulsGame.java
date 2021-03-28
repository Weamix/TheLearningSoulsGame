package lsg;

import lsg.armor.BlackWitchVeil;
import lsg.armor.RingedKnightArmor;
import lsg.characters.Hero;
import lsg.characters.Monster;
import lsg.characters.Character;
import lsg.weapons.Claw;
import lsg.weapons.ShotGun;
import lsg.weapons.Sword;
import lsg.weapons.Weapon;

import java.util.Scanner;

public class LearningSoulsGame {
    private Hero hero;
    private Monster monster;

    private void refresh() {
        hero.printStats();
        monster.printStats();
    }

    private void fight1vs1(){
        init();
        Scanner scanner = new Scanner(System.in);
        refresh();
        Character attacker = this.hero;
        Character victim = this.monster;
        Character turn;
        while(attacker.isAlive() && victim.isAlive()){
            System.out.println("Hit enter key for next move > ");
            scanner.nextLine();
            int damage = attacker.attack();
            System.out.println(String.format("%s attacks %s with %s (ATTACK:%d | DMG: %d)", attacker.getName(), victim.getName(), attacker.getWeapon().getName(), damage, victim.getHitWith(damage)));
            refresh();
            turn=attacker;
            attacker=victim;
            victim=turn;

        }
        System.out.println(String.format("--- %s WINS !!! ---", (hero.isAlive() ? hero.getName() : monster.getName())));
    }

    private void init(){
        monster = new Monster();
        hero = new Hero();
        Weapon claw = new Claw();
        Weapon sword = new Sword();
        monster.setWeapon(claw);
        hero.setWeapon(sword);
    }

    private void play_v1(){
        fight1vs1();
    }

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
        if (h.isAlive()){
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
            int damage = rick.attack();
            System.out.println("!!!" +rick.getName()+" attack " +zombie.getName()+" with " +rick.getWeapon().getName()+" (" + damage +") !!! -> Effective DMG:000" +zombie.getHitWith(damage)+"PV");
        }
        if (!zombie.isAlive()){
            rick.printStats();
            zombie.printStats();
        }

        System.out.print("---------------------------------------- GAME ---------------------------------------- \n");
        LearningSoulsGame game = new LearningSoulsGame();
        game.play_v1();
    }
}
