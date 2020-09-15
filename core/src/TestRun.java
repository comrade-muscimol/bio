import com.muscimol.bio.generate.Map;

public class TestRun {

    public static void main(String[] args){
        Map.getInstance();
        System.out.println("Создана тупая карта");


        for(int i = 0; i < Map.getInstance().getMap().size(); i++){
            for(int o = 0; o < Map.getInstance().getMap().get(0).size(); o++){
                String letter;


                switch (Map.getInstance().getMap().get(i).get(o).getImageName()){
                    case "empty_cell": letter = "Em"; break;
                    case "reducent_ready_cell": letter = "rR"; break;
                    case "producent_ready_cell": letter = "pR"; break;
                    case "dual_ready_cell": letter = "dR"; break;
                    case "producent_1_cell": letter = "p1"; break;
                    case "producent_2_cell": letter = "p2"; break;
                    case "producent_3_cell": letter = "p3"; break;
                    case "reducent_1_cell": letter = "r1"; break;
                    case "reducent_2_cell": letter = "r2"; break;
                    case "reducent_3_cell": letter = "r3"; break;
                    case "consument_1_1_cell": letter = "11"; break;
                    case "consument_1_2_cell": letter = "12"; break;
                    case "consument_1_3_cell": letter = "13"; break;
                    case "consument_2_1_cell": letter = "21"; break;
                    case "consument_2_2_cell": letter = "22"; break;
                    case "consument_2_3_cell": letter = "23"; break;
                    case "consument_3_1_cell": letter = "31"; break;
                    case "consument_3_2_cell": letter = "32"; break;
                    case "consument_3_3_cell": letter = "33"; break;
                    case "consument_4_1_cell": letter = "11"; break;
                    case "consument_4_2_cell": letter = "12"; break;
                    case "consument_4_3_cell": letter = "13";
                    default: letter = "OO";
                }
                System.out.print(letter+"|");
            }
            System.out.println();
        }
    }
}
