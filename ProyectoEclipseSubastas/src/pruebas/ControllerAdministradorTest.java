package pruebas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.BaseDatosEmpresa;
import controller.BaseDatosGaleria;
import controller.ControllerAdministrador;
import model.Administrador;
import model.Venta;

import java.io.IOException;
import java.util.HashMap;

public class ControllerAdministradorTest {

    private ControllerAdministrador controller;

    @BeforeEach
    public void setUp() {
        // Inicializar el controlador antes de cada prueba
        controller = new ControllerAdministrador();
    }

    @Test
    public void testLogIn() {
        // Simular datos de inicio de sesión
        BaseDatosEmpresa datosEmpresa = new BaseDatosEmpresa();
        HashMap<String, Administrador> mapaAdministradores = new HashMap<>();
        Administrador admin = new Administrador("admin", "admin123", "Administrador");
        mapaAdministradores.put("admin", admin);
        datosEmpresa.getMapaAdministradores();
        controller.setDatosEmpresa(datosEmpresa);

        // Probar el inicio de sesión exitoso
        controller.LogIn("admin", "admin123");
        assertEquals(admin, controller.getAdministrador());

        // Probar el inicio de sesión fallido
        controller.LogIn("admin", "contrasenaincorrecta");
        assertNull(controller.getAdministrador());
    }

    @Test
    public void testCrearSubasta() {
        // Simular datos de subasta
        BaseDatosGaleria datosGaleria = new BaseDatosGaleria();
        controller.setDatosGaleria(datosGaleria);

        // Probar la creación de una nueva subasta
        try {
            assertTrue(controller.crearSubasta("Pieza de arte", "100", "50"));
            assertNotNull(datosGaleria.getMapaSubastas().get("Pieza de arte"));
        } catch (Exception e) {
            fail("Error al crear subasta: " + e.getMessage());
        }
    }

    @Test
    public void testConfirmarVenta() throws IOException {
        // Simular datos de venta
        BaseDatosGaleria datosGaleria = new BaseDatosGaleria();
        HashMap<String, Venta> mapaVentas = new HashMap<>();
        Venta venta = new Venta("cliente1", "Pieza1", 100, null, null);
        mapaVentas.put("venta1", venta);
        datosGaleria.getMapaVentas();
        controller.setDatosGaleria(datosGaleria);

        // Probar la confirmación de una venta existente
        assertTrue(controller.ConfirmarVenta("cliente1", "Pieza1"));
        assertTrue(datosGaleria.getMapaVentas().get("venta1").getVentaConfirmada());

        // Probar la confirmación de una venta inexistente
        assertFalse(controller.ConfirmarVenta("cliente2", "Pieza2"));
    }
}