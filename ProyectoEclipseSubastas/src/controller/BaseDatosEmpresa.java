package controller;

import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import model.Administrador;
import model.Cliente;
import model.Empleado;

public class BaseDatosEmpresa{
	
	private HashMap<String,Cliente> mapaClientes; //mapa clientes por login
	private HashMap<String,Administrador> mapaAdministradores;
	private HashMap<String,Empleado> mapaEmpleados; //mapa empleados por login

	public BaseDatosEmpresa() {
		this.mapaClientes=new HashMap<>();
	    this.mapaEmpleados=new HashMap<>();
	    this.mapaAdministradores= new HashMap<>();
	}
	
	public HashMap<String, Cliente> getMapaClientes(){
		return mapaClientes;
	}
	
	public HashMap<String, Empleado> getMapaEmpleados(){
		return mapaEmpleados;
	}
	
	public HashMap<String, Administrador> getMapaAdministradores(){
		return mapaAdministradores;
	}
	
    ///////////////////////////////////////////////////////////////////////////
    //EL WRITE ADMINISTRADOR
	private String generarTextoAdministradores(){
		String texto="";
		for(Administrador administrador:mapaAdministradores.values()) {
			texto+=comprimirAdministrador(administrador);
			texto+="\n";
		}
		return texto;
	}
	
	public void actualizarArchivoAdministradores() throws IOException {
		String texto=generarTextoAdministradores();
		FileWriter fichero = new FileWriter("data/administradores.txt");
		fichero.write(texto);
		fichero.close();
	}
	
	// ADMINISTRADOR READ
		public String comprimirAdministrador(Administrador administrador) {
			String usuario = administrador.getUsuario();
			String contrasena = administrador.getContrasena();
			String nombre = administrador.getNombre();
			return usuario + ";" + contrasena + ";" + nombre + ";";
		}
    
		private void crearMapaAdministradores() throws IOException {
			BufferedReader br = new BufferedReader(new FileReader("data/administradores.txt"));
			String linea = br.readLine();
			while (linea != null) {
				String[] partes = linea.split(";");
				String usuario = partes[0];
				
				Administrador administrador= descomprimirAdministrador(linea);
				mapaAdministradores.put(usuario, administrador);
				linea = br.readLine();
			}
		    br.close();
		  }
	//READ ADMINISTRADOR 
		public Administrador descomprimirAdministrador(String linea) {
			String[] partes = linea.split(";");
			String usuario = partes[0];
			String contrasena = partes[1];
			String nombre=partes[2];
			
			Administrador admin= new Administrador(usuario, contrasena, nombre);
			
			return admin;
		}

	
	//READ: Descargar todas las clientes

	private void crearMapaClientes() throws IOException {
	BufferedReader br = new BufferedReader(new FileReader("data/clientes.txt"));
	String linea = br.readLine();
	while (linea != null) {
		String[] partes = linea.split(";");
		String usuario = partes[0];
		ArrayList<Object> lista=descomprimirCliente(linea);
		Cliente cliente =(Cliente) lista.get(0);
		
		//Se agregan los objetos anteriores a cliente
		mapaClientes.put(usuario,cliente);
		linea = br.readLine();
	}
	br.close();
	}
	
	// CLIENTE 
	//Retornamos una lista de objetos porque necesitamos añadirle a cliente
	//una pieza o varias piezas asociadas
	public ArrayList<Object> descomprimirCliente(String linea) {
		String[] partes = linea.split(";");
		String usuario = partes[0];
		String contrasena = partes[1];
		String nombre=partes[2];
		int maximoCompras= Integer.parseInt(partes[3]);
		boolean validado= Boolean.parseBoolean(partes[4]);

		Cliente cliente = new Cliente(usuario, contrasena, nombre, maximoCompras, validado);
		ArrayList<Object> lista=new ArrayList<Object>();
		lista.add(cliente);
		return lista;
	}
	
	//Write: Actualizar archivo, reescribirlo.
	private String generarTextoClientes(){
		String texto="";
		for(Cliente cliente:mapaClientes.values()) {
			texto+=comprimirCliente(cliente);
			texto+="\n";
		}
		return texto;
	}
	public void actualizarArchivoClientes() throws IOException {
		String texto=generarTextoClientes();
		FileWriter fichero = new FileWriter("data/clientes.txt");
		fichero.write(texto);
		fichero.close();
	}
	
	// Cliente
	public String comprimirCliente(Cliente cliente) {
		    String usuario = cliente.getUsuario();
		    String nombre = cliente.getNombre();
			String contrasena = cliente.getContrasena();
			return usuario + ";" + contrasena + ";" + nombre ;
		}
	
	//EMPLEADO
	//READ: Descargar todas las alquileres

	private void crearMapaEmpleados() throws IOException {
	BufferedReader br = new BufferedReader(new FileReader("data/empleados.txt"));

	String linea = br.readLine();

	while (linea != null) {
		String[] partes = linea.split(";");
		String usuario = partes[2];
		Empleado empleado = descomprimirEmpleado(linea, mapaEmpleados);
		mapaEmpleados.put(usuario, empleado);
		linea = br.readLine();
	}
	br.close();
	}
	
	public Empleado descomprimirEmpleado(String linea, HashMap<String, Empleado> mapaEmpleados)
	{
		String[] partes = linea.split(";");
		String id = partes[0];
		String nombre = partes[1];
		String usuario = partes[2];
		String contrasena = partes[3];
		
		Empleado empleado= new Empleado(id, nombre, usuario, contrasena);
		
		return empleado;
	}
	
	//Write: Actualizar archivo, reescribirlo.
	private String generarTextoEmpleados(){
		String texto="";
		for(Empleado empleado:mapaEmpleados.values()) {
			texto+=comprimirEmpleado(empleado);
			texto+="\n";
		}
		return texto;
	}
	
	private void actualizarArchivoEmpleados() throws IOException {
		String texto=generarTextoEmpleados();
		FileWriter fichero = new FileWriter("data/empleados.txt");
		fichero.write(texto);
		fichero.close();
	}
	
	public String comprimirEmpleado(Empleado empleado) {
		String id = empleado.getId();
		String nombre = empleado.getNombre();
		String usuario = empleado.getUsuario();
		String contrasena = empleado.getContrasena();
		return id + ";" + nombre + ";" + usuario + ";" + contrasena;
	}
	
	public void descargarDatosEmpresa() throws IOException {

		crearMapaClientes();// Incompleto
		crearMapaEmpleados();// Falta
		crearMapaAdministradores(); // Incompleto	
	}
	
	public void cargarDatosEmpresa() throws IOException {
		actualizarArchivoClientes();
		actualizarArchivoEmpleados();
		actualizarArchivoAdministradores();
	}
}

