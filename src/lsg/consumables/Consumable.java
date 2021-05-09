package lsg.consumables;

import lsg.bags.Collectible;

public class Consumable implements Collectible {
    private String name;
    private int capacity;
    private String stat;

    public Consumable(String name, int capacity, String stat) {
        this.name = name;
        this.capacity = capacity;
        this.stat = stat;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getStat() {
        return stat;
    }

    protected void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int use(){
        int capacity = getCapacity();
        setCapacity(0);
        return capacity;
    }

    @Override
    public String toString() {
        return String.format("%-20s %-20s",getName(), "[ "+getCapacity()+" "+getStat()+" point(s)]");
    }

    public void printStats(){
        System.out.println(this);
    }

    @Override
    public int getWeight() {
        return 1;
    }
}
