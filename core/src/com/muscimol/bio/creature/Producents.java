package com.muscimol.bio.creature;

public enum Producents {
    SMALL(3, "producent_1_cell"),
    MIDDLE(5, "producent_2_cell"),
    BIG(7, "producent_3_cell");

    int max_amount;
    String image_atlas_name;

    Producents(int max_amount, String image_atlas_name){
        this.max_amount = max_amount;
        this.image_atlas_name = image_atlas_name;
    }
}
