import java.util.*;

class FletteTrad implements Runnable{
    Monitor2 monitor;

    FletteTrad(Monitor2 monitor){  
        this.monitor = monitor;
    }
    
    @Override 
    public void run(){
        while (monitor.antallMaps() > 1){
            ArrayList <HashMap <String, Subsekvens>> hashmaps = monitor.hentTo();
            monitor.settInn(Monitor2.slaSammen(hashmaps.get(0), hashmaps.get(1)));
        }
    }
}
