package Modelo;

/**
 *
 * @author IanEH
 */
public class Medicamento {
    
    private int id_medicamento;
    private String nombre;
    private String fabricante;
    private String gramaje;
    private String tipo_admi;
    private String proveedor;
    private String lote;
    private String fecha;
    
    // 1. Constructor para crear un objeto vacío
    public Medicamento(){}
    
    // 2. Constructor para crear un objeto dentro del sistema (Sin BD)
    public Medicamento(String nombre, String fabricante, String gramaje, 
                       String tipo_admi, String proveedor, String lote, String fecha){
        
        this.nombre = nombre;
        this.fabricante = fabricante;
        this.gramaje = gramaje;
        this.tipo_admi = tipo_admi;
        this.proveedor = proveedor;
        this.lote = lote;
        this.fecha = fecha;
        
    }
    
    // 3. Constructor para crear un objeto que ocupe la BD
    public Medicamento(int id_medicamento, String nombre, String fabricante, 
                       String gramaje, String tipo_admi, String proveedor, 
                       String lote, String fecha){
        
        this.id_medicamento = id_medicamento;
        this.nombre = nombre;
        this.fabricante = fabricante;
        this.gramaje = gramaje;
        this.tipo_admi = tipo_admi;
        this.proveedor = proveedor;
        this.lote = lote;
        this.fecha = fecha;
        
    }
    
    // GETTERS Y SETTERS
    
    public int getId_medicamento(){
        return id_medicamento;
    }
    
    public void setId_medicamento(int id_medicamento){
        this.id_medicamento = id_medicamento;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public String getFabricante(){
        return fabricante;
    }
    
    public void setFabricante(String fabricante){
        this.fabricante = fabricante;
    }
    
    public String getGramaje(){
        return gramaje;
    }
    
    public void setGramaje(String gramaje){
        this.gramaje = gramaje;
    }
    
    public String getTipo_admi(){
        return tipo_admi;
    }
    
    public void setTipo_admi(String tipo_admi){
        this.tipo_admi = tipo_admi;
    }
    
    public String getProveedor(){
        return proveedor;
    }
    
    public void setProveedor(String proveedor){
        this.proveedor = proveedor;
    }
    
    public String getLote(){
        return lote;
    }
    
    public void setLote(String lote){
        this.lote = lote;
    }
    
    public String getFecha(){
        return fecha;
    }
    
    public void setFecha(String fecha){
        this.fecha = fecha;
    }
    
}