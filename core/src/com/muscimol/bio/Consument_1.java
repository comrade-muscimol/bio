package com.muscimol.bio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Consument_1 implements Thing {

    private boolean active;

    private int satiety;
    private int max_satiety;


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
    public void try_feed(Cell cell){
        int x = cell.getX();
        int y = cell.getY();

        List<Cell> near = Map.getInstance().getNear(x, y);

        List<Cell> good = new ArrayList<>();

        for (Cell c : near){
            //если c продуцент, то добавить к good
        }

        boolean exit = false;
        do{

        if(!good.isEmpty()) {
            Collections.shuffle(good);

            try {
                Producent target = (Producent) Map.getInstance().get(good.get(0).getX(), good.get(0).getY()).getThing();
                if (target.try_feed()) {
                    if (satiety < max_satiety) {
                        satiety++;

                        if (satiety==max_satiety){
                            try_to_reproduction(cell);
                        }
                        exit = true;
                    }
                } else {
                    good.remove(0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }else{
            satiety--;
            if (satiety<=0) active = false;
        }

        }while(!exit);

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
