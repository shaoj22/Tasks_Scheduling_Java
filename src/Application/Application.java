/*
    description:
        · this class is the main class of the project
        · it will read the data from csv file and create the instance
        · then transmit the instance to the OLBHeuristicMain and get the solutionT
    author: 626
    time: 2023-08-08
    version: 1.0
 */
package Application;

import Model.Instance;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        //from csv file read tasks dependency and job scheduling data
        String tasksDependencyFilePath = "data/Appendix_2_Tasks_Dependency.csv";
        String jobSchedulingFilePath = "data/Appendix_3_Job_Scheduling.csv";
        int[][] tasksDependencyData = ReadData.readCSVToArray(tasksDependencyFilePath, ",");
        List<List<Object>> tasks = ReadData.readCSVToList(jobSchedulingFilePath);
        // create instance
        Instance instance = CreateInstance.raedDataToInstance(tasksDependencyData, tasks);
        // transmit the instance to the OLBHeuristicMain and get the solutionT
        long startTime = System.currentTimeMillis(); // get the start time
        int[] SolutionT = Model.IterMainStructure.getIterSolution(instance); // get the solutionT
        long endTime = System.currentTimeMillis(); // get the end time
        long totalTime = endTime - startTime; // calculate the total time
        // print the result and time
        System.out.println("total time = " + totalTime + "ms");
        System.out.println("resource_cap = " + (double)SolutionT[SolutionT.length - 1]/100000);
    }
}
