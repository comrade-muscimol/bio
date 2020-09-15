package com.muscimol.bio.creature;

import com.muscimol.bio.Cell;

    public abstract class Thing {

    private boolean active;

    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    public abstract void action(Cell cell);



    public abstract boolean isEaten();


}
