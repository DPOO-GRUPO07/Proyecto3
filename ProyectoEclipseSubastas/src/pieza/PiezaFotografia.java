package pieza;

public class PiezaFotografia extends Pieza{

	private float alto;
	private float largo;
	private String color;
	
	public PiezaFotografia(String titulo, int anoCreacion, String artista, String lugarCreacion,  String propietario,
			int valorFijo, Boolean disponibleEnSubasta, String tipo, boolean bloqueada,float alto, float largo, String color) {
		super(titulo, anoCreacion, artista, lugarCreacion, propietario, valorFijo, disponibleEnSubasta, tipo, bloqueada);
		// TODO Auto-generated constructor stub
		this.alto=alto;
		this.largo=largo;
		this.color=color;
		
	}
	
	/*
	 * GETTERS AND SETTERS
	 * */
	
	public float getAlto() {
		return alto;
	}
	public float getLargo() {
		return largo;
	}
	public String getColor() {
		return color;
	}



}
