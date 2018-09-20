import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Bank implements BankInterface {
    public HashMap<Person, List<Account>> clienti;

    // Incarcam hashmap-ul din fisier la instantierea clasei bank 
    public Bank() throws ClassNotFoundException,IOException{
        loadData();
    }

    private void loadData() throws IOException,ClassNotFoundException {
        File filez = new File("D:\\proiect_anul_2_TP_BANK4\\map.ser");
        FileInputStream file = new FileInputStream(filez);
        ObjectInputStream input = new ObjectInputStream(file);
        clienti = (HashMap<Person,List<Account>>) input.readObject();
        input.close();
        file.close();
    }

    private void saveData() throws IOException{
        FileOutputStream file = new FileOutputStream("D:\\proiect_anul_2_TP_BANK4\\map.ser");
        ObjectOutputStream output = new ObjectOutputStream(file);
        output.writeObject(clienti);
        output.close();
        file.close();
    }

    public Person checkInvariant(int id){
        Person persoana = null;
        int ok = 0;
        for(Person a : clienti.keySet()){
            if(a.getId()==id){
                ok = 1;
                persoana = a;
            }
        }
        if(ok==0){
            return null;
        }else{
            return persoana;
        }
    }

    @Override
    public void addPerson(Person person) {
        // @ invariant
        if(person==null){
            throw new IllegalArgumentException("Invalid person");
        }

        // @ pre
        for(Person p : clienti.keySet()){
            if(p.getId()==person.getId()){
                throw new IllegalArgumentException("Person already exists");
            }
        }
        if(person.getId()<1){
            throw new IllegalArgumentException("Invalid id for person");
        }

        // adaugarea efectiva
        int hashmapSize = 0;
        if(clienti!=null){
            clienti.put(person,new ArrayList<>());
        }else{
            clienti = new HashMap<>();
            hashmapSize = clienti.size();
            clienti.put(person,new ArrayList<>());
        }

        // @post
        if(clienti.size()==hashmapSize){
            throw new AssertionError("Persoana nu a fost adaugata");
        }
        try{
            saveData();
        }catch (IOException e){e.printStackTrace();}
    }

    @Override
    public void addAccount(int idPerson, Account account) {
        // @ invariant
        Person person = checkInvariant(idPerson);
        if(person==null){
            throw new IllegalArgumentException("persoana invalida");
        }
        // @ pre
        if(account==null){
            throw new IllegalArgumentException("Invalid account");
        }
        if(account.getIdPersoana()!=person.getId()){
            throw new IllegalArgumentException("Contul nu are acelasi id ca si persoana");
        }
        for(Account a : clienti.get(person)){
            if(a.getIdCont()==account.getIdCont()){
                throw new IllegalArgumentException("Persoana are deja un cont cu id similar");
            }
        }
        // adaugarea efectiva
        int accountList = clienti.get(person).size();
        clienti.get(person).add(account);

        // @ post
        if(clienti.get(person).size()!=accountList+1){
            throw new AssertionError("nu a fost inserat accountul");
        }
        try{
            saveData();
        }catch (IOException e){e.printStackTrace();}
    }

    @Override
    public void removePerson(int id) {
        // Variabile ajutatoare
        Person person = checkInvariant(id);
        int size;

        //@ Invariant
        if(person==null){
            throw new IllegalArgumentException("nu exista persoana cu id-ul "+ id);
        }

        // Stergerea efectiva
        size = clienti.size();
        clienti.remove(person);

        // @ post
        if(size!=clienti.size()+1){
            throw new AssertionError("size-ul persoanelor nu s-a schimbat la stergere");
        }
        try{
            saveData();
        }catch (IOException e){e.printStackTrace();}
    }

    @Override
    public void removeAccount(int idPerson, int idAccount) {
        // Variabila ajutatoare
        Person person = checkInvariant(idPerson);
        Account account = null;
        int size = -1;
        int ok = 1 ;

        if(person==null){
            throw new IllegalArgumentException("nu exista persoana cu idPersoana dat");
        }
        // @pre
        if(idAccount<1){
            throw new IllegalArgumentException("id Account invalid");
        }
        for(Account x : clienti.get(person)){
            if(x.getIdCont()==idAccount){
                System.out.println(x.getIdCont());
                ok = 0;
            }
        }
        if(ok==1){
            throw new IllegalArgumentException("accountul cu acest id nu exista");
        }

        // stergerea efectiva
        for(Account a : clienti.get(person)){
            if(a.getIdCont()==idAccount){
                size = clienti.get(person).size();
                account = a;
            }
        }
        clienti.get(person).remove(account);
        // @post
        if(size!=clienti.get(person).size()+1){
            throw new AssertionError("nu am putut sterge contul ");
        }
        try{
            saveData();
        }catch (IOException e){e.printStackTrace();}
    }

    @Override
    public void updateAccount(int idPerson, Account account) {
        // Variabile ajutatoare
        Person person = checkInvariant(idPerson);
        Account oldAcc = null;
        int index = 0;
        if(person==null){
            throw new IllegalArgumentException("nu exista persoana cu id respectiv");
        }

        // @ pre
        if(idPerson!=account.getIdPersoana()){
            throw new IllegalArgumentException("nu corespund id-urile de persoana dintre cont si Account");
        }

        // update efectiv
        int indexAccountDeSchimbat = 0;
        for(Person i : clienti.keySet()){
            if(i.getId()==idPerson){
                person = i;
                for(Account j : clienti.get(i)){
                    if(j.getIdCont()==account.getIdCont()){
                        oldAcc = j;
                        indexAccountDeSchimbat = index;
                    }
                    index++;
                }
            }
        }
        clienti.get(person).remove(oldAcc);
        clienti.get(person).add(account);

        // @ post
        if(clienti.get(person).get(indexAccountDeSchimbat)==oldAcc){
            throw new AssertionError("nu s-a schimbat contul");
        }
        try{
            saveData();
        }catch (IOException e){e.printStackTrace();}
    }

    @Override
    public List<Account> returnList(int idPerson) {
        // @ Invariant

        Person person = checkInvariant(idPerson);
        if(person==null){
            throw new IllegalArgumentException("nu exista persoana");
        }
        return clienti.get(person);
    }

    @Override
    public void deposit(int idPerson, int idAccount, int sum) {
        //variabile ajutatoare
        int flag = 0;
        int indexAcc = -1;
        int index = 0;
        int oldBalance = -1;
        // @ Invariant
        Person person = checkInvariant(idPerson);
        if(person==null){
            throw new IllegalArgumentException("persoana nu exista");
        }

        // @ pre
        for(Account i : clienti.get(person)){
            if(idAccount==i.getIdCont()){
                flag = 1;
                indexAcc = index;
            }
            index++;
        }
        if(flag==0){
            throw new IllegalArgumentException("contul cu id specificat nu exista");
        }
        if(sum < 1){
            throw new IllegalArgumentException("suma invalida");
        }
        if(sum > 10000){
            throw new IllegalArgumentException("suma prea mare");
        }

        //depozitare
        oldBalance = clienti.get(person).get(indexAcc).getDepositedSum();
        System.out.println(oldBalance);
        clienti.get(person).get(indexAcc).deposit(sum);
        System.out.println(clienti.get(person).get(indexAcc).getDepositedSum());

        // @ post
        if(oldBalance==clienti.get(person).get(indexAcc).getDepositedSum()){
            throw new AssertionError("nu s-au depositat banii boi");
        }
        try{
            saveData();
        }catch (IOException e){e.printStackTrace();}
    }

    @Override
    public void withdraw(int idPerson, int idAccount, int sum) {
        //variabile ajutatoare
        int flag = 0;
        int indexAcc = -1;
        int index = 0;
        int oldBalance = -1;
        // @ Invariant
        Person person = checkInvariant(idPerson);
        if(person==null){
            throw new IllegalArgumentException("persoana nu exista");
        }

        // @ pre
        for(Account i : clienti.get(person)){
            if(idAccount==i.getIdCont()){
                flag = 1;
                indexAcc = index;
            }
            index++;
        }
        if(flag==0){
            throw new IllegalArgumentException("contul cu id specificat nu exista");
        }
        if(sum < 1){
            throw new IllegalArgumentException("suma invalida");
        }
        if(sum > 10000){
            throw new IllegalArgumentException("suma prea mare");
        }
        if(sum > clienti.get(person).get(indexAcc).getDepositedSum()){
            throw new IllegalArgumentException("hopa tati, n-ai atatia bani");
        }
        //depozitare
        oldBalance = clienti.get(person).get(indexAcc).getDepositedSum();
        System.out.println(oldBalance);
        clienti.get(person).get(indexAcc).withdraw(sum);
        System.out.println(clienti.get(person).get(indexAcc).getDepositedSum());

        // @ post
        if(oldBalance==clienti.get(person).get(indexAcc).getDepositedSum()){
            throw new AssertionError("nu s-au scos banii boi");
        }
        try{
            saveData();
        }catch (IOException e){e.printStackTrace();}
    }



    public void deleteAll(){
        clienti.keySet().clear();
        try{
            saveData();
        }catch (IOException e){e.printStackTrace();}
    }
}
