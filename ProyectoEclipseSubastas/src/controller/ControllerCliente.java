package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;

import model.Administrador;
import model.Cliente;
import model.Inventario;
import model.Venta;
import pieza.Pieza;

public class ControllerCliente {
	
	private HashMap<String, Pieza> mapaPiezas;
	private Cliente cliente;
    private BaseDatosEmpresa datos; // debe haber unos datos asociados para trabajar
    private BaseDatosInventario datosInventario;
    private BaseDatosGaleria datosGaleria;

     
    
    //Constructor
    public ControllerCliente(BaseDatosEmpresa datos,BaseDatosInventario datosInventario ) {
    	this.cliente= null;
    	this.datos=datos;
    	this.datosInventario = datosInventario;
    	this.mapaPiezas= datosInventario.getMapaPiezas();
    }
    
    //METODOS
    //Get
    public Cliente getCliente() {
	   return cliente;
    }
    
    
	public void setDatosInventario(BaseDatosInventario datosInventario) {
		this.datosInventario=datosInventario;
	}
	
	public void setDatosGaleria(BaseDatosGaleria datosGaleria) {
		this.datosGaleria=datosGaleria;
	}

	
   /*
	public void logIn(String usuario,String contrasena) {
	   Cliente cliente = datos.getMapaClientes().get(usuario);
	    if(cliente!=null) {
	    	String contr=cliente.getContrasena();
		    if(contr.equals(contrasena)==true) {
			    this.cliente=cliente;
		    }
		    else {
			
		    }
          }	
	    else {
        }
       }*/
    
    
	public BaseDatosEmpresa getDatos() {
		return datos;
	}

	public void setDatos(BaseDatosEmpresa datos) {
		this.datos = datos;
	}

	public void LogIn(String usuario,String contrasena) {
		Cliente cliente = datos.getMapaClientes().get(usuario);
		
		if(cliente.getUsuario().equals(usuario)&& cliente.getContrasena().equals(contrasena)) {
			this.cliente=cliente;
		}
		else {
			System.out.println("Error al ingresar");
		}
	}

	/*
	 * METODOS DE LOS REQUERIMIENTOS DEL CLIENTE
	*/
    
    public void realizarCompraFija(String nombrePieza) {
    	HashMap<String,Pieza> mapaPiezas = datosInventario.getMapaPiezas();
    	HashMap<String,Venta> mapaVentas = datosGaleria.getMapaVentas(); 
    	for (Pieza pieza: mapaPiezas.values()) {
			if (pieza.getTitulo().equals(nombrePieza)&& pieza.getBloqueo()==false) {
				String esteUsuario=cliente.getUsuario();
				String estaPieza = pieza.getTitulo();
				int esteValorPieza = pieza.getValorFijo();
				String esteTipoCompra = "fijo";
				boolean estaConfirmada = false; 
				Venta estaVenta= new Venta(esteUsuario, estaPieza, esteValorPieza, esteTipoCompra, estaConfirmada);
				mapaVentas.put(cliente.getUsuario(), estaVenta);
				System.out.println(mapaVentas);
			}
    	}
    }
    
    
    /*Para este metodo el cliente se postulara por la subasta de una pieza, indicando su nombre, el de la pieza por la que deseea hacer la puja y el valor que ofrece en la subasta*/
    
    public void actualizarOferta(String nombreComprador, String nombrePieza, String precioOferta) {
        try {
            Pieza pieza = mapaPiezas.get(nombrePieza);   // Validar si la pieza está disponible para subasta
            if (pieza != null && pieza.getDisponibleSubasta()) {
                BufferedWriter writer = new BufferedWriter(new FileWriter("data/ofertaSubasta.txt", true));  // Abrir el archivo "ofertas.txt" en modo de escritura
                writer.write(nombreComprador + "," + nombrePieza + "," + precioOferta); // Escribir la información en el archivo
                writer.newLine();

                writer.close();
            } else {
                System.out.println("La pieza no está disponible para subasta.");
            }
        } catch (IOException e) {
            // Manejo de excepciones en caso de error al escribir en el archivo
            e.printStackTrace();
        }
    }
 
    	
 
    
    
    /*Esta función permitira al cliente o dueño de una pieza poner a en venta alguna pieza*/
    public void entregarEsculturaConsignacion (String titulo, int anoCreacion,String artista, String lugarCreacion, String propietario, int valorFijo, Boolean disponibleEnSubasta, String tipo, boolean bloqueada) {
    	
    }
    
    public void entregarPinturaConsignación(String titulo, int anoCreacion) {
    	
    }
    
    
    
    
	

    public void actualizarDatos() throws IOException {
	    //datosGaleria.cargarDatosVentas();
    }

}