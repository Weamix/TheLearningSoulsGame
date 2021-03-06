package lsg.Helpers;

import java.util.Random;

public class Dice {
    protected int faces;
    protected Random random;

    public Dice(int faces) {
        this.faces = faces;
    }

    public int roll(){
        random = new Random();
        int rolled_number = 0;
        rolled_number =  random.nextInt((faces)+1);
        return rolled_number;
    }

    @Override
    public String toString() {
        return "Dice{" +
                "faces=" + faces +
                ", random=" + random +
                '}';
    }

    public void printStats(){
        System.out.println(this);
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
