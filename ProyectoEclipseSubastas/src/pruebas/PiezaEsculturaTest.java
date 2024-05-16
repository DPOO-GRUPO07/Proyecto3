package pruebas;

import org.junit.jupiter.api.Test;

import pieza.PiezaEscultura;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PiezaEsculturaTest {

    @Test
    public void testCrearPiezaEscultura() {
        PiezaEscultura escultura = new PiezaEscultura("David", 1504, "Miguel Ángel", "Italia", "Galería de la Academia de Florencia", 100000000, false, "escultura", false, 5, 3, 2, "Mármol", 500, false, "No requiere instalación especial");

        assertEquals("David", escultura.getTitulo());
        assertEquals(1504, escultura.getAnoCreacion());
        assertEquals("Miguel Ángel", escultura.getArtista());
        assertEquals("Italia", escultura.getLugarCreacion());
        assertEquals("Galería de la Academia de Florencia", escultura.getPropietario());
        assertEquals(100000000, escultura.getValorFijo());
        assertEquals(false, escultura.getDisponibleSubasta());
        assertEquals("escultura", escultura.getTipo());
        assertEquals(false, escultura.getBloqueo());
        assertEquals(5.0f, escultura.getAlto());
        assertEquals(3.0f, escultura.getAncho());
        assertEquals(2.0f, escultura.getProfundidad());
        assertEquals("Mármol", escultura.getMaterial());
        assertEquals(500.0f, escultura.getPeso());
        assertEquals(false, escultura.getNecesitaElectricidad());
        assertEquals("No requiere instalación especial", escultura.getDetallesInstalacion());
    }

    @Test
    public void testModificarPiezaEscultura() {
        PiezaEscultura escultura = new PiezaEscultura("David", 1504, "Miguel Ángel", "Italia", "Galería de la Academia de Florencia", 100000000, false, "escultura", false, 5, 3, 2, "Mármol", 500, false, "No requiere instalación especial");

        escultura.setTitulo("El Pensador");
        escultura.setArtista("Auguste Rodin");
        escultura.setAnoCreacion(1904);
        escultura.setMaterial("Bronce");
        escultura.setPropietario("Museo Rodin");

        assertEquals("El Pensador", escultura.getTitulo());
        assertEquals("Auguste Rodin", escultura.getArtista());
        assertEquals(1904, escultura.getAnoCreacion());
        assertEquals("Bronce", escultura.getMaterial());
        assertEquals("Museo Rodin", escultura.getPropietario());
    }
}