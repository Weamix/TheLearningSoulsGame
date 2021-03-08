package lsg.characters;

public class Hero extends Character{

    public Hero(String name) {
        setName(name);
        setMaxStamina(50);
        setMaxLife(100);
        setStamina(this.getMaxStamina());
        setLife(this.getMaxLife());
    }

    public Hero(){
        this( "Gregooninator");
    }
}
