import java.io.Serializable;
import java.util.ArrayList;

        /*
            Description :
            Account Class care are 2 subclase (Spending si Saving Account ) care suprascriu metodele de withdraw si de depositare
            deoarece vrem sa avem metode specifice pentru conturi specifice

            Nu e rocket science xD
         */

public class Account implements Serializable {
    private int idPersoana;
    private int idCont;
    private int depositedSum;
    public ArrayList<ObserverInterface> observers = new ArrayList<>();

    public Account(int idPersoana, int idCont, int depositedSum) {
        this.idPersoana = idPersoana;
        this.idCont = idCont;
        this.depositedSum = depositedSum;
    }

    public Account(){}

    public int getIdPersoana() {
        return idPersoana;
    }

    public void setIdPersoana(int idPersoana) {
        this.idPersoana = idPersoana;
    }

    public int getIdCont() {
        return idCont;
    }

    public void setIdCont(int idCont) {
        this.idCont = idCont;
    }

    public void deposit(int sum){}

    public void withdraw(int sum){}

    public int getDepositedSum() {
        return depositedSum;
    }

    public void setDepositedSum(int x){
        this.depositedSum = x;
    }

    public void addListener(ObserverInterface listener){
        observers.add(listener);
    }
}
