package com.muscimol.bio;

public class Reducent extends Thing {

    private boolean active;
    private int counter;

    public Reducent(int counter) {
        this.counter = counter;
        active = true;
    }

    @Override
    public void action(Cell cell) {
        if(counter>0){
            counter--;
        }else{
            active = false;
        }
    }

    @Override
    public boolean isEaten() {
        return false;
    }


}
