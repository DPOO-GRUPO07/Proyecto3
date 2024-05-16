package model;

public class Subasta {
	// Atributos
	
	private String tituloPieza;
	private int valorMininmo;
	private static int valorInicial;
	
	public Subasta(String tituloPieza, int valorMininmo, int valorInicial)
	{
		this.tituloPieza= tituloPieza;
		this.valorMininmo= valorMininmo;
		this.valorInicial= valorInicial;
	}
	
	//getters
	
	public String getTituloPieza()
	{
		return this.tituloPieza;
	}
	
	
	public double getValorMininimo()
	{
		return this.valorMininmo;
	}
	
	public double getValorInicial()
	{
		return this.valorInicial;
	}
	
	//setters
	
	public void setValor(int valor)
	{
		this.valorInicial= + valor;
	}
}
