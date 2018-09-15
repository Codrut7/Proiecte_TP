public class Test {
    public static void main(String arg[]){
        Generator alpha = new Generator(1,3,2,10,5,9);
        Thread a = new Thread(alpha);
        a.start();
    }
}
