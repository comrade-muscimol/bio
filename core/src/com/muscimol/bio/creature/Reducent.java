package com.muscimol.bio.creature;

import com.muscimol.bio.Cell;

import java.util.concurrent.ThreadLocalRandom;

public class Reducent extends Thing {

    public static Reducent createNew() {
        int index = ThreadLocalRandom.current().nextInt(Reducents.values().length);
        return new Reducent(Reducents.values()[index].counts);
    }

    private boolean active;
    private int counter;

    private Reducent(int counter) {
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
