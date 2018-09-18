package DataAccesPackage;

    /*
        Description :
        Aceasta clasa are rolul de a implementa in mod generic query-urile ce vor fi efectuate de catre aplicatie
     */

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AbstractDAO<T> {

    // In aceasta variabila vom putea stoca metadata despre clasa ce vrea sa faca un query
    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO(){
        this.type = (Class<T>)   // Type-ul <T> trebuie castat la Clasa deoarece noi vrem sa accesam metadata obiectului
                ((ParameterizedType) // cu ajutorul functiei getActualTypeArguments putem apela functia care ne ofera Type-ul ( linia 21 ) si cu ajutorul ei putem lua Type-ul din subclasa
                getClass() // ne identifica type-ul instante  ( ex :  = new ClientDAO(); )
                .getGenericSuperclass()) // ne da T-ul
                .getActualTypeArguments()[0]; // extragem T-ul mapat in memorie
    }
    /*
        Description :
        Metoda este folosita pentru a transforma un obiect de tipul ResultSet ( rezultat al unui query ) intr-un obiect java
        1. Cream o instanta de tip T ( parametru generic )
        2. Ii parcurgem fields-urile si invocam dinamic ( la run-time ) o metoda ce scrie acele field-uri in instanta creata de noi
        3. Adaugam instanta cu valori din database intr-o lista
        4. Profit ??
     */
    private List<T> createObject(ResultSet resultSet) {
        List<T> result = new ArrayList<T>();
        try{
            while(resultSet.next()){
                T instance = type.getDeclaredConstructor().newInstance(); // cream o instanta de Model nou ( Client, Produs, Comanda )
                for(Field a : type.getDeclaredFields()){
                    Object rezultat = resultSet.getObject(a.getName()); // cream un obiect in care salvam valorile variabilelor instanta
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(a.getName(),type);
                    Method method = propertyDescriptor.getWriteMethod(); // instantiem seterul pentru field-ul respectiv
                    method.invoke(instance,rezultat); // scriem in instanta obiectul field
                }
                result.add(instance);
            }

            return result;
        }
        catch(SQLException e){e.printStackTrace();}
        catch(InstantiationException e){e.printStackTrace();}
        catch(IllegalAccessException e){e.printStackTrace();}
        catch(NoSuchMethodException e){e.printStackTrace();}
        catch(InvocationTargetException e){e.printStackTrace();}
        catch(IntrospectionException e){e.printStackTrace();}

        return null;
    }

    /*
        Description :
        Query-urile efective construite cu ajutorul StringBuilder-ului
     */

    private String createSelectQuery(String field) {
        StringBuilder selectQuery = new StringBuilder();
        selectQuery.append("SELECT ");
        selectQuery.append(" * ");
        selectQuery.append(" FROM ");
        selectQuery.append(type.getSimpleName());
        selectQuery.append(" WHERE " + field + "=?");
        return selectQuery.toString();
    }

    private String insertQuery() throws IllegalAccessException{
        StringBuilder insertQuery = new StringBuilder();
        insertQuery.append("INSERT INTO ");
        insertQuery.append(type.getSimpleName());
        insertQuery.append(" VALUES (");
        for(Field fields : type.getDeclaredFields()){
            insertQuery.append("?,");
        }
        insertQuery.deleteCharAt(insertQuery.length()-1);
        insertQuery.append(")");
        return insertQuery.toString();
    }

    private String deleteQuery(String field){
        StringBuilder deleteQuery = new StringBuilder();
        deleteQuery.append("DELETE FROM ");
        deleteQuery.append(type.getSimpleName());
        deleteQuery.append(" WHERE " +field+ type.getSimpleName() + " = ?");
        return deleteQuery.toString();
    }

    private String updateQuery(String x){
        StringBuilder updateQuery = new StringBuilder();
        updateQuery.append("UPDATE "+ type.getSimpleName());
        updateQuery.append(" SET ");
        for(Field field : type.getDeclaredFields()){
            updateQuery.append(field.getName() + " = ? ,");
        }
        updateQuery.deleteCharAt(updateQuery.length()-1);
        updateQuery.append("WHERE " + x + type.getSimpleName() + "= ?");
        return updateQuery.toString();
    }

    /*

       Description :
       Putem selecta un model dintr-un tabel din database
       1-> pregatim conexiunea
       2-> pregatim si executam query-ul
       3-> inchidem conexiunea

    */

    public T findById(int id) throws SQLException{
        //1
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id" + type.getSimpleName());
        //2
        connection = SingletonConnection.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        resultSet = preparedStatement.executeQuery();
        //3
        T rezultat = createObject(resultSet).get(0);
        preparedStatement.close();
        connection.close();
        return rezultat;
    }

    /*

        Description :
        Putem adauga un model intr-un tabel din database
        1-> pregatim conexiunea
        2-> pregatim si executam query-ul + folosim reflexia pentru a ne lua field-urile din modelul pe care-l dorim sa il inseram
        3-> inchidem conexiunea

     */

    public void insertModel (T model) throws IllegalAccessException,SQLException{

        //1
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int i = 1;
        //2
        connection = SingletonConnection.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(insertQuery());
        for(Field field : model.getClass().getDeclaredFields()){
            field.setAccessible(true);
            Object x = field.get(model);
            preparedStatement.setObject(i,x);
            i++;
        }
        preparedStatement.executeUpdate();
        //3
        preparedStatement.close();
        connection.close();
    }

    /*

        Description :
        Putem sterge un model dintr-un tabel din database
        1-> pregatim conexiunea
        2-> pregatim si executam query-ul
        3-> inchidem conexiunea

     */

    public void deletebyId(int id) throws SQLException{
        //1
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        connection = SingletonConnection.getInstance().getConnection();
        //2
        preparedStatement = connection.prepareStatement(deleteQuery("id"));
        preparedStatement.setInt(1,id);
        preparedStatement.executeUpdate();
        //3
        preparedStatement.close();
        connection.close();

    }
    /*

        Description :
        Putem modifica un model dintr-un tabel din database
        1-> pregatim conexiunea
        2-> pregatim si executam query-ul
        3-> inchidem conexiunea

     */
    public void updateModel(T model,int id) throws SQLException,IllegalAccessException{
        //1
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        //2
        connection = SingletonConnection.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(updateQuery("id"));
        int i = 1;
        for(Field field : model.getClass().getDeclaredFields()){
            field.setAccessible(true);
            Object obj;
            obj = field.get(model);
            preparedStatement.setObject(i,obj);
            i++;
        }
        preparedStatement.setObject(i,id);
        preparedStatement.executeUpdate();
        //3
        preparedStatement.close();
        connection.close();
    }
}
