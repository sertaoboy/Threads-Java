import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Aplicacao {

    private static int NUM_CARROS = 100;

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

        Info info = new Info();
        int numLeitores = 10;
        int numEscritores = 2;
        ExecutorService e2 = Executors.newFixedThreadPool(numEscritores + numLeitores);
        for(int i = 0;i<numLeitores;i++) {
            Leitor r = new Leitor("Leitor" + i, info);
            e2.execute(r);
        }
        for(int i = 0;i<numLeitores;i++) {
            Escritor w = new Escritor(info);
            e2.execute(w);
        }
        e2.shutdown();

        ExecutorService e3 = Executors.newFixedThreadPool(NUM_CARROS + 1);
        Semaforo semaforo = new Semaforo();
        e3.execute(semaforo);
        for(int i = 0; i<= NUM_CARROS; i++) {
            Carro carro = new Carro(i, semaforo);
            e3.execute(carro);
        }
        e3.shutdown();


    }
}
