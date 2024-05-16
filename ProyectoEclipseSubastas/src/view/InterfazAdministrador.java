package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import controller.BaseDatosEmpresa;
import controller.BaseDatosGaleria;
import controller.BaseDatosInventario;
import controller.ControllerAdministrador;
import controller.ControllerEmpleado;
import model.Empleado;
import pieza.PiezaEscultura;
import pieza.PiezaFotografia;
import pieza.PiezaPintura;
import pieza.PiezaVideo;


public class InterfazAdministrador {
	public static ControllerAdministrador elAdministrador;
	public static ControllerAdministrador laPieza;
	public static void correrAdministrador(BaseDatosEmpresa datos, BaseDatosInventario datosInventario, BaseDatosGaleria datosGaleria) throws Exception
	{
		System.out.println("LogIn Administrador");
		elAdministrador = new ControllerAdministrador();
		elAdministrador.setDatosEmpresa(datos); // Creamos instancia del controlador y añadimos los datos
		elAdministrador.setDatosInventario(datosInventario);
		elAdministrador.setDatosGaleria(datosGaleria);
		
		// para trabajar
		
		boolean continuar = true;
		while (continuar)
		{
			try
			{
					boolean continuar2 = LogIn();
					while (continuar2) 
					{
						try
						{
							mostrarMenu2();
							int opcion_seleccionada2 = Integer.parseInt(input("Ingresa una opcion "));
							if (opcion_seleccionada2 == 1) {
							    String titulo = input("Ingrese el Titulo de la Pieza");
							    int ano = Integer.parseInt(input("Ingrese el año de creacion"));
							    String lugar = input("Ingrese el lugar de creacion");
							    String artista = input("Ingrese el nombre del artista");
							    String propietario = input("Ingrese el nombre del propietario de la pieza, si no tiene puede colocar N/A");
							    int valorFijo = Integer.parseInt(input("Ingrese el valor fijo"));
							    String tipo = input("Ingrese el Tipo de pieza");

							    // Dependiendo del tipo de pieza, creamos la instancia correspondiente y la registramos
							    switch (tipo.toLowerCase()) {
							        case "escultura":
							            int alto = Integer.parseInt(input("Ingrese la altura de la pieza"));
							            int ancho = Integer.parseInt(input("Ingrese el ancho de la pieza"));
							            int profundidad = Integer.parseInt(input("Ingrese la profundidad"));
							            String material = input("Ingrese cual es el material de la pieza");
							            int peso = Integer.parseInt(input("Ingrese el peso de la pieza"));
							            boolean necesitaElectricidad = Boolean.parseBoolean(input("Ingrese si necesita electricidad (true o false): "));
							            String detallesInstalacion = input("Ingrese los detalles de la pieza");
							            PiezaEscultura escultura = new PiezaEscultura(titulo, ano, artista, lugar, propietario,valorFijo, true, tipo, false, alto, ancho, profundidad, material, peso, necesitaElectricidad, detallesInstalacion);
							            elAdministrador.agregarPiezaAInventario(escultura);
							            break;
							        case "pintura":
							            String tipoPintura = input("Ingrese cual es el tipo de pintura");
							            String dimensiones = input("Ingrese las dimensiones de la pieza");
							            String detallesPintura = input("Escriba detalles de la pieza");
							            PiezaPintura pintura = new PiezaPintura(titulo, ano, lugar, propietario, null, valorFijo, true, tipo, false, tipoPintura, dimensiones, detallesPintura);
							            elAdministrador.agregarPiezaAInventario(pintura);
							            break;
							        case "fotografia":
							            int altoFoto = Integer.parseInt(input("Ingrese la altura de la pieza"));
							            int largo = Integer.parseInt(input("Ingrese el largo de la pieza"));
							            String color = input("Ingrese cual es el color de la pieza");
							            PiezaFotografia fotografia = new PiezaFotografia(titulo, ano, lugar, propietario, null, valorFijo, true, tipo, false, altoFoto, largo, color);
							            elAdministrador.agregarPiezaAInventario(fotografia);
							            break;
							        case "video":
							            float duracion = Float.parseFloat(input("Ingrese la duracion del video"));
							            String formato = input("Ingrese el tipo de formato del video");
							            PiezaVideo video = new PiezaVideo(titulo, ano, lugar, propietario, null, valorFijo, true, tipo, false, duracion, formato);
							            elAdministrador.agregarPiezaAInventario(video);
							            break;
							        default:
							            System.out.println("Tipo de pieza no válido.");
							            return;
							    }
							}
								
							else if (opcion_seleccionada2 == 2) {
								String userName= input("Ingrese el usuario del comprador");
								String namePieza = input("Ingrese el Titulo de la pieza");
								if (elAdministrador.ConfirmarVenta(userName, namePieza)) {
									System.out.println("La venta del Comprador "+ userName + " fue aceptada");
								}
								else {
									System.out.println("La confirmacion no fue aceptada");
								}
							}
							else if (opcion_seleccionada2 ==3) {
								String userName= input("Ingrese el nombre de usuario del cliente que va entrar a la Subasta");
								boolean estadoVerificacion = elAdministrador.verificarComprador(userName);
								if (estadoVerificacion==true) {
									System.out.println("El usuario "+ userName + " fue verificado exitosamente");
									System.out.println("Ahora ajuste un maximo de compras para el comprador");
									int maximoCompras= Integer.parseInt(input("Escriba el valor maximo de compras: "));
									elAdministrador.maximoCompras(userName, maximoCompras);
								}
								else {
									System.out.println("El usuario no esta registrado en el sistema no se puede VERIFICAR");
								}
							}
							else if (opcion_seleccionada2 == 4) {
								System.out.println("Para crear una subasta se debe ingresar una Pieza y asigne un valor minimo y un valor inicial");
								String tituloPieza= input("Ingrese el titulo de la pieza que va ingresar a la Subasta");
								String valorMininmo= input("Ingrese el valor minimo que debe tener la pieza");
								String valorInicial= input("Ingrese el valor incial (Este es el valor que se va a incrementar): ");
								boolean estadoVerificacion = elAdministrador.crearSubasta(tituloPieza, valorMininmo, valorInicial);
								
							}
							else if (opcion_seleccionada2 == 5) {
							    String usuario = input("Ingrese el nombre de usuario del cliente para ver su historial de compras: ");
							    List<String> historialCompras = elAdministrador.historialComprasCliente(usuario);
							    
							    if (historialCompras.isEmpty()) {
							        System.out.println("El cliente no tiene historial de compras.");
							    } else {
							        System.out.println("Historial de compras para el cliente " + usuario + ":");
							        for (String compra : historialCompras) {
							            System.out.println(compra);
							        }
							    }
								
						    }
						}
						catch (NumberFormatException e)
						{
							System.out.println("Debe seleccionar uno de los números de las opciones.");
						}

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
		
		public static void mostrarMenu2(){
			System.out.println("\nMENU ADMINISTRADOR");
			System.out.println("1. Registrar Pieza en el Inventario");
			System.out.println("2. Confirmar Venta");
			System.out.println("3. Verificar comprador");
			System.out.println("4. Crear subasta");
			System.out.println("5. Ver historial de un comprador");
		}
		
		
		private static boolean LogIn() {
	
			boolean login=false;
			String usuario = input("Usuario");
			String contrasena = input("Contraseña");
			
			elAdministrador.LogIn(usuario, contrasena);
			if(elAdministrador.getAdministrador().equals(null)) {
				System.out.println("Error ingresando sesión");
				
			}
			else {
				System.out.println("Ingresado correctamente");
				login=true;
			}
			return login;
		}

  
  public static void cargarDatos() throws IOException {
	  elAdministrador.actualizarDatos();
	  laPieza.actualizarDatos();
  }


}
