package DatabaseAccesLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

    /*
        27/09/2018 - Fodor Bogdan Codrut
        Clasa creaza conexiunea de tip singleton catre baza de date ce va retine utilizatorii retelei sociale

     */
public class SingletonDatabaseConnection {

    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost:3306/socialnetworkdatabase";
    private final String USERNAME = "root";
    private final String PASSWORD = "";

    private static SingletonDatabaseConnection instance = null;
    private Connection connection;

    private SingletonDatabaseConnection(){
            try{
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }catch(SQLException e){
                e.printStackTrace();
            }
    }

    public Connection getConnection(){
        return this.connection;
    }

    public static SingletonDatabaseConnection getInstance() throws SQLException{
        if(instance==null){
            instance = new SingletonDatabaseConnection();
            return instance;
        }else if(instance.connection.isClosed()){
            instance = new SingletonDatabaseConnection();
            return instance;
        }else{
            return instance;
        }
    }
}
