package pieza;

public class PiezaEscultura extends Pieza{
	
	private int alto;
	private int ancho;
	private int profundidad;
	private String material;
	private int peso;
	private boolean necesitaElectricidad;
	private String detallesInstalacion;

	public PiezaEscultura(String titulo, int anoCreacion, String artista, String lugarCreacion, String propietario,
			int valorFijo, Boolean disponibleEnSubasta, String tipo, boolean bloqueada, int alto, int ancho, int profundidad, String material, int peso, boolean necesitaElectricidad, String detallesInstalacion) {
		super(titulo, anoCreacion, artista, lugarCreacion, propietario, valorFijo, disponibleEnSubasta, tipo, bloqueada);
		// TODO Auto-generated constructor stub
		this.alto=alto;
		this.ancho=ancho;
		this.profundidad=profundidad;
		this.material=material;
		this.peso=peso;
		this.necesitaElectricidad=necesitaElectricidad;
		this.detallesInstalacion=detallesInstalacion;
	}
	
	
	/*
	 * GETTER AND SETTERS
	 * 
	 * */
	public int getAlto() {
		return alto;
	}
	public int getAncho() {
		return ancho;
	}
	public int getProfundidad() {
		return profundidad;
	}

	public String getMaterial() {
		return material;
	}
	
	public int getPeso() {
		return peso;
	}
	
	public boolean getNecesitaElectricidad() {
		return necesitaElectricidad;
	}
	
	public String getDetallesInstalacion() {
		return detallesInstalacion;
	}

	//Setters
	public void setAlto(int alto) {
		this.alto = alto;
	}


	public void setAncho(int ancho) {
		this.ancho = ancho;
	}


	public void setProfundidad(int profundidad) {
		this.profundidad = profundidad;
	}


	public void setMaterial(String material) {
		this.material = material;
	}


	public void setPeso(int peso) {
		this.peso = peso;
	}


	public void setNecesitaElectricidad(boolean necesitaElectricidad) {
		this.necesitaElectricidad = necesitaElectricidad;
	}


	public void setDetallesInstalacion(String detallesInstalacion) {
		this.detallesInstalacion = detallesInstalacion;
	}
	

	
	
/*
	public float setAltura(float altura) {
		return altura;
	}
	
	public float setAncho(float ancho) {
		return ancho;
	}
	
	public float setProfundidad(float profundidad) {
		return profundidad;
	}
	
	public String setMaterial(String nuevoPropietario) {
		return nuevoPropietario;
	}
	
*/
}
