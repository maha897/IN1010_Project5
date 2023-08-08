import java.util.concurrent.*;

class LeseTrad2 implements Runnable{
    String filnavn;
    Monitor2 monitor;

    LeseTrad2(String filnavn, Monitor2 monitor){
        this.filnavn = filnavn;
        this.monitor = monitor;
    }

    @Override 
    public void run(){
        monitor.settInn(Monitor2.filLeser(filnavn));
    }
}

