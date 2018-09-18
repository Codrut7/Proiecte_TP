package BusinessLogicLevel;

import BusinessLogicLevel.Validators.ComandaValidator;
import BusinessLogicLevel.Validators.ProductValidator;
import DataAccesPackage.ProdusDAO;
import Model.Comanda;
import Model.Produs;

import java.sql.SQLException;

    /*
        Aici vom executa metodele efective de inserare,stergere,selectare si update din baza de date Produs;
        P.S : in mod normal in cazul in care nu exista Modelul in database se va trata exceptia in blocul catch
                (dar mi-a fost lene si am folosit throws, XD )
    */

public class ProdusBLL {

    private ProdusDAO produsDAO = new ProdusDAO();
    private ProductValidator productValidator = new ProductValidator();

        public ProdusBLL(){
            this.produsDAO = new ProdusDAO();
            this.productValidator = new ProductValidator();
        }

    /*
        Description :
        1. Cream obiectul produs si ii asignam ceea ce ne returneaza database-ul
        2. Verificam daca exista acel obiect in database + daca este unul valid.
        3. Il returnam

     */

        public Produs selectProdus(int id) throws SQLException {
            Produs rezultat = produsDAO.findById(id);
            if(rezultat!=null) {
                if(productValidator.validateProdus(rezultat) == true) {
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

        public boolean deleteProdus(int id) throws SQLException{
            Produs deSters = produsDAO.findById(id);
            if(deSters!=null){
                produsDAO.deletebyId(id);
                return true;
            }
            return false;
        }

    /*
        Description :
        1.Validam produsul pe care dorim sa o introducem
        2.O introducem

     */

        public boolean insertComanda(Produs produsIns) throws SQLException,IllegalAccessException{
            if(productValidator.validateProdus(produsIns)==true){
                produsDAO.insertModel(produsIns);
                return true;
            }
            return false;
        }

    /*
        Description :
        1. Vedem daca exista produsul pe care dorim sa o modificam
        2. Vedem daca produsul modificat este unul valid
        3. modificam

     */

        public boolean updateProdus(Produs produsUpdate,int id)throws SQLException,IllegalAccessException{
            Produs produsVechi = produsDAO.findById(id);
            if(produsVechi!=null){
                if(productValidator.validateProdus(produsUpdate)==true){
                    produsDAO.updateModel(produsUpdate,id);
                    return true;
                }
                return false;
            }
            return false;
        }
    }

