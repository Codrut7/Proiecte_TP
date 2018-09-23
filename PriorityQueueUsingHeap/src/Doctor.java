import java.util.Random;
    /*
        Description :
        Un doctor are propriul lui thread in care rezolva pacientii
     */
public class Doctor implements Runnable {
    private int id;
    private String nume;
    private String CNP;

    public Doctor(int id, String nume, String CNP) {
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
    public void run() {
        Heap<Pacients> doctorsQueue = new Heap<>(Pacients.class,10);
        Random random = new Random();
        for(int i = 0 ; i < 9 ; i++){
            Pacients x = new Pacients(i,"Pacient"+i,"111",random.nextInt((10-1)+1));
            doctorsQueue.insert(x);
        }
        while(doctorsQueue.getSize()!=0){
            try{
                Thread.sleep(random.nextInt((10-1)+1));
                for(int i = 0 ; i < doctorsQueue.getSize() ;i++){
                    System.out.print(doctorsQueue.getHeap()[i].getPriority()+" " );
                }
                System.out.println();
                doctorsQueue.extractMin();
            }catch(InterruptedException e){e.printStackTrace();}
        }
    }
}
