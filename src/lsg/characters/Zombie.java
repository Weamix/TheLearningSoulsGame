package lsg.characters;

import lsg.weapons.Weapon;

public class Zombie extends Monster{
    Weapon hands;
    public Zombie() {
        super("Zombie");
        setLife(10);
        setStamina(10);
        hands = new Weapon("Zombie's hands", 5, 20, 1, 1000);
        setWeapon(hands);
    }
}
