package DataAccesPackage;

    /*
        Description :
        Pachetul se ocupa cu tot ceea ce tine de conexiunea dintre database si aplicatia Java.
        In acesta clasa vom crea conexiunea catre baza de date ( folosind Singleton DP )
    */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class SingletonConnection {

    private static SingletonConnection instance = null;

        //Variabile folosite pentru a ne conecta catre Database

    private Connection connection;
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/schooldb";
    private final String username = "root";
    private final String password = "Bmwseria3";

    private SingletonConnection() throws SQLException {
        try{
            Class.forName(DRIVER); // initializeaza variabilele si campurile statice din libraria jdbc ( ca sa putem initializa conexiunea
            this.connection = DriverManager.getConnection(url,username,password);
        }catch(ClassNotFoundException e){e.printStackTrace();}
    }

    public Connection getConnection(){
        return this.connection;
    }

    public static SingletonConnection getInstance() throws java.sql.SQLException{
        if(instance==null){
            instance = new SingletonConnection();
        }else if(instance.getConnection().isClosed()){
            instance = new SingletonConnection();
        }
        return instance;
    }
}
