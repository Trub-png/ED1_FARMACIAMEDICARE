package Main;

import Controlador.InicioSesionController;
import Vista.InicioSesion;

public class Main {
    public static void main(String[] args) {
        // 1. Instanciar la Vista
        InicioSesion vistaLogin = new InicioSesion();
        
        // 2. Instanciar el Controlador pasándole la vista
        InicioSesionController controlador = new InicioSesionController(vistaLogin);
        
        // 3. Centrar y hacer visible la ventana
        vistaLogin.setLocationRelativeTo(null);
        vistaLogin.setVisible(true);
    }
}