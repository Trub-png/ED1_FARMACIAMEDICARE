package Modelo;

public class Usuario {
    
    private int id_usuario;
    private String usuario;
    private String password;

    // Constructor para crear un usuario
    public Usuario(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    // Constructor para obtener un usuario desde la BD
    public Usuario(int id_usuario, String usuario, String password) {
        this.id_usuario = id_usuario;
        this.usuario = usuario;
        this.password = password;
    }

    // Getters
    public int getId_usuario() {
        return id_usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getPassword() {
        return password;
    }

    // Setters
    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}