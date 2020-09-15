package com.muscimol.bio.creature;

public enum Consuments_1 {
    SMALL(2),
    MIDDLE(4),
    BIG(6);
    int max_satiety;
    Consuments_1(int max_satiety){
        this.max_satiety = max_satiety;
    }
}
