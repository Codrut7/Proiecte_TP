package Model;

    /*
      Description :
        Unul dintre cele trei elemente care trebuie transpuse din Database :
        -Client
        -Produs
        -Comanda
     */

public class Comanda {

    private int idComanda;
    private int idClient;
    private int idProdus;
    private int cantitate;

    public Comanda(int idComanda, int idClient, int idProdus, int cantitate) {
        this.idComanda = idComanda;
        this.idClient = idClient;
        this.idProdus = idProdus;
        this.cantitate = cantitate;
    }

    public Comanda(){
    }

    public int getIdComanda() {
        return idComanda;
    }

    public void setIdComanda(int idComanda) {
        this.idComanda = idComanda;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdProdus() {
        return idProdus;
    }

    public void setIdProdus(int idProdus) {
        this.idProdus = idProdus;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

}
