import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Aplicacao {

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        ExecutorService executorPool = Executors.newFixedThreadPool(2);
        MyThread t = new MyThread();
        executor.execute(t);
        executor.shutdown();

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
