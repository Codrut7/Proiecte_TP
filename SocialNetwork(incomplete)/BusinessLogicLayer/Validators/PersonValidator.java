package BusinessLogicLayer.Validators;
        /*
          28/9/2018
          Clasa contine metode de validare a tututor campurilor din modelul nostru ( Person )
       */
public class PersonValidator {
    public static boolean validateName(String name){
        if(name.length()>50||name.length()<3){
            throw new IllegalArgumentException("Invalid name length");
        }
        if(name.matches(".*\\d+.*")){   //contine numere
            throw new IllegalArgumentException("Name can't contain numbers");
        }
        return true;
    }

    public static boolean validateEmail(String email){
        if(email.length()<15||!email.contains("@")||!email.contains(".")){
            throw  new IllegalArgumentException("Email invalid");
        }
        return true;
    }

    public static boolean validatePassword(String password){
        if(password.length()<4){
            throw new IllegalArgumentException("Password invalid");
        }
        return true;
    }
}
