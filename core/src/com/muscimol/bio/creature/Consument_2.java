package com.muscimol.bio.creature;

import com.muscimol.bio.Cell;
import com.muscimol.bio.generate.Map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Consument_2 extends Thing {

    public static Consument_2 createNew() {
        int index = ThreadLocalRandom.current().nextInt(Consuments_2.values().length);
        return new Consument_2(Consuments_2.values()[index].max_satiety, Consuments_2.values()[index].image_atlas_name);
    }
    public Consument_2(int max_satiety, String image_atlas_name) {
        this.max_satiety = max_satiety;
        this.image_atlas_name = image_atlas_name;
        satiety = max_satiety;
        active = true;
    }

    private boolean active;

    private int satiety;
    private int max_satiety;

    @Override
    public void action(Cell cell) {

        if(satiety==0){
            active=false;
            return;
        }
        if(!active) return;


        if(tryEat(cell)){
            tryReproduction(cell);
        }else{
            tryMove(cell);
        }


        if(satiety==0){
            active=false;
            return;
        }
        if(!active) return;


    }

    @Override
    public boolean isEaten() {
        if(!active) return false;
        else {
            active = false;
            return true;
        }
    }

    private boolean tryEat(Cell cell) {
        int x = cell.getX();
        int y = cell.getY();

        List<Cell> near = Map.getInstance().getNear(x, y);
        List<Cell> good = new ArrayList<>();


        for (Cell c : near) {
            if(c.getThing()!=null&&
                    (c.getThing() instanceof Producent||c.getThing() instanceof Consument_1)
            ){
                good.add(c);
            }
        }

        boolean exit = false;
        do {
            if (!good.isEmpty()) {
                Collections.shuffle(good);

                Thing thing = Map.getInstance().get(good.get(0).getX(), good.get(0).getY()).getThing();

                if(thing instanceof Producent){
                    try{
                        Producent producent = (Producent) Map.getInstance().get(good.get(0).getX(), good.get(0).getY()).getThing();
                        if (producent.isEaten()) {
                            if (satiety < max_satiety) {
                                satiety++;

                                return true;
                            }
                        } else {
                            good.remove(0);
                        }
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
                if(thing instanceof Consument_1){
                    try{
                        Consument_1 consument_1 = (Consument_1) Map.getInstance().get(good.get(0).getX(), good.get(0).getY()).getThing();
                        if (consument_1.isEaten()) {
                            if (satiety < max_satiety) {
                                satiety++;

                                return true;
                            }
                        } else {
                            good.remove(0);
                        }
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }else{
                exit=true;
            }
        }
        while (!exit);

        return false;

    }
    private boolean tryReproduction(Cell cell){
        int x = cell.getX();
        int y = cell.getY();

        List<Cell> near = Map.getInstance().getNear(x, y);

        List<Cell> good = new ArrayList<>();

        for (Cell c : near){
            if(c.getThing()==null){
                good.add(c);
            }
        }
        
        boolean exit = false;
        do{

            if(!good.isEmpty()) {
                Collections.shuffle(good);

                try {

                    Map.getInstance().get(good.get(0).getX(), good.get(0).getY()).setThing((Thing)this.clone());

                    return true;
                } catch (Exception e) {
                    good.remove(0);

                    e.printStackTrace();
                }

            }else{
                exit = true;
            }

        }while(!exit);
        return false;

    }
    private boolean tryMove(Cell cell){
        int x = cell.getX();
        int y = cell.getY();

        List<Cell> near = Map.getInstance().getNear(x, y);
        List<Cell> good = new ArrayList<>();


        for (Cell c : near) {
            if(c.getThing()!=null&&
                    (c.getThing()==null)
            ){
                good.add(c);
            }

        }
        boolean exit = false;

        do {
            if (!good.isEmpty()) {

                Collections.shuffle(good);

            }else{
                exit = true;
            }

            try {
                satiety--;
                if(satiety<=0){
                    active = false;
                    return false;
                }
                Map.getInstance().get(good.get(0).getX(), good.get(0).getY()).setThing(
                        Map.getInstance().get(x, y).getThing()
                );

                Map.getInstance().get(x, y).setThing(null);

                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }while(!exit);
        return false;

    }
}
