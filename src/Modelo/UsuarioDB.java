package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Conexion.ConexionDB;

public class UsuarioDB {
    
    public Usuario validarLogin(String usuario, String password) {
        
        String sql = "SELECT * FROM usuarios WHERE usuario = ? AND password = ?";
        
        try (Connection conn = ConexionDB.conexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, usuario);
            ps.setString(2, password);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return new Usuario(
                    rs.getInt("id_usuario"),
                    rs.getString("usuario"),
                    rs.getString("password")
                );
            }
            
        } catch (SQLException e) {
            System.out.println("Error login: " + e.getMessage());
        }
        
        return null;
    }
}