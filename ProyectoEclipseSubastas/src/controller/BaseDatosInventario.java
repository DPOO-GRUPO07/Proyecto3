package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import pieza.Pieza;
import pieza.PiezaEscultura;
import pieza.PiezaFotografia;
import pieza.PiezaPintura;
import pieza.PiezaVideo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class BaseDatosInventario {
	private HashMap<String, Pieza> mapaPiezas;
	private HashMap<String, List<Pieza>> mapaArtistas;
	
	public BaseDatosInventario() {
	    this.mapaPiezas= new HashMap<>();
	    this.mapaArtistas = new HashMap<>();
	}
	
	public HashMap<String, Pieza> getMapaPiezas(){
		return mapaPiezas;
		
	}
	
	public void setMapaArtistas(HashMap<String, List<Pieza>> mapaArtistas) {
	    this.mapaArtistas = mapaArtistas;
	}

	
	
	//Inventario
	//READ: Descargar todas las piezas

	public void crearMapaPiezas() throws IOException {
	BufferedReader br = new BufferedReader(new FileReader("data/piezas.txt"));

	String linea = br.readLine();

	while (linea != null) {
		String[] partes = linea.split(",");
		String titulo = partes[0];
		Pieza pieza = descomprimirPieza(linea);
		mapaPiezas.put(titulo,pieza);
		linea = br.readLine();
		
	}
	br.close();
	}
	
	public Pieza descomprimirPieza(String linea) {
		String[] partes = linea.split("[,;]");
		String titulo = partes[0];

		int anoCreacion = Integer.parseInt(partes[1]);
		String artista = partes[2];
		String lugarCreacion = partes[3];
		String propietario = partes[4];
		int valorFijo=Integer.parseInt(partes[5]);
		Boolean disponibleEnSubasta= Boolean.parseBoolean(partes[6].trim());
		String tipo=partes[7];
		boolean bloqueada = Boolean.parseBoolean(partes[8]);
		Pieza pieza = new Pieza(titulo, anoCreacion, artista, lugarCreacion, propietario, valorFijo, disponibleEnSubasta, tipo, bloqueada);
		if ("pintura".equals(tipo)) {
			String tipoPintura=partes[9];
			String dimensiones=partes[10];
			String detalles=partes[11];
			pieza = new PiezaPintura(titulo, anoCreacion, artista, lugarCreacion, propietario, valorFijo, disponibleEnSubasta, tipo, bloqueada, tipoPintura, dimensiones, detalles);
		}
		else if ("Escultura".equals(tipo)) {
			int alto=Integer.parseInt(partes[9]);
			int ancho=Integer.parseInt(partes[10]);
			int profundidad=Integer.parseInt(partes[11]);
			String material=partes[12];
			int peso=Integer.parseInt(partes[13]);
			Boolean necesitaElectricidad = Boolean.parseBoolean(partes[14]);
			String detallesInstalacion=partes[15];
			pieza=new PiezaEscultura(titulo, anoCreacion, artista, lugarCreacion, propietario, valorFijo, disponibleEnSubasta, tipo, bloqueada, alto, ancho, profundidad, material, peso, necesitaElectricidad, detallesInstalacion);
		}
		 else if ("fotografia".equals(tipo)) {
			float alto = Float.parseFloat(partes[9]);
			float largo = Float.parseFloat(partes[10]);
			String color = partes[11];
			pieza = new PiezaFotografia(titulo, anoCreacion, artista, lugarCreacion, propietario, valorFijo, disponibleEnSubasta, tipo, bloqueada, alto, largo, color);
			
		}
		 else if ("video".equals(tipo))  {
			float duracion = Float.parseFloat(partes[9]);
			String largo = partes[10];
			pieza = new PiezaVideo(titulo, anoCreacion, artista, lugarCreacion, propietario, valorFijo, disponibleEnSubasta, tipo, bloqueada,duracion, largo);
		}

		

		return pieza;
	}
	
	//Write: Actualizar archivo, reescribirlo.

	private String generarTextoPiezas(){
		String texto="";
		for(Pieza pieza:mapaPiezas.values()) {
			texto+=comprimirPieza(pieza);
			texto+="\n";
		}
		return texto;
	}
	
	public String comprimirPieza(Pieza pieza) {
		String titulo = pieza.getTitulo();
		String anoCreacion = String.valueOf(pieza.getAnoCreacion());
		String artista = pieza.getArtista();
		String lugarCreacion = pieza.getLugarCreacion();
		String propietario = pieza.getPropietario();
		String bloqueada = String.valueOf(pieza.getBloqueo());
	    String valorFijo = String.valueOf(pieza.getValorFijo()); 
	    String disponibleEnSubasta = String.valueOf(pieza.getDisponibleSubasta());
		String tipo = pieza.getTipo();
		String str = titulo + ";" + anoCreacion + ";"+ artista + ";" + lugarCreacion + ";" + propietario + ";" + valorFijo + ";"
				+ propietario + ";" + disponibleEnSubasta + ";" + tipo + ";" + bloqueada ;
		if(tipo=="pintura") {
			PiezaPintura piezaPintura = (PiezaPintura) pieza;
			String tipoPintura = piezaPintura.getTipoPintura();
			String dimensiones = piezaPintura.getDimensiones();
			String detalles = piezaPintura.getDetalles();
			
			//titulo, ano, artista, lugar, propietario, valorFijo, true, tipo, false, alto, ancho, profundidad, material, peso, necesitaElectricidad, detallesInstalacion
			
			str = titulo + ";" + anoCreacion + ";"+ artista + ";"+ lugarCreacion + ";" + propietario + ";" + valorFijo + ";"
					+ disponibleEnSubasta + ";" + tipo + ";" + bloqueada +";"+ tipoPintura + ";" + dimensiones + ";" + detalles; 
			
		}
		else if ("Escultura".equals(tipo))  {
			PiezaEscultura piezaEscultura = (PiezaEscultura) pieza;
			String alto = String.valueOf(piezaEscultura.getAlto());
			String ancho = String.valueOf(piezaEscultura.getAncho());
			String profundidad = String.valueOf(piezaEscultura.getProfundidad());
			String material = piezaEscultura.getMaterial();
			String peso = String.valueOf(piezaEscultura.getPeso());
			String necesitaElectricidad= String.valueOf(piezaEscultura.getNecesitaElectricidad());
			String detallesInstalacion = piezaEscultura.getDetallesInstalacion();
			
			//El beso;1889;Auguste Rodin; "París"; "Museo Rodin";12000000; "Museo Rodin";true;"Escultura";true
			
			str=titulo + ";" + anoCreacion + ";"+ artista + ";"+ lugarCreacion + ";" + propietario + ";" + valorFijo + ";"+ disponibleEnSubasta + ";" + tipo + ";" + bloqueada +";" + alto + ";" + ancho + ";" + profundidad + ";" + material + ";" + peso + ";" + necesitaElectricidad + ";" + detallesInstalacion;
		}
		
		else if (tipo=="fotografia") {
			PiezaFotografia piezaFotografia = (PiezaFotografia) pieza;
			String alto = String.valueOf(piezaFotografia.getAlto());
			String ancho = String.valueOf(piezaFotografia.getLargo());
			String profundidad = String.valueOf(piezaFotografia.getColor());
			str=titulo + ";" + anoCreacion + ";"+ artista + ";"+ lugarCreacion + ";" + propietario + ";" + valorFijo + ";"
					+ disponibleEnSubasta + ";" + tipo + ";" + bloqueada +";" + alto + ";" + ancho + ";" + profundidad;
		}
		
		else if (tipo=="video") {
			PiezaFotografia piezaFotografia = (PiezaFotografia) pieza;
			String alto = String.valueOf(piezaFotografia.getAlto());
			String ancho = String.valueOf(piezaFotografia.getLargo());
			String profundidad = String.valueOf(piezaFotografia.getColor());
			str=titulo + ";" + anoCreacion + ";"+ artista + ";"+ lugarCreacion + ";" + propietario + ";" + valorFijo + ";"
					+ disponibleEnSubasta + ";" + tipo + ";" + bloqueada +";" + alto + ";" + ancho + ";" + profundidad;
		}
		

		return str;

	}
	
	
	public void actualizarArchivoPiezas() throws IOException {
		String texto=generarTextoPiezas();
		FileWriter fichero = new FileWriter("data/piezas.txt");
		fichero.write(texto);
		fichero.close();
	}
	
	public void descargarDatosInventario() throws IOException {
		crearMapaPiezas();// Incompleto
		cargarMapaArtistas();  //revisar

	}
	
	public void cargarTodosLosDatos() throws IOException {
		actualizarArchivoPiezas();

		
	}
	
	// Revisar 
	
	public String obtenerHistoriaPieza(String tituloPieza) {
	    Pieza pieza = mapaPiezas.get(tituloPieza);
	    if (pieza !=null) {
	    	StringBuilder historia = new StringBuilder(); // Aquí se crea el StringBuilder para construir la historia
	        historia.append("Historia de la pieza: ").append(tituloPieza).append("\n");
	        historia.append("Año de creación: ").append(pieza.getAnoCreacion()).append("\n");
	        historia.append("Artista: ").append(pieza.getArtista()).append("\n");
	        historia.append("Lugar de creación: ").append(pieza.getLugarCreacion()).append("\n");
	        historia.append("Propietario: ").append(pieza.getPropietario()).append("\n");
	        historia.append("Valor fijo: ").append(pieza.getValorFijo()).append("\n");
	        historia.append("Disponible en subasta: ").append(pieza.getDisponibleSubasta()).append("\n");
	        historia.append("Historial de transacciones:").append("\n");
	        for (String transaccion : pieza.getHistorialTransacciones()) {
	            historia.append(transaccion).append("\n");
	        }
	        return historia.toString(); // Aquí se convierte el StringBuilder a una cadena para devolverla
	    } else {
	        return "No se encontró la pieza con el título: " + tituloPieza;
	    }
	}
	
	/*
    private void asignarPiezasAArtistas() {
        for (Pieza pieza : mapaPiezas.values()) {
            String nombreArtista = pieza.getArtista();
            List<Pieza> piezasArtista = mapaArtistas.getOrDefault(nombreArtista, new ArrayList<>());
            piezasArtista.add(pieza);
            mapaArtistas.put(nombreArtista, piezasArtista);
        }
    }*/
    
    
    
    private void cargarMapaArtistas() throws IOException {
        for (Pieza pieza : mapaPiezas.values()) {
            String nombreArtista = pieza.getArtista();
            List<Pieza> piezasArtista = mapaArtistas.getOrDefault(nombreArtista, new ArrayList<>());
            piezasArtista.add(pieza);
            mapaArtistas.put(nombreArtista, piezasArtista);
        }
    }
    
	public String obtenerHistoriaArtista(String nombreArtista) {
        List<Pieza> piezasArtista = mapaArtistas.get(nombreArtista);

        if (piezasArtista != null) {
            StringBuilder historia = new StringBuilder();
            historia.append("Historia del artista: ").append(nombreArtista).append("\n");

            for (Pieza pieza : piezasArtista) {
                historia.append("Título: ").append(pieza.getTitulo()).append("\n");
                historia.append("Año de creación: ").append(pieza.getAnoCreacion()).append("\n");
                historia.append("Valor fijo: ").append(pieza.getValorFijo()).append("\n");
                historia.append("Propietario: ").append(pieza.getPropietario()).append("\n");
        
                historia.append("\n");
            }

            return historia.toString();
        } else {
            return "No se encontró el artista: " + nombreArtista;
        }
    }
	
    
    public static void agregarLinea(String archivo, String nuevaLinea) {
        try {
            // Abre el archivo en modo de adicion
            BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true));
            
            // Escribe la nueva linea en el archivo
            writer.write(nuevaLinea + System.getProperty("line.separator"));
            
            writer.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	//pendiente probar estas funciones, hacer la del cliente y hacer la parte de 
}



