package com.muscimol.bio.generate;

import com.muscimol.bio.Cell;
import com.muscimol.bio.Directions;
import com.muscimol.bio.creature.Consument_1;
import com.muscimol.bio.creature.Consument_2;
import com.muscimol.bio.creature.Consument_3;
import com.muscimol.bio.creature.Consument_4;
import com.muscimol.bio.creature.Producent;
import com.muscimol.bio.creature.Reducent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Map {
    private long lastUpdated;

    private static Map instance;
    public static Map getInstance(){
        if(instance==null){
            instance = new Map();
        }

        return instance;
    }
    public static synchronized void reset(){
        instance = null;
        instance = new Map();
    }
    private List<List<Cell>> map;

    public Map(){
        map = new LinkedList<>();
        for (int i = 0; i< GeneratorSettings.x; i++){
            List<Cell> column = new LinkedList<>();
            for (int o=0; o<GeneratorSettings.y; o++){
                column.add(new Cell(i, o));
            }
            map.add(column);
        }

        set_reducent();
        System.out.println("-----------");

        set_producent();
        System.out.println("-----------");

        set_consument_1();
        System.out.println("-----------");

        set_consument_2();
        System.out.println("-----------");

        set_consument_3();
        System.out.println("-----------");

        set_consument_4();
        System.out.println("-----------");

        lastUpdated = new Date().getTime();
    }

    public long getLastUpdateTime(){
        return lastUpdated;
    }

    public synchronized Cell get(int x, int y){
        return map.get(x).get(y);
    }
    public  void set(int x, int y, Cell cell) {
        map.get(x).set(y, cell);
    }
    public synchronized List<List<Cell>> getMap(){
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
        int height = map.size();
        int width = map.get(0).size();

        for(int i=0; i<height; i++){
            for(int o = 0; o<width; o++){
                get(i, o).action();
            }
        }

        lastUpdated = new Date().getTime();
    }


    private void set_reducent(){
        System.out.println("set_reducent{");

        List<Cell> cells = new ArrayList<>();

        int height = map.size();
        int width = map.get(0).size();
        System.out.println("height:"+height+"-width:"+width);

        for(int i=0; i<height; i++){
            for(int o = 0; o<width; o++){
                cells.add(map.get(i).get(o));
            }
        }

        int field = cells.size(); // до 46к на 46к примерно 2млрд
        System.out.println("field:"+field);

        int units = (int)(
                ((double)field/(double)100)
                *
                GeneratorSettings.reducent_percent);

        if(units<=0) units=1;
        System.out.println("units:"+units);
        int counter = 0;

        while(counter<units){
            if (cells.isEmpty())return;
            Collections.shuffle(cells);

            if(
                    cells.get(0).isEmpty()
            ){

                int cell_x = cells.get(0).getX();
                int cell_y = cells.get(0).getY();
                System.out.println("cell correct:"+cell_x+"-"+cell_y);


                Cell temp = get(cell_x, cell_y);

                if(temp.getThing()==null){
                    System.out.println("info_before:"+temp.getImageName());
                }else{
                    System.out.println("info_before:"+temp.getThing().getImageName());
                }
                cells.remove(0);
                temp.setThing(Reducent.createNew());

                set(cell_x, cell_y, temp);
                System.out.println("info_after:"+get(cell_x, cell_y).getThing().getImageName());

                counter++;

            }else{
                System.out.println("cell incorrect:"+cells.get(0).getX()+"-"+cells.get(0).getX());
                cells.remove(0);
            }

        }
        System.out.println("}");
    }
    private void set_producent(){
        System.out.println("set_producent{");

        List<Cell> cells = new ArrayList<>();

        int height = map.size();
        int width = map.get(0).size();
        System.out.println("height:"+height+"-width:"+width);

        for(int i=0; i<height; i++){
            for(int o = 0; o<width; o++){
                cells.add(map.get(i).get(o));
            }
        }

        int field = cells.size(); // до 46к на 46к примерно 2млрд
        System.out.println("field:"+field);

        int units = (int)(
                ((double)field/(double)100)
                        *
                        GeneratorSettings.producent_percent);

        if(units<=0) units=1;
        System.out.println("units:"+units);
        int counter = 0;

        while(counter<units){
            if (cells.isEmpty())return;
            Collections.shuffle(cells);

            if(
                    cells.get(0).isEmpty()
            ){

                int cell_x = cells.get(0).getX();
                int cell_y = cells.get(0).getY();
                System.out.println("cell correct:"+cell_x+"-"+cell_y);


                Cell temp = get(cell_x, cell_y);

                if(temp.getThing()==null){
                    System.out.println("info_before:"+temp.getImageName());
                }else{
                    System.out.println("info_before:"+temp.getThing().getImageName());
                }
                cells.remove(0);
                temp.setThing(Producent.createNew());

                set(cell_x, cell_y, temp);
                System.out.println("info_after:"+get(cell_x, cell_y).getThing().getImageName());

                counter++;

            }else{
                System.out.println("cell incorrect:"+cells.get(0).getX()+"-"+cells.get(0).getX());
                cells.remove(0);
            }

        }
        System.out.println("}");
    }
    private void set_consument_1(){
        System.out.println("set_consument_1{");

        List<Cell> cells = new ArrayList<>();

        int height = map.size();
        int width = map.get(0).size();
        System.out.println("height:"+height+"-width:"+width);

        for(int i=0; i<height; i++){
            for(int o = 0; o<width; o++){
                cells.add(map.get(i).get(o));
            }
        }

        int field = cells.size(); // до 46к на 46к примерно 2млрд
        System.out.println("field:"+field);

        int units = (int)(
                ((double)field/(double)100)
                        *
                        GeneratorSettings.consument_1_percent);

        if(units<=0) units=1;
        System.out.println("units:"+units);
        int counter = 0;

        while(counter<units){
            if (cells.isEmpty())return;
            Collections.shuffle(cells);

            if(
                    cells.get(0).isEmpty()
            ){

                int cell_x = cells.get(0).getX();
                int cell_y = cells.get(0).getY();
                System.out.println("cell correct:"+cell_x+"-"+cell_y);


                Cell temp = get(cell_x, cell_y);

                if(temp.getThing()==null){
                    System.out.println("info_before:"+temp.getImageName());
                }else{
                    System.out.println("info_before:"+temp.getThing().getImageName());
                }
                cells.remove(0);
                temp.setThing(Consument_1.createNew());

                set(cell_x, cell_y, temp);
                System.out.println("info_after:"+get(cell_x, cell_y).getThing().getImageName());

                counter++;

            }else{
                System.out.println("cell incorrect:"+cells.get(0).getX()+"-"+cells.get(0).getX());
                cells.remove(0);
            }

        }
        System.out.println("}");
    }
    private void set_consument_2(){
        System.out.println("set_consument_2{");

        List<Cell> cells = new ArrayList<>();

        int height = map.size();
        int width = map.get(0).size();
        System.out.println("height:"+height+"-width:"+width);

        for(int i=0; i<height; i++){
            for(int o = 0; o<width; o++){
                cells.add(map.get(i).get(o));
            }
        }

        int field = cells.size(); // до 46к на 46к примерно 2млрд
        System.out.println("field:"+field);

        int units = (int)(
                ((double)field/(double)100)
                        *
                        GeneratorSettings.consument_2_percent);

        if(units<=0) units=1;
        System.out.println("units:"+units);
        int counter = 0;

        while(counter<units){
            if (cells.isEmpty())return;
            Collections.shuffle(cells);

            if(
                    cells.get(0).isEmpty()
            ){

                int cell_x = cells.get(0).getX();
                int cell_y = cells.get(0).getY();
                System.out.println("cell correct:"+cell_x+"-"+cell_y);


                Cell temp = get(cell_x, cell_y);

                if(temp.getThing()==null){
                    System.out.println("info_before:"+temp.getImageName());
                }else{
                    System.out.println("info_before:"+temp.getThing().getImageName());
                }
                cells.remove(0);
                temp.setThing(Consument_2.createNew());

                set(cell_x, cell_y, temp);
                System.out.println("info_after:"+get(cell_x, cell_y).getThing().getImageName());

                counter++;

            }else{
                System.out.println("cell incorrect:"+cells.get(0).getX()+"-"+cells.get(0).getX());
                cells.remove(0);
            }

        }
        System.out.println("}");
    }
    private void set_consument_3(){
        System.out.println("set_consument_3{");

        List<Cell> cells = new ArrayList<>();

        int height = map.size();
        int width = map.get(0).size();
        System.out.println("height:"+height+"-width:"+width);

        for(int i=0; i<height; i++){
            for(int o = 0; o<width; o++){
                cells.add(map.get(i).get(o));
            }
        }

        int field = cells.size(); // до 46к на 46к примерно 2млрд
        System.out.println("field:"+field);

        int units = (int)(
                ((double)field/(double)100)
                        *
                        GeneratorSettings.consument_3_percent);

        if(units<=0) units=1;
        System.out.println("units:"+units);
        int counter = 0;

        while(counter<units){
            if (cells.isEmpty())return;
            Collections.shuffle(cells);

            if(
                    cells.get(0).isEmpty()
            ){

                int cell_x = cells.get(0).getX();
                int cell_y = cells.get(0).getY();
                System.out.println("cell correct:"+cell_x+"-"+cell_y);


                Cell temp = get(cell_x, cell_y);

                if(temp.getThing()==null){
                    System.out.println("info_before:"+temp.getImageName());
                }else{
                    System.out.println("info_before:"+temp.getThing().getImageName());
                }
                cells.remove(0);
                temp.setThing(Consument_3.createNew());

                set(cell_x, cell_y, temp);
                System.out.println("info_after:"+get(cell_x, cell_y).getThing().getImageName());

                counter++;

            }else{
                System.out.println("cell incorrect:"+cells.get(0).getX()+"-"+cells.get(0).getX());
                cells.remove(0);
            }

        }
        System.out.println("}");
    }
    private void set_consument_4(){
        System.out.println("set_consument_4{");

        List<Cell> cells = new ArrayList<>();

        int height = map.size();
        int width = map.get(0).size();
        System.out.println("height:"+height+"-width:"+width);

        for(int i=0; i<height; i++){
            for(int o = 0; o<width; o++){
                cells.add(map.get(i).get(o));
            }
        }

        int field = cells.size(); // до 46к на 46к примерно 2млрд
        System.out.println("field:"+field);

        int units = (int)(
                ((double)field/(double)100)
                        *
                        GeneratorSettings.consument_4_percent);

        if(units<=0) units=1;
        System.out.println("units:"+units);
        int counter = 0;

        while(counter<units){
            if (cells.isEmpty())return;
            Collections.shuffle(cells);

            if(
                    cells.get(0).isEmpty()
            ){

                int cell_x = cells.get(0).getX();
                int cell_y = cells.get(0).getY();
                System.out.println("cell correct:"+cell_x+"-"+cell_y);


                Cell temp = get(cell_x, cell_y);

                if(temp.getThing()==null){
                    System.out.println("info_before:"+temp.getImageName());
                }else{
                    System.out.println("info_before:"+temp.getThing().getImageName());
                }
                cells.remove(0);
                temp.setThing(Consument_4.createNew());

                set(cell_x, cell_y, temp);
                System.out.println("info_after:"+get(cell_x, cell_y).getThing().getImageName());

                counter++;

            }else{
                System.out.println("cell incorrect:"+cells.get(0).getX()+"-"+cells.get(0).getX());
                cells.remove(0);
            }

        }
        System.out.println("}");
    }
}
