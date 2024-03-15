package Model;

import java.util.List;

public class CalculateResourceLowerBound {
    private CalculateResourceLowerBound() {
    }
    // calculate the resource lower bound of the instance
    public static double getResourceLowerBound(List<List<Object>> tasks){
        double resourceLowerBound = 0.0;
        for (int i = 0; i < tasks.size(); i++) {
            Object num1 = tasks.get(i).get(3); // get the resource of the task
            Object num2 = tasks.get(i).get(2); // get the duration time of the task
            int a1 = Integer.parseInt(num2.toString());
            double a2 = Double.parseDouble(num1.toString());
            resourceLowerBound += a1 * a2;
        }
        return resourceLowerBound/1440;
    }
}
