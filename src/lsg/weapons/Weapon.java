package lsg.weapons;

import lsg.characters.Hero;

public class Weapon {
    protected String name;
    protected int minDamage;
    protected int maxDamage;
    protected int stamCost;
    protected int durability;

    public Weapon(String name, int minDamage, int maxDamage, int stamCost, int durability) {
        this.name = name;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.stamCost = stamCost;
        this.durability = durability;
    }

    public String getName() {
        return name;
    }

    public int getMinDamage() {
        return minDamage;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public int getStamCost() {
        return stamCost;
    }

    public int getDurability() {
        return durability;
    }

    private void setDurability(int durability) {
        this.durability = durability;
    }

    public void use(){
        durability=durability--;
    }

    public boolean isBroken(){
        return this.durability<=0;
    }

    @Override
    public String toString() {
        //return String.format("%20s %20s %20s %20s %20s",getName(),"(min: " + getMinDamage()  , "max: " + getMaxDamage() , "stam:" + getStamCost(),"dur:"+getDurability())+")" ;
        return "attaque avec "+ getName() +" (min:"+ getMinDamage()+ " max: " + getMaxDamage() + " stam:" + getStamCost() + " dur:"+getDurability()+")";
    }

    public void printStats(){ System.out.println(this); }
}