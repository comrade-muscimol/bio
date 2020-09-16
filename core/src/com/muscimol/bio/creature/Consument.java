package com.muscimol.bio.creature;

import com.muscimol.bio.Cell;
import com.muscimol.bio.generate.Map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Consument extends Thing {

    protected int satiety;
    protected int max_satiety;

    protected int reproduction_chance;

    protected int moves;
    protected int max_moves;

    @Override
    public void action(Cell cell) {
        if (!active) {
            return;
        }

        if (satiety <= 0) {
            active = false;
            return;
        }

        System.out.println("active. satiety:"+satiety);

        switch (tryEat(cell)){

            case FAILED:
            case NO_TARGETS:
                System.out.println("no targets try to move. satiety:"+satiety);

                tryMove(cell);
                System.out.println("после попытки идти. satiety:"+satiety);

                break;

            case SUCCESS:
                moves = max_moves;
                satiety++;
                tryReproduction(cell);
                System.out.println("success. satiety:"+satiety);
                break;

            case MAX_SATIETY:
                System.out.println("max satiety. try to rep. satiety before:"+satiety);
                tryReproduction(cell);
                satiety--;
                System.out.println("max satiety. try to rep. satiety after:"+satiety);
                break;

        }


    }

    @Override
    public boolean isEaten() {
        if (!active) return false;
        else {
            active = false;
            return true;
        }
    }

    protected EatResponses tryEat(Cell cell) {

        List<Cell> near = Map.getInstance().getNear(cell.getX(), cell.getY());
        List<Cell> good = new ArrayList<>();

        for (Cell c : near) {
            if (
                    c.getThing() != null
                            &&
                            isEdible(c.getThing())
            ) {
                good.add(c);
            }
        }

        if (!good.isEmpty()) {
            Collections.shuffle(good);

            if (satiety < max_satiety) {
                if (
                        Map.getInstance().get(
                                good.get(0).getX(), good.get(0).getY()
                        ).getThing().isEaten()
                ) {

                    return EatResponses.SUCCESS;
                } else {
                    return EatResponses.FAILED;
                }
            }else{
                return EatResponses.MAX_SATIETY;
            }
        }else{
            return EatResponses.NO_TARGETS;
        }

    }
    protected void tryReproduction(Cell cell) {
        List<Cell> near = Map.getInstance().getNear(
                cell.getX(),
                cell.getY()
        );

        List<Cell> good = new ArrayList<>();

        for (Cell c : near) {
            if (c.getThing() == null) {
                good.add(c);
            }
        }

        if(good.isEmpty()) return;
        int random_int = ThreadLocalRandom.current().nextInt(100);

        if(reproduction_chance>random_int){
                good.get(0).setThing(getClone());
            System.out.println("reproduction done. satiety:"+satiety);
        }else{
            System.out.println("no reproduction. satiety:"+satiety);
        }

    }
    protected void tryMove(Cell cell) {
        int x = cell.getX();
        int y = cell.getY();

        List<Cell> near = Map.getInstance().getNear(x, y);
        List<Cell> good = new ArrayList<>();


        for (Cell c : near) {
            if (
                    (c.getThing() == null)
            ) {
                good.add(c);
            }
        }

        if(!good.isEmpty()){
            System.out.println("move start");
            try{

                Cell temp_move_from = Map.getInstance().get(x, y);
                Cell temp_move_to = Map.getInstance().get(
                        good.get(0).getX(),
                        good.get(0).getY()
                );

                temp_move_to.setThing(temp_move_from.getThing());
                temp_move_from.setThing(null);

                Map.getInstance().set(temp_move_from.getX(), temp_move_from.getY(), temp_move_from);
                Map.getInstance().set(temp_move_to.getX(), temp_move_to.getY(), temp_move_to);

                if(moves<=0){
                    satiety--;
                    if(satiety<=0){
                        active = false;
                        System.out.println("Умер в успешном  движении");
                    }
                }
                moves--;


                System.out.println("move complete. satiety:"+satiety);
            }catch (Exception e){
                System.out.println("move failed. satiety:"+satiety);
                e.printStackTrace();
            }
        }else{
            System.out.println("нет пододящих для движения клеток");
            if(moves<=0){
                satiety--;
                if(satiety<=0){
                    active = false;
                    System.out.println("Умер в движении");
                }
            }
            moves--;
        }


    }

    abstract boolean isEdible(Thing thing);
    abstract Thing getClone();

    enum EatResponses{
        MAX_SATIETY,
        FAILED,
        NO_TARGETS,
        SUCCESS




    }

}
