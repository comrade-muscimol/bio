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

            // если класс Producent то producent_avaliable = false;
            // если редуцент, то producent = true, reducent = false;
            // если консумент, то редуцент = true;

            thing = null;
        }

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
