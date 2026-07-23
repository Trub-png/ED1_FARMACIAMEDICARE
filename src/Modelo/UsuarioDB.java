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
    public Usuario validarLogin(String correo, String contrasena) {
        String sql = "SELECT * FROM Usuario WHERE correo = ? AND contrasena = ?";
        try (Connection conn = ConexionDB.conexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, correo);
            ps.setString(2, contrasena);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Usuario(rs.getInt("id_usuario"), rs.getString("correo"), rs.getString("contrasena"), 
                                   rs.getString("nombre"), rs.getString("telefono"), rs.getString("fecha_nac"));
            }
        } catch (SQLException e) { System.out.println("Error login: " + e.getMessage()); }
        return null;
    }

    public boolean registrarNuevoUsuario(Usuario u) {
        String sql = "INSERT INTO Usuario (correo, contrasena, nombre, telefono, fecha_nac) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexionDB.conexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, u.getCorreo());
            ps.setString(2, u.getContraseña());
            ps.setString(3, u.getNombre());
            ps.setString(4, u.getTelefono());
            ps.setString(5, u.getFechaNacimiento());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { System.out.println("Error registro: " + e.getMessage()); }
        return false;
    }
}
