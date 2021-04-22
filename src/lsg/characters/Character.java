package lsg.characters;

import lsg.consumables.Consumable;
import lsg.consumables.drinks.Drink;
import lsg.consumables.food.Food;
import lsg.consumables.repair.RepairKit;
import lsg.helpers.Dice;
import lsg.weapons.Weapon;

import java.util.Locale;

public abstract class Character {
    private String name;
    private int life;
    private int maxLife;
    private int stamina;
    private int maxStamina;
    private final Dice dice = new Dice(101);
    private Weapon weapon;
    private Consumable consumable;

    public static final String LIFE_STAT_STRING = "life :";
    public static  final String STAM_STAT_STRING = "stamina :";
    public static  final String BUFF_STAT_STRING = "buff :";
    public static  final String PROTECTION_STAT_STRING = "protection :";


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

    public Weapon getWeapon() { return weapon; }

    protected void setName(String name) {
        this.name = name;
    }

    protected void setLife(int life) {
        if(life>getMaxLife()){
            this.life=getMaxLife();
        }else{
            this.life = life;
        }
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

    public void setWeapon(Weapon w) { this.weapon = w; }

    public Consumable getConsumable() {
        return consumable;
    }

    public void setConsumable(Consumable consumable) {
        this.consumable = consumable;
    }

    public boolean isAlive(){ return this.life>0; }

    private int attackWith(Weapon weapon){
        int damage = 0;
        if(!weapon.isBroken() && getStamina()> 0){
            int minDamage = weapon.getMinDamage();
            int maxDamage = weapon.getMaxDamage();
            float additionalDamage = (float)dice.roll()/100;

            damage = minDamage + Math.round((maxDamage-minDamage)*additionalDamage);

            weapon.use();
            float stamina_ratio = (float) getStamina() / this.weapon.getStamCost();
            if(stamina_ratio > 1) stamina_ratio = 1;
            damage = Math.round(damage * stamina_ratio);
            damage += (damage * (computeBuff() / 100));
            setStamina(getStamina() - this.weapon.getStamCost());
        }
        return damage;
    }

    public int attack(){
        return attackWith(this.weapon);
    }

    public int getHitWith(int value){
        if(computeProtection()>100){
            value = 0;
        }
        else{
            value = getLife() < value ? getLife() : Math.round(value - (value * (computeProtection() / 100)));
        }
        setLife(getLife()-value);
        return value;
    }

    protected abstract float computeProtection();
    protected abstract float computeBuff();

    private void drink(Drink drink){
        System.out.println(name+" drinks " + drink.toString());
        int capacity = drink.use();
        if(capacity < getMaxStamina()){
            setStamina(getStamina()+capacity);
        }
        else{
            setStamina(getMaxStamina());
        }
    }

    private void eat(Food food){
        System.out.println(name+" eats " + food.toString());
        int capacity = food.use();
        if(capacity < getMaxLife()){
            setLife(getLife()+capacity);
        }
        else{
            setLife(getMaxLife());
        }
    }

    private void repairWith(RepairKit repairKit){
        System.out.println(name+" repairs " + weapon.toString() +" with " + repairKit);
        weapon.repairWeaponWith(repairKit);
    }

    public void use(Consumable consumable){
        if(consumable instanceof Drink){
            drink((Drink) consumable);
        }
        else if (consumable instanceof Food){
            eat((Food)consumable);
        }
        else if (consumable instanceof RepairKit){
            repairWith((RepairKit) consumable);
        }
    }

    public void consume(){
        use(consumable);
    }

    @Override
    public String toString() {
        return String.format("%-20s %-20s %-20s %-20s %-20s %-20s  %-20s","["+getClass().getSimpleName()+"]", getName(), LIFE_STAT_STRING + getLife()  , STAM_STAT_STRING + getStamina() ,PROTECTION_STAT_STRING + String.format(Locale.US,"%6.2f",computeProtection()) , BUFF_STAT_STRING + String.format(Locale.US,"%6.2f",computeBuff()), (isAlive() ? "(ALIVE)" : "(DEAD)")) ;
    }

    public void printStats(){
        System.out.println(this);
    }
}
