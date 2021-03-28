package lsg.characters;

import lsg.weapons.Claw;

public class Lycanthrope extends Monster{
    public Lycanthrope() {
        super("Lycanthrope");
        Claw claw = new Claw();
        super.setWeapon(claw);
        super.setSkinThickness(30);
    }
}
