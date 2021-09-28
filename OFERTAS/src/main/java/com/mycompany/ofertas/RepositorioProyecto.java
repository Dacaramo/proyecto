package com.mycompany.ofertas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class RepositorioProyecto {

    public static Connection connect(){
        final String url = "jdbc:mysql://sql5.freesqldatabase.com:3306/sql5440544";
        final String user = "sql5440544";
        final String password = "dAe3VQAf27";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch(ClassNotFoundException e){
            e.printStackTrace();
        } catch(SQLException f) {
            f.printStackTrace();
        }

        return null;
    }

    public static Boolean insertAspirante() {
        return true;
    }

    public static Boolean insertEmpleador() {
        return true;
    }

    public static Boolean insertOferta() {
        return true;
    }
}
