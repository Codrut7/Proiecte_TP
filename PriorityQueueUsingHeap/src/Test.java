
public class Test {
    public static void main(String arg[]){
        Doctor doctor = new Doctor(1,"Miha","342423432");
        Thread a = new Thread(doctor);
        a.start();

    }
}
