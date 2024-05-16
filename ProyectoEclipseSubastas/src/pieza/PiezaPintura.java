package pieza;

public class PiezaPintura extends Pieza {
	
	private String tipoPintura;
    private String dimensiones;
    private String detalles;
	
	public PiezaPintura(String titulo, int anoCreacion, String artista, String lugarCreacion, String propietario,
			int valorFijo, Boolean disponibleEnSubasta, String tipo, boolean bloqueada,String tipoPintura, String dimensiones, String detalles) {
		super(titulo, anoCreacion, artista, lugarCreacion, propietario, valorFijo, disponibleEnSubasta, tipo, bloqueada);
		// TODO Auto-generated constructor stub
		this.tipoPintura=tipoPintura;
		this.dimensiones=dimensiones;
		this.detalles=detalles;
	}

	
    // Getters y setters específicos para PiezaPintura
	public String getTipoPintura() {
		return tipoPintura;
	}
	public String getDimensiones() {
		return dimensiones;
	}
	public String getDetalles() {
		return detalles;
	}

	
	//Setters
	
	
	public String setTipoPintura(String nuevoTitulo) {
		return nuevoTitulo;
	}
	
	public String setDimensiones(String dimension) {
		return dimension;
	}
	
	public String setDetalles(String detalles) {
		return detalles;
	}
	

    
}
