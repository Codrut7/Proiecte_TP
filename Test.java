import javax.swing.UIManager;

public class Test {
	public static void main(String arg[]) {
		Server a = new Server();
		Client x = new Client();
		Client y = new Client();
		Thread thread1 = new Thread(a);
		Thread thread2 = new Thread(x);
		thread1.start();
		thread2.start();
	}
}
