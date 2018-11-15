/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peliculas;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Crud {
    private Conexion cx = new Conexion();
    private Connection con; // SE IMPORTA JAVA.SQL.CONNECTION
    private PreparedStatement pst; //permire ejecutar querys y cargar parametros del query (instrucciones sql)
    private ResultSet rs; // cuando hago select me va a almacenar todos los registros
    private Statement st; // ejecuta instrucciones sql
    
    
    public Crud() {
        try {
            if ((con = cx.getConexion()) == null) {
                JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException e) {
            System.out.println(e.getMessage());
        }
       
    }
    // Metodos para insertar
    public boolean insertar(String Nombre, String Genero, int Año, String Actor, String Pais) {
        try {
            String query = "INSERT INTO tbl_peliculas (Nom_peli, Gen_peli, Año_peli, Act_peli, Pais_peli) VALUES (?, ?, ?, ?, ?)";
            pst = con.prepareStatement(query);
            pst.setString(1, Nombre);
            pst.setString(2, Genero);
            pst.setInt(3, Año);
            pst.setString(4, Actor);
            pst.setString(5, Pais);
            pst.executeUpdate(); // SE USA PARA (INSERT, UPDATE, DELETE)
            System.out.println("Registro guardado exitosamente");
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
     // Metodo para actualizar
    public boolean actualizar(int Id, String Nombre, String Genero, int Año, String Actor, String Pais) {
        try {

            String query = "UPDATE tbl_peliculas SET Nom_peli=?, Gen_peli=?, Año_peli=?, Act_peli=?, Pais_peli=? WHERE Id_peli=?";
            pst = con.prepareStatement(query);
            pst.setString(1, Nombre);
            pst.setString(2, Genero);
            pst.setInt(3, Año);
            pst.setString(4, Actor);
            pst.setString(5, Pais);
            pst.setInt(6, Id);
            pst.executeUpdate(); 
            
            System.out.println("Registro actualizado exitosamente");
            return true;

        } catch (Exception e) {

            System.out.println(e.getMessage());
            return false;
        }
    }
    // Metodo para Mostrar
    public ResultSet Mostrar() {
        try {
          String quer = "SELECT * FROM tbl_peliculas";
          st = con.createStatement();
          rs = st.executeQuery(quer);
          return rs;
          
        } catch(SQLException e){
            System.err.println(e);
            return null;
        }
    }
   // Metodo para Consulatr
    public ResultSet ConsultarGenero(String genero){
        try {
            String query = "Select * from tbl_peliculas WHERE Gen_peli = ?";
            pst = con.prepareStatement(query);
            pst.setString(1, genero);
            rs = pst.executeQuery();
            return rs;
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    
    }
    
    
    public boolean Eliminar (int Id) {        
        try {
            String query = "Delete * from tbl_peliculas WHERE id_peli = ?";
            pst = con.prepareStatement(query);
            pst.setInt(1, Id);
            rs = pst.executeQuery();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    
    }
   
}
