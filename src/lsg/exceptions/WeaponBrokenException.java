package lsg.exceptions;

import lsg.weapons.Weapon;

public class WeaponBrokenException extends Exception {
    private Weapon weapon;

    public WeaponBrokenException(Weapon weapon) {
        super(weapon.getName() + "is broken !");
    }

    public Weapon getWeapon() {
        return weapon;
    }
}
