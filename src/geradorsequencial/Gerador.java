import java.util.concurrent.atomic.AtomicInteger;

public class Gerador {

    private static AtomicInteger seq = new AtomicInteger(0);

    public static int getNextSeq() {
        return seq.getAndIncrement();
    }


}
