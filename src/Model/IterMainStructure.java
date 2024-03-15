package Model;
/*
    *
 */
public class IterMainStructure {
    private IterMainStructure(){
    }
    // iter_optimization
    // run the heuristic OLB for many times to get the best solution
    public static int[] getIterSolution(Instance instance){
        double resourceGap = instance.getResourceGap();
        double upperBound = instance.getResourceCap();
        int [] outPutSolutionT = new int [instance.getTasks().size() + 1]; // 记录要输出的solutionT,最后一位表示是否是可行解，若为1则表示为可行解，否则为不可行解
        // 通过二分法来确定最终的upperBound
        while (resourceGap > instance.getAcceptResourceGap()){
            if (upperBound > resourceGap && upperBound - resourceGap > instance.getResourceLowerBound()){
                int [] solutionT = MainStructure.getMainStructureSolution(instance, upperBound - resourceGap); // 通过OLBHeuristicMain来获取solutionT
                if (solutionT[solutionT.length - 1] == 1){
                    outPutSolutionT = solutionT;
                    upperBound = upperBound - resourceGap;
                    if (resourceGap != instance.getAcceptResourceGap()){
                        resourceGap = resourceGap / 2;
                    }
                }
                else{
                    resourceGap = resourceGap / 2;
                }
            }
            else{
                resourceGap = resourceGap / 2;
            }
        }
        // 将upperBound转换为int类型，并且将其放入outPutSolutionT中
        double roundedValue = Math.round(upperBound * 100000.0) / 100000.0;
        double multipliedValue = roundedValue * 100000;
        int intUpperBound = (int) multipliedValue;
        outPutSolutionT[outPutSolutionT.length - 1] = intUpperBound;

        return outPutSolutionT;
    }
}
