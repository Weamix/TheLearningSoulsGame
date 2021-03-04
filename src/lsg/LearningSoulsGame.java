package lsg;

import lsg.characters.Hero;
import lsg.characters.Monster;

public class LearningSoulsGame {
    public static void main(String[] args) {
        Hero h = new Hero();
        h.printStats();
        System.out.println(h); //appel toString par défaut
        Hero h2 = new Hero("Marionapator");
        h2.printStats();
        for (int i=0;i<=5;i++){
            Monster m = new Monster();
            m.printStats();
        }
    }

}
