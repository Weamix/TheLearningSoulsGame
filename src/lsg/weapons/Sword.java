package lsg.weapons;

public class Sword extends Weapon{
    public Sword(String name, int minDamage, int maxDamage, int stamCost, int durability) {
        super("Basic sword", 5, 10, 20, 100);
    }

    public static void main(String[] args){
        Weapon BasicSword = new Sword("Basic sword", 5, 10, 20, 100);
        BasicSword.printStats();
    }
}