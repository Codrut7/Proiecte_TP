import java.util.ArrayList;
import java.util.Random;

import static java.lang.Thread.sleep;

public class Coada implements Runnable {

    /* OUTPUTS */
    private final int MIN_SERV_TIME = Generator.getMinServTime();
    private final int MAX_SERV_TIME = Generator.getMaxServTime();
    private int averageWaitingTime;
    private int averageServiceTime;
    private int emptyQueueTime;

    /* Variabile auxiliare */
    private int totalServiceTime;
    private int numarTotalClienti;
    private int numarCoada;

    /* Coada propriu-zisa */
    private ArrayList<Client> queue;

    /* La crearea unei cozi noi nu stim nici un detaliu, ele vor fi puse pe parcurs */

    public Coada() {
        this.queue = new ArrayList<>();
    }

    public int getAverageWaitingTime() {
        return averageWaitingTime;
    }

    public void setAverageWaitingTime(int averageWaitingTime) {
        this.averageWaitingTime = averageWaitingTime;
    }

    public int getAverageServiceTime() {
        return averageServiceTime;
    }

    public void setAverageServiceTime(int averageServiceTime) {
        this.averageServiceTime = averageServiceTime;
    }

    public int getEmptyQueueTime() {
        return emptyQueueTime;
    }

    public void setEmptyQueueTime(int emptyQueueTime) {
        this.emptyQueueTime = emptyQueueTime;
    }

    public int getNumarCoada() {
        return numarCoada;
    }

    public void setNumarCoada(int numarCoada) {
        this.numarCoada = numarCoada;
    }

    public ArrayList<Client> getQueue() {
        return queue;
    }

    public void addClient(Client x){
        synchronized (this.getQueue()){
            this.queue.add(x);
        }
    }
    /*
        Stergerea se va face doar atunci cand varful cozii e diferit de null ( exista client )
     */
    public void removeClient(){
        if(this.getQueue().get(0)!=null){
            synchronized (this.getQueue()){
                this.queue.remove(0);
            }
        }
    }

    /*
        Description :
        Vom face suma timpului de asteptare a tuturor clientilor iar apoi o vom impartii la numarul de clienti
        din coada -1 ( deoarece primul client din coada nu mai asteapta, e servit deja )

     */
    public void calculateAverageWaitingTime(){
        int totalWaitingTime = 0;
        for(Client a : this.queue){
            totalWaitingTime += a.getWaitingTime();
        }
        averageWaitingTime = totalWaitingTime/(queue.size()-1);
    }

    /*
        Description :
        Pentru a calcula timpul de servire mediul al cozii va trebui sa stim cat clienti au fost in total pe
        parcursul simularii la coada respectiva si timpul total de servire pe care a avut-o coada pentru fiecare
        client pe parcursul simularii  => timpTotalDeServire/numarTotalClienti pe parcursul simularii

     */

    public void calculateAverageServingTime(){
        totalServiceTime += this.queue.get(0).getServiceTime();
        averageServiceTime = totalServiceTime/numarTotalClienti;
    }

    /*
        Description :
        Metoda genereaza timpul de servire pentru primul client si ii seteaza timpul de asteptare la 0 deoarece
        nu mai asteapta dupa nimeni, lol.

    */

    public int getClientServingTime(){
        Random random = new Random();
        int servingTime = random.nextInt((MAX_SERV_TIME - MIN_SERV_TIME) + 1) + MIN_SERV_TIME;
        numarTotalClienti++;
        this.getQueue().get(0).setServiceTime(servingTime);
        this.getQueue().get(0).setWaitingTime(0);
        return servingTime;
    }

    public void printClients(){
        System.out.println("Queue " + getNumarCoada());
        for(Client i : this.getQueue()){
            System.out.print("Clientul cu : Waiting Time " + i.getWaitingTime() + " arrival time " + i.getArrivalTime() + " finish time " + i.getServiceTime() + " service time " + i.getServiceTime());
        }
        System.out.println();
    }

    public void printQueueTimes(){
        System.out.println("Queue " + getNumarCoada());
        System.out.println("Average Serving time : " + this.getAverageServiceTime() + " Average Waiting time : " + this.getAverageWaitingTime() + "Empty time : " + this.emptyQueueTime);
    }

    /*
        Description :
        Metoda creste timpul de asteptare pentru fiecare client, atata timp cat nu este primul .
        Este apelata cand primul client este servit ( in fiecare secunda )
     */

    public void incrementWaitingTime(){
        for(Client i : this.queue){
            if(i!=this.queue.get(0))
            i.setWaitingTime(i.getWaitingTime() + 1);
        }
    }

    /*
        Description :
        Metoda principala a threadului .
        Nu exista clienti => incrementam emptyQueueTime-ul
        Exista Clienti => calculam timpul de servire (x), asteptam x secunde, crestem timpul de asteptare al
                          clientilor, scoatem primul client dupa servire
     */
    @Override
    public void run() {
        while(true){
            if(this.getQueue().isEmpty()){
                try{
                    sleep(1000);
                    this.emptyQueueTime++;
                }catch(InterruptedException e){e.printStackTrace();}
            }else {
                emptyQueueTime = 0;
                int servingTime = getClientServingTime();
                calculateAverageServingTime();
                try{
                    for(int i = 0 ; i < servingTime ; i++){
                        sleep(1000);
                        synchronized (this.getQueue()){
                            incrementWaitingTime();
                        }
                    }
                }
                catch(InterruptedException e){e.printStackTrace();}
                finally{
                    calculateAverageWaitingTime();
                    synchronized (this.getQueue()){
                        //printClients();
                        printQueueTimes();
                        removeClient();
                    }
                }
            }
        }
    }
}
