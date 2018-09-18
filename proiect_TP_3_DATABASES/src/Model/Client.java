package Model;

    /*
      Description :
        Unul dintre cele trei elemente care trebuie transpuse din Database :
        -Client
        -Produs
        -Comanda
     */

public class Client {

    private int idClient;
    private String CNP;
    private String nume;
    private String email;

        public Client(int idClient, String CNP, String nume, String email) {
            this.idClient = idClient;
            this.CNP = CNP;
            this.nume = nume;
            this.email = email;
        }

        public Client(){
        }

        public int getIdClient() {
            return idClient;
        }

        public void setIdClient(int idClient) {
            this.idClient = idClient;
        }

        public String getCNP() {
            return CNP;
        }

        public void setCNP(String CNP) {
            this.CNP = CNP;
        }

        public String getNume() {
            return nume;
        }

        public void setNume(String nume) {
            this.nume = nume;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

    }
