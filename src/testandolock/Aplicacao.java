package testandolock;

import leitura.Escritor;
import leitura.Info;
import leitura.Leitor;
import localizador.Localizador;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class Aplicacao {

    private static int NUM_CARROS = 100;

    public static void main(String[] args) throws Exception {
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
