package Model;

import java.util.ArrayList;

public class CalculatePretasks {
    private CalculatePretasks() {
    }
    public static boolean getCalculatePretasks(Instance instance, ArrayList<Integer> scheduledTasks, int task_id){
        boolean isCalculatePretasks = true; // if all the pretasks of the task_id are scheduled, return true
        for (int i = 0; i < instance.getPretasksArray().get(task_id).size(); i++) {
            if (!scheduledTasks.contains(instance.getPretasksArray().get(task_id).get(i))){
                isCalculatePretasks = false; // if there is a pretask of the task_id is not scheduled, return false
                break;
            }
        }

        return isCalculatePretasks;
    }
}
