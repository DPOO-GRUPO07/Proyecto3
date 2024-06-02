package model;
import java.io.*;
import java.util.ArrayList;

public class Cliente implements Usuario {

	//Atributos
	//login
	private String usuario;
	private String contrasena;
	//datos generales:
	private String nombre;
	private int maximoCompras;
	private boolean validado;
	//private Boolean Propietario;
	//
	
	public Cliente(String usuario, String contrasena, String nombre , Integer maximoCompras, Boolean validado) {
		this.contrasena=contrasena;
		this.nombre=nombre;
		this.usuario=usuario;
		this.maximoCompras=maximoCompras;
		this.validado=validado;
	}
	
	
	public String getUsuario() {
		return usuario;
	}

	public String getContrasena() {

		return contrasena;
	}

	public String getNombre() {

		return nombre;
	}
	
	public int getMaximo() {

		return maximoCompras;
	}
	public Boolean getValidado() {

		return validado;
	}
	//SETTERS
	
	public Integer setMaximo(int maximo) {
		return maximo;
	}


	public int getMaximoCompras() {
		return maximoCompras;
	}


	public void setMaximoCompras(int maximoCompras) {
		this.maximoCompras = maximoCompras;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public void setValidado(boolean validado) {
		this.validado = validado;
	}
	
   
}
	
	
	
	
	

