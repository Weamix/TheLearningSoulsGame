package lsg.weapons;

public class Weapon {
    private String name;
    private int minDamage;
    private int maxDamage;
    private int stamCost;
    private int durability;

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

    public int getMaxDamage() { return maxDamage; }

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
        durability=durability-1;
    }

    public boolean isBroken(){ return this.durability<=0; }

    @Override
    public String toString() {
        return String.format("%20s %20s %20s %20s %20s",getName(),"(min: " + getMinDamage()  , "max: " + getMaxDamage() , "stam:" + getStamCost(),"dur:"+getDurability())+")" ;
    }

    public void printStats(){ System.out.println(this); }
}
