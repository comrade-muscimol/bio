package com.muscimol.bio;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private static Map instance;
    public static Map getInstance(){
        if(instance==null) reset(1000, 1000);
        return instance;
    }
    public static void reset(int x, int y){
        instance = new Map(x, y);
    }
    private Cell[][] map;

    public Map(int x, int y){
        map = new Cell[x][y];
    }

    public Cell get(int x, int y){
        return map[x][y];
    }
    public synchronized void set(int x, int y, Cell cell) {
        map[x][y] = cell;
    }
    public Cell[][] getMap(){
        return map;
    }
    public List<Cell> getNear(int x, int y){

        List<Cell> out = new ArrayList<>();

        for(Directions direction :  Directions.values()){

            try{
                out.add(map[x][y]);

            }catch (Exception e){

            }
        }

        return out;
    }
}
