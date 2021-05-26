package lsg;

import lsg.armor.DragonSlayerLeggings;
import lsg.buffs.rings.DragonSlayerRing;
import lsg.buffs.rings.RingOfDeath;
import lsg.buffs.talismans.MoonStone;
import lsg.characters.Hero;
import lsg.characters.Lycanthrope;
import lsg.characters.Monster;
import lsg.characters.Character;
import lsg.consumables.food.Hamburger;
import lsg.exceptions.*;
import lsg.weapons.Sword;
import lsg.weapons.Weapon;
import java.util.Scanner;

public class LearningSoulsGame {
    private Hero hero;
    private Monster monster;

    public static final String BULLET_POINT = "\u2219";

    private void refresh() {
        hero.printStats();
        hero.printArmor();
        hero.printRings();
        hero.printConsumable();
        hero.printWeapon();
        hero.printBag();
        System.out.println();
        monster.printStats();
    }

    private void fight1vs1() throws WeaponNullException {
        Scanner scanner = new Scanner(System.in);
        refresh();
        Character attacker = this.hero;
        Character victim = this.monster;
        Character turn;
        int damage;
        while(attacker.isAlive() && victim.isAlive()){
            System.out.println();
            if(attacker instanceof Hero){
                int action = 0;
                while (action != 1 && action != 2){
                    System.out.println("Hero's action for next move (1) attack | (2) consume > ");
                    action = scanner.nextInt();
                }
                if(action ==2){
                    try{
                        attacker.consume();
                    }
                    catch(ConsumeNullException e){
                        System.out.println("IMPOSSIBLE ACTION : no consumable has beeen equiped !");
                    }
                    catch(ConsumeEmptyException e){
                        System.out.println("ACTION HAS NO EFFECT :" + e.getConsumable() + " is empty !");
                    } catch (ConsumeException e) {
                        e.printStackTrace();
                    }
                } else{
                    try{
                        damage = attacker.attack();
                        System.out.println(String.format("%s attacks %s with %s (ATTACK:%d | DMG: %d)", attacker.getName(), victim.getName(), attacker.getWeapon().getName(), damage, victim.getHitWith(damage)));
                    }catch (WeaponNullException e){
                        damage = 0;
                        System.out.println("WARNING : no weapon has been equiped !!!");
                        System.out.println(String.format("%s attacks %s with %s (ATTACK:%d | DMG: %d)", attacker.getName(), victim.getName(), null, damage, victim.getHitWith(damage)));
                    }
                    catch(WeaponBrokenException e){
                        damage = 0;
                        System.out.println("WARNING : weapon is broken !!!");
                        System.out.println(String.format("%s attacks %s with %s (ATTACK:%d | DMG: %d)", attacker.getName(), victim.getName(), null, damage, victim.getHitWith(damage)));
                    }
                    catch(StaminaEmptyException e){
                        System.out.println("ACTION HAS NO EFFECT : no more stamina !!!");
                    }
                }
                System.out.println();
            }
            else {
                try{
                    damage = attacker.attack();
                }catch (WeaponNullException e){
                    damage = 0;
                    System.out.println("WARNING : no weapon has been equiped !!!");
                    System.out.println(String.format("%s attacks %s with %s (ATTACK:%d | DMG: %d)", attacker.getName(), victim.getName(), null, damage, victim.getHitWith(damage)));
                }
                catch(WeaponBrokenException e){
                    damage = 0;
                    System.out.println("WARNING : weapon is broken !!!");
                    System.out.println(String.format("%s attacks %s with %s (ATTACK:%d | DMG: %d)", attacker.getName(), victim.getName(), null, damage, victim.getHitWith(damage)));
                }
                catch(StaminaEmptyException e){
                    damage = 0;
                    System.out.println("ACTION HAS NO EFFECT : no more stamina !!!");
                }
                System.out.println(String.format("%s attacks %s with %s (ATTACK:%d | DMG: %d)", attacker.getName(), victim.getName(), attacker.getWeapon().getName(), damage, victim.getHitWith(damage)));
            }
            refresh();
            turn=attacker;
            attacker=victim;
            victim=turn;

        }
        System.out.println(String.format("--- %s WINS !!! ---", (hero.isAlive() ? hero.getName() : monster.getName())));
    }

    private void init(){
        monster = new Monster();
        monster = new Lycanthrope() ;
        hero = new Hero();
        hero.setWeapon(new Sword());
        hero.setConsumable(new Hamburger());
        hero.setArmor(new DragonSlayerLeggings(), 1);
        hero.setRing(new RingOfDeath(), 1);
        hero.setRing(new DragonSlayerRing(), 2);

        monster.setTalisman(new MoonStone());
    }

    private void createExhaustedHero(){
        this.hero = new Hero();
        hero.getHitWith(99);
        Weapon grosseArme = new Weapon("Grosse arme",0,0,1000,100);
        hero.setWeapon(grosseArme);
        try {
            hero.attack();
        } catch (WeaponNullException | WeaponBrokenException | StaminaEmptyException e) {
            e.printStackTrace();
        }
        System.out.println("Created exhausted hero : ");
        hero.printStats();
    }

    private void testExceptions() {
        Weapon weapon = new Weapon("pelle",0,0,0,0);
        hero.setWeapon(weapon);
        try {
            fight1vs1();
        } catch (WeaponNullException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.print("---------------------------------------- THE LEARNING SOULS GAME ---------------------------------------- \n");
        LearningSoulsGame game = new LearningSoulsGame();
        game.init();
        game.testExceptions();
    }
}
