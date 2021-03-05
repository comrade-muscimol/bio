package com.muscimol.bio;

import com.muscimol.bio.gen.Map;

public class Lifetime extends Thread {

    public Map map;
    public static boolean stopped;

    public static int mills;

    public static Lifetime current;

    public Lifetime(Map map){
        stopped = false;
        mills = 500;
        this.map = map;
    }
    @Override
    public void run() {
        while(true){

            //System.out.println("LIFETIME ACTION");
            if(!stopped) {
                //System.out.println(new Date());
                try {
                    Thread.sleep(mills);
                    map.action();
                } catch (Exception e) {
                    stopped=true;
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
