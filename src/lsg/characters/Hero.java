package lsg.characters;

import lsg.armor.ArmorItem;
import lsg.armor.BlackWitchVeil;
import lsg.armor.RingedKnightArmor;
import lsg.buffs.rings.Ring;
import lsg.buffs.rings.RingOfSwords;
import lsg.exceptions.NoBagException;

public class Hero extends Character{

    private static final int MAX_ARMOR_PIECES = 3;
    private static final int MAX_RINGS = 2;
    private final ArmorItem[] armor;
    private final Ring[] rings;

    public Hero(String name) {
        super(name);
        setMaxStamina(50);
        setMaxLife(100);
        setStamina(this.getMaxStamina());
        setLife(this.getMaxLife());
        armor = new ArmorItem[MAX_ARMOR_PIECES];
        rings = new Ring[MAX_RINGS];
    }

    public Hero(){
        this( "Gregooninator");
    }

    // armor

    public void setArmor(ArmorItem pieceArmor, int slot) {
        if (slot>=1 && slot<=MAX_ARMOR_PIECES){
            this.armor[slot-1]=pieceArmor;
        }
    }

    public float getTotalArmor(){
        float sum = 0;
        for (ArmorItem armorItem : armor) {
            if (armorItem != null) {
                sum = sum + armorItem.getArmorValue();
            }
        }
        return sum;
    }

    public String armorToString() {
        StringBuilder s = new StringBuilder("ARMOR ");
        for(int i = 0; i<MAX_ARMOR_PIECES; i++){
            s.append(String.format(" %s:%s ", i+1, armor[i]!=null ? armor[i].toString() : "empty"));
        }
        return s + "TOTAL :" + getTotalArmor();
    }

    public ArmorItem[] getArmorItems() {
        int size = 0;
        for (int i = 0; i < MAX_ARMOR_PIECES; i++) {
            if (this.armor[i] != null) {
                size++;
            }
        }
        ArmorItem[] ArmorItemsUsed = new ArmorItem[size];
        for (int j = 0,i=0; j < MAX_ARMOR_PIECES; j++) {
            if (this.armor[j] != null) {
                ArmorItemsUsed[i] = this.armor[j];
                i++;
            }
        }
        return ArmorItemsUsed;
    }

    public void printArmor(){
        System.out.println(armorToString());
    }

    // rings
    public void setRing(Ring ring, int slot) {
        if (slot>=1 && slot<=MAX_RINGS){
            this.rings[slot-1]=ring;
            ring.setHero(this);
        }
    }

    public float getTotalRings(){
        float sum = 0;
        for (Ring ring : this.rings) {
            if (ring != null) {
                sum = sum + ring.computeBuffValue();
            }
        }
        return sum;
    }

    public String ringToString() {
        StringBuilder s = new StringBuilder("RINGS :");
        for(int i = 0; i<MAX_RINGS; i++){
            s.append(String.format(" %s:%s ", i+1, rings[i]!=null ? rings[i].toString() : "empty"));
        }
        return s + "TOTAL :" + computeBuff();
    }

    public void printRings() {
        StringBuilder s = new StringBuilder("RINGS :");
        for(int i = 0; i<MAX_RINGS; i++){
            s.append(String.format(" %s:%s ", i+1, rings[i]!=null ? rings[i].toString() : "empty"));
        }
        System.out.println(s);
    }

    // abstract character

    @Override
    protected float computeProtection() {
        return getTotalArmor();
    }

    @Override
    protected float computeBuff() {
        return getTotalRings();
    }

    // bags

    public void equip(ArmorItem item, int slot) throws NoBagException {
        if(bag==null) throw new NoBagException();
        if(bag.contains(item)){
            setArmor(item, slot);
            pullOut(item);
            System.out.println(" and equips it !");
        }
    }

    public void equip(Ring ring, int slot) throws NoBagException {
        if(bag==null) throw new NoBagException();
        if(bag.contains(ring)){
            setRing(ring, slot);
            pullOut(ring);
            System.out.println(" and equips it !");
        }
    }

    public static void main(String[] args) {
        Hero heroTest = new Hero("HeroArmorTest");
        BlackWitchVeil blackWitchVeil = new BlackWitchVeil();
        RingedKnightArmor ringedKnightArmor = new RingedKnightArmor();
        heroTest.setArmor(blackWitchVeil,1);
        heroTest.setArmor(ringedKnightArmor,3);
        RingOfSwords ringOfSwords = new RingOfSwords();
        heroTest.setRing(ringOfSwords,1);
        System.out.println(heroTest.armorToString());
        System.out.println(heroTest.ringToString());
    }
}
