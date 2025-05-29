package leitura;




import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


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
    }
}
