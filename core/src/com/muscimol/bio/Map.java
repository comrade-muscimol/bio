package com.muscimol.bio;

import java.util.ArrayList;
import java.util.LinkedList;
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
    private List<List<Cell>> map;

    public Map(int x, int y){
        map = new LinkedList<>();
        for (int i = 0; i<x;i++){
            List<Cell> column = new LinkedList<>();
            for (int o=0; o<y; o++){
                column.add(new Cell(i, o));
            }
            map.add(column);
        }
    }

    public synchronized Cell get(int x, int y){
        return map.get(x).get(y);
    }
    public synchronized void set(int x, int y, Cell cell) {
        map.get(x).set(y, cell);
    }
    public List<List<Cell>> getMap(){
        return map;
    }
    public List<Cell> getNear(int x, int y){

        List<Cell> out = new ArrayList<>();

        for(Directions direction :  Directions.values()){

            try{
                out.add(map.get(x+direction.x).get(y+direction.y));

            }catch (Exception e){

            }
        }

        return out;
    }
    public synchronized void action(){

    }
}
