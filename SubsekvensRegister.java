import java.util.*;
import java.io.*;

class SubsekvensRegister{
    ArrayList <HashMap <String, Subsekvens>> subsekvensRegister = new ArrayList<>();

    void settInn(HashMap <String, Subsekvens> hashmap){
        subsekvensRegister.add(hashmap);
    }

    HashMap <String, Subsekvens> taUt(){
        return subsekvensRegister.remove(0);
    }

    int antallMaps(){
        return subsekvensRegister.size();
    }

    ArrayList <HashMap <String, Subsekvens>> hentSubsekvensRegister(){
        return subsekvensRegister;
    }

    static HashMap <String, Subsekvens> filLeser(String filnavn){
        HashMap <String, Subsekvens> hashmap = new HashMap<>();
        Scanner sc;
        try{
            sc = new Scanner(new File(filnavn));
            while (sc.hasNextLine()){
                String linje = sc.nextLine();
                if (linje.length() > 3){
                    for (int i = 0; i < linje.length()-2; i++){
                        String subsekvens = linje.substring(i, i+3);
                        if (!hashmap.containsKey(subsekvens)){
                            hashmap.put(subsekvens, new Subsekvens(subsekvens));
                        }
                    }
                } else System.exit(-1);
            }
        } catch (FileNotFoundException e){
            System.err.println(e);
        }
        return hashmap;
    }

    static ArrayList <HashMap <String, Subsekvens>> mappeLeser(String mappenavn){
        ArrayList <HashMap <String, Subsekvens>> hashmapBeholder = new ArrayList<>();
        Scanner sc;
        try{
            sc = new Scanner(new File(mappenavn+"/metadata.csv"));
            while (sc.hasNextLine()){
                hashmapBeholder.add(filLeser(mappenavn+"/"+sc.nextLine())); 
            }

        } catch (FileNotFoundException e){
            System.err.println(e);
        }
        return hashmapBeholder;
    }

    void settInnMange(ArrayList <HashMap <String, Subsekvens>> hashBeholder){
        for (HashMap <String, Subsekvens> hashmap: hashBeholder){
            settInn(hashmap);
        }
    }

    static HashMap <String, Subsekvens> slaSammen(HashMap <String, Subsekvens> hashmap1, HashMap <String, Subsekvens> hashmap2){
        HashMap <String, Subsekvens> newHashmap = new HashMap<>();
        newHashmap.putAll(hashmap1); 

        for (String key: hashmap2.keySet()){
            if (newHashmap.containsKey(key)){
                newHashmap.get(key).oekAntall(hashmap2.get(key).hentAntall());
            }
            else{newHashmap.put(key, hashmap2.get(key));}
        }
        return newHashmap;
    }
}
