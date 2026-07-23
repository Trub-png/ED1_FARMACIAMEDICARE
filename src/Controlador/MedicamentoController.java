package Controlador;

import Modelo.Medicamento;
import Modelo.MedicamentoDB;
import Vista.FRM_AgregarMedicamento;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MedicamentoController implements ActionListener {
    
    private final FRM_AgregarMedicamento ventana;
    private final MedicamentoDB medicamentoDB;
    private JTable tabla;
    private int idSeleccionado = -1;
    
    // CONSTRUCTOR
    public MedicamentoController(FRM_AgregarMedicamento ventana, MedicamentoDB medicamentoDB) {
        this.ventana = ventana;
        this.medicamentoDB = medicamentoDB;
        
        this.ventana.btnGuardar.addActionListener(this);
        this.ventana.btnActualizar.addActionListener(this);
        this.ventana.btnBorrar.addActionListener(this);
        this.ventana.btnLimpiar.addActionListener(this);
        
        // Cargar los datos a la tabla al abrir la ventana
        mostrarMedicamentos(); 
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == ventana.btnGuardar){
            guardarMedicamento();
        }
        if(e.getSource() == ventana.btnActualizar){
            actualizarMedicamento();
        }
        if(e.getSource() == ventana.btnBorrar){
            eliminarMedicamento();
        }
        if(e.getSource() == ventana.btnLimpiar){
            limpiarCampos();
        }
    }
    
    private void seleccionTabla() {
        tabla.getSelectionModel().addListSelectionListener(evento -> {
            if(!evento.getValueIsAdjusting()){
                cargarDatosEnCampos();
            }
        });
    }
    
    private void cargarDatosEnCampos() {
        int fila = tabla.getSelectedRow();
        if(fila == -1) return;
        
        idSeleccionado = Integer.parseInt(tabla.getValueAt(fila, 0).toString());
        ventana.txt_Nombre.setText(tabla.getValueAt(fila, 1).toString());
        ventana.txt_Fabricante.setText(tabla.getValueAt(fila, 2).toString());
        ventana.txt_Gramaje.setText(tabla.getValueAt(fila, 3).toString());
        ventana.txt_Administracion.setText(tabla.getValueAt(fila, 4).toString());
        ventana.txt_Proveedor.setText(tabla.getValueAt(fila, 5).toString());
        ventana.txt_Lote.setText(tabla.getValueAt(fila, 6).toString());
        ventana.txt_Fecha_Expiracion.setText(tabla.getValueAt(fila, 7).toString());
    }
    
    private void guardarMedicamento() {
        String nombre = ventana.txt_Nombre.getText().trim();
        String fabricante = ventana.txt_Fabricante.getText().trim();
        String gramaje = ventana.txt_Gramaje.getText().trim();
        String admin = ventana.txt_Administracion.getText().trim();
        String proveedor = ventana.txt_Proveedor.getText().trim();
        String lote = ventana.txt_Lote.getText().trim();
        String fecha = ventana.txt_Fecha_Expiracion.getText().trim();
        
        if(nombre.isEmpty() || fabricante.isEmpty() || fecha.isEmpty()){
            JOptionPane.showMessageDialog(ventana, "Por favor completa los campos.");
            return;
        }
        
        Medicamento med = new Medicamento(nombre, fabricante, gramaje, admin, proveedor, lote, fecha);
        
        if(medicamentoDB.insertar(med)){
            JOptionPane.showMessageDialog(ventana, "Medicamento guardado con éxito.");
            mostrarMedicamentos();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(ventana, "Error al guardar el medicamento.");
        }
    }
    
    private void actualizarMedicamento() {
        if(idSeleccionado == -1){
            JOptionPane.showMessageDialog(ventana, "Selecciona un medicamento de la tabla.");
            return;
        }
        
        String nombre = ventana.txt_Nombre.getText().trim();
        String fabricante = ventana.txt_Fabricante.getText().trim();
        String gramaje = ventana.txt_Gramaje.getText().trim();
        String admin = ventana.txt_Administracion.getText().trim();
        String proveedor = ventana.txt_Proveedor.getText().trim();
        String lote = ventana.txt_Lote.getText().trim();
        String fecha = ventana.txt_Fecha_Expiracion.getText().trim();
        
        Medicamento med = new Medicamento(idSeleccionado, nombre, fabricante, gramaje, admin, proveedor, lote, fecha);
        
        if(medicamentoDB.actualizar(med)){
            JOptionPane.showMessageDialog(ventana, "Actualización exitosa.");
            mostrarMedicamentos();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(ventana, "Error al actualizar.");
        }
    }
    
    public void eliminarMedicamento() {
        if(idSeleccionado == -1){
            JOptionPane.showMessageDialog(ventana, "Selecciona un medicamento de la tabla.");
            return;
        }
        
        int confirmacion = JOptionPane.showConfirmDialog(ventana, "¿Deseas eliminar este medicamento?", "Confirmar", JOptionPane.YES_NO_OPTION);
        
        if(confirmacion == JOptionPane.YES_OPTION){
            if(medicamentoDB.eliminar(idSeleccionado)){
                JOptionPane.showMessageDialog(ventana, "Eliminación exitosa.");
                mostrarMedicamentos();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(ventana, "Error al eliminar.");
            }
        }
    }
    
    private void limpiarCampos() {
        idSeleccionado = -1;
        ventana.txt_Nombre.setText("");
        ventana.txt_Fabricante.setText("");
        ventana.txt_Gramaje.setText("");
        ventana.txt_Administracion.setText("");
        ventana.txt_Proveedor.setText("");
        ventana.txt_Lote.setText("");
        ventana.txt_Fecha_Expiracion.setText("");
        
        if(tabla != null){
            tabla.clearSelection();
        }
    }
    
    private void mostrarMedicamentos() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Fabricante");
        modelo.addColumn("Gramaje");
        modelo.addColumn("Admin.");
        modelo.addColumn("Proveedor");
        modelo.addColumn("Lote");
        modelo.addColumn("Fecha Exp.");
        
        List<Medicamento> lista = medicamentoDB.consultarMedicamentos();
        
        for(Medicamento med : lista){
            Object[] fila = {
                med.getId_medicamento(), med.getNombre(), med.getFabricante(), 
                med.getGramaje(), med.getTipo_admi(), med.getProveedor(), 
                med.getLote(), med.getFecha()
            };
            modelo.addRow(fila);
        }
        
        tabla = new JTable(modelo);
        ventana.paneTabla.setViewportView(tabla);
        seleccionTabla();
    }
}
