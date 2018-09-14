import javafx.print.Collation;

import java.util.ArrayList;
import java.util.Collections;

public class Polinom implements Operatii {
    /* Un polinom este alcatuit din mai multe monoame => o lista de monoame */
    private ArrayList<Monom> polinom ;

    public Polinom(){
        polinom = new ArrayList<>();
    }

    public ArrayList<Monom> getPolinom() {
        return polinom;
    }

    /* Vom adauga un monom nou in polinomul final doar daca acesta va fi diferit de 0 */
    public void addMonom(Monom p){
        if(p!=null&&p.getCoeficient()!=0){
            polinom.add(p);
        }
    }

    public Polinom adunare(Polinom a){
        Polinom rezultat = new Polinom();
        ArrayList<Monom> listaDeSters = new ArrayList<>();
        ArrayList<Monom> listaDeAdaugat = new ArrayList<>();
        for(Monom i : this.getPolinom()){
            rezultat.addMonom(i);
        }
        for(Monom i : a.getPolinom()){
            rezultat.addMonom(i);
        }
        for(Monom i : rezultat.getPolinom()){
            for(Monom j : rezultat.getPolinom().subList(0,rezultat.getPolinom().size()/2)){
                if(i.getPutere()==j.getPutere()&&i!=j){
                    Monom x = new Monom(i.getPutere(),i.getCoeficient()+j.getCoeficient());
                    listaDeSters.add(i);
                    listaDeSters.add(j);
                    listaDeAdaugat.add(x);
                }
            }
        }
        rezultat.getPolinom().removeAll(listaDeSters);
        rezultat.getPolinom().addAll(listaDeAdaugat);
        listaDeSters.clear();
        return rezultat;
    }

    @Override
    public Polinom scadere(Polinom a) {
        Polinom rezultat = new Polinom();
        ArrayList<Monom> listaDeSters = new ArrayList<>();
        ArrayList<Monom> listaDeAdaugat = new ArrayList<>();
        for(Monom i : this.getPolinom()){
            rezultat.addMonom(i);
        }
        for(Monom i : a.getPolinom()){
            rezultat.addMonom(i);
        }
        for(Monom i : rezultat.getPolinom()){
            for(Monom j : rezultat.getPolinom().subList(0,rezultat.getPolinom().size()/2)){
                if(i.getPutere()==j.getPutere()&&i!=j){
                    Monom x = new Monom(i.getPutere(),j.getCoeficient()-i.getCoeficient());
                    listaDeSters.add(i);
                    listaDeSters.add(j);
                    listaDeAdaugat.add(x);
                }
            }
        }
        rezultat.getPolinom().removeAll(listaDeSters);
        rezultat.getPolinom().addAll(listaDeAdaugat);
        listaDeAdaugat.clear();
        listaDeSters.clear();
        return rezultat;
    }

    @Override
    public Polinom derivare() {
        Polinom rezultat = new Polinom();
        for(Monom i : this.getPolinom()){
            Monom x = new Monom(i.getPutere()-1,i.getCoeficient()*i.getPutere());
            rezultat.addMonom(x);
        }
        return rezultat;
    }

    @Override
    public Polinom integrare() {
        Polinom rezultat = new Polinom();
        for(Monom i : this.getPolinom()){
            Monom x = new Monom(i.getPutere()+1,i.getCoeficient()/(i.getPutere()+1));
            rezultat.addMonom(x);
        }
        return rezultat;
    }

    @Override
    public Polinom inmultire(Polinom a) {
        Polinom rezultat = new Polinom();
        ArrayList<Monom> listaDeSters = new ArrayList<>();
        ArrayList<Monom> listaDeAdaugat = new ArrayList<>();
        for(Monom i : this.getPolinom()){
            for(Monom j : a.getPolinom()){
                Monom x = new Monom(i.getPutere()+j.getPutere(),i.getCoeficient()*j.getCoeficient());
                rezultat.addMonom(x);
            }
        }
        for(Monom i : rezultat.getPolinom()){
            for(Monom j : rezultat.getPolinom().subList(0,rezultat.getPolinom().size()/2)){
                if(i.getPutere()==j.getPutere()&&i!=j){
                    Monom x = new Monom(i.getPutere(),j.getCoeficient()+i.getCoeficient());
                    listaDeSters.add(i);
                    listaDeSters.add(j);
                    listaDeAdaugat.add(x);
                }
            }
        }
        rezultat.getPolinom().removeAll(listaDeSters);
        rezultat.getPolinom().addAll(listaDeAdaugat);
        listaDeSters.clear();
        return rezultat;
    }

    @Override
    public Polinom impartire(Polinom b) {
       //TO-DO
        return null;
    }
    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Polinom)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        Polinom c = (Polinom) o;

        // Compare the data members and return accordingly
        for(int i = 0 ; i < c.polinom.size(); i++){
            if(this.polinom.get(i).getCoeficient()!=c.polinom.get(i).getCoeficient()||this.polinom.get(i).getPutere()!=c.polinom.get(i).getPutere()){
                return false;
            }
        }
        return true;
}

    public static void main(String arg[]){

        Monom a = new Monom(2,1);
        Monom b = new Monom(0,1);
        Monom c = new Monom(2,1);
        Monom d = new Monom(0,5);
        Polinom x = new Polinom();
        Polinom y = new Polinom();
        x.addMonom(a);
        x.addMonom(b);
        y.addMonom(c);
        y.addMonom(d);
        Polinom rezultat = x.inmultire(y);
        Collections.sort(rezultat.polinom);
        for(Monom u : rezultat.getPolinom()){
            System.out.print(u.getCoeficient()+"x^"+u.getPutere()+"+");
        }

    }
}
