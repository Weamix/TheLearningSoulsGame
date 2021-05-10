package lsg.characters;

import lsg.bags.Bag;
import lsg.bags.Collectible;
import lsg.bags.SmallBag;
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
    protected Bag bag;

    public static final String LIFE_STAT_STRING = "life :";
    public static  final String STAM_STAT_STRING = "stamina :";
    public static  final String BUFF_STAT_STRING = "buff :";
    public static  final String PROTECTION_STAT_STRING = "protection :";

    public Character(String name){
        setName(name);
        bag = new SmallBag();
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

    // weapon

    public Weapon getWeapon() { return weapon; }

    public void setWeapon(Weapon w) { this.weapon = w; }

    public void printWeapon(){
        System.out.println("WEAPON : " + weapon);
    }

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

    // consumables

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

    private void repairWeaponWith(RepairKit repairKit){
        System.out.println(name+" repairs " + weapon.toString() +" with " + repairKit);
        weapon.repairWith(repairKit);
    }

    public void use(Consumable consumable){
        if(consumable instanceof Drink){
            drink((Drink) consumable);
        }
        else if (consumable instanceof Food){
            eat((Food)consumable);
        }
        else if (consumable instanceof RepairKit){
            repairWeaponWith((RepairKit) consumable);
        }
    }

    public void consume(){
        use(consumable);
    }

    // bags

    public void pickUp(Collectible item){
        bag.push(item);
        System.out.println(getName() + " picks up " + item);
    }

    public Collectible pullOut(Collectible item){
        if(bag.contains(item)){
            bag.pop(item);
            System.out.println(getName() + " pulls out " + item);
            return item;
        }
        return null;
    }

    public void printBag(){
        System.out.println("BAG : " + bag);
    }

    public int getBagCapacity(){
        return bag.getCapacity();
    }

    public int getBagWeight(){
        return bag.getWeight();
    }

    public void getBagItems(){
        bag.getItems();
    }

    public Bag setBag(Bag bag){
    Bag.transfer(this.bag,bag);
    Bag old = this.bag;
    System.out.println(getName() + " changes " + this.bag.getClass().getSimpleName() + " for " + bag.getClass().getSimpleName() );
    this.bag = bag;
    return old;
    }

    public void equip(Weapon weapon){
        if(bag.contains(weapon)){
            setWeapon(weapon);
            pullOut(weapon);
            System.out.println(" and equips it !");
        }
    }

    public void equip(Consumable consumable){
        if(bag.contains(consumable)){
            setConsumable(consumable);
            pullOut(consumable);
            System.out.println(" and equips it !");
        }
    }

    // fast: http://web.gregory-bourguin.fr/06%20Genericite.pdf

    private Consumable fastUseFirst(Class<? extends Consumable> type){
        for (Collectible item : bag.getItems()){
            if(type.isInstance(item)){
                Consumable cons = (Consumable) item;
                use(cons);
                if(cons.getCapacity()==0){
                    pullOut(cons);
                }
                return cons;
            }
        }
        return null;
    }

    public Drink fastDrink(){
        System.out.println(getName() + " drinks FAST :");
        return (Drink) fastUseFirst(Drink.class);
    }
    public Food fastFood(){
        System.out.println(getName() + " eats FAST :");
        return (Food) fastUseFirst(Food.class);
    }
    public RepairKit fastRepair(){
        System.out.println(getName() + " repairs FAST :");
        return (RepairKit) fastUseFirst(RepairKit.class);
    }

    @Override
    public String toString() {
        return String.format("%-20s %-20s %-20s %-20s %-20s %-20s  %-20s","["+getClass().getSimpleName()+"]", getName(), LIFE_STAT_STRING + getLife()  , STAM_STAT_STRING + getStamina() ,PROTECTION_STAT_STRING + String.format(Locale.US,"%6.2f",computeProtection()) , BUFF_STAT_STRING + String.format(Locale.US,"%6.2f",computeBuff()), (isAlive() ? "(ALIVE)" : "(DEAD)")) ;
    }

    public void printStats(){
        System.out.println(this);
    }
}
