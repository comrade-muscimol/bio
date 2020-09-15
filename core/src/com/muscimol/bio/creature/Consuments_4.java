package com.muscimol.bio.creature;

public enum Consuments_4 {
    SMALL(2, "consument_4_1_cell"),
    MIDDLE(4, "consument_4_1_cell"),
    BIG(6, "consument_4_1_cell");

    int max_satiety;
    String image_atlas_name;

    Consuments_4(int max_satiety, String image_atlas_name){
        this.max_satiety = max_satiety;
        this.image_atlas_name = image_atlas_name;
    }
}
