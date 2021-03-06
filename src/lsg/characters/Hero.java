package lsg.characters;

public class Hero extends Character{

    public Hero(String name) {
        this.setName(name);
        this.setMaxStamina(50);
        this.setMaxLife(100);
        this.setStamina(this.getMaxStamina());
        this.setLife(this.getMaxLife());
    }

    public Hero(){
        this( "Gregooninator");
    }
}

