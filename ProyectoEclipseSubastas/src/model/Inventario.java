package model;

import java.time.LocalTime;
import java.util.ArrayList;

import pieza.Pieza;

public class Inventario {

	private Administrador administrador;
	private ArrayList<Pieza> piezas;
	private ArrayList<Usuario> usuarios;
	
	public Inventario(Administrador administrador)
	{

		this.piezas = new ArrayList<Pieza>();
		this.usuarios= new ArrayList<Usuario>();}
		

	//Métodos consultar información
	

	
	public ArrayList<Pieza> getPiezas()
	{ 
		return this.piezas;
	}
	
	
	public Administrador setAdministrador(Administrador administrador)
	{
		return this.administrador;
	}
	
	//setters
	
	public void setPieza(Pieza pieza)
	{
		piezas.add(pieza);
	}
	

	public void setUsuarios(Usuario usuario)
	{
		usuarios.add(usuario);
	}
}
