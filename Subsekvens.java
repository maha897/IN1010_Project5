class Subsekvens{
    public final String subsekvens;
    private int antall;

    Subsekvens(String subsekvens){
        this.subsekvens = subsekvens;
        antall = 1;
    }

    void oekAntall(int oek){
        antall += oek;
    }

    int hentAntall(){
        return antall;
    }

    String hentSubsekvens(){
        return subsekvens;
    }

    @Override
    public String toString(){
        return "("+hentSubsekvens()+","+hentAntall()+")";
    }  
}

