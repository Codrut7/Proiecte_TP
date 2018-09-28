package BusinessLogicLayer;

import BusinessLogicLayer.Validators.PersonValidator;
import DatabaseAccesLayer.PersonDatabaseAccesLevel;
import ModelLayer.Person;

import java.io.IOException;
import java.sql.SQLException;
    /*
        28/09/2018 - Fodor Bogdan Codrut
        Clasa se va ocupa de logica efectiva a query-urilor si totodata de actualizare a
        structurii de date ce se ocupa cu reprezentarea persoanelor din reteaua sociala ( graful )

     */
public class PersonDatabaseLogic {
    private PersonDatabaseAccesLevel personDatabaseAccesLevel;
    private PersonRepresentation personRepresentation;

    public PersonDatabaseLogic() throws IOException, ClassNotFoundException {
        personDatabaseAccesLevel = new PersonDatabaseAccesLevel();
        personRepresentation = new PersonRepresentation();
    }
    /*
        In cazul ca nu exista o persoana cu email-ul respectiv ( Email-ul e primary key-ul ) vom adauga o noua persoana
     */
    public boolean insertPerson(Person p) throws SQLException, IOException {
        if(personRepresentation.contineNod(p)){
            throw new IllegalArgumentException("persoana cu emailul asta deja exista");
        }
        if(validate(p)){
            personDatabaseAccesLevel.insertObject(p);
            personRepresentation.addNode(p);
            return true;
        }
        return false;
    }

    public boolean updatePerson(Person p,String email) throws SQLException,IllegalAccessException{
        try {
            Person deInlocuit = personDatabaseAccesLevel.searchByEmail(email);
            if(validate(p)){
                personDatabaseAccesLevel.updateModel(p,email);
                personRepresentation.deleteNode(deInlocuit);
                personRepresentation.addNode(p);
                return true;
            }
        }catch(IndexOutOfBoundsException e){
            throw new IllegalArgumentException("nu exista persoana cu acest email");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void deletePerson(String email) throws SQLException {
        try {
            Person deSters = personDatabaseAccesLevel.searchByEmail(email);
            personDatabaseAccesLevel.deletebyEmail(email);
            personRepresentation.deleteNode(deSters);
        }catch(IndexOutOfBoundsException e){
            throw new IllegalArgumentException("Persoana cu acest email nu exista");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean validate(Person p){
        if(PersonValidator.validateEmail(p.getEmail())&&PersonValidator.validateName(p.getFirstName())&&PersonValidator.validateName(p.getSecondName())&&PersonValidator.validatePassword(p.getPassword())){
            return true;
        }
        return false;
    }
}
