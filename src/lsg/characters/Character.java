package lsg.characters;

import lsg.helpers.Dice;
import lsg.weapons.Weapon;

public class Character {
    protected String name;
    protected int life;
    protected int maxLife;
    protected int stamina;
    protected int maxStamina;
    protected Dice dice;

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

    public boolean isAlive(){ return this.life>0; }

    public int attackWith(Weapon weapon){
        int damage = 0;
        if(!weapon.isBroken() && getStamina()> 0){
            int minDamage = weapon.getMinDamage();
            int maxDamage = weapon.getMaxDamage();
            int additionalDamage = dice.roll();

            if((additionalDamage+minDamage)>maxDamage){
                damage=maxDamage;
            }
            else{
                damage = minDamage + additionalDamage;
            }

            weapon.use();

            if (getStamina() < weapon.getStamCost()){
                damage=Math.round(damage*((float)this.stamina/weapon.getStamCost()));
                setStamina(0);
            }
            else{
                setStamina(getStamina()-weapon.getStamCost());
            }
        }
        return damage;
    }

    @Override
    public String toString() {
        return String.format("%-20s %-20s %-20s %-20s %-20s","["+getClass().getSimpleName()+"]", getName(),"LIFE: " + getLife()  , "STAMINA: " + getStamina() , (isAlive() ? "(ALIVE)" : "(DEAD)")) ;

    }

    public void printStats(){
        System.out.println(this);
    }
}
