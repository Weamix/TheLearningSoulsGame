package lsg.weapons;

public class Sword extends Weapon{
    public Sword() {
        super("Basic sword", 5, 10, 20, 100);
    }

    public static void main(String[] args){
        Weapon BasicSword = new Sword();
        BasicSword.printStats();
    }
}
