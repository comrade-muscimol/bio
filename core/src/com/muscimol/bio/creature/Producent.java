package com.muscimol.bio.creature;

import com.muscimol.bio.Cell;

import java.util.concurrent.ThreadLocalRandom;

public class Producent extends Thing {

    public static Producent createRandom() {
        int index = ThreadLocalRandom.current().nextInt(Producents.values().length);
        return new Producent(Producents.values()[index].max_amount, Producents.values()[index].image_atlas_name);
    }

    int amount;
    int max_amount;


    public Producent(int max_amount, String image_atlas_name) {
        this.max_amount = max_amount;
        this.image_atlas_name = image_atlas_name;
        active = true;
        amount = 1;
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
            if(amount<=0) active=false;
            return true;
        }else{
            active = false;
            return false;

        }
    }
    @Override
    public Thing duplicate() {
        return new Producent(max_amount, image_atlas_name);
    }
}
