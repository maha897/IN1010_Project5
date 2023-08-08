public class Oblig5Del1{
    public static void main(String[] args){
        SubsekvensRegister subsekvensRegister = new SubsekvensRegister();
        subsekvensRegister.settInnMange(SubsekvensRegister.mappeLeser("testdatalike"));
        
        while (subsekvensRegister.antallMaps() > 1){
            subsekvensRegister.settInn(SubsekvensRegister.slaSammen(subsekvensRegister.taUt(), subsekvensRegister.taUt()));
        }
        System.out.println(subsekvensRegister.hentSubsekvensRegister());
    }
}
