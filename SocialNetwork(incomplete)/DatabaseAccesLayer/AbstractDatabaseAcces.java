package DatabaseAccesLayer;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
    27/09/2018 - Fodor Bogdan Codrut
    Clasa va implemente query-urile folosind parametrii generici
 */
public class AbstractDatabaseAcces<T> {
    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDatabaseAcces(){
        this.type = (Class<T>)   // Type-ul <T> trebuie castat la Clasa deoarece noi vrem sa accesam metadata obiectului
                ((ParameterizedType) // cu ajutorul functiei getActualTypeArguments putem apela functia care ne ofera Type-ul ( linia 21 ) si cu ajutorul ei putem lua Type-ul din subclasa
                        getClass()     // ne identifica type-ul instante  ( ex :  = new ClientDAO(); )
                                .getGenericSuperclass()) // ne da T-ul
                                         .getActualTypeArguments()[0]; // extragem T-ul mapat in memorie

    }

    //Metoda pentru a returna obiectul resultat dupa query

    private List<T> getObjectFromQuery(ResultSet resultSet) throws SQLException,NoSuchMethodException,InstantiationException,IllegalAccessException, InvocationTargetException, IntrospectionException {
        List<T> rezultat = new ArrayList<>();
        while(resultSet.next()){
            T instance = type.getDeclaredConstructor().newInstance(); // instanta obiectului ce o luam din resultatul query-ului
            for(Field i : type.getDeclaredFields()){
                Object rez = resultSet.getObject(i.getName()); // luam cate 1 field
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(i.getName(),type); // luam propertyDescriptorul pentru metoda de write
                Method writeMethod = propertyDescriptor.getWriteMethod(); // luam metoda de write
                writeMethod.invoke(instance,rez); //scriem in intanta creata field-ul
            }
            rezultat.add(instance);
        }
        return rezultat;
    }

    //Metode pentru a crea query-uri

    private String getSearchQuery(String field1,String field2){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM ");
        stringBuilder.append(type.getSimpleName());
        stringBuilder.append(" WHERE " + field1 + " =? AND " + field2 + "= ?");

        return stringBuilder.toString();
    }

    private String getSearchQuery2(String field1){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM ");
        stringBuilder.append(type.getSimpleName());
        stringBuilder.append(" WHERE " + field1 + " =?");

        return stringBuilder.toString();
    }

    private String insertQuery(){
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

    private String updateQuery(String x){
        StringBuilder updateQuery = new StringBuilder();
        updateQuery.append("UPDATE "+ type.getSimpleName());
        updateQuery.append(" SET ");
        for(Field field : type.getDeclaredFields()){
            updateQuery.append(field.getName() + " = ? ,");
        }
        updateQuery.deleteCharAt(updateQuery.length()-1);
        updateQuery.append("WHERE " + x + "= ?");
        return updateQuery.toString();
    }

    private String deleteQuery(String field){
        StringBuilder deleteQuery = new StringBuilder();
        deleteQuery.append("DELETE FROM ");
        deleteQuery.append(type.getSimpleName());
        deleteQuery.append(" WHERE " + field + " = ?");
        return deleteQuery.toString();
    }

    //Metode care efectueaza query-ul

    public List<T> searchByName(String firstName,String secondName) throws SQLException {
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        String query = getSearchQuery("firstName","secondName");
        try{
            connection = SingletonDatabaseConnection.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setObject(1,firstName);
            preparedStatement.setObject(2,secondName);
            resultSet = preparedStatement.executeQuery();
            return getObjectFromQuery(resultSet);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }finally{
            resultSet.close();
            preparedStatement.close();
            connection.close();
        }
        return null;
    }

    public T searchByEmail(String email) throws SQLException {
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        String query = getSearchQuery2("email");
        try{
            connection = SingletonDatabaseConnection.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setObject(1,email);
            resultSet = preparedStatement.executeQuery();
            return getObjectFromQuery(resultSet).get(0);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }finally{
            resultSet.close();
            preparedStatement.close();
            connection.close();
        }
        return null;
    }

    public void insertObject(T obj) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int index = 1;
        try{
            connection = SingletonDatabaseConnection.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(insertQuery());
            for(Field i : obj.getClass().getDeclaredFields()){
                i.setAccessible(true);
                Object x = i.get(obj);
                preparedStatement.setObject(index,x);
                index++;
            }
            preparedStatement.executeUpdate();
        }catch (IllegalAccessException e) {
            e.printStackTrace();
        }finally {
            preparedStatement.close();
            connection.close();
        }
    }

    public void updateModel(T model,String email) throws SQLException,IllegalAccessException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = SingletonDatabaseConnection.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(updateQuery("email"));
            int i = 1;
            for (Field field : model.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object obj;
                obj = field.get(model);
                preparedStatement.setObject(i, obj);
                i++;
            }
            preparedStatement.setObject(i, email);
            preparedStatement.executeUpdate();
        }finally {
            preparedStatement.close();
            connection.close();
        }
    }

    public void deletebyEmail(String email) throws SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = SingletonDatabaseConnection.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(deleteQuery("email"));
            preparedStatement.setString(1, email);
            preparedStatement.executeUpdate();
        }finally{
            preparedStatement.close();
            connection.close();
        }
    }
}
