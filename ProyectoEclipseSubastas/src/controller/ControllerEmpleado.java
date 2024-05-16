package controller;


import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import model.Cliente;
import model.Empleado;
import pieza.Pieza;


public class ControllerEmpleado {
	
	//Atributos
	
	private Empleado empleado;
	
	private BaseDatosEmpresa datos;
	
	
	private HashMap<String,Pieza> mapaPiezas; //mapa carros por placa
	//private HashMap<String,Inventario> mapaInventario;//mapa sedes por nombre
	//USUARIOS
	private HashMap<String,Cliente> mapaClientes; //mapa clientes por login
	private HashMap<String,Empleado> mapaEmpleados; //mapa empleados por login

	
	
	//Constructor
	
	public ControllerEmpleado(BaseDatosEmpresa datos)
	{
		this.empleado=null;
		this.datos=datos;
		this.mapaEmpleados=datos.getMapaEmpleados();
		this.mapaClientes = datos.getMapaClientes();
	}
	
	
	public BaseDatosEmpresa getDatos()
	{
		return this.datos;
	}
	
	
	
	//Verificar login
	public void logIn(String usuario,String contrasena) {
		Empleado empleado = datos.getMapaEmpleados().get(usuario);
		if(empleado!=null) {
			String contr=empleado.getContrasena();
			if(contr.equals(contrasena)==true) {
				this.empleado=empleado;
			}
	}	
	
	}
	
	//devolver empleado
	
	public Empleado getEmpleado()
	{
		return this.empleado;
	}
	
	private Pieza Disponibilidad(Pieza pieza){
		boolean disponible= false;
		
		
		return null;	
	}
	




//Actualizar Dtaos
		
		public void actualizarDatos() throws IOException {
			//datos.cargarTodosLosDatos();
		}
		
		
		

		
		
}
