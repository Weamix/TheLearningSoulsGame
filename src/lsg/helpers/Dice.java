package lsg.helpers;

import java.util.Random;

public class Dice {
    private int faces;
    private Random random;

    public Dice(int faces) {
        random = new Random();
        this.faces = faces;
    }

    public int roll(){
        return random.nextInt(faces);
    }

    public static void main(String[] args) {
        Dice dice = new Dice(50);
        int min = 0 ,max = 0;
        for(int i =0;i<=500;i++){
            int num = dice.roll();
            System.out.print(" "+num);
            if(num>max){
                max=num;
            }
            if(num<min){
                min=num;
            }
        }
        System.out.print("\n");
        System.out.println("Min : "+min);
        System.out.println("Max : "+max);
    }
}
