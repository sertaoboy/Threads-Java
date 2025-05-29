package ForkJoin.sumarray;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {
        int [] arr = new int[100000000];
        Arrays.fill(arr,1);
        SumArrayTask task = new SumArrayTask(arr,0,arr.length-1);
        ForkJoinPool pool = new ForkJoinPool(); //criação do pool de threads para executar as tasks nos processadores disponíveis
        int soma = pool.invoke(task);
        System.out.println(soma);

    }
}
