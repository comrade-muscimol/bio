package com.muscimol.bio;

import com.muscimol.bio.creature.Consument_1;
import com.muscimol.bio.creature.Consument_2;
import com.muscimol.bio.creature.Consument_3;
import com.muscimol.bio.creature.Consument_4;
import com.muscimol.bio.creature.Producent;
import com.muscimol.bio.creature.Reducent;
import com.muscimol.bio.creature.Thing;

public class Cell {

    private int x;
    private int y;

    private boolean reducent_available;
    private boolean producent_available;

    private Thing thing;

    public Cell(int x, int y){
        this.x = x;
        this.y = y;

        reducent_available = false;
        producent_available = false;

        thing = null;
    }

    public void action(){
        if(thing.isActive()){
            thing.action(this);
        }else{
            clean();
        }

    }
    private void clean(){
        if (thing==null) return;

        if(thing instanceof Producent){
            producent_available = false;
        }

        if (thing instanceof Reducent){
            producent_available = true;
            reducent_available = false;
        }

        if(thing instanceof Consument_1
                ||thing instanceof Consument_2
                ||thing instanceof Consument_3
                ||thing instanceof Consument_4){
            reducent_available = true;
        }

        thing = null;
    }

    public boolean isEmpty(){
        if(
        thing==null
        &&
        !reducent_available
        &&
        !producent_available
        )return true;
        else return false;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public boolean isReducent_available() {
        return reducent_available;
    }
    public boolean isProducent_available() {
        return producent_available;
    }
    public Thing getThing() {
        return thing;
    }
    public synchronized void setThing(Thing thing) {
        this.thing = thing;
    }
    public synchronized void setReducent_available(boolean reducent_available) {
        this.reducent_available = reducent_available;
    }
    public synchronized void setProducent_available(boolean producent_available) {
        this.producent_available = producent_available;
    }
}
