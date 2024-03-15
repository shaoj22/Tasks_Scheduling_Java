package Application;

import Model.Instance;
import java.util.List;

public class RandomDataApplication {
    public static void main(String[] args) {
        //from csv file read tasks dependency and job scheduling data
        for (int i = 1; i < 5; i++) {
            String JobSchedulingTableFilePath = "data/RandomData/Job_Scheduling_Table_" + i + ".csv";
            String TasksDependencyTableFilePath = "data/RandomData/Task_Dependency_Table_" + i + ".csv";
            String csvDelimiter = ",";
            int[][] tasksDependencyData = ReadData.readCSVToArray(TasksDependencyTableFilePath, csvDelimiter);
            List<List<Object>> tasks = ReadData.readCSVToList(JobSchedulingTableFilePath);
            // create instance
            Instance instance = CreateInstance.raedDataToInstance(tasksDependencyData, tasks);
            // transmit the instance to the OLBHeuristicMain and get the solutionT
            long startTime = System.currentTimeMillis(); // get the start time
            int[] SolutionT = Model.IterMainStructure.getIterSolution(instance); // get the solutionT
            long endTime = System.currentTimeMillis(); // get the end time
            long totalTime = endTime - startTime; // calculate the total time
            // print the result and time
            System.out.println("Instances  " + i + "  total time = " + totalTime + "ms" + "   " + "resource_cap = " + (double)SolutionT[SolutionT.length - 1]/100000);
        }
    }
}
