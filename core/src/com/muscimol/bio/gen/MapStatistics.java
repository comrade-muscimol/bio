package com.muscimol.bio.gen;

public class MapStatistics {
    public boolean game_over;

    public int turns;

    public int reducent;
    public int producent;
    public int consument_1;
    public int consument_2;
    public int consument_3;
    public int consument_4;

    MapStatistics(){
        game_over = false;

        turns = 0;

       clear();
    }

    public synchronized void clear(){
        reducent = 0;
        producent = 0;
        consument_1 = 0;
        consument_2 = 0;
        consument_3 = 0;
        consument_4 = 0;
    }
    public synchronized void check(){
        if(        reducent    <=0
                || producent   <=0
                || consument_1 <=0
                || consument_2 <=0
                || consument_3 <=0
                || consument_4 <=0)

            game_over = true;
    }
}
