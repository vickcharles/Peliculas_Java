/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peliculas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author SoftA3-HP01
 */
public class Conexion {

    private final String host = "jdbc:mysql://localhost:3306/";
    private final String bd = "bd_peliculas";
    private final String user = "root";
    private final String pass = "";

    private Connection con = null;
    public Conexion() {}

    public Connection getConexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance(); // INSTANCIAR EL DRIVER
            con = DriverManager.getConnection(host + bd, user, pass); // AQUI ALIMENTO MYSQL CON LOS DATOS DE CONEXION
            System.out.println("Conexión Establecida");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) { //CADA EXEPCION MANEJA UN TIPO DE EROR
            System.out.println(e.getMessage());// DE AQUI SALE EL MENSAJE DEL TIPOM DE ERROR
        }
        return con;// RETORNAR *CON*
    }
    
//    public static void main(String[] args) {
//        Conexion con = new Conexion();
//        con.getConexion();
//    }
}
