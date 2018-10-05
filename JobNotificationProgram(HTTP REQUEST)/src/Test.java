/**
 * Created by cordu on 10/5/2018.
 */
public class Test {
    public static void main(String arg[]){
        Website indeed = new Website("https://ro.indeed.com/jobs?q=java&l=Cluj&sort=date");
        Thread a = new Thread(indeed);
        a.start();
    }
}
