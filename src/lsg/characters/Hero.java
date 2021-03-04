package lsg.characters;

public class Hero {
    private String name;
    private int life;
    private int maxLife;
    private int stamina;
    private int maxStamina;

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

    public boolean isAlive(){ return this.life>0; }

    @Override
    public String toString() {
        return "["+getClass().getSimpleName()+"] " + this.getName() + "\tLIFE: " + this.getLife() + "\tSTAMINA: " + this.getStamina() + (isAlive() ? "\t(ALIVE)" : "\t(DEAD)") ;
    }

    public void printStats(){
        System.out.println(this);
    }
}

