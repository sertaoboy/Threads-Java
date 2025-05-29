package geradorsequencial;

import leitura.Escritor;
import leitura.Info;
import leitura.Leitor;
import localizador.Localizador;
import testandolock.Carro;
import testandolock.Semaforo;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class Aplicacao {

    private static int NUM_CARROS = 100;

    public static void main(String[] args) throws Exception {
        for(int i = 0; i< 10; i++) {
            int seq = Gerador.getNextSeq();
            System.out.println(seq);
        }
    }
}
