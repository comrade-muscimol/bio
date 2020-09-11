package com.muscimol.bio;

public class Reducent implements Thing {

    private boolean active;
    private int counter;

    @Override
    public void action() {
        if(counter>0){
            counter--;
        }else{
            active = false;
        }
    }

    @Override
    public boolean is_active() {
        return active;
    }
}
