package com.muscimol.bio;

public enum Directions {

    UP_LEFT(-1, +1),
    UP_MID(0, +1),
    UP_RIGHT(+1, +1),

    MID_LEFT(-1, 0),
    MID_RIGHT(+1, 0),

    DOWN_LEFT(-1, -1),
    DOWN_MID(0, -1),
    DOWN_RIGHT(+1, -1);

    public int x;
    public int y;

    Directions(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     *      4 - (x, y)
     *
     *          0  1  2
     *          3  4  5
     *          6  7  8
     *
     *          0 - (x-1, y+1)
     *          1 - (x, y+1)
     *          2 - (x+1, y+1)
     *
     *          3 - (x-1, y)
     *          5 - (x+1, y)
     *
     *          6 - (x-1, y-1)
     *          7 - (x, y-1)
     *          8 - (x+1, y-1)
     */
}
