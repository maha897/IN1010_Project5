import java.util.*;
import java.util.concurrent.locks.*;

class Monitor1{
    SubsekvensRegister subsekvensRegister = new SubsekvensRegister();
    Lock lock = new ReentrantLock();

    void settInn(HashMap <String, Subsekvens> hashmap){
        lock.lock();
        try{
            subsekvensRegister.settInn(hashmap);
        } finally{
            lock.unlock();
        }
    }

    HashMap <String, Subsekvens> taUt(){
        lock.lock();
        try{
            return subsekvensRegister.taUt();
        } finally{
            lock.unlock();
        }
    }

    int antallMaps(){
        return subsekvensRegister.antallMaps();
    }

    ArrayList <HashMap <String, Subsekvens>> hentSubsekvensRegister(){
        return subsekvensRegister.hentSubsekvensRegister();
    }

    static HashMap <String, Subsekvens> filLeser(String filnavn){
        return SubsekvensRegister.filLeser(filnavn);
    }

    static ArrayList <HashMap <String, Subsekvens>> mappeLeser(String mappenavn){
        return SubsekvensRegister.mappeLeser(mappenavn);
    }

    void settInnMange(ArrayList <HashMap <String, Subsekvens>> hashBeholder){
        for (HashMap <String, Subsekvens> hashmap: hashBeholder){
            settInn(hashmap);
        }
    }

    static HashMap <String, Subsekvens> slaSammen(HashMap <String, Subsekvens> hashmap1, HashMap <String, Subsekvens> hashmap2){
        return SubsekvensRegister.slaSammen(hashmap1, hashmap2);
    }
}
