package pruebas;

import org.junit.jupiter.api.Test;

import pieza.Pieza;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;

public class PiezaTest {

    @Test
    public void testCrearPieza() {
        Pieza pieza = new Pieza("La Gioconda", 1503, "Leonardo da Vinci", "Italia", "Museo del Louvre", 100000000, false, "pintura", false);

        assertEquals("La Gioconda", pieza.getTitulo());
        assertEquals(1503, pieza.getAnoCreacion());
        assertEquals("Leonardo da Vinci", pieza.getArtista());
        assertEquals("Italia", pieza.getLugarCreacion());
        assertEquals("Museo del Louvre", pieza.getPropietario());
        assertEquals(100000000, pieza.getValorFijo());
        assertEquals(false, pieza.getDisponibleSubasta());
        assertEquals("pintura", pieza.getTipo());
        assertEquals(false, pieza.getBloqueo());
    }

    @Test
    public void testModificarPieza() {
        Pieza pieza = new Pieza("La Gioconda", 1503, "Leonardo da Vinci", "Italia", "Museo del Louvre", 100000000, false, "pintura", false);

        pieza.setTitulo("Mona Lisa");
        pieza.setArtista("Leonardo di ser Piero");
        pieza.setAnoCreacion(1506);
        pieza.setTipo("Óleo sobre tabla");
        pieza.setPropietario("Gobierno francés");

        assertEquals("Mona Lisa", pieza.getTitulo());
        assertEquals("Leonardo di ser Piero", pieza.getArtista());
        assertEquals(1506, pieza.getAnoCreacion());
        assertEquals("Óleo sobre tabla", pieza.getTipo());
        assertEquals("Gobierno francés", pieza.getPropietario());
    }

    @Test
    public void testHistorialTransacciones() {
        Pieza pieza = new Pieza("La Gioconda", 1503, "Leonardo da Vinci", "Italia", "Museo del Louvre", 100000000, false, "pintura", false);

        pieza.agregarTransaccion("Vendida a Museo del Louvre por $100,000,000");
        pieza.agregarTransaccion("Transferida de Museo del Louvre a Gobierno francés");

        List<String> historial = pieza.getHistorialTransacciones();
        assertEquals(2, historial.size());
        assertEquals("Vendida a Museo del Louvre por $100,000,000", historial.get(0));
        assertEquals("Transferida de Museo del Louvre a Gobierno francés", historial.get(1));
    }
}
