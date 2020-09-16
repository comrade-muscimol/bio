package com.muscimol.bio.creature;

public enum Consuments_4 {
    SMALL(2,25, "consument_4_1_cell"),
    MIDDLE(4,13, "consument_4_2_cell"),
    BIG(6,6, "consument_4_3_cell");

    int max_satiety;
    int reproduction_chance;
    String image_atlas_name;

    Consuments_4(int max_satiety,int reproduction_chance,  String image_atlas_name){
        this.max_satiety = max_satiety;
        this.image_atlas_name = image_atlas_name;
        this.reproduction_chance = reproduction_chance;
    }
}
