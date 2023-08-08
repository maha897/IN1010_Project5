import java.util.*;
import java.util.concurrent.locks.*;

class Monitor2{
    SubsekvensRegister subsekvensRegister = new SubsekvensRegister();
    Lock lock = new ReentrantLock();
    Condition harEn = lock.newCondition();
    
    void settInn(HashMap <String, Subsekvens> hashmap){
        lock.lock();
        try{
            subsekvensRegister.settInn(hashmap);
            harEn.signalAll();
        } finally{
            lock.unlock();
        }
    }

    HashMap <String, Subsekvens> taUt(){
        lock.lock();
        try{
            while (antallMaps() < 1){
                harEn.await();
            }
        } catch (InterruptedException e){
            System.err.println(e);

        } finally{
            lock.unlock();
        }
        return subsekvensRegister.taUt();
    }

    ArrayList <HashMap <String, Subsekvens>> hentTo(){
        lock.lock();
        ArrayList <HashMap <String, Subsekvens>> hashmaps = new ArrayList<>();
        try{
            hashmaps.add(taUt()); hashmaps.add(taUt());

        } finally{
            lock.unlock();
        }
        return hashmaps;
    }

    HashMap <String, Subsekvens> hentMap(){
        return subsekvensRegister.hentSubsekvensRegister().get(0);
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