import java.io.*;
import java.util.*;

class Oblig5Del2B{
    public static void main(String[] args){
        ArrayList <String> filnavnListe = new ArrayList<>();
        Monitor2 monitor = new Monitor2();
        final int ANT_TRADER = 8;

        Scanner sc;
        try{
            sc = new Scanner(new File("testdatalike/metadata.csv"));
            while (sc.hasNextLine()){
                filnavnListe.add("testdatalike/"+sc.nextLine());
            }
        } catch (FileNotFoundException e){
            System.err.println(e);
        }

        for (String filnavn: filnavnListe){
            Thread leseTrad = new Thread(new LeseTrad2(filnavn, monitor));
            leseTrad.start();

            try{
                leseTrad.join();
            } catch (InterruptedException e){
                System.err.println(e);
            }
        }

        for (int i = 0; i < ANT_TRADER; i++){
            Thread fletteTrad = new Thread(new FletteTrad(monitor));
            fletteTrad.start();

            try{
                fletteTrad.join();
            } catch (InterruptedException e){
                System.err.println(e);
            }
        }
        System.out.println(monitor.hentSubsekvensRegister());
    }
}
