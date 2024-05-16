package model;

public class Administrador implements Usuario {
	
	
	private String usuario;
	
	private String contrasena;
	
	private String nombre;
	

    public Administrador(String usuario, String contrasena, String nombre) {

        this.usuario = usuario;
        this.contrasena = contrasena;
        this.nombre = nombre;
        
    }
    
    
	public String getUsuario()
	{
		return usuario;
		
	}
	
	public String getContrasena()
	{
		return contrasena;
		
	}
	
	public String getNombre()
	{
		return nombre;
	}
	
	

    /*
     * Inicion de sesion
     * */

    
}







