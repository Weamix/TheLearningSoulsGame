package lsg.characters;

import lsg.buffs.talismans.Talisman;

public class Monster extends Character{
    private static int INSTANCES_COUNT = 0;
    private float skinThickness = 20;
    private Talisman talisman;

    public Monster(String name) {
        super(name);
        INSTANCES_COUNT++;
        setMaxStamina(10);
        setMaxLife(10);
        setStamina(this.getMaxStamina());
        setLife(this.getMaxLife());
    }

    public Monster(){
        this("Monster_"+INSTANCES_COUNT+1);
    }

    // armor
    public float getSkinThickness() {
        return skinThickness;
    }

    protected void setSkinThickness(float skinThickness) {
        this.skinThickness = skinThickness;
    }

    // buff

    public Talisman getTalisman() {
        return talisman;
    }

    public void setTalisman(Talisman talisman) {
        this.talisman = talisman;
    }

    // abstract character

    @Override
    protected float computeProtection() {
        return skinThickness;
    }

    @Override
    protected float computeBuff() {
        return getTalisman().computeBuffValue();
    }
}
