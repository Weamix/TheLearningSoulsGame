package lsg.Helpers;

import java.util.Random;

public class Dice {
    int faces;
    Random random;

    public Dice(int faces) {
        this.faces = faces;
    }

    public int roll(){
        random.nextInt((faces-0)+1)+faces);
    }
}
