package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import controller.BaseDatosInventario;
import controller.BaseDatosEmpresa;
import controller.BaseDatosGaleria;
import controller.ControllerCliente;
import model.Inventario;

public class InterfazCliente {
	public static ControllerCliente elCliente;
	private static BaseDatosGaleria datosGaleria;
	private static BaseDatosInventario datosPiezas;
	
	
	public static void correrCliente(BaseDatosEmpresa datos, BaseDatosInventario datosInventario, BaseDatosGaleria datosGaleria) throws IOException
	{
		System.out.println("Bienvenido cliente");
		elCliente= new ControllerCliente(datos, datosInventario);
		elCliente.setDatosGaleria(datosGaleria); // Creamos instancia del controlador y añadimos los datos
		datosPiezas = datosInventario;
		// para trabajar
		
		boolean continuar = true;
		while (continuar)
		{
			try
			{
				mostrarMenu();
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
				if (opcion_seleccionada == 1) {
					boolean continuar2 = LogIn();
					if (continuar2==false) {
						System.out.println("No se pudo inicar sesion");
					}
				    while (continuar2) 
					{
						try
						{
							mostrarMenu2();
							int opcion_seleccionada2 = Integer.parseInt(input("Ingresa una opcion "));
				            if (opcion_seleccionada2 == 1 && elCliente.getCliente() != null) {
				            	String nombrePieza = input("Ingrese el nombre de la pieza que desee comprar");
				            	elCliente.realizarCompraFija(nombrePieza);
				            	datosGaleria.actualizarArchivoVenta();
				            	//realizarCompra();
				            }
				            else if (opcion_seleccionada2 == 2){
				                if (elCliente.getCliente() != null) {
				                    String nombreCliente = input("Ingrese su nombre");
				                    String nombrePieza = input("Ingrese el nombre de la pieza por la que desea participar en subasta");
				                    String valorPropuesto = input("ingrese el valor propuesto");
				                    elCliente.actualizarOferta(nombreCliente, nombrePieza, valorPropuesto);;
				                    System.out.println("Saliendo de la aplicación ...");
				                    continuar = false;
				                } 

				                else {
				                    System.out.println("Para poder ejecutar esta opción primero debe iniciar sesión");
				                }
				            }
				              else if (opcion_seleccionada2 == 3) {
				            	  // pensar como hacerlo logico con la estructura del proyecto
				            	  System.out.println("En construccion");
				              }
				              else if(opcion_seleccionada2 == 4) {
				            	  String piezaNombre = input("Escriba el nombre de la pieza a consultar");
				            	  System.out.println("\n");
				            	  System.out.println(datosPiezas.obtenerHistoriaPieza(piezaNombre));

				              }
				              else if(opcion_seleccionada2 == 5) {
				            	  String artistaNombre = input("Escriba el nombre del artista que desea consultar");
				            	  System.out.println("\n");
				            	  System.out.println(datosPiezas.obtenerHistoriaArtista(artistaNombre));
				              }
				            else if (elCliente == null){
					            System.out.println("Para poder ejecutar esta opción primero debe iniciar sesión");
				            }
						}   
						catch (NumberFormatException e)
							{
								System.out.println("Debe seleccionar uno de los números de las opciones.");
						}
					}
				 }
				 else {
						System.out.println("Saliendo de la aplicación ...");
						continuar = false;
				  }
				}
				catch (NumberFormatException e)
				{
					System.out.println("Debe seleccionar uno de los números de las opciones.");
				}
		}
	}

	public static String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;}
		public static void mostrarMenu()
		{
			System.out.println("\nOpciones de la aplicación\n");
			System.out.println("1. LogIn");
			System.out.println("2. Salir de la aplicacion");

	   }
		
		public static void mostrarMenu2()
		{
			System.out.println("\nMENU COMPRADOR");
			System.out.println("1. Realizar Compra por Valor fijo");
			System.out.println("2. Realizar Compra por medio de Subasta");
			System.out.println("3. Vender o subastar Pieza (Consignacion)");
			System.out.println("4. Ver información de una pieza expecífica");
			System.out.println("5. Ver historial de un artista expecifico");
		}
		
		
		private static boolean LogIn() {
			boolean login=false;
			String usuario =input("Usuario ");
			String contrasena =input("Contraseña ");
			
			elCliente.LogIn(usuario, contrasena);
			if(elCliente.getCliente().equals(null)) {
				System.out.println("Error ingresando sesión");
				
			}
			else {
				System.out.println("Ingresado correctamente");
				login=true;
			}
			return login;
		}
		
       public static void realizarCompra() {
    	   String nombrePieza=input("Titulo o nombre de la pieza: ");
    	   
    	   //SString userNameCliente= elCliente.getCliente();
	       //double cobro=elCliente.realizarCompra(nombrePieza);
       }
       
       public static void cargarDatos() throws IOException {
    	   elCliente.actualizarDatos();
       }

}

