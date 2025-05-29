package testandolock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Semaforo implements Runnable{

    private boolean aberto = false;
    private ReentrantLock lock = new ReentrantLock();
    private Condition c = lock.newCondition();

    @Override
    public void run() {
        while(true) {
            lock.lock();
            try {
                aberto = !aberto;
                System.out.println("Semáforo aberto: " + aberto);
            } finally {
                lock.unlock();
            }
            try{
                Thread.sleep(1000 + (int) (Math.random() * 4000));
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void atravessar(int id) {
        lock.lock();
        try {
            while(!aberto) {
                System.out.println("[testandolock.Carro " + id + "] Aguardando");
                if(aberto) {
                    c.signalAll();
                }
                try {
                    c.await();
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("[testandolock.Carro " + id +"] Atravessou");
        } finally {
            lock.unlock();
        }
    }
}
