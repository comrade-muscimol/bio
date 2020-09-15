package com.muscimol.bio.creature;

public enum Consuments_4 {
    SMALL(2),
    MIDDLE(4),
    BIG(6);
    int max_satiety;
    Consuments_4(int max_satiety){
        this.max_satiety = max_satiety;
    }
}
