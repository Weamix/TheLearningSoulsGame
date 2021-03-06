package lsg.characters;

public class Character {
    protected String name;
    protected int life;
    protected int maxLife;
    protected int stamina;
    protected int maxStamina;

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

    void setName(String name) {
        this.name = name;
    }

    void setLife(int life) {
        this.life = life;
    }

    void setMaxLife(int maxLife) {
        this.maxLife = maxLife;
    }

    void setStamina(int stamina) {
        this.stamina = stamina;
    }

    void setMaxStamina(int maxStamina) {
        this.maxStamina = maxStamina;
    }

    public boolean isAlive(){ return this.life>0; }

    @Override
    public String toString() {
        return String.format("%-20s %-20s %-20s %-20s %-20s","["+getClass().getSimpleName()+"]", getName(),"LIFE: " + getLife()  , "STAMINA: " + getStamina() , (isAlive() ? "(ALIVE)" : "(DEAD)")) ;

    }

    public void printStats(){
        System.out.println(this);
    }
}
