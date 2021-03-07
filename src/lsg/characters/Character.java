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

    public int attackWith(Weapon weapon){
        //TODO fix this method
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
            setStamina(stamina-weapon.getStamCost());

            if (getStamina() < weapon.getStamCost()){
                int reduction = weapon.getStamCost()-getStamina();
                damage = damage - reduction;
                setStamina(0);
            }
        }
        return damage;
    }

    public int attack(){
        return attackWith(this.w);
    }

    public int getHitWith(int value){
        value = getLife() < value ? getLife() : value;
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
