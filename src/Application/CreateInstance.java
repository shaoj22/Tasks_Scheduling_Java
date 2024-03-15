package Application;

import Model.CalculatePretasksArray;
import Model.CalculateResourceLowerBound;
import Model.Instance;

import java.util.List;

public class CreateInstance {
    public static Instance raedDataToInstance(int [][] tasksDependencyData, List<List<Object>> tasks){
        // create instance
        int timeCap = 1440;
        double resourceCap = 1.0;
        double resourceGap = 1.2;
        double acceptResourceGap = 0.01;
        List<List<Integer>> pretasksArray = CalculatePretasksArray.PretasksArray(tasks.size(), tasksDependencyData);
        double resourceLowerBound = CalculateResourceLowerBound.getResourceLowerBound(tasks);
        Instance instance = new Instance(
                tasks.size(),
                tasks,
                timeCap,
                resourceCap,
                resourceGap,
                acceptResourceGap,
                pretasksArray,
                resourceLowerBound);

        return instance;
    }
}
