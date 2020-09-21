package com.muscimol.bio.creature;

public enum Consuments_3 {
    SMALL(2,10, 10, "consument_3_1_cell"),
    MIDDLE(4, 5,15,"consument_3_2_cell"),
    BIG(6,2, 20,"consument_3_3_cell");

    int max_satiety;
    int max_moves;
    int reproduction_chance;
    String image_atlas_name;

    Consuments_3(int max_satiety,int reproduction_chance, int max_moves,  String image_atlas_name){
        this.max_satiety = max_satiety;
        this.max_moves = max_moves;
        this.image_atlas_name = image_atlas_name;
        this.reproduction_chance = reproduction_chance;
    }
}
