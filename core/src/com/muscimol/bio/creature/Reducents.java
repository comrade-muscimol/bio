package com.muscimol.bio.creature;

public enum Reducents {
    SMALL(3),
    MIDDLE(5),
    BIG(7);
    int counts;
    Reducents(int counts){
        this.counts = counts;
    }
}
