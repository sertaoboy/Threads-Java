import java.io.File;
import java.util.concurrent.Callable;

public class Localizador implements Callable<File> {

    private String startDir;
    private String nomeArquivo;

    public Localizador(String startDir, String nomeArquivo) {
        this.nomeArquivo=nomeArquivo;
        this.startDir=startDir;
    }

    @Override
    public File call() throws Exception {
        return search(new File(startDir));
    }

    private File search(File dir) {
        File[] files = dir.listFiles();
        File result = null;
        if(files == null) {
            return null;
        }
        for(File file : files) {
            if(file.isDirectory()) {
                result = search(file); //recursão
                if(result!=null) {
                    break;
                }
            } else {
                if(file.getName().equalsIgnoreCase(nomeArquivo)) {
                    return file;
                }
            }
        }
        return result;
    }


}
