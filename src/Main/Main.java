/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Vista.InicioSesion;
import Controlador.InicioSesionController;

public class Main {
    public static void main(String[] args){
        InicioSesion ventana = new InicioSesion();
        
        InicioSesionController controlador = new InicioSesionController(ventana);
        
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
}
