import java.io.Serializable;

        /*
            Description :
            no description
         */

public class Person implements Serializable,ObserverInterface {
    private int id;
    private String nume;
    private String CNP;

    public Person(int id, String nume, String CNP) {
        this.id = id;
        this.nume = nume;
        this.CNP = CNP;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    @Override
    public void balanceUpdate() {
        System.out.println("s-a schimbat cv la un account boss");
    }
}
