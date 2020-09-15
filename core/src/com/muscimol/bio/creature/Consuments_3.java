package com.muscimol.bio.creature;

public enum Consuments_3 {
    SMALL(2, "consument_3_1_cell"),
    MIDDLE(4, "consument_3_1_cell"),
    BIG(6, "consument_3_1_cell");

    int max_satiety;
    String image_atlas_name;

    Consuments_3(int max_satiety, String image_atlas_name){
        this.max_satiety = max_satiety;
        this.image_atlas_name = image_atlas_name;
    }
}
