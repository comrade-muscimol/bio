package com.muscimol.bio.creature;

public enum Consuments_1 {
    SMALL(2,10,  "consument_1_1_cell"),
    MIDDLE(4, 5,"consument_1_2_cell"),
    BIG(6,2, "consument_1_3_cell");

    int max_satiety;
    int reproduction_chance;
    String image_atlas_name;

    Consuments_1(int max_satiety,int reproduction_chance,  String image_atlas_name){
        this.max_satiety = max_satiety;
        this.image_atlas_name = image_atlas_name;
        this.reproduction_chance = reproduction_chance;
    }
}
