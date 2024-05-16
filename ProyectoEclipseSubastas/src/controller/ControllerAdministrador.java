package controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import model.Administrador;
import model.Cliente;
//import java.util.HashMap;
//import controller.BaseDatos;
import model.Empleado;
import model.Subasta;
import model.Venta;
import pieza.Pieza;
import pieza.PiezaEscultura;
import pieza.PiezaFotografia;
import pieza.PiezaPintura;
import pieza.PiezaVideo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;



public class ControllerAdministrador {
	
	private HashMap<String, Pieza> mapaPiezas;
	private Administrador administrador;
	private static BaseDatosInventario datosInventario;
	private BaseDatosGaleria datosGaleria;
	private BaseDatosEmpresa datosEmpresa;
	//

	public ControllerAdministrador() {
		
	}
	
	public Administrador getAdministrador() {	
		return this.administrador;
	}
	
	public void setDatosEmpresa(BaseDatosEmpresa datos) {
		this.datosEmpresa=datos;
	}
	
	public void setDatosGaleria(BaseDatosGaleria datosGaleria) {
		this.datosGaleria=datosGaleria;
	}
	
	public void setDatosInventario(BaseDatosInventario datosInventario) {
		ControllerAdministrador.datosInventario=datosInventario;
	}
	
	public boolean LogIn(String usuario,String contrasena) {
		
		Administrador administrador = datosEmpresa.getMapaAdministradores().get(usuario);
		
		if(administrador.getUsuario().equals(usuario)&& administrador.getContrasena().equals(contrasena)) {
			this.administrador=administrador;
			return true;
		}
		else {
			System.out.println("Error al ingresar");
			return false;
		}
	}
	
	
	/*
	 * Getters and Setters
	 * 
	*/
	
	//LOGICA GALERIA
	
	public boolean verificarComprador(String nombreCliente){
		HashMap<String,Cliente> mapaParticipantesSubasta = datosGaleria.getMapaParticipantesSubasta();
		HashMap<String,Cliente> mapaClientes = datosEmpresa.getMapaClientes();
		boolean verificador=false;
		for(Cliente cliente:mapaClientes.values()) {
			if (cliente.getNombre().equals(nombreCliente)==false) {
				verificador = true;
				mapaParticipantesSubasta.put(nombreCliente, cliente);
			}
		}
		return verificador;
	}
	
	public boolean ConfirmarVenta(String name, String nombrePieza) throws IOException 
	{
		HashMap<String,Venta> mapaVentas = datosGaleria.getMapaVentas();
		
		Boolean estadoVenta=false;
		for(Venta venta : mapaVentas.values()) {
			if (venta.getUsuario().equals(name)==true && venta.getTituloPieza().equals(nombrePieza)==true ) {
				venta.setVentaConfirmada(true);
				datosGaleria.getMapaVentas().replace(venta.getUsuario(), venta);
				estadoVenta=true;
				datosGaleria.actualizarArchivoVenta();
			}
		}
		return estadoVenta;
		 
	}
	
	public boolean crearSubasta(String tituloPieza, String valorMinimo, String valorInicial) throws Exception {
		HashMap<String,Subasta> mapaSubastas = datosGaleria.getMapaSubastas();
        int valorMinimoInt = Integer.parseInt(valorMinimo);
        int valorInicialInt = Integer.parseInt(valorInicial);
        Subasta nuevaSubasta = new Subasta(tituloPieza, valorMinimoInt, valorInicialInt);
        
        mapaSubastas.put(tituloPieza, nuevaSubasta);
        
        datosGaleria.actualizarArchivoSubastas();
		
		return true;
	}
	
	//Despues de verificar el comprador se ejecuta:
	public Boolean maximoCompras(String cliente, int maximo) {
	       try {
	            HashMap<String, Cliente> mapaClientes = datosGaleria.getMapaParticipantesSubasta();

	            if (mapaClientes.containsKey(cliente)) {
	                Cliente nuevoCliente = mapaClientes.get(cliente);
	                
	                nuevoCliente.setMaximo(maximo);
	                datosGaleria.actualizarArchivoParticipantesSubasta();
	                return true;
	        
	            } else {
	                System.out.println("El usuario " + cliente + " no existe.");
	                return false;
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false; // Ocurrió un error al actualizar el archivo de clientes
	        }
	    }
	

		
	//LOGICA INVENTARIO
	
	public void agregarPiezaAInventario(Pieza pieza) {
	    HashMap<String, Pieza> mapaPiezas = datosInventario.getMapaPiezas();
		mapaPiezas.put(pieza.getTitulo(), pieza);
		String linea = datosInventario.comprimirPieza(pieza);
		BaseDatosInventario.agregarLinea("data/piezas.txt",linea);

		System.out.println("Pieza registrada exitosamente.");
		
	}
	
    
    public List<String> historialComprasCliente(String nombreCliente) {
        return datosGaleria.obtenerHistorialComprasCliente(nombreCliente);
    }
    
	public void actualizarDatos() throws IOException {
		// los datos de todas las calses BasesDatos... :
		datosEmpresa.cargarDatosEmpresa();
		datosGaleria.cargarDatosGaleria();
		datosInventario.cargarTodosLosDatos();	
		}
    

}
