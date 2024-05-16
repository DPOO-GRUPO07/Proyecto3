package model;

public class Empleado implements Usuario {
	
	//Atributos
	
	private String id;
	
	private String nombre;
	
	private String usuario;
	
	private String contrasena;

	
	
	public Empleado(String id, String nombre, String usuario, String contrasena)
	{
		this.id= id;
		this.nombre=nombre;
		this.usuario= usuario;
		this.contrasena=contrasena;	
	}
	
	//getters
	
	public String getId()
	{
		return this.id;
	}
	
	public String getNombre()
	{
		return this.nombre;
	}
	
	public String getUsuario()
	{
		return this.usuario;
		
	}
	
	public String getContrasena()
	{
		return this.contrasena;
	}
	

	/*
	 * Metodos inicio de sesion
	 * */ 
	
    //private String usuario;
    //private String contrase�a;
    private boolean sesionIniciada;

    public Empleado(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    
    public void iniciarSesion(String nombreUsuario, String contrasena) {
        if (usuario.equals(this.usuario) && contrasena.equals(this.contrasena)) {
            sesionIniciada = true;
            System.out.println("Sesion iniciada para el empleado: " + usuario);
        } else {
            System.out.println("Error: Nombre de usuario o contrase�a incorrectos.");
        }
    }

    
    public void cerrarSesion() {
        sesionIniciada = false;
        System.out.println("Sesión cerrada para el empleado.");
    }

    
    public boolean estaSesionIniciada() {
        return sesionIniciada;
    }
}
	
