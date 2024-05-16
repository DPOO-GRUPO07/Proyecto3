package model;

public class Venta {

	//Atributos

	private String nombreUsuario;
	private String tituloPieza;
	private int valor;
	private String tipoVenta;
	private Boolean ventaConfirmada;
	
	//private Boolean Propietario;
	//
	
	public Venta(String nombreUsuario, String tituloPieza, int valor, String tipoVenta ,Boolean ventaconfirmada) {
		this.nombreUsuario=nombreUsuario;
		this.tituloPieza=tituloPieza;
		this.valor=valor;
		this.tipoVenta=tipoVenta;
		this.ventaConfirmada=ventaconfirmada;
	}
	
	
	public String getUsuario() {
		return nombreUsuario;
	}

	public String getTituloPieza() {

		return tituloPieza;
	}
	
	public int getValorPieza() {

		return valor;
	}
	
	public String getTipoVenta() {
		
		return tipoVenta;
	}

	public Boolean getVentaConfirmada() {

		return ventaConfirmada;
	}
	
	public Boolean setVentaConfirmada(Boolean confirmacion) {

		return confirmacion;
	}

   
}
