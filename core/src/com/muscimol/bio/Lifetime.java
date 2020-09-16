package com.muscimol.bio;

import com.muscimol.bio.generate.Map;

import java.util.Date;

public class Lifetime extends Thread {

    public static boolean stopped;

    public static int mills;

    public static Lifetime current;


    public Lifetime(){
        stopped = false;
        mills = 1000;
    }
    @Override
    public void run() {
        while(true){

            //System.out.println("LIFETIME ACTION");
            if(!stopped) {

                Map.getInstance().action();
                //System.out.println(new Date());

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
