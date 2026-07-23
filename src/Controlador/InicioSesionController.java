package Controlador;

import Modelo.Usuario;
import Modelo.UsuarioDB;
import Vista.InicioSesion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class InicioSesionController implements ActionListener {

    private final InicioSesion ventana;
    private final UsuarioDB usuarioDB;

    public InicioSesionController(InicioSesion ventana) {
        this.ventana = ventana;
        this.usuarioDB = new UsuarioDB(); 
        this.ventana.btnInicioSesion.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ventana.btnInicioSesion) {
            
            String user = ventana.txtUsuario.getText();
            String pass = ventana.txtPassword.getText();

            if (user.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(ventana, "Por favor, llene todos los campos.");
                return; 
            }

            Usuario usuarioValidado = usuarioDB.validarLogin(user, pass);

            if (usuarioValidado != null) {
                JOptionPane.showMessageDialog(ventana, "¡Bienvenido al sistema, " + usuarioValidado.getUsuario() + "!");
                
                // 1. Creamos la vista (la ventana vacía)
                Vista.FRM_AgregarMedicamento ventanaMedicamentos = new Vista.FRM_AgregarMedicamento();
                
                // 2. Creamos la conexión a la base de datos de medicamentos
                Modelo.MedicamentoDB medDB = new Modelo.MedicamentoDB();
                
                // 3. Le pasamos la ventana y la BD al controlador para que llene la tabla
                Controlador.MedicamentoController medCtrl = new Controlador.MedicamentoController(ventanaMedicamentos, medDB);
                
                // Mostramos la ventana ya con los datos cargados
                ventanaMedicamentos.setLocationRelativeTo(null);
                ventanaMedicamentos.setVisible(true);
                
                // Cerramos la ventana de Login
                ventana.dispose(); 
                
            } else {
                JOptionPane.showMessageDialog(ventana, "Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}