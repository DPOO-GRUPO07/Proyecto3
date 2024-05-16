package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.Administrador;
import model.Cliente;
import model.Empleado;
import model.Subasta;
import model.Venta;
import pieza.Pieza;

public class BaseDatosGaleria {
   /*Estructuras de los usuarios que ya estan en la subasta (mapaParticipantes..),
    *las piezas disponibles para comprar o subastar(mapaPiezasSubasta) y un 
    *registro de los usuarios que hallan realizado compras(mapaCompradores) */
	
	
	private HashMap<String,Cliente> mapaParticipantesSubasta; 
	private HashMap<String,Venta> mapaVentas; 
	private HashMap<String,Subasta> mapaSubastas; 
	
	
	//CONSTRUCTOR
	public BaseDatosGaleria() {
		this.mapaParticipantesSubasta=new HashMap<>();
		this.mapaVentas=new HashMap<>();
		this.mapaSubastas = new HashMap<>();
	}
	
	public HashMap<String, Cliente> getMapaParticipantesSubasta(){
		return mapaParticipantesSubasta;
	}
	
	public HashMap<String, Venta> getMapaVentas(){
		return mapaVentas;
	}
	
	public HashMap<String, Subasta> getMapaSubastas(){
		return mapaSubastas;
	}
	
	//METODOS CARGA DE DATOS
	
	//---------------------------------------------------------------------------------------
	//PARTICIPANTES
	
