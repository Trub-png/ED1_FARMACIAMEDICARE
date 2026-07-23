package Conexion;

import java.sql.*;

public class ConexionDB {
    
    // Manejo de las constantes de datos de la conexión
    private static final String URL = "jdbc:mysql://localhost:3306/medicare_db";
    private static final String USER = "root";
    private static final String PASS = "";
    
    // Método para realizar la conexión
    public static Connection conexion(){
        
        Connection conn = null;
        
        // Manejar el error en la conexión con la base de datos
        try{
            
            // Guardar conexión en el objeto Connection
            conn = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conexión exitosa");
            
            System.out.println("Cambio en el repositorio local");
            
            
        }catch(SQLException e){
            // Manejar el error de SQL y mostrarlo al usuario
            System.out.println("Error en la conexion: " + e.getMessage());
        }
        
        return conn;
        
    }
    
}
