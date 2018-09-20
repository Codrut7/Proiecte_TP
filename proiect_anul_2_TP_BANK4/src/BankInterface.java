import java.util.List;

    /*
        Description :
        Interfata va declara toate operatiile care se pot efectua asupra conturilor detinute de persoane
        Vom folosi design by contract, specificand preconditiile, postconditiile si invariantii
     */

    /*
        @Invariants : Person !=null
    */
public interface BankInterface {
    /*
        Conditii pentru functia de adaugare a unei persoane
        @pre : hashmap.get(person)==null
        @pre : persoana.id != hashmap.keyset.ids ( nu avem clienti cu acelasi id )
        @pre : id > 0
        @post : Hashmap !=null
                @pre Hashmap.size() == @post Hashmap.size()+1

     */

    void addPerson(Person person);

    /*
        Conditii pentru functia de adaugare a unui Account
        @pre :  Account!=null
        @pre : account.idAcc != person.Accounts.id ( nu are deja un cont cu id similar )
        @pre :  person.getiD==account.getPersonID ( id-ul de posesor al accountului trebuie sa corespunda cu id-ul persoanei )
        @post : @pre Hashmap.size().get(person) == @post Hashmap.get(person).size()+1

     */

    void addAccount(int idPerson,Account account);

    /*
        Conditii pentru functia de remove a unei persoane :

        @pre : id > 0
        @post : @pre HashMap.size() == @post Hashmap.size()-1

     */

    void removePerson(int idPerson);

    /*
        Conitii pentru functia de remove a unui Account :
        @pre : idAcc > 0
        @pre : map.get(person).contains(idAccount)  persoana cu id-ul respectiv detine un cont cu idCont care poate fi scos
        @post : @pre map.get(persoana).size()==map.get(persoana).size()+1

     */

    void removeAccount(int idPerson,int idAccount);

    /*
        Conditii pentru functia de update a unui Account :
        @pre : idPersoana(de la account nou ) == idPersoana

        @post @pre account != account

    */

    void updateAccount(int idPerson,Account account);

    List<Account> returnList(int idPerson);

    /*
        Conditii pentru a face o depositare in cont :
        @pre : hashmap.get(persoana).contains(account) -> account-ul exista
        @pre : depositedSum > 0
        @pre : depositedSum < 10000
        @post @pre  accBalance == accBalance - sum

     */

    void deposit(int idPerson,int idAccount,int sum);

    /*
        Conditii pentru a face o retragere din cont :
        @pre : hashmap.get(persoana).contains(account) -> account-ul exista
        @pre : sum > 0
        @pre : sum < 10000
        @pre : accountBalance > withdrawalSum
        @post : @pre accBalance ==  accBalance + sum;
     */

    void withdraw(int idPerson,int idAccount,int sum);
}
