package Model;

    /*
      Description :
        Unul dintre cele trei elemente care trebuie transpuse din Database :
        -Client
        -Produs
        -Comanda
     */

public class Produs {

    private int idProduct;
    private String nume;
    private String cantitate;
    private String pret;

    public Produs(int idProduct, String nume, String cantitate, String pret) {
        this.idProduct = idProduct;
        this.nume = nume;
        this.cantitate = cantitate;
        this.pret = pret;
    }

    public Produs(){

    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getCantitate() {
        return cantitate;
    }

    public void setCantitate(String cantitate) {
        this.cantitate = cantitate;
    }

    public String getPret() {
        return pret;
    }

    public void setPret(String pret) {
        this.pret = pret;
    }

}
