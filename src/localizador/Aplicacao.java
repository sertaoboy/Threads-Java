package localizador;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class Aplicacao {

    public static void main(String[] args) throws Exception {
        ExecutorService executorSimples2 = Executors.newSingleThreadExecutor();
        Localizador l = new Localizador("/home/raul/Códigos/ThreadsJava/ConcurrentsThreads1/src/localizador/teste", "arquivoteste.txt");
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
    }
}
