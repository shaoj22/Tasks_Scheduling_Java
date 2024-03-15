package Model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainStructure {
    private MainStructure() {
    }
    public static int [] getMainStructureSolution(Instance instance, double resourceCap){
        int [] solutionT = new int[instance.getTasks().size() + 1]; // 初始化每个任务开始的执行时间为0
        ArrayList<Integer> schedulingTasks = new ArrayList<>(); //存储正在调度的任务
        ArrayList<Integer> unscheduledTasks = new ArrayList<>(); //存储未调度的任务
        for (int i = 0; i < instance.getTasks().size(); i++) {
            unscheduledTasks.add(i);
        } // 初始化未调度的任务列表
        ArrayList<Integer> scheduledTasks = new ArrayList<>(); //存储已经调度的任务
        double resourceNow = 0.0; //记录当前的资源占用
        for (int i = 0; i < instance.getTimeCap(); i++) {
            ArrayList<Integer> checkTasks = new ArrayList<>(schedulingTasks); //存储检查的任务
            for (int i1 = 0; i1 < checkTasks.size(); i1++) {
                if (i >= (solutionT[checkTasks.get(i1)] + (int) instance.getTasks().get(checkTasks.get(i1)).get(2))) {
                    schedulingTasks.remove(Integer.valueOf(checkTasks.get(i1))); // 将已经调度的任务从正在调度的任务列表中移除
                    scheduledTasks.add(checkTasks.get(i1)); // 将已经调度的任务加入到已调度的任务列表中
                    resourceNow = resourceNow - (double) instance.getTasks().get(checkTasks.get(i1)).get(3);
                }
            }
            ArrayList<Integer> insertTasksList = TaskAssignment.getInsertTasksList(instance, i, unscheduledTasks, scheduledTasks, resourceNow, resourceCap); // 获取插入的任务列表
            for (int i2 = 0; i2 < insertTasksList.size(); i2++) {
                unscheduledTasks.remove(Integer.valueOf(insertTasksList.get(i2))); // 将插入的任务从未调度的任务列表中移除
                solutionT[insertTasksList.get(i2)] = i; // 将插入的任务的开始执行时间设置为当前时间
                schedulingTasks.add(insertTasksList.get(i2)); // 将插入的任务加入到正在调度的任务列表中
                resourceNow = resourceNow + (double)instance.getTasks().get(insertTasksList.get(i2)).get(3);
            }
//            System.out.println("time now = " + i + " resource_now = " + resourceNow + " scheduling_task_num = " + schedulingTasks.size() + " scheduled_task_num = " + scheduledTasks.size() + " unscheduled_task_num = " + unscheduledTasks.size() + " resource_upper_bound = " + resourceCap);
        }
        if (scheduledTasks.size() != instance.getTasks().size()){
            solutionT[solutionT.length - 1] = 0;
        }
        else{
            solutionT[solutionT.length - 1] = 1;
        }

        return solutionT;
    }
}
