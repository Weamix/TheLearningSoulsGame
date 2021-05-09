package lsg;

import lsg.armor.BlackWitchVeil;
import lsg.armor.DragonSlayerLeggings;
import lsg.armor.RingedKnightArmor;
import lsg.bags.MediumBag;
import lsg.buffs.rings.RingOfDeath;
import lsg.buffs.rings.RingOfSwords;
import lsg.buffs.talismans.Talisman;
import lsg.characters.Hero;
import lsg.characters.Lycanthrope;
import lsg.characters.Monster;
import lsg.characters.Character;
import lsg.consumables.Consumable;
import lsg.consumables.MenuBestOfV4;
import lsg.consumables.drinks.Coffee;
import lsg.consumables.drinks.Whisky;
import lsg.consumables.food.Hamburger;
import lsg.consumables.repair.RepairKit;
import lsg.weapons.Claw;
import lsg.weapons.ShotGun;
import lsg.weapons.Sword;
import lsg.weapons.Weapon;

import java.util.Iterator;
import java.util.Scanner;

public class LearningSoulsGame {
    private Hero hero;
    private Monster monster;

    public static final String BULLET_POINT = "\u2219";

    private void refresh() {
        hero.printStats();
        System.out.println(BULLET_POINT+hero.getWeapon().toString());
        System.out.println(BULLET_POINT+hero.getConsumable().toString()+"\n");
        monster.printStats();
    }

    private void fight1vs1(){
        Scanner scanner = new Scanner(System.in);
        refresh();
        Character attacker = this.hero;
        Character victim = this.monster;
        Character turn;
        while(attacker.isAlive() && victim.isAlive()){
            System.out.println();
            if(attacker instanceof Hero){
                int action = 0;
                while (action != 1 && action != 2){
                    System.out.println("Hero's action for next move (1) attack | (2) consume > ");
                    action = scanner.nextInt();
                }
                if(action ==2){
                    attacker.consume();
                } else{
                    int damage = attacker.attack();
                    System.out.println(String.format("%s attacks %s with %s (ATTACK:%d | DMG: %d)", attacker.getName(), victim.getName(), attacker.getWeapon().getName(), damage, victim.getHitWith(damage)));
                }
                System.out.println();
            }
            else {
                int damage = attacker.attack();
                System.out.println(String.format("%s attacks %s with %s (ATTACK:%d | DMG: %d)", attacker.getName(), victim.getName(), attacker.getWeapon().getName(), damage, victim.getHitWith(damage)));
            }
            refresh();
            turn=attacker;
            attacker=victim;
            victim=turn;

            //Coffee coffee = new Coffee();
            //attacker.use(coffee);

        }
        System.out.println(String.format("--- %s WINS !!! ---", (hero.isAlive() ? hero.getName() : monster.getName())));
    }

    private void init(){
        monster = new Monster();
        hero = new Hero();
        Hamburger hamburger = new Hamburger();
        Weapon claw = new Claw();
        Weapon sword = new Sword();
        monster.setWeapon(claw);
        hero.setWeapon(sword);
        hero.setConsumable(hamburger);
    }

    private void play_v1(){
        init();
        fight1vs1();
    }

    private void play_v2(){
        init();
        BlackWitchVeil blackWitchVeil = new BlackWitchVeil();
        RingedKnightArmor ringedKnightArmor = new RingedKnightArmor();
        DragonSlayerLeggings dragonSlayerLeggings = new DragonSlayerLeggings();
        hero.setArmor(blackWitchVeil,1);
        hero.setArmor(ringedKnightArmor,2);
        hero.setArmor(dragonSlayerLeggings,3);
        fight1vs1();
    }

    private void play_v3(){
        init();
        RingedKnightArmor ringedKnightArmor = new RingedKnightArmor();
        hero.setArmor(ringedKnightArmor,2);
        RingOfDeath ringOfDeath = new RingOfDeath();
        hero.setRing(ringOfDeath,1);
        RingOfSwords ringOfSwords = new RingOfSwords();
        hero.setRing(ringOfSwords,2);
        monster = new Lycanthrope();
        Talisman talisman = new Talisman("Renault",20,1,2);
        monster.setTalisman(talisman);
        fight1vs1();
    }

    private void test_bag(){
        /*Hero hero = new Hero();
        DragonSlayerLeggings dragonSlayerLeggings = new DragonSlayerLeggings();
        hero.pickUp(dragonSlayerLeggings);

        Hamburger hamburger = new Hamburger();
        hero.pickUp(hamburger);
        hero.pullOut(hamburger);

        ShotGun shotGun = new ShotGun();
        RingedKnightArmor ringedKnightArmor = new RingedKnightArmor();
        hero.pickUp(shotGun);
        hero.pickUp(ringedKnightArmor);
        hero.printBag();

        MediumBag mediumBag = new MediumBag();
        hero.setBag(mediumBag);

        hero.equip(shotGun);
        hero.equip(dragonSlayerLeggings,2);*/

        createExhaustedHero();

        DragonSlayerLeggings dragonSlayerLeggings = new DragonSlayerLeggings();
        RingedKnightArmor ringedKnightArmor = new RingedKnightArmor();
        ShotGun shotGun = new ShotGun();
        hero.pickUp(dragonSlayerLeggings);
        hero.pickUp(ringedKnightArmor);
        hero.pickUp(shotGun);
        hero.printBag();

        MediumBag mediumBag = new MediumBag();

        hero.setBag(mediumBag);
        hero.printBag();

        Coffee coffee = new Coffee();
        Hamburger hamburger = new Hamburger();
        Whisky whisky = new Whisky();
        RepairKit repairKit = new RepairKit();
        RepairKit repairKit1 = new RepairKit();
        hero.pickUp(coffee);
        hero.pickUp(hamburger);
        hero.pickUp(whisky);
        hero.pickUp(repairKit);
        hero.pickUp(repairKit1);

        hero.printBag();
    }

    private void createExhaustedHero(){
        this.hero = new Hero();
        hero.getHitWith(99);
        Weapon grosseArme = new Weapon("Grosse arme",0,0,1000,100);
        hero.setWeapon(grosseArme);
        hero.attack();
        System.out.println("Created exhausted hero : ");
        hero.printStats();

    }

    private void aTable(){
        Hero h = new Hero();
        Weapon grosseArme = new Weapon("Grosse arme",0,0,1000,100);
        h.setWeapon(grosseArme);
        MenuBestOfV4 menuBestOfV4 = new MenuBestOfV4();
        Iterator it = menuBestOfV4.iterator();
        while (it.hasNext()){
            Consumable consumable = (Consumable) it.next();
            h.use(consumable);
            System.out.println(h.toString());
            System.out.println("Apres utilisation : "+consumable.toString());
        }
        System.out.println(h.getWeapon().toString());
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

        /*System.out.print("----------------------------------------------------------------------------------------\n");
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
        }*/

        System.out.print("---------------------------------------- THE LEARNING SOULS GAME ---------------------------------------- \n");
        LearningSoulsGame game = new LearningSoulsGame();
        //game.play_v1();
        //game.play_v2();
        //game.play_v3();
        //game.createExhaustedHero();
        //game.aTable();
        game.test_bag();
    }
}
