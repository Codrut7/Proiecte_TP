package Test;

import BusinessLogicLevel.ClientBLL;
import DataAccesPackage.ClientDAO;
import DataAccesPackage.ComandaDAO;
import Model.Client;
import Model.Comanda;


import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String arg[]) throws IllegalAccessException, SQLException {
        ClientBLL a = new ClientBLL();
        Client x = new Client(22,"432432","aurel","aurel69");
        a.updateClient(x,5435);
    }

    public static void getFields(Object o){
        for(Field fields : o.getClass().getDeclaredFields()){
            fields.setAccessible(true);
            Object resultat;
            try{
                resultat = fields.get(o);
                System.out.println(fields.getName() + " " + resultat);
            } catch (IllegalAccessException e) { e.printStackTrace(); }
        }
    }
}
