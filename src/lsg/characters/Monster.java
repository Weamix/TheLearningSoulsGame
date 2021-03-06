package lsg.characters;

public class Monster extends Character{
    private static int INSTANCES_COUNT = 0;

    public Monster(String name) {
        INSTANCES_COUNT++;
        setName(name);
        setMaxStamina(10);
        setMaxLife(10);
        setStamina(this.getMaxStamina());
        setLife(this.getMaxLife());
    }

    public Monster(){
        this("Monster_"+INSTANCES_COUNT+1);
    }
}

