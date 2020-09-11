package com.muscimol.bio;

public class Producent extends Thing {


    boolean active;

    int amount;
    int max_amount;


    public Producent(int max_amount) {
        this.max_amount = max_amount;
        active = true;
        amount = 0;
    }

    @Override
    public void action(Cell cell) {

        if (!active) return;
        if (amount<=0) {
            active = false;
            return;
        }

        if (amount<max_amount) amount++;

    }

    @Override
    public boolean isEaten() {
        if (!active) return false;

        if(amount>0) {
            amount--;
            return true;
        }else{
            active = false;
            return false;
        }
    }



}
