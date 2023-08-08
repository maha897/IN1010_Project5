import java.util.*;
import java.io.*;

public class Oblig5Hele{
    public static void main(String[] args) {
        final int ANT_TRADER = 8;
        String mappenavn;

        HashMap <String, String> filnavnMedStatus = new HashMap<>();
        ArrayList <Thread> leseTrader = new ArrayList<>();
        ArrayList <Thread> fletteTrader = new ArrayList<>();

        Monitor2 monitorSyk = new Monitor2();
        Monitor2 monitorFrisk = new Monitor2();
        Monitor2 monitor = new Monitor2();

        try{
            mappenavn = args[0];
            Scanner sc;
            try {
                sc = new Scanner(new File(mappenavn+"/metadata.csv"));
                while(sc.hasNextLine()){
                    String[] linje = sc.nextLine().split(",");
                    filnavnMedStatus.put(mappenavn+"/"+linje[0], linje[1]);
                }
                sc.close();
                
            } catch (FileNotFoundException e){
                System.err.println(e);
            }
        } catch (IndexOutOfBoundsException e){
            System.out.println("Skriv mappenavn.");
        }

        for (String filnavn: filnavnMedStatus.keySet()){            
            switch (filnavnMedStatus.get(filnavn)){
                case "True":
                    leseTrader.add(new Thread(new LeseTrad2(filnavn, monitorSyk)));
                    break;
                case "False":
                    leseTrader.add(new Thread(new LeseTrad2(filnavn, monitorFrisk)));
                    break;
                default:
                    leseTrader.add(new Thread(new LeseTrad2(filnavn, monitor)));
                    break;
            }
        }      
        
        for (Thread leseTrad: leseTrader){
            leseTrad.start();
        }

        for (Thread leseTrad: leseTrader){
            try{
                leseTrad.join();
            } catch (InterruptedException e){
                System.err.println(e);
            }
        }
       
        for (int i = 0; i < ANT_TRADER; i++){
            Thread fletteSyk = new Thread(new FletteTrad(monitorSyk));
            Thread fletteFrisk = new Thread(new FletteTrad(monitorFrisk));
            fletteTrader.add(fletteSyk); fletteSyk.start(); 
            fletteTrader.add(fletteFrisk); fletteFrisk.start();
        }

        for (Thread fletteTrad: fletteTrader){
            try{
                fletteTrad.join();
            } catch (InterruptedException e){
                System.err.println(e);
            }
        }
        
        System.out.println("Dominante subsekvenser");
        System.out.println("======================");
        for (String subsekvens: monitorSyk.hentMap().keySet()){
            if (monitorFrisk.hentMap().containsKey(subsekvens) &&
                        monitorSyk.hentMap().get(subsekvens).hentAntall() 
                        > monitorFrisk.hentMap().get(subsekvens).hentAntall() + 6){
                System.out.println("Subsekvens: "+subsekvens+", "+monitorSyk.hentMap().get(subsekvens).hentAntall()+" forekomster.");
            }
        }
    }
}
