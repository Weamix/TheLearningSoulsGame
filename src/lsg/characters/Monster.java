package lsg.characters;

public class Monster {
    private String name;
    private int life;
    private int maxLife;
    private int stamina;
    private int maxStamina;
    private static int INSTANCES_COUNT = 0;

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

    private void setName(String name) {
        this.name = name;
    }

    private void setLife(int life) {
        this.life = life;
    }

    private void setMaxLife(int maxLife) {
        this.maxLife = maxLife;
    }

    private void setStamina(int stamina) {
        this.stamina = stamina;
    }

    private void setMaxStamina(int maxStamina) {
        this.maxStamina = maxStamina;
    }

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

    public boolean isAlive(){
        return this.life>0;
    }

    @Override
    public String toString() {
        return "["+getClass().getSimpleName()+"] " + getName() + "\tLIFE: " + getLife() + "\tSTAMINA: " + getStamina() + (isAlive() ? "\t(ALIVE)" : "\t(DEAD)") ;
    }

    public void printStats(){
        System.out.println(this);
    }
}

