package com.muscimol.bio.creature;

import com.muscimol.bio.Cell;

    public abstract class Thing {

    protected boolean active;
    protected String image_atlas_name;

    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public String getImageName(){
        return image_atlas_name;
    }

    public  abstract void action(Cell cell);

    public abstract boolean isEaten();


}
