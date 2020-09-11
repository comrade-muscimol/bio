package com.muscimol.bio;

public class Cell {

    private int x;
    private int y;

    private boolean reducent_available;
    private boolean producent_available;

    private Thing thing;

    public Cell(int x, int y){
        this.x = x;
        this.y = y;

        reducent_available = true;
        producent_available = true;

        thing = null;
    }

    public void action(){
        if(thing.is_active()){
            thing.action();
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
                ||thing instanceof  Consument_4){
            reducent_available = true;
        }

        thing = null;
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
}
