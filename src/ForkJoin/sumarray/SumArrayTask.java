package ForkJoin.sumarray;

import java.util.concurrent.RecursiveTask;

public class SumArrayTask extends RecursiveTask<Integer> {

    private int [] arr;
    private int minN;
    private int maxN;

    public SumArrayTask(int[] arr, int minN, int maxN) {
        this.arr = arr;
        this.minN = minN;
        this.maxN = maxN;
    }

    @Override
    protected Integer compute() { // método que age como uma task, dividindo a responsabildiade para uma outra task utilizando o .fork() e cuidando ela mesma da outra metade
        int numElem = maxN - minN + 1;
        if(numElem < 4) {
            int partialSum = 0;
            for(int i = minN; i<= maxN; i++) {
                partialSum = partialSum + arr[i];
            }
            return partialSum;
        } else {
            int half = numElem/2;
            SumArrayTask leftTask = new SumArrayTask(arr,minN,minN+half);
            SumArrayTask rightTask = new SumArrayTask(arr,minN + half + 1, maxN);
            leftTask.fork();
            int rightPartialSum = rightTask.compute();
            int leftPartialSum = leftTask.join();
            return rightPartialSum+leftPartialSum;
        }
    }
}
