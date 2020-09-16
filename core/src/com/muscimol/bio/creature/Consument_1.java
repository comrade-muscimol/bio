package com.muscimol.bio.creature;

import com.muscimol.bio.Cell;
import com.muscimol.bio.generate.Map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Consument_1 extends Consument {

    public static Consument_1 createNew() {
        int index = ThreadLocalRandom.current().nextInt(Consuments_1.values().length);
        return new Consument_1(Consuments_1.values()[index].max_satiety, Consuments_1.values()[index].reproduction_chance, Consuments_1.values()[index].image_atlas_name);
    }
    public static Consument_1 createNew(int max_satiety, int reproduction_chance, String image_atlas_name) {
        return new Consument_1(max_satiety, reproduction_chance, image_atlas_name);
    }
    private Consument_1(int max_satiety, int reproduction_chance, String image_atlas_name ) {
        this.max_satiety = max_satiety;
        this.image_atlas_name = image_atlas_name;
        satiety = max_satiety;
        this.active = true;
        this.reproduction_chance = reproduction_chance;

    }

    protected boolean isEdible(Thing thing){
        if (thing instanceof Producent) return true;
        else return false;
    }

    @Override
    Thing getClone() {
        return (Thing) Consument_1.createNew(max_satiety, reproduction_chance, image_atlas_name);
    }
}

