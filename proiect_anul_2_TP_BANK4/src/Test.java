import java.io.IOException;
import java.util.HashMap;

public class Test {
    public static void main(String arg[]) throws ClassNotFoundException, IOException {
        Bank banca = new Bank();
        Person persoana = new Person(1,"Mihai","123456789");
        Account x = new SavingAccount(1,1,200,4);
        x.addListener(persoana);
        banca.withdraw(1,1,3434);
    }
}
