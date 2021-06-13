package lsg.bags;

import lsg.armor.BlackWitchVeil;
import lsg.armor.DragonSlayerLeggings;
import lsg.armor.RingedKnightArmor;
import lsg.consumables.food.Hamburger;
import lsg.exceptions.BagFullException;
import lsg.weapons.ShotGun;
import lsg.weapons.Sword;

import java.util.HashSet;
import static lsg.LearningSoulsGame.BULLET_POINT;

public class Bag {
    private int capacity;
    private int weight;
    private HashSet<Collectible> items;

    public Bag(int capacity) {
        this.capacity = capacity;
        this.items = new HashSet<Collectible>();
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void push(Collectible item) throws BagFullException {
        if(getWeight()+item.getWeight()>=getCapacity()) throw new BagFullException(this);
        if(getWeight()+item.getWeight()<=getCapacity()){
            items.add(item);
            setWeight(getWeight()+item.getWeight());
        }
    }

    public Collectible pop(Collectible item){
        if(contains(item)) {
            setWeight(getWeight()-item.getWeight());
            items.remove(item);
            return item;
        }
        else{
            return null;
        }
    }

    public boolean contains(Collectible item){
        return items.contains(item);
    }

    public Collectible[] getItems(){
        Collectible[] tabItems = new Collectible[this.items.size()];
        int i = 0;
        for (Collectible it : items){
            tabItems[i] = it;
            i++;
        }
        return tabItems;
    }

    public static void transfer(Bag from, Bag into) throws BagFullException {
        if (from==null || into ==null){
            return;
        }
        Collectible[] tabItems = from.getItems();
        for (Collectible it : tabItems){
            into.push(it);
            if(into.contains(it)){
                from.pop(it);
            }
        }
    }

    public String toString(){
        StringBuilder bag = new StringBuilder(getClass().getSimpleName() + "[ " + items.size() + " items" +" | " + getWeight() + "/" + getCapacity() + " kg" + " ]");
        if(items.isEmpty()){
            return bag + "\n" + BULLET_POINT + "empty" + "\n";

        }else{
            for (Collectible it : items){
                bag.append("\n"+BULLET_POINT).append("(").append(it.toString()).append(")").append("[").append(it.getWeight()).append(" kg").append("]");
            }
            bag.append("\n");
        }
        return bag.toString();
    }

    public static void main(String[] args) throws BagFullException {
        BlackWitchVeil blackWitchVeil = new BlackWitchVeil();
        Hamburger hamburger = new Hamburger();
        Sword sword = new Sword();
        DragonSlayerLeggings dragonSlayerLeggings = new DragonSlayerLeggings();

        SmallBag smallBag = new SmallBag();
        smallBag.push(blackWitchVeil);
        smallBag.push(hamburger);
        smallBag.push(sword);
        smallBag.push(dragonSlayerLeggings);
        System.out.println(smallBag);

        System.out.println("Pop sur Dragon Slayer Leggings(10.2)\n");
        smallBag.pop(dragonSlayerLeggings);
        System.out.println(smallBag);

        System.out.println("Sac 1 :");
        Bag bag10kg = new Bag(10);
        ShotGun shotGun = new ShotGun();
        RingedKnightArmor ringedKnightArmor = new RingedKnightArmor();
        DragonSlayerLeggings dragonSlayerLeggings1 = new DragonSlayerLeggings();
        bag10kg.push(shotGun);
        bag10kg.push(dragonSlayerLeggings1);
        bag10kg.push(ringedKnightArmor);
        System.out.println(bag10kg);

        System.out.println("Sac 2 :");
        Bag bag5kg = new Bag(5);
        System.out.println(bag5kg);

        System.out.println("Sac 2 après transfert :");
        transfer(bag10kg,bag5kg);
        System.out.println(bag5kg);

        System.out.println("Sac 1 après transfert :");
        System.out.println(bag10kg);
    }
}
