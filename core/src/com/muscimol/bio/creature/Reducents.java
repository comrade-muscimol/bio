package com.muscimol.bio.creature;

public enum Reducents {
    SMALL(3, "reducent_1_cell"),
    MIDDLE(5,"reducent_2_cell"),
    BIG(7, "reducent_3_cell");

    int counts;
    String image_atlas_name;
    Reducents(int counts, String image_atlas_name){
        this.counts = counts;
        this.image_atlas_name = image_atlas_name;
    }
}
