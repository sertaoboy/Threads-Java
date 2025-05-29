package testedethread;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;


public class Aplicacao {

    private static int NUM_CARROS = 100;

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        ExecutorService executorPool = Executors.newFixedThreadPool(2);
        MyThread t = new MyThread();
        executor.execute(t);
        executor.shutdown();




    }
}
