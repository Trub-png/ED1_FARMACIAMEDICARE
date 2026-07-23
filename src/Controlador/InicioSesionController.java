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
        
        // Le decimos al botón público de la vista que este controlador escuchará sus clics
        this.ventana.btnInicioSesion.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Verificamos si la acción vino del botón Iniciar Sesión
        if (e.getSource() == ventana.btnInicioSesion) {
            
            // 1. Obtener los textos directamente de tus campos públicos
            String user = ventana.txtUsuario.getText();
            String pass = ventana.txtPassword.getText();

            // 2. Validar que no estén vacíos
            if (user.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(ventana, "Por favor, llene todos los campos.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
                return; // Detenemos la ejecución aquí
            }

            // 3. Consultar a la base de datos
            Usuario usuarioValidado = usuarioDB.validarLogin(user, pass);

            // 4. Evaluar el resultado
            if (usuarioValidado != null) {
                JOptionPane.showMessageDialog(ventana, "¡Bienvenido al sistema, " + usuarioValidado.getUsuario() + "!");
                
                // --- AQUÍ HACEMOS EL SALTO DE VENTANA ---
                
                // 1. Instanciamos la nueva ventana de medicamentos
                Vista.FRM_AgregarMedicamento ventanaMedicamentos = new Vista.FRM_AgregarMedicamento();
                
                // 2. La centramos en la pantalla (opcional pero recomendado)
                ventanaMedicamentos.setLocationRelativeTo(null);
                
                // 3. Hacemos visible la nueva ventana
                ventanaMedicamentos.setVisible(true);
                
                // 4. Cerramos (destruimos) la ventana actual de Login
                ventana.dispose(); 
                
            } else {
                JOptionPane.showMessageDialog(ventana, "Usuario o contraseña incorrectos.", "Error de Autenticación", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}