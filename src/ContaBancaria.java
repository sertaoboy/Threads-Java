import java.util.concurrent.locks.ReentrantLock;

public class ContaBancaria {

    private double saldo;
    private ReentrantLock lock = new ReentrantLock();

    public void depositar(double valor) {
        lock.lock();
        try {
            saldo = saldo + valor;
            System.out.println(getSaldo());
        } finally {
            lock.unlock();
        }
    }

    public void sacar(double valor) {
        lock.lock();
        try {
            saldo = saldo - valor;
            System.out.println(getSaldo());
        } finally {
            lock.unlock();
        }
    }

    public double getSaldo() {
        return saldo;
    }
}
