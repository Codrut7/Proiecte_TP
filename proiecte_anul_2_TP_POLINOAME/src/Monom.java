public class Monom implements Comparable {
    /* Un monom contine o putere si un coeficient */

    private int putere;
    private float coeficient;

    public Monom(int putere,float coeficient){
        this.putere = putere;
        this.coeficient = coeficient;
    }

    public int getPutere() {
        return putere;
    }

    public void setPutere(int putere) {
        this.putere = putere;
    }

    public float getCoeficient() {
        return coeficient;
    }

    public void setCoeficient(float coeficient) {
        this.coeficient = coeficient;
    }

    @Override
    public int compareTo(Object o) {
        Monom x = (Monom) o ;
        if(x.getPutere()<this.getPutere()){
            return -1;
        }else if(x.getPutere()==this.getPutere()){
            return 0;
        }else{
            return 1;
        }
    }
}
