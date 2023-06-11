package proyect;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

/**
 *
 * @author David Ronald Calle Blanco
 */

public class Conexion {
    
    Connection conectar =null;
    String usuario ="david";
    String contraseña ="david";
    String BD ="prueba";//BD es la abrebiacion de Base de Datos
    String ip ="localhost";// ip o servidor
    String puerto ="5432";
    String cadena ="jdbc:postgresql://"+ip+":"+puerto+"/"+BD;
    
   public Connection EstablecerConeccion(){
       try {
           Class.forName("org.postgresql.Driver");
           conectar= DriverManager.getConnection(cadena,usuario,contraseña);
           //para verificar si la conexion fue exitosa
           //JOptionPane.showMessageDialog(null, "Se conecto de manera correcta a la base de datos");
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Error:"+e.toString());
       }
       return conectar;
   }
   
}