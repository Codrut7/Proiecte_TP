import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Thread.sleep;

public class Generator implements Runnable {
    // INPUTS
    private int minArriveTime;
    private int maxArriveTime;
    private int nrQueues;
    private int simInterval;
    private static int minServTime;
    private static int maxServTime;

    // Variabile auxiliare
    private int actualTime;

   // Queues
    ArrayList<Coada> queues;
    ArrayList<Thread> threads;

    public Generator(int minArriveTime, int maxArriveTime, int nrQueues, int simInterval, int minServTime, int maxServTime) {
        this.minArriveTime = minArriveTime;
        this.maxArriveTime = maxArriveTime;
        this.nrQueues = nrQueues;
        this.simInterval = simInterval;
        this.minServTime = minServTime;
        this.maxServTime = maxServTime;
        queues = new ArrayList<>(nrQueues);
        threads = new ArrayList<>();
    }

    /*
        Getters and setters
     */

    public int getMinArriveTime() {
        return minArriveTime;
    }

    public void setMinArriveTime(int minArriveTime) {
        this.minArriveTime = minArriveTime;
    }

    public int getMaxArriveTime() {
        return maxArriveTime;
    }

    public void setMaxArriveTime(int maxArriveTime) {
        this.maxArriveTime = maxArriveTime;
    }

    public int getNrQueues() {
        return nrQueues;
    }

    public void setNrQueues(int nrQueues) {
        this.nrQueues = nrQueues;
    }

    public int getSimInterval() {
        return simInterval;
    }

    public void setSimInterval(int simInterval) {
        this.simInterval = simInterval;
    }

    public static int getMinServTime() {
        return minServTime;
    }

    public void setMinServTime(int minServTime) {
        this.minServTime = minServTime;
    }

    public static int getMaxServTime() {
        return maxServTime;
    }

    public void setMaxServTime(int maxServTime) {
        this.maxServTime = maxServTime;
    }

    public int getActualTime() {
        return actualTime;
    }

    public void setActualTime(int actualTime) {
        this.actualTime = actualTime;
    }

    /*
        Metode ajutatoare :
     */

    public int generateArriveTime(){
        Random random = new Random();
        int arriveTime = random.nextInt((maxArriveTime - minArriveTime) + 1) + minArriveTime;
        return arriveTime;
    }

    public int selectRandomQueue(){
        Random random = new Random();
        int queue = random.nextInt(nrQueues);
        return queue;
    }

    public void initializeThreads(){
        for(int i = 0 ; i < nrQueues ; i++){
            Coada a = new Coada();
            a.setNumarCoada(i);
            queues.add(a);
            Thread x = new Thread(a);
            threads.add(x);
            x.start();
        }
    }

    @Override
    public void run() {
        initializeThreads();
        while(true){
            int arriveTime = generateArriveTime();
            int queue = selectRandomQueue();
            try{
                for(int i = 0 ; i < arriveTime ; i++){
                    sleep(1000);
                    actualTime++;
                }
                Client x = new Client(actualTime);
                synchronized (queues.get(queue)){
                    queues.get(queue).addClient(x);
                }
            }catch(InterruptedException e){e.printStackTrace();}
        }
    }
}
