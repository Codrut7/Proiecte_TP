public class SavingAccount extends Account {
    private final float interest;

    public SavingAccount(int idPersoana, int idCont, int depositedSum, float interest) {
        super(idPersoana, idCont, depositedSum);
        this.interest = interest;
    }

    public SavingAccount(float interest) {
        this.interest = interest;
    }

    public float getInterest() {
        return interest;
    }

    public void deposit(int sum){
        if(sum < 500 ){
            throw new IllegalArgumentException("hopa toti, contu de saving e pentru sume mari");
        }else{
            float interestSum = sum * interest/100 * 100/360; // presupunem ca depoziteaza pentru 100 de zile
            setDepositedSum(getDepositedSum()+(int)(sum+interestSum)); // nu stiu daca e o idee buna sa am public metoda aia dar asta 2late boys
            for(ObserverInterface o : observers){
                o.balanceUpdate();
            }
        }
    }

    public void withdraw(int sum){
        if(sum < 500){
            throw new IllegalArgumentException("hopa toti, contu de saving e pentru sume mari");
        }else{
            setDepositedSum(getDepositedSum()-sum);
            for(ObserverInterface o : observers){
                o.balanceUpdate();
            }
        }
    }
}
