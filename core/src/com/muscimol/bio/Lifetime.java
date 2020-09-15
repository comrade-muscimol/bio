package com.muscimol.bio;

import com.muscimol.bio.generate.Map;

public class Lifetime extends Thread {

    public static boolean stopped;
    public static int mills;

    public static Lifetime current;


    public Lifetime(){

    }
    @Override
    public void run() {
        while(true){

            if(!stopped) {
                Map.getInstance().action();

                try {
                    Thread.sleep(mills);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
