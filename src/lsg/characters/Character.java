package lsg.characters;

import lsg.helpers.Dice;
import lsg.weapons.Weapon;

public abstract class Character {
    private String name;
    private int life;
    private int maxLife;
    private int stamina;
    private int maxStamina;
    private Dice dice = new Dice(101);
    private Weapon w;

    public String getName() {
        return name;
    }

    public int getLife() {
        return life;
    }

    public int getMaxLife() {
        return maxLife;
    }

    public int getStamina() {
        return stamina;
    }

    public int getMaxStamina() {
        return maxStamina;
    }

    public Weapon getWeapon() { return w; }

    protected void setName(String name) {
        this.name = name;
    }

    protected void setLife(int life) {
        this.life = life;
    }

    protected void setMaxLife(int maxLife) {
        this.maxLife = maxLife;
    }

    protected void setStamina(int stamina) {
        if(stamina<0){
            stamina=0;
        }
        this.stamina = stamina;
    }

    protected void setMaxStamina(int maxStamina) {
        this.maxStamina = maxStamina;
    }

    public void setWeapon(Weapon w) { this.w = w; }

    public boolean isAlive(){ return this.life>0; }

    private int attackWith(Weapon weapon){
        int damage = 0;
        if(!weapon.isBroken() && getStamina()> 0){
            int minDamage = weapon.getMinDamage();
            int maxDamage = weapon.getMaxDamage();
            float additionalDamage = (float)dice.roll()/100;

            //damage = Math.round((minDamage + additionalDamage * (maxDamage - minDamage)) * Math.min((float) stamina / weapon.getStamCost(), 1));
            damage = minDamage + Math.round((maxDamage-minDamage)*additionalDamage);

            weapon.use();
            float stamina_ratio = (float) getStamina() / w.getStamCost();
            if(stamina_ratio > 1) stamina_ratio = 1;
            damage = Math.round(damage * stamina_ratio);
            setStamina(getStamina() - w.getStamCost());
        }
        return damage;
    }

    public int attack(){
        return attackWith(this.w);
    }

    public int getHitWith(int value){
        float protection = computeProtection();
        if(protection<100){
            value = (int) Math.min(getLife(), value*protection/100);
        }
        setLife(getLife()-value);
        return value;
    }

    protected abstract float computeProtection();

    @Override
    public String toString() {
        return String.format("%-20s %-20s %-20s %-20s %-20s  %-20s","["+getClass().getSimpleName()+"]", getName(),"LIFE: " + getLife()  , "STAMINA: " + getStamina() ,"PROTECTION :" + computeProtection() , (isAlive() ? "(ALIVE)" : "(DEAD)")) ;
    }

    public void printStats(){
        System.out.println(this);
    }
}
