package com.muscimol.bio.gen;

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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Map {

    private long lastUpdated;

    private List<List<Cell>> ground;

    private GenerateInfo generateInfo;
    private  MapStatistics mapStatistics;

    public GenerateInfo getGenerateInfo() {
        return generateInfo;
    }

    public Map(int x){
       generateInfo = new GenerateInfo();
       mapStatistics = new MapStatistics();

        ground = new ArrayList<>();
        for (int i = 0; i< x; i++){
            List<Cell> column = new ArrayList<>();
            for (int o = 0; o< x; o++){
                column.add(new Cell(i, o, this));
            }
            ground.add(column);
        }

        lastUpdated = new Date().getTime();
    }

    public long getLastUpdateTime(){
        return lastUpdated;
    }

    public synchronized Cell get(int x, int y){
        return ground.get(x).get(y);
    }
    public synchronized void set(int x, int y, Cell cell) {
        ground.get(x).set(y, cell);
    }
    public List<List<Cell>> getGround(){
        return ground;
    }
    public List<Cell> getNear(int x, int y){

        List<Cell> out = new ArrayList<>();

        for(Directions direction :  Directions.values()){

            try{
                out.add(ground.get(x+direction.x).get(y+direction.y));

            }catch (Exception e){

            }
        }

        return out;
    }
    public synchronized void action(){
        int height = ground.size();
        int width = ground.get(0).size();

        for(int i=0; i<height; i++){
            for(int o = 0; o<width; o++){
                get(i, o).action();
            }
        }


        refreshStatistics();
        System.out.println(
                "game_over:"+mapStatistics.game_over+"\n"
                        +"turns:"+mapStatistics.turns+"\n"
                        +"r:"+mapStatistics.reducent+"\n"
                        +"p:"+mapStatistics.producent+"\n"
                        +"c_1:"+mapStatistics.consument_1+"\n"
                        +"c_2:"+mapStatistics.consument_2+"\n"
                        +"c_3:"+mapStatistics.consument_3+"\n"
                        +"c_4:"+mapStatistics.consument_4+"\n"

        );

        lastUpdated = new Date().getTime();
    }

    public void generate_default(Game game){


        int fields = mapToArray(ground).size();

        int part;

        generateInfo.reducent_all = (int)(((double)fields/100)*GeneratorDefaultSettings.reducent_percent);
        generateInfo.producent_all = (int)(((double)fields/100)*GeneratorDefaultSettings.producent_percent);
        generateInfo.consument_1_all = (int)(((double)fields/100)*GeneratorDefaultSettings.consument_1_percent);
        generateInfo.consument_2_all = (int)(((double)fields/100)*GeneratorDefaultSettings.consument_2_percent);
        generateInfo.consument_3_all = (int)(((double)fields/100)*GeneratorDefaultSettings.consument_3_percent);
        generateInfo.consument_4_all = (int)(((double)fields/100)*GeneratorDefaultSettings.consument_4_percent);

        switch (GeneratorDefaultSettings.reducent_varieties){
            case 1:
                set_things(Reducent.createRandom(), generateInfo.reducent_all);
                break;
            case 2:
                Reducent first;
                Reducent second;

                part = generateInfo.reducent_all/2;

                first = Reducent.createRandom();

                do{
                    second = Reducent.createRandom();
                }while(second.equals(first));

                set_things(first, part);
                set_things(second, part);
                break;
            case 3:
                part = generateInfo.reducent_all/3;
                set_things(new Reducent(Reducents.SMALL.counts, Reducents.SMALL.image_atlas_name), part);
                set_things(new Reducent(Reducents.MIDDLE.counts, Reducents.MIDDLE.image_atlas_name), part);
                set_things(new Reducent(Reducents.BIG.counts, Reducents.BIG.image_atlas_name), part);
                break;
            default:
                System.err.println("default");
        }
        switch (GeneratorDefaultSettings.producent_varieties){
            case 1:
                set_things(Producent.createRandom(), generateInfo.producent_all);
                break;
            case 2:
                Producent first;
                Producent second;

                part = generateInfo.producent_all/2;

                first = Producent.createRandom();

                do{
                    second = Producent.createRandom();
                }while(second.equals(first));

                set_things(first, part);
                set_things(second, part);
                break;
            case 3:
                part = generateInfo.producent_all/3;
                set_things(new Producent(Producents.SMALL.max_amount, Producents.SMALL.image_atlas_name), part);
                set_things(new Producent(Producents.MIDDLE.max_amount, Producents.MIDDLE.image_atlas_name), part);
                set_things(new Producent(Producents.BIG.max_amount, Producents.BIG.image_atlas_name), part);
                break;
            default:
                System.err.println("default");
        }
        switch (GeneratorDefaultSettings.consument_1_varieties){
            case 1:
                set_things( Consument_1.createRandom(), generateInfo.consument_1_all);
                break;
            case 2:
                Consument_1 first;
                Consument_1 second;

                part = generateInfo.consument_1_all/2;

                first = Consument_1.createRandom();

                do{
                    second = Consument_1.createRandom();
                }while(second.equals(first));

                set_things(first, part);
                set_things(second, part);
                break;
            case 3:
                part = generateInfo.consument_1_all/3;
                set_things( Consument_1.createNew(Consuments_1.SMALL), part);
                set_things(Consument_1.createNew(Consuments_1.MIDDLE), part);
                set_things(Consument_1.createNew(Consuments_1.BIG), part);
                break;
            default:
                System.err.println("default");
        }
        switch (GeneratorDefaultSettings.consument_2_varieties){
            case 1:
                set_things( Consument_2.createRandom(), generateInfo.consument_2_all);
                break;
            case 2:
                Consument_2 first;
                Consument_2 second;

                part = generateInfo.consument_2_all/2;

                first = Consument_2.createRandom();

                do{
                    second = Consument_2.createRandom();
                }while(second.equals(first));

                set_things(first, part);
                set_things(second, part);
                break;
            case 3:
                part = generateInfo.consument_2_all/3;
                set_things( Consument_2.createNew(Consuments_2.SMALL), part);
                set_things(Consument_2.createNew(Consuments_2.MIDDLE), part);
                set_things(Consument_2.createNew(Consuments_2.BIG), part);
                break;
            default:
                System.err.println("default");
        }
        switch (GeneratorDefaultSettings.consument_3_varieties){
            case 1:
                set_things( Consument_3.createRandom(), generateInfo.consument_3_all);
                break;
            case 2:
                Consument_3 first;
                Consument_3 second;

                part = generateInfo.consument_3_all/2;

                first = Consument_3.createRandom();

                do{
                    second = Consument_3.createRandom();
                }while(second.equals(first));

                set_things(first, part);
                set_things(second, part);
                break;
            case 3:
                part = generateInfo.consument_3_all/3;
                set_things( Consument_3.createNew(Consuments_3.SMALL), part);
                set_things(Consument_3.createNew(Consuments_3.MIDDLE), part);
                set_things(Consument_3.createNew(Consuments_3.BIG), part);
                break;
            default:
                System.err.println("default");
        }
        switch (GeneratorDefaultSettings.consument_4_varieties){
            case 1:
                set_things( Consument_4.createRandom(), generateInfo.consument_4_all);
                break;
            case 2:
                Consument_4 first;
                Consument_4 second;

                part = generateInfo.consument_4_all/2;

                first = Consument_4.createRandom();

                do{
                    second = Consument_4.createRandom();
                }while(second.equals(first));

                set_things(first, part);
                set_things(second, part);
                break;
            case 3:
                part = generateInfo.consument_4_all/3;
                set_things( Consument_4.createNew(Consuments_4.SMALL), part);
                set_things(Consument_4.createNew(Consuments_4.MIDDLE), part);
                set_things(Consument_4.createNew(Consuments_4.BIG), part);
                break;
            default:
                System.err.println("default");
        }

    }

    public void set_things(Thing thing, int units){

        List<Cell> cells;
        cells = mapToArray(ground);

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
                    generateInfo.reducent_done++;
                }else if(thing instanceof Producent){
                    generateInfo.producent_done++;
                }else if(thing instanceof Consument_1){
                    generateInfo.consument_1_done++;
                }else if(thing instanceof Consument_2){
                    generateInfo.consument_2_done++;
                }else if(thing instanceof Consument_3){
                    generateInfo.consument_3_done++;
                }else if(thing instanceof Consument_4){
                    generateInfo.consument_4_done++;
                }

            }
            cells.remove(0);
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

    private void refreshStatistics(){
        if(mapStatistics.game_over) return;
        mapStatistics.turns++;

        mapStatistics.clear();

        List<Cell> list  = mapToArray(ground);
        for (Cell c: list){
            if(c.getThing()!=null){
                if(c.getThing() instanceof Reducent) mapStatistics.reducent++;
                if(c.getThing() instanceof Producent) mapStatistics.producent++;
                if(c.getThing() instanceof Consument_1) mapStatistics.consument_1++;
                if(c.getThing() instanceof Consument_2) mapStatistics.consument_2++;
                if(c.getThing() instanceof Consument_3) mapStatistics.consument_3++;
                if(c.getThing() instanceof Consument_4) mapStatistics.consument_4++;
            }
        }

        mapStatistics.check();

    }
}
