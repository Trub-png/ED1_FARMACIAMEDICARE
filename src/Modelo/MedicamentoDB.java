package Modelo;

import Conexion.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicamentoDB {

    // MÉTODO PARA INSERTAR MEDICAMENTO
    public boolean insertar(Medicamento med) {
        String sql = "INSERT INTO medicamentos (nombre, fabricante, gramaje, tipo_administracion, proveedor, lote, fecha_expiracion) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionDB.conexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, med.getNombre());
            ps.setString(2, med.getFabricante());
            ps.setString(3, med.getGramaje());
            ps.setString(4, med.getTipo_admi()); // Fíjate cómo usamos tus getters
            ps.setString(5, med.getProveedor());
            ps.setString(6, med.getLote());
            ps.setString(7, med.getFecha());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al insertar medicamento: " + e.getMessage());
            return false;
        }
    }

    // MÉTODO PARA ACTUALIZAR MEDICAMENTO
    public boolean actualizar(Medicamento med) {
        String sql = "UPDATE medicamentos SET nombre=?, fabricante=?, gramaje=?, tipo_administracion=?, proveedor=?, lote=?, fecha_expiracion=? WHERE id_medicamento=?";
        try (Connection conn = ConexionDB.conexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, med.getNombre());
            ps.setString(2, med.getFabricante());
            ps.setString(3, med.getGramaje());
            ps.setString(4, med.getTipo_admi());
            ps.setString(5, med.getProveedor());
            ps.setString(6, med.getLote());
            ps.setString(7, med.getFecha());
            ps.setInt(8, med.getId_medicamento()); // ID para saber cuál actualizar

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al actualizar medicamento: " + e.getMessage());
            return false;
        }
    }

    // MÉTODO PARA ELIMINAR MEDICAMENTO
    public boolean eliminar(int id) {
        String sql = "DELETE FROM medicamentos WHERE id_medicamento=?";
        try (Connection conn = ConexionDB.conexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al eliminar medicamento: " + e.getMessage());
            return false;
        }
    }

    // MÉTODO PARA MOSTRAR TODOS LOS MEDICAMENTOS EN LA TABLA
    public List<Medicamento> consultarMedicamentos() {
        List<Medicamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM medicamentos";

        try (Connection conn = ConexionDB.conexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                // Creamos un objeto Medicamento por cada fila de la BD
                Medicamento med = new Medicamento(
                    rs.getInt("id_medicamento"),
                    rs.getString("nombre"),
                    rs.getString("fabricante"),
                    rs.getString("gramaje"),
                    rs.getString("tipo_administracion"),
                    rs.getString("proveedor"),
                    rs.getString("lote"),
                    rs.getString("fecha_expiracion")
                );
                lista.add(med);
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar medicamentos: " + e.getMessage());
        }
        return lista;
    }
}
