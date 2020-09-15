package com.muscimol.bio.creature;

public enum Consuments_2 {
    SMALL(2),
    MIDDLE(4),
    BIG(6);
    int max_satiety;
    Consuments_2(int max_satiety){
        this.max_satiety = max_satiety;
    }
}
