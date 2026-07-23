/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Conexion.ConexionDB;

public class UsuarioDB {
    public Usuario validarLogin(String nombre, String password) {
        String sql = "SELECT * FROM Usuario WHERE nombre = ? AND password = ?";
        try (Connection conn = ConexionDB.conexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Usuario(rs.getInt("id_usuario"), rs.getString("correo"), rs.getString("contrasena"), 
                                   rs.getString("nombre"), rs.getString("telefono"), rs.getString("fecha_nac"));
            }
        } catch (SQLException e) { System.out.println("Error login: " + e.getMessage()); }
        return null;
    }

}
