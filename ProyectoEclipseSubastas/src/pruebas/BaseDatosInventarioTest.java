package pruebas;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import controller.BaseDatosInventario;
import pieza.Pieza;
public class BaseDatosInventarioTest {
    
	   private BaseDatosInventario inventario;

	    @Before
	    public void setUp() {
	        inventario = new BaseDatosInventario();
	        // Agregar datos de prueba al mapa de piezas y de artistas
	        cargarDatosDePrueba();
	    }

	    @Test
	    public void testObtenerHistoriaArtista() {
	        // Aquí se prueba la función obtenerHistoriaArtista()

	        // Artista existente en los datos de prueba
	        String nombreArtistaExistente = "Artista1";
	        String historiaArtistaExistente = inventario.obtenerHistoriaArtista(nombreArtistaExistente);
	        // Verificar que la historia del artista existente sea correcta
	        String historiaArtistaExistenteEsperada = "Historia del artista: Artista1\n" +
	            "Título: Título1\n" +
	            "Año de creación: 2000\n" +
	            "Valor fijo: 100\n" +
	            "Propietario: Propietario1\n" +
	            "\n" +
	            "Título: Título2\n" +
	            "Año de creación: 2005\n" +
	            "Valor fijo: 200\n" +
	            "Propietario: Propietario2\n" +
	            "\n";
	        assertEquals(historiaArtistaExistenteEsperada, historiaArtistaExistente);

	        // Artista no existente en los datos de prueba
	        String nombreArtistaNoExistente = "ArtistaNoExistente";
	        String historiaArtistaNoExistente = inventario.obtenerHistoriaArtista(nombreArtistaNoExistente);
	        // Verificar que se devuelve el mensaje correcto para un artista no existente
	        String mensajeEsperadoNoExistente = "No se encontró el artista: " + nombreArtistaNoExistente;
	        assertEquals(mensajeEsperadoNoExistente, historiaArtistaNoExistente);
	    }

	    private void cargarDatosDePrueba() {
	        // Crear algunas piezas de prueba
	        Pieza pieza1 = new Pieza("Título1", 2000, "Artista1", "Lugar1", "Propietario1", 100, true, "tipo1", false);
	        Pieza pieza2 = new Pieza("Título2", 2005, "Artista1", "Lugar2", "Propietario2", 200, false, "tipo2", true);
	        Pieza pieza3 = new Pieza("Título3", 2010, "Artista2", "Lugar3", "Propietario3", 300, true, "tipo3", false);

	        // Agregar las piezas al mapa de piezas de inventario
	        HashMap<String, Pieza> mapaPiezas = new HashMap<>();
	        mapaPiezas.put(pieza1.getTitulo(), pieza1);
	        mapaPiezas.put(pieza2.getTitulo(), pieza2);
	        mapaPiezas.put(pieza3.getTitulo(), pieza3);
	        inventario.getMapaPiezas().putAll(mapaPiezas);

	        // Crear el mapa de artistas con las piezas correspondientes
	        HashMap<String, List<Pieza>> mapaArtistas = new HashMap<>();
	        List<Pieza> piezasArtista1 = new ArrayList<>();
	        piezasArtista1.add(pieza1);
	        piezasArtista1.add(pieza2);
	        mapaArtistas.put("Artista1", piezasArtista1);

	        List<Pieza> piezasArtista2 = new ArrayList<>();
	        piezasArtista2.add(pieza3);
	        mapaArtistas.put("Artista2", piezasArtista2);

	        // Asignar el mapa de artistas al inventario
	        inventario.setMapaArtistas(mapaArtistas);
	    }
}