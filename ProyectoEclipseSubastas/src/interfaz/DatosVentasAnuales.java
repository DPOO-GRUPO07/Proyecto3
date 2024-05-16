package interfaz;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class DatosVentasAnuales {
    private Map<LocalDate, Boolean> disponibilidad;

    public DatosVentasAnuales() {
        this.disponibilidad = new HashMap<>();
    }

    public void cargarDatosDesdeArchivo(String filePath) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                
                // La fecha se encuentra en la penúltima posición
                String fechaString = data[data.length - 1];
                boolean disponible = data[data.length - 2].equals("fija") || data[data.length - 1].equals("subasta");

                // Evitamos errores de formato en la cadena "Disponible"
                if (disponible) {
                    LocalDate fecha = LocalDate.parse(fechaString, formatter);
                    disponibilidad.put(fecha, disponible);
                }
            }
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    public Map<LocalDate, Boolean> getDisponibilidad() {
        return disponibilidad;
    }
}
