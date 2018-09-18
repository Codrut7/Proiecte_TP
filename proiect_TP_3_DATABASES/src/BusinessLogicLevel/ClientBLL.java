package BusinessLogicLevel;

import BusinessLogicLevel.Validators.ClientValidator;
import DataAccesPackage.ClientDAO;
import Model.Client;
import Model.Comanda;

import java.sql.SQLException;

public class ClientBLL {
    /*
        Aici vom executa metodele efective de inserare,stergere,selectare si update din baza de date Client ;
        P.S : in mod normal in cazul in care nu exista Modelul in database se va trata exceptia in blocul catch
                (dar mi-a fost lene si am folosit throws, XD )
    */
        ClientDAO client;
        ClientValidator clientValidator;

        public ClientBLL(){
            this.client = new ClientDAO();
            this.clientValidator = new ClientValidator();
        }

    /*
        Description :
        1. Cream obiectul client si ii asignam ceea ce ne returneaza database-ul
        2. Verificam daca exista acel obiect in database + daca este unul valid.
        3. Il returnam

     */

        public Client selectClient(int id) throws SQLException {
            Client rezultat = client.findById(id);

            if(rezultat!=null) {
                if(clientValidator.validateClient(rezultat) == true) {
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

        public boolean deleteClient(int id) throws SQLException{
            Client deSters = client.findById(id);
            if(deSters!=null){
                client.deletebyId(id);
                return true;
            }
            return false;
        }

    /*
        Description :
        1.Validam comanda pe care dorim sa o introducem
        2.O introducem

     */

        public boolean insertClient(Client clientIns) throws SQLException,IllegalAccessException{
            if(clientValidator.validateClient(clientIns)==true){
                client.insertModel(clientIns);
                return true;
            }
            return false;
        }

    /*
        Description :
        1. Vedem daca exista clientul pe care dorim sa o modificam
        2. Vedem daca clientul modificat este valid
        3. modificam

     */

        public boolean updateClient(Client clientUpdate,int id)throws SQLException,IllegalAccessException{
            Client clientVechi = client.findById(id);
            if(clientVechi!=null){
                if(clientValidator.validateClient(clientUpdate)==true){
                    client.updateModel(clientUpdate,id);
                    return true;
                }
                return false;
            }
            return false;
        }
    }

