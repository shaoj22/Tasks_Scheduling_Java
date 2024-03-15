package Model;


import java.util.ArrayList;
import java.util.List;

public class CalculatePretasksArray {
    private CalculatePretasksArray() {
    }
    // get pretasksArray
    public static List<List<Integer>> PretasksArray(int taskNum, int [][] tasksDependency){
        List<List<Integer>> pretasksArray = new ArrayList<>();
        for (int i = 0; i < taskNum; i++) {
            pretasksArray.add(new ArrayList<>());
        }
        for (int i = 0; i < taskNum; i++) {
            for (int j = 0; j < taskNum; j++) {
                if (tasksDependency[j][i] == 1){
                    pretasksArray.get(i).add(j);
                }
            }
        }
        return pretasksArray;
    }
}
