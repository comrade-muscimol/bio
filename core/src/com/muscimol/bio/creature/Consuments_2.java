package com.muscimol.bio.creature;

public enum Consuments_2 {
    SMALL(2,15,  "consument_2_1_cell"),
    MIDDLE(4,10, "consument_2_2_cell"),
    BIG(6,5, "consument_2_3_cell");

    int max_satiety;
    int reproduction_chance;
    String image_atlas_name;

    Consuments_2(int max_satiety,int reproduction_chance,  String image_atlas_name){
        this.max_satiety = max_satiety;
        this.image_atlas_name = image_atlas_name;
        this.reproduction_chance = reproduction_chance;
    }
}
