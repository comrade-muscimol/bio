package com.muscimol.bio;

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
