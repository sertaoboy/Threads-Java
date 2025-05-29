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

        Info info = new Info();
        int numLeitores = 10;
        int numEscritores = 2;
        ExecutorService e2 = Executors.newFixedThreadPool(numEscritores + numLeitores);
        for(int i = 0;i<numLeitores;i++) {
            Leitor r = new Leitor("leitura.Leitor" + i, info);
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

        ExecutorService executorSimples2 = Executors.newSingleThreadExecutor();
        Localizador l = new Localizador("/home/raul/Códigos/ThreadsJava/ConcurrentsThreads1/src", "arquivoteste.txt");
        FutureTask<File> task = new FutureTask<File>(l);
        executorSimples2.execute(task);
        while(!task.isDone()) {
            System.out.println("Aguardando localização do arquivo...");
            Thread.sleep(2000);
        }
        File file = task.get();
        if(file!=null) {
            System.out.println("Arquivo encontrado: " +file.getPath());
        }else{
            System.out.println("Arquivo não encontrado.");
        }
        executorSimples2.shutdown();

        for(int i = 0; i< 10; i++) {
            int seq = Gerador.getNextSeq();
            System.out.println(seq);
        }



    }
}
