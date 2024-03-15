package Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class TaskAssignment {
    private TaskAssignment() {
    }
    public static ArrayList<Integer> getInsertTasksList(Instance instance, int time_now, ArrayList<Integer> unscheduledTasks, ArrayList<Integer> scheduledTasks, double resource_now, double resource_cap){
        ArrayList<Integer> readyTasks = new ArrayList<>(); // 存储已经准备好的任务
        ArrayList<Integer> insertTasksList = new ArrayList<>(); // 存储可以插入的任务
        ArrayList<Integer> readyTasksLatestStartTime = new ArrayList<>(); // 可插入任务的最晚开始时间
        // 获取已经准备好的任务
        for (int i = 0; i < unscheduledTasks.size(); i++) {
            boolean isCalculatePretasks = CalculatePretasks.getCalculatePretasks(instance, scheduledTasks, unscheduledTasks.get(i));
            if (time_now >= (int)instance.getTasks().get(unscheduledTasks.get(i)).get(4) && isCalculatePretasks == true){
                readyTasks.add(unscheduledTasks.get(i));
                readyTasksLatestStartTime.add((int)instance.getTasks().get(unscheduledTasks.get(i)).get(5) - (int)instance.getTasks().get(unscheduledTasks.get(i)).get(2));
            }
        }
        // 从准备好的任务中获取可以插入的任务
        while(readyTasks.size()!=0){
            int minStartTime = Collections.min(readyTasksLatestStartTime);
            int minStartTimeIndex = readyTasksLatestStartTime.indexOf(minStartTime);
            int insertTask = readyTasks.get(minStartTimeIndex);
            resource_now = resource_now + (double)instance.getTasks().get(insertTask).get(3);
            if (resource_now >= resource_cap){
                break;
            }
            else{
                insertTasksList.add(insertTask);
                unscheduledTasks.remove(Integer.valueOf(insertTask));
                readyTasks.remove(Integer.valueOf(insertTask));
                readyTasksLatestStartTime.remove(Integer.valueOf(minStartTime));
            }
        }

        return insertTasksList;
    }
}
