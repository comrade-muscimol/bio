package com.muscimol.bio.generate;

import com.muscimol.bio.Cell;
import com.muscimol.bio.Map;
import com.muscimol.bio.creature.Consument_1;
import com.muscimol.bio.creature.Consument_2;
import com.muscimol.bio.creature.Consument_3;
import com.muscimol.bio.creature.Consument_4;
import com.muscimol.bio.creature.Producent;
import com.muscimol.bio.creature.Reducent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MapGenerator {

    public static void generate(int x, int y,
                                double reducent_percent,
                                double producent_percent,
                                double consument_1_percent,
                                double consument_2_percent,
                                double consument_3_percent,
                                double consument_4_percent
    ){
        map_reset(x, y);
        set_reducent(reducent_percent);
        set_producent(producent_percent);
        set_consument_1(consument_1_percent);
        set_consument_2(consument_2_percent);
        set_consument_3(consument_3_percent);
        set_consument_4(consument_4_percent);
    }

    private static void map_reset(int x, int y){
        Map.reset(x, y);
    }
    private static void set_reducent(double percent){

        List<Cell> cells = new ArrayList<>();
        int height = Map.getInstance().getMap().size();
        int width = Map.getInstance().getMap().get(0).size();

        for(int i=0; i<height; i++){
            for(int o = 0; o<width; o++){
                cells.add(Map.getInstance().getMap().get(i).get(o));
            }
        }

        int field = cells.size(); // до 46к на 46к примерно 2млрд

        int units = (int)((double)field*percent);
        if(units<=0) units=1;

        int counter = 0;

        while(counter<=units){

            if (cells.isEmpty())return;

            Collections.shuffle(cells);

            if(
                    cells.isEmpty()
            ){

                int cell_x = cells.get(0).getX();
                int cell_y = cells.get(0).getY();

                Map.getInstance().get(cell_x, cell_y).setThing(Reducent.createNew());

                cells.remove(0);
                counter++;

            }else{
                cells.remove(0);
            }


        }
    }
    private static void set_producent(double percent){

        List<Cell> cells = new ArrayList<>();
        int height = Map.getInstance().getMap().size();
        int width = Map.getInstance().getMap().get(0).size();

        for(int i=0; i<height; i++){
            for(int o = 0; o<width; o++){
                cells.add(Map.getInstance().getMap().get(i).get(o));
            }
        }

        int field = cells.size(); // до 46к на 46к примерно 2млрд

        int units = (int)((double)field*percent);
        if(units<=0) units=1;

        int counter = 0;

        while(counter<=units){

            if (cells.isEmpty())return;

            Collections.shuffle(cells);

            if(
                    cells.isEmpty()
            ){

                int cell_x = cells.get(0).getX();
                int cell_y = cells.get(0).getY();

                Map.getInstance().get(cell_x, cell_y).setThing(Producent.createNew());

                cells.remove(0);
                counter++;

            }else{
                cells.remove(0);
            }

        }
    }
    private static void set_consument_1(double percent){
        List<Cell> cells = new ArrayList<>();
        int height = Map.getInstance().getMap().size();
        int width = Map.getInstance().getMap().get(0).size();

        for(int i=0; i<height; i++){
            for(int o = 0; o<width; o++){
                cells.add(Map.getInstance().getMap().get(i).get(o));
            }
        }

        int field = cells.size(); // до 46к на 46к примерно 2млрд

        int units = (int)((double)field*percent);
        if(units<=0) units=1;

        int counter = 0;

        while(counter<=units){

            if (cells.isEmpty())return;

            Collections.shuffle(cells);

            if(
                    cells.isEmpty()
            ){

                int cell_x = cells.get(0).getX();
                int cell_y = cells.get(0).getY();

                Map.getInstance().get(cell_x, cell_y).setThing(Consument_1.createNew());

                cells.remove(0);
                counter++;

            }else{
                cells.remove(0);
            }

        }
    }
    private static void set_consument_2(double percent){
        List<Cell> cells = new ArrayList<>();
        int height = Map.getInstance().getMap().size();
        int width = Map.getInstance().getMap().get(0).size();

        for(int i=0; i<height; i++){
            for(int o = 0; o<width; o++){
                cells.add(Map.getInstance().getMap().get(i).get(o));
            }
        }

        int field = cells.size(); // до 46к на 46к примерно 2млрд

        int units = (int)((double)field*percent);
        if(units<=0) units=1;

        int counter = 0;

        while(counter<=units){

            if (cells.isEmpty())return;

            Collections.shuffle(cells);

            if(
                    cells.isEmpty()
            ){

                int cell_x = cells.get(0).getX();
                int cell_y = cells.get(0).getY();

                Map.getInstance().get(cell_x, cell_y).setThing(Consument_2.createNew());

                cells.remove(0);
                counter++;

            }else{
                cells.remove(0);
            }

        }
    }
    private static void set_consument_3(double percent){
        List<Cell> cells = new ArrayList<>();
        int height = Map.getInstance().getMap().size();
        int width = Map.getInstance().getMap().get(0).size();

        for(int i=0; i<height; i++){
            for(int o = 0; o<width; o++){
                cells.add(Map.getInstance().getMap().get(i).get(o));
            }
        }

        int field = cells.size(); // до 46к на 46к примерно 2млрд

        int units = (int)((double)field*percent);
        if(units<=0) units=1;

        int counter = 0;

        while(counter<=units){

            if (cells.isEmpty())return;

            Collections.shuffle(cells);

            if(
                    cells.isEmpty()
            ){

                int cell_x = cells.get(0).getX();
                int cell_y = cells.get(0).getY();

                Map.getInstance().get(cell_x, cell_y).setThing(Consument_3.createNew());

                cells.remove(0);
                counter++;

            }else{
                cells.remove(0);
            }

        }
    }
    private static void set_consument_4(double percent){
        List<Cell> cells = new ArrayList<>();
        int height = Map.getInstance().getMap().size();
        int width = Map.getInstance().getMap().get(0).size();

        for(int i=0; i<height; i++){
            for(int o = 0; o<width; o++){
                cells.add(Map.getInstance().getMap().get(i).get(o));
            }
        }

        int field = cells.size(); // до 46к на 46к примерно 2млрд

        int units = (int)((double)field*percent);
        if(units<=0) units=1;

        int counter = 0;

        while(counter<=units){

            if (cells.isEmpty())return;

            Collections.shuffle(cells);

            if(
                    cells.isEmpty()
            ){

                int cell_x = cells.get(0).getX();
                int cell_y = cells.get(0).getY();

                Map.getInstance().get(cell_x, cell_y).setThing(Consument_4.createNew());

                cells.remove(0);
                counter++;

            }else{
                cells.remove(0);
            }

        }
    }


}
