import java.util.concurrent.*;

class LeseTrad implements Runnable{
    String filnavn;
    Monitor1 monitor;
    CountDownLatch barrier;

    LeseTrad(String filnavn, Monitor1 monitor, CountDownLatch barrier){
        this.filnavn = filnavn;
        this.monitor = monitor;
        this.barrier = barrier;
    }

    @Override 
    public void run(){
        monitor.settInn(Monitor1.filLeser(filnavn));
        barrier.countDown();
    }
}
