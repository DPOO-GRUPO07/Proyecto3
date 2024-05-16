package pieza;

public class PiezaVideo extends Pieza{
	
	private Float duracion;
	private String formato;

	public PiezaVideo(String titulo, int anoCreacion, String artista, String lugarCreacion, String propietario,
			int valorFijo, Boolean disponibleEnSubasta, String tipo, boolean bloqueada,float duracion, String formato) {
		super(titulo, anoCreacion, artista,lugarCreacion, propietario, valorFijo, disponibleEnSubasta, tipo, bloqueada);
		
	}
	
    // Getters y setters específicos para PiezaPintura
	public Float getDuracion() {
		return duracion;
	}
	public String getFormato() {
		return formato;
	}

	
	//Setters
	
	
	public Float setDuracion(Float duracion) {
		return duracion;
	}
	
	public String setFormato(String dimension) {
		return dimension;
	}
	

}
