package com.muscimol.bio;

public class Lifetime extends Thread {
    public static Lifetime current;
    private int mills;

    public Lifetime(int mills){
        this.mills = mills;
    }
    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()){

            Map.getInstance().action();
            try{
                Thread.sleep(mills);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
