package com.muscimol.bio;

public class Producent implements Thing {

    boolean active;

    int amount;
    int max_amount;


    @Override
    public void action() {

        if (!active) return;
        if (amount<=0) {
            active = false;
            return;
        }

        if (amount<max_amount) amount++;

    }

    @Override
    public boolean is_active() {
        return active;
    }
    public boolean try_feed(){
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
