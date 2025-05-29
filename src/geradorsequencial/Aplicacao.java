package geradorsequencial;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class Aplicacao {
    public static void main(String[] args) throws Exception {
        for(int i = 0; i< 10; i++) {
            int seq = Gerador.getNextSeq();
            System.out.println(seq);
        }
    }
}
