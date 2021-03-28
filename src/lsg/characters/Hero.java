package lsg.characters;

import lsg.armor.ArmorItem;
import lsg.armor.BlackWitchVeil;
import lsg.armor.RingedKnightArmor;

import java.util.Arrays;

public class Hero extends Character{

    private static int MAX_ARMOR_PIECES = 3;
    private ArmorItem armor[];

    public Hero(String name) {
        armor = new ArmorItem[MAX_ARMOR_PIECES];
        setName(name);
        setMaxStamina(50);
        setMaxLife(100);
        setStamina(this.getMaxStamina());
        setLife(this.getMaxLife());
    }

    public void setArmor(ArmorItem pieceArmor, int slot) {
        if (slot>=1 && slot<=MAX_ARMOR_PIECES){
            this.armor[slot-1]=pieceArmor;
        }
    }

    public float getTotalArmor(){
        float sum = 0;
        for(int i = 0; i<armor.length; i++){
            if(armor[i]!=null){
                sum = sum + armor[i].getArmorValue();
            }
        }
        return sum;
    }

    public String armorToString() {
        StringBuilder s = new StringBuilder("ARMOR ");
        for(int i = 0; i<armor.length; i++){
            if(armor[i]!=null){
                s.append(String.format(" %s:%s(%s) ", i+1, armor[i].getName(), armor[i].getArmorValue()));
            }
            else{
                s.append(String.format(" %s:%s ", i+1, "empty"));
            }
        }
        return s + "TOTAL :" + getTotalArmor();
    }

    public Hero(){
        this( "Gregooninator");
    }

    public static void main(String[] args) {
        Hero heroArmorTest = new Hero("HeroArmorTest");
        BlackWitchVeil blackWitchVeil = new BlackWitchVeil();
        RingedKnightArmor ringedKnightArmor = new RingedKnightArmor();
        heroArmorTest.setArmor(blackWitchVeil,1);
        heroArmorTest.setArmor(ringedKnightArmor,3);
        System.out.println(heroArmorTest.armorToString());
    }
}
