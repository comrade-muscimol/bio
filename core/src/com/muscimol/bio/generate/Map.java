package com.muscimol.bio.generate;

import com.badlogic.gdx.Game;
import com.muscimol.bio.Cell;
import com.muscimol.bio.Directions;
import com.muscimol.bio.creature.Consument_1;
import com.muscimol.bio.creature.Consument_2;
import com.muscimol.bio.creature.Consument_3;
import com.muscimol.bio.creature.Consument_4;
import com.muscimol.bio.creature.Consuments_1;
import com.muscimol.bio.creature.Consuments_2;
import com.muscimol.bio.creature.Consuments_3;
import com.muscimol.bio.creature.Consuments_4;
import com.muscimol.bio.creature.Producent;
import com.muscimol.bio.creature.Producents;
import com.muscimol.bio.creature.Reducent;
import com.muscimol.bio.creature.Reducents;
import com.muscimol.bio.creature.Thing;
import com.muscimol.bio.ui.LoadingScreen;
import com.muscimol.bio.ui.MapScreen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Map {

    public volatile int reducent_done;
    public volatile int reducent_all;

    public volatile int producent_done;
    public volatile int producent_all;

    public volatile int consument_1_done;
    public volatile int consument_1_all;

    public volatile int consument_2_done;
    public volatile int consument_2_all;

    public volatile int consument_3_done;
    public volatile int consument_3_all;

    public volatile int consument_4_done;
    public volatile int consument_4_all;


    private long lastUpdated;

    private List<List<Cell>> earth;

    public Map(int x){
        reducent_done = 0;
        reducent_all = 0;

        producent_done = 0;
        producent_all = 0;

        consument_1_done = 0;
        consument_1_all = 0;

        consument_2_done = 0;
        consument_2_all = 0;

        consument_3_done = 0;
        consument_3_all = 0;

        consument_4_done = 0;
        consument_4_all = 0;

        earth = new ArrayList<>();
        for (int i = 0; i< x; i++){
            List<Cell> column = new ArrayList<>();
            for (int o = 0; o< x; o++){
                column.add(new Cell(i, o, this));
            }
            earth.add(column);
        }

        lastUpdated = new Date().getTime();
    }

    public long getLastUpdateTime(){
        return lastUpdated;
    }

    public synchronized Cell get(int x, int y){
        return earth.get(x).get(y);
    }
    public synchronized   void set(int x, int y, Cell cell) {
        earth.get(x).set(y, cell);
    }
    public List<List<Cell>> getEarth(){
        return earth;
    }
    public List<Cell> getNear(int x, int y){

        List<Cell> out = new ArrayList<>();

        for(Directions direction :  Directions.values()){

            try{
                out.add(earth.get(x+direction.x).get(y+direction.y));

            }catch (Exception e){

            }
        }

        return out;
    }
    public synchronized void action(){
        int height = earth.size();
        int width = earth.get(0).size();

        for(int i=0; i<height; i++){
            for(int o = 0; o<width; o++){
                get(i, o).action();
            }
        }

        lastUpdated = new Date().getTime();
    }

    public void generate_default(Game game){
        System.out.println("начало стандартной генерации");

        int fields = mapToArray(earth).size();

        int part;

        reducent_all = (int)(((double)fields/100)*GeneratorDefaultSettings.reducent_percent);
        producent_all = (int)(((double)fields/100)*GeneratorDefaultSettings.producent_percent);
        consument_1_all = (int)(((double)fields/100)*GeneratorDefaultSettings.consument_1_percent);
        consument_2_all = (int)(((double)fields/100)*GeneratorDefaultSettings.consument_2_percent);
        consument_3_all = (int)(((double)fields/100)*GeneratorDefaultSettings.consument_3_percent);
        consument_4_all = (int)(((double)fields/100)*GeneratorDefaultSettings.consument_4_percent);

        switch (GeneratorDefaultSettings.reducent_varieties){
            case 1:
                set_things(Reducent.createRandom(), reducent_all);
                break;
            case 2:
                Reducent first;
                Reducent second;

                part = reducent_all/2;

                first = Reducent.createRandom();

                do{
                    second = Reducent.createRandom();
                }while(second.equals(first));

                set_things(first, part);
                set_things(second, part);
                break;
            case 3:
                part = reducent_all/3;
                set_things(new Reducent(Reducents.SMALL.counts, Reducents.SMALL.image_atlas_name), part);
                set_things(new Reducent(Reducents.MIDDLE.counts, Reducents.MIDDLE.image_atlas_name), part);
                set_things(new Reducent(Reducents.BIG.counts, Reducents.BIG.image_atlas_name), part);
                break;
            default:
                System.err.println("default");
        }
        switch (GeneratorDefaultSettings.producent_varieties){
            case 1:
                set_things(Producent.createRandom(), producent_all);
                break;
            case 2:
                Producent first;
                Producent second;

                part = producent_all/2;

                first = Producent.createRandom();

                do{
                    second = Producent.createRandom();
                }while(second.equals(first));

                set_things(first, part);
                set_things(second, part);
                break;
            case 3:
                part = producent_all/3;
                set_things(new Producent(Producents.SMALL.max_amount, Producents.SMALL.image_atlas_name), part);
                set_things(new Producent(Producents.MIDDLE.max_amount, Producents.MIDDLE.image_atlas_name), part);
                set_things(new Producent(Producents.BIG.max_amount, Producents.BIG.image_atlas_name), part);
                break;
            default:
                System.err.println("default");
        }
        switch (GeneratorDefaultSettings.consument_1_varieties){
            case 1:
                set_things( Consument_1.createRandom(), consument_1_all);
                break;
            case 2:
                Consument_1 first;
                Consument_1 second;

                part = consument_1_all/2;

                first = Consument_1.createRandom();

                do{
                    second = Consument_1.createRandom();
                }while(second.equals(first));

                set_things(first, part);
                set_things(second, part);
                break;
            case 3:
                part = consument_1_all/3;
                set_things( Consument_1.createNew(Consuments_1.SMALL), part);
                set_things(Consument_1.createNew(Consuments_1.MIDDLE), part);
                set_things(Consument_1.createNew(Consuments_1.BIG), part);
                break;
            default:
                System.err.println("default");
        }
        switch (GeneratorDefaultSettings.consument_2_varieties){
            case 1:
                set_things( Consument_2.createRandom(), consument_2_all);
                break;
            case 2:
                Consument_2 first;
                Consument_2 second;

                part = consument_2_all/2;

                first = Consument_2.createRandom();

                do{
                    second = Consument_2.createRandom();
                }while(second.equals(first));

                set_things(first, part);
                set_things(second, part);
                break;
            case 3:
                part = consument_2_all/3;
                set_things( Consument_2.createNew(Consuments_2.SMALL), part);
                set_things(Consument_2.createNew(Consuments_2.MIDDLE), part);
                set_things(Consument_2.createNew(Consuments_2.BIG), part);
                break;
            default:
                System.err.println("default");
        }
        switch (GeneratorDefaultSettings.consument_3_varieties){
            case 1:
                set_things( Consument_3.createRandom(), consument_3_all);
                break;
            case 2:
                Consument_3 first;
                Consument_3 second;

                part = consument_3_all/2;

                first = Consument_3.createRandom();

                do{
                    second = Consument_3.createRandom();
                }while(second.equals(first));

                set_things(first, part);
                set_things(second, part);
                break;
            case 3:
                part = consument_3_all/3;
                set_things( Consument_3.createNew(Consuments_3.SMALL), part);
                set_things(Consument_3.createNew(Consuments_3.MIDDLE), part);
                set_things(Consument_3.createNew(Consuments_3.BIG), part);
                break;
            default:
                System.err.println("default");
        }
        switch (GeneratorDefaultSettings.consument_4_varieties){
            case 1:
                set_things( Consument_4.createRandom(), consument_4_all);
                break;
            case 2:
                Consument_4 first;
                Consument_4 second;

                part = consument_4_all/2;

                first = Consument_4.createRandom();

                do{
                    second = Consument_4.createRandom();
                }while(second.equals(first));

                set_things(first, part);
                set_things(second, part);
                break;
            case 3:
                part = consument_4_all/3;
                set_things( Consument_4.createNew(Consuments_4.SMALL), part);
                set_things(Consument_4.createNew(Consuments_4.MIDDLE), part);
                set_things(Consument_4.createNew(Consuments_4.BIG), part);
                break;
            default:
                System.err.println("default");
        }
        System.out.println("окончание стандартной генерации");
    }

    public void set_things(Thing thing, int units){

        List<Cell> cells;
        cells = mapToArray(earth);

        int counter = 0;

        while(counter<units){
            if (cells.isEmpty())return;
            Collections.shuffle(cells);

            if(
                    cells.get(0).isEmpty()
            ){

                int cell_x = cells.get(0).getX();
                int cell_y = cells.get(0).getY();

                Cell temp = get(cell_x, cell_y);

                temp.setThing(thing.duplicate());

                set(cell_x, cell_y, temp);

                counter++;

                if(thing instanceof Reducent){
                    reducent_done++;
                }else if(thing instanceof Producent){
                    producent_done++;
                }else if(thing instanceof Consument_1){
                    consument_1_done++;
                }else if(thing instanceof Consument_2){
                    consument_2_done++;
                }else if(thing instanceof Consument_3){
                    consument_3_done++;
                }else if(thing instanceof Consument_4){
                    consument_4_done++;
                }

            }
            cells.remove(0);

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    private static List<Cell> mapToArray(List<List<Cell>> map){
        List<Cell> cells = new ArrayList<>();

        int height = map.size();
        int width = map.get(0).size();

        for(int i=0; i<height; i++){
            for(int o = 0; o<width; o++){
                cells.add(map.get(i).get(o));
            }
        }

        return cells;
    }
}