	//READ : DESCARGAR LOS PARTICIPANTES
	private void crearMapaClientesSubasta() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("data/participantesSubasta.txt"));
		String linea = br.readLine();
		while (linea != null) {
			String[] partes = linea.split(",");
			String usuario = partes[0];
			
			Cliente cliente= descomprimirClienteSubasta(linea);
			mapaParticipantesSubasta.put(usuario, cliente);
			linea = br.readLine();
		}
	    br.close();
	  }
	
    //READ CLIENTES DENTRO DE LA SUBASTA
	public Cliente descomprimirClienteSubasta(String linea) {
		String[] partes = linea.split(",");
		String usuario = partes[0];
		String contrasena = partes[1];
		String nombre=partes[2];
		int maximoCompras= Integer.parseInt(partes[3]);
		boolean validado= Boolean.parseBoolean(partes[4]);
		Cliente clienteSubasta= new Cliente(usuario, contrasena, nombre,maximoCompras,validado);
		return clienteSubasta;
	}
	
	//EL WRITE MAPA PARTICIPANTES
	private String generarTextoClientesSubasta(){
		String texto="";
		for(Cliente cliente:mapaParticipantesSubasta.values()) {
			texto+=comprimirClienteSubasta(cliente);
			texto+="\n";
		}
		return texto;
	}
	
	public void actualizarArchivoParticipantesSubasta() throws IOException {
		String texto=generarTextoClientesSubasta();
		FileWriter fichero = new FileWriter("data/participantesSubasta.txt");
		fichero.write(texto);
		fichero.close();
	}
	
	// CLIENTES DENTRO DE LA SUBASTA
	public String comprimirClienteSubasta(Cliente cliente) {
			String usuario = cliente.getUsuario();
			String contrasena = cliente.getContrasena();
			String nombre = cliente.getNombre();
			String maximo = String.valueOf(cliente.getMaximo());
			String validado = String.valueOf(cliente.getValidado());
			return usuario + ";" + contrasena + ";" + nombre + ";"  + maximo + ";" + validado;
	}
	
    //------------------------------------------------------------------
	//VENTAS
	//EL READ MAPA DE VENTAS EN LA GALERIA
	private void crearMapaVentas() throws IOException {
			BufferedReader br = new BufferedReader(new FileReader("data/ventasGaleria.txt"));
			String linea = br.readLine();
			while (linea != null) {
				String[] partes = linea.split(";");
				String usuario = partes[0];
				
				Venta venta= descomprimirVentas(linea);
				mapaVentas.put(usuario, venta);
				linea = br.readLine();
			}
		    br.close();
	}
	//READ CLIENTES DENTRO DE LA SUBASTA
	public Venta descomprimirVentas(String linea) {
			String[] partes = linea.split(";");
			String usuario = partes[0];
			String tituloPieza = partes[1];
			int valor = Integer.parseInt(partes[2]);
			String tipoVenta=partes[3];
			Boolean ventaConfirmada= Boolean.parseBoolean(partes[4]);
			Venta ventaSubasta= new Venta(usuario, tituloPieza, valor, tipoVenta, ventaConfirmada);
			return ventaSubasta;
	}
		
	private String generarTextoVentas(){
			String texto="";
			for(Venta venta:mapaVentas.values()) {
				texto+=comprimirVenta(venta);
			}
			return texto;
	}
		
	public void actualizarArchivoVenta() throws IOException {
			String texto=generarTextoVentas();
			FileWriter fichero = new FileWriter("data/ventasGaleria.txt");
			fichero.write(texto);
			fichero.close();
	}
		
	// CLIENTES DENTRO DE LA SUBASTA
	public String comprimirVenta(Venta venta) {
				String usuario = venta.getUsuario();
				String titulo = venta.getTituloPieza();
				String valor = String.valueOf(venta.getValorPieza());
				String tipoVenta = venta.getTipoVenta();
				String confirmacion = String.valueOf(venta.getVentaConfirmada());
				return usuario + ";" + titulo + ";" + valor + ";" + tipoVenta + ";" + confirmacion;
			}	
	//
	//---------------------------------------------------------------------------------------------
	// SUBASTAS
	//READ : DESCARGAR LOS PARTICIPANTES
	private void crearMapaSubastas() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("data/subastas.txt"));
		String linea = br.readLine();
		while (linea != null) {
			String[] partes = linea.split(";");
			String titulo = partes[0];
			
			Subasta subasta= descomprimirSubasta(linea);
			mapaSubastas.put(titulo, subasta);
			linea = br.readLine();
		}
	    br.close();
	  }
	
    //READ CLIENTES DENTRO DE LA SUBASTA
	public Subasta descomprimirSubasta(String linea) {
		String[] partes = linea.split(";");
		String titulo = partes[0];
		int valorMininmo = Integer.parseInt(partes[1]);
		int valorInicial=Integer.parseInt(partes[2]);
		Subasta subasta= new Subasta(titulo, valorMininmo, valorInicial);
		return subasta;
	}
	
	//EL WRITE MAPA PARTICIPANTES
	private String generarTextoSubasta(){
		String texto="";
		for(Subasta subasta:mapaSubastas.values()) {
			texto+=comprimirSubasta(subasta);
			texto+="\n";
		}
		return texto;
	}
	
	public void actualizarArchivoSubastas() throws IOException {
		String texto=generarTextoSubasta();
		FileWriter fichero = new FileWriter("data/subastas.txt");
		fichero.write(texto);
		fichero.close();
	}
	
	// CLIENTES DENTRO DE LA SUBASTA
	public String comprimirSubasta(Subasta subasta) {
			String titulo = subasta.getTituloPieza();
			String valorMininmo = String.valueOf(subasta.getValorMininimo());
			String valorInicial = String.valueOf(subasta.getValorInicial());
			return titulo + ";" + valorMininmo + ";" + valorInicial + ";";
	}
	
	//---------------------------------------------------------------------
	 public List<String> obtenerHistorialComprasCliente(String nombreCliente) {
	        List<String> historialCompras = new ArrayList<>();
	        try {
	            BufferedReader br = new BufferedReader(new FileReader("data/historialCompradores.txt"));
	            String linea;
	            while ((linea = br.readLine()) != null) {
	                String[] partes = linea.split(",");
	                if (partes.length >= 2 && partes[0].equals(nombreCliente)) {
	                    // El primer valor es el nombre de usuario del cliente
	                    // Los pares de valores son títulos de piezas y los impares son los valores de las compras
	                    StringBuilder historial = new StringBuilder();
	                    for (int i = 1; i < partes.length; i += 2) {
	                        historial.append("Pieza: ").append(partes[i]).append(", Valor: ").append(partes[i + 1]).append("\n");
	                    }
	                    historialCompras.add(historial.toString());
	                }
	            }
	            br.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return historialCompras;
	    }
	    
	
    //--------------------------------------------------------------
	// METODOS CARGA DE DATOS PARA LLAMADOS EXTERNOS DE LA CLASE
	
	public void descargarDatosGaleria() throws IOException {
			crearMapaClientesSubasta();
			crearMapaVentas();
			crearMapaSubastas();
		}
	public void cargarDatosGaleria() throws IOException {

			actualizarArchivoParticipantesSubasta();
			actualizarArchivoVenta();
			actualizarArchivoSubastas();
	}

	
	
}
