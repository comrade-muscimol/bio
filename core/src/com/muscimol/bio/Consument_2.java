package com.muscimol.bio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Consument_2 implements Thing {
    private boolean active;

    private int satiety;
    private int max_satiety;

    private double producent_priority = 30.2;
    private double consument_1_priority = 69.8;


    @Override
    public void action() {

    }

    @Override
    public boolean is_active() {
        return false;
    }

    public boolean is_eaten(){
        if(!active) return false;
        else {
            active = false;
            return true;
        }
    }
    public void try_feed(Cell cell) {
        int x = cell.getX();
        int y = cell.getY();

        List<Cell> near = Map.getInstance().getNear(x, y);

        List<Cell> good = new ArrayList<>();


        for (Cell c : near) {
            //если c продуцент или консумент 1 добавить к good
        }

        boolean exit = false;
        do {

            if (!good.isEmpty()) {
                Collections.shuffle(good);

                try {
                    //если продуцент
                    Producent producent = (Producent) Map.getInstance().get(good.get(0).getX(), good.get(0).getY()).getThing();
                    if (producent.try_feed()) {
                        if (satiety < max_satiety) {
                            satiety++;

                            if (satiety == max_satiety) {
                                try_to_reproduction(cell);
                            }
                            exit = true;
                        }
                    } else {
                        good.remove(0);
                    }
                    // если консумент 1
                    Consument_1 consument_1 = (Consument_1) Map.getInstance().get(good.get(0).getX(), good.get(0).getY()).getThing();
                    if (consument_1.is_eaten()) {
                        if (satiety < max_satiety) {
                            satiety++;

                            if (satiety == max_satiety) {
                                try_to_reproduction(cell);
                            }
                            exit = true;
                        }
                    } else {
                        good.remove(0);
                    }


                } catch (Exception ee) {
                    ee.printStackTrace();

                }
            }else{
                exit=true;
            }
        }
        while (!exit) ;

    }

    void try_to_reproduction(Cell cell){
        int x = cell.getX();
        int y = cell.getY();

        List<Cell> near = Map.getInstance().getNear(x, y);

        List<Cell> good = new ArrayList<>();

        for (Cell c : near){
            //если c пустая клетка
        }
        
        boolean exit = false;
        do{

            if(!good.isEmpty()) {
                Collections.shuffle(good);

                try {

                    Map.getInstance().get(good.get(0).getX(), good.get(0).getY()).setThing((Thing)this.clone());

                    exit = true;
                } catch (Exception e) {
                    good.remove(0);

                    e.printStackTrace();
                }

            }else{
                exit = true;
            }

        }while(!exit);

    }
}
