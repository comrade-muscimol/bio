package com.muscimol.bio.creature;

public enum Producents {
    SMALL(3),
    MIDDLE(5),
    BIG(7);
    int max_amount;
    Producents(int max_amount){
        this.max_amount = max_amount;
    }
}
