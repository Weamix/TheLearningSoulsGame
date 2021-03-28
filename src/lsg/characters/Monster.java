package lsg.characters;

public class Monster extends Character{
    private static int INSTANCES_COUNT = 0;
    private float skinThickness = 20;

    public Monster(String name) {
        INSTANCES_COUNT++;
        setName(name);
        setMaxStamina(10);
        setMaxLife(10);
        setStamina(this.getMaxStamina());
        setLife(this.getMaxLife());
    }

    public float getSkinThickness() {
        return skinThickness;
    }

    protected void setSkinThickness(float skinThickness) {
        this.skinThickness = skinThickness;
    }

    public Monster(){
        this("Monster_"+INSTANCES_COUNT+1);
    }

    @Override
    protected float computeProtection() {
        return skinThickness;
    }
}
