package com.muscimol.bio.creature;

public enum Producents {
    SMALL(1, "producent_1_cell"),
    MIDDLE(2, "producent_2_cell"),
    BIG(4, "producent_3_cell");

    public int max_amount;
    public String image_atlas_name;

    Producents(int max_amount, String image_atlas_name){
        this.max_amount = max_amount;
        this.image_atlas_name = image_atlas_name;
    }



}
