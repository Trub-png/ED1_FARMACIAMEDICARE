package Modelo;

public class Usuario {
    private int id_usuario;
    private String  contraseña, nombre;

    public Usuario(int id_usuario, String correo, String contraseña, String nombre, String telefono, String fechaNacimiento) {
        this.id_usuario = id_usuario;
        this.contraseña = contraseña;
        this.nombre = nombre;
    }

    // Getters
    public int getId_usuario() { return id_usuario; }
    public String getContraseña() { return contraseña; }
    public String getNombre() { return nombre; }
}
