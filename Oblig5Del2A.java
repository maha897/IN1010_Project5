import java.util.*;
import java.util.concurrent.*;
import java.io.*;

public class Oblig5Del2A{
    public static void main(String[] args){
        ArrayList <String> filnavnListe = new ArrayList<>();
        Monitor1 monitor = new Monitor1();
        CountDownLatch barrier;
        Scanner sc;

        try{
            sc = new Scanner(new File("testdatalike/metadata.csv"));
            while (sc.hasNextLine()){
                filnavnListe.add("testdatalike/"+sc.nextLine());
            }
        } catch (FileNotFoundException e){
            System.err.println(e);
        }
        
        barrier = new CountDownLatch(filnavnListe.size());

        for (String filnavn: filnavnListe){
            new Thread(new LeseTrad(filnavn, monitor, barrier)).start();
        }

        try {
            barrier.await();
        } catch (InterruptedException e){
            System.err.println(e);;
        }

        while (monitor.antallMaps() > 1){
            monitor.settInn(Monitor1.slaSammen(monitor.taUt(), monitor.taUt()));
        }
        System.out.println(monitor.hentSubsekvensRegister());
    }
}