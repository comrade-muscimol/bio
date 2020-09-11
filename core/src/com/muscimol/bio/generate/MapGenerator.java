package com.muscimol.bio.generate;

import com.muscimol.bio.Cell;
import com.muscimol.bio.Map;

import java.util.ArrayList;
import java.util.List;

public class MapGenerator {

    public static void generate(int x, int y,
                                double reducent_percent,
                                double producent_percent,
                                double consument_1_percent,
                                double consument_2_percent,
                                double consument_3_percent,
                                double consument_4_percent
    ){
        map_reset(x, y);
        set_reducent(reducent_percent);
        set_producent(producent_percent);
        set_consument_1(consument_1_percent);
        set_consument_2(consument_2_percent);
        set_consument_3(consument_3_percent);
        set_consument_4(consument_4_percent);
    }

    private static void map_reset(int x, int y){
        Map.reset(x, y);
    }
    private static void set_reducent(double percent){

        List<Cell> input = new ArrayList<>();

        for()

        long field;



    }
    private static void set_producent(double percent){

    }
    private static void set_consument_1(double percent){

    }
    private static void set_consument_2(double percent){

    }
    private static void set_consument_3(double percent){

    }
    private static void set_consument_4(double percent){

    }


}
