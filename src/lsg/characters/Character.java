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
        this.stamina = stamina;
    }

    void setMaxStamina(int maxStamina) {
        this.maxStamina = maxStamina;
    }

    public void setWeapon(Weapon w) { this.w = w; }

    public boolean isAlive(){ return this.life>0; }

    private int attackWith(Weapon weapon){
        int damage = 0;
        if(!w.isBroken() && getStamina()> 0){
            int minDamage = w.getMinDamage();
            int maxDamage = w.getMaxDamage();
            int additionalDamage = dice.roll();

            if((additionalDamage+minDamage)>maxDamage){
                damage=maxDamage;
            }
            else{
                damage = minDamage + additionalDamage;
            }

            w.use();
           //setStamina(getStamina()-weapon.getStamCost());
            float stamina_ratio = (float) getStamina() / w.getStamCost();
            if(stamina_ratio > 1) {
                stamina_ratio = 1;
                //setStamina(0);
            }
            /*if (w.getStamCost() > getStamina()){
                int reduction = w.getStamCost()-getStamina();
                damage = damage - reduction;
                setStamina(0);
            }*/
            setStamina(getStamina() - w.getStamCost());
            damage = Math.round(damage * stamina_ratio);
        }
        return damage;
    }

    public int attack(){
        return attackWith(this.w);
    }

    @Override
    public String toString() {
        return String.format("%-20s %-20s %-20s %-20s %-20s","["+getClass().getSimpleName()+"]", getName(),"LIFE: " + getLife()  , "STAMINA: " + getStamina() , (isAlive() ? "(ALIVE)" : "(DEAD)")) ;
    }

    public void printStats(){
        System.out.println(this);
    }
}
