package testedethread;

public class MyThread implements Runnable{

    @Override
    public void run() {
        System.out.println("Thread executada");
    }
}
