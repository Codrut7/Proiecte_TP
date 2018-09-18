
package BusinessLogicLevel;
import BusinessLogicLevel.Validators.ComandaValidator;
import DataAccesPackage.ComandaDAO;
import Model.Comanda;

import java.sql.SQLException;

    /*
        Aici vom executa metodele efective de inserare,stergere,selectare si update din baza de date Comanda;
        P.S : in mod normal in cazul in care nu exista Modelul in database se va trata exceptia in blocul catch
                (dar mi-a fost lene si am folosit throws, XD )
    */

public class ComandaBLL {
    ComandaDAO comanda;
    ComandaValidator comandaValidator;

    public ComandaBLL(){
        this.comanda = new ComandaDAO();
        this.comandaValidator = new ComandaValidator();
    }

    /*
        Description :
        1. Cream obiectul comanda si ii asignam ceea ce ne returneaza database-ul
        2. Verificam daca exista acel obiect in database + daca este unul valid.
        3. Il returnam

     */

    public Comanda selectComanda(int id) throws SQLException {
        Comanda rezultat = comanda.findById(id);

        if(rezultat!=null) {
            if(comandaValidator.validateComanda(rezultat) == true) {
                return rezultat;
            }
        }
        return null;
    }

     /*
        Description :
        1. Vedem daca exista obiectul pe care dorim sa-l stergem
        2  Il stergem

     */

    public boolean deleteComanda(int id) throws SQLException{
        Comanda deSters = comanda.findById(id);
        if(deSters!=null){
            comanda.deletebyId(id);
            return true;
        }
        return false;
    }

    /*
        Description :
        1.Validam comanda pe care dorim sa o introducem
        2.O introducem

     */

    public boolean insertComanda(Comanda comandaIns) throws SQLException,IllegalAccessException{
        if(comandaValidator.validateComanda(comandaIns)==true){
            comanda.insertModel(comandaIns);
            return true;
        }
        return false;
    }

    /*
        Description :
        1. Vedem daca exista comanda pe care dorim sa o modificam
        2. Vedem daca comanda modificata este una valida
        3. modificam

     */

    public boolean updateComanda(Comanda comandaUpdate,int id)throws SQLException,IllegalAccessException{
        Comanda comandaVeche = comanda.findById(id);
        if(comandaVeche!=null){
            if(comandaValidator.validateComanda(comandaUpdate)==true){
                comanda.updateModel(comandaUpdate,id);
                return true;
            }
            return false;
        }
        return false;
    }
}
