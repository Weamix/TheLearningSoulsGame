package lsg.characters;

import lsg.helpers.Dice;
import lsg.weapons.Weapon;

public class Character {
    private String name;
    private int life;
    private int maxLife;
    private int stamina;
    private int maxStamina;
    private Dice dice;
    private Weapon w;

    public Character(){
        dice = new Dice(101);
    }

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

    void setName(String name) {
        this.name = name;
    }

    void setLife(int life) {
        this.life = life;
    }

    void setMaxLife(int maxLife) {
        this.maxLife = maxLife;
    }

    void setStamina(int stamina) {
        if(stamina<0){
            stamina=0;
        }
        this.stamina = stamina;
    }

    void setMaxStamina(int maxStamina) {
        this.maxStamina = maxStamina;
    }

    public void setWeapon(Weapon w) { this.w = w; }

    public boolean isAlive(){ return this.life>0; }

    public int attackWith(Weapon weapon){
        int damage = 0;
        if(!weapon.isBroken() && getStamina()> 0){
            int minDamage = weapon.getMinDamage();
            int maxDamage = weapon.getMaxDamage();
            float additionalDamage = (float)dice.roll()/100;

            if((additionalDamage+minDamage)>maxDamage){
                damage=maxDamage;
            }
            else{
                damage = Math.round((minDamage + additionalDamage * (maxDamage - minDamage)) * Math.min((float) stamina / weapon.getStamCost(), 1));
            }

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
        value = Math.min(getLife(), value);
        setLife(getLife()-value);
        return value;
    }

    @Override
    public String toString() {
        return String.format("%-20s %-20s %-20s %-20s %-20s","["+getClass().getSimpleName()+"]", getName(),"LIFE: " + getLife()  , "STAMINA: " + getStamina() , (isAlive() ? "(ALIVE)" : "(DEAD)")) ;
    }

    public void printStats(){
        System.out.println(this);
    }
}
