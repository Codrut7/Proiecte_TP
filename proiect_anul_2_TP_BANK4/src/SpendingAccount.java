public class SpendingAccount extends Account {

    public SpendingAccount(int idPersoana, int idCont, int depositedSum) {
        super(idPersoana, idCont, depositedSum);
    }

    public SpendingAccount() {}

    // nimic special, conturi pentru plebi

    public void deposit(int sum){
        setDepositedSum(getDepositedSum()+sum);
        for(ObserverInterface o : observers){
            o.balanceUpdate();
        }
    }

    public void withdraw(int sum){
        setDepositedSum(getDepositedSum()-sum);
        for(ObserverInterface o : observers){
            o.balanceUpdate();
        }
    }
}
