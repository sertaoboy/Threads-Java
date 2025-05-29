package conta;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class Aplicacao {

    private static int NUM_CARROS = 100;

    public static void main(String[] args) throws Exception {
        ExecutorService executorPool = Executors.newFixedThreadPool(2);
        ContaBancaria conta = new ContaBancaria();
        Cliente c1 = new Cliente(conta);
        Cliente c2 = new Cliente(conta);
        executorPool.execute(c1);
        executorPool.execute(c2);
        executorPool.shutdown();
        while(!executorPool.isTerminated()) {
            Thread.sleep(1000);
        }
    }
}
