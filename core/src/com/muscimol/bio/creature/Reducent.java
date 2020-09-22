package com.muscimol.bio.creature;

import com.muscimol.bio.Cell;

import java.util.concurrent.ThreadLocalRandom;

public class Reducent extends Thing {

    public static Reducent createRandom() {
        int index = ThreadLocalRandom.current().nextInt(Reducents.values().length);
        return new Reducent(Reducents.values()[index].counts, Reducents.values()[index].image_atlas_name);
    }

    private int counter;

    public Reducent(int counter, String image_atlas_name) {
        this.counter = counter;
        this.image_atlas_name = image_atlas_name;
        active = true;
    }

    @Override
    public Thing duplicate() {
        return new Reducent(counter, image_atlas_name);
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
