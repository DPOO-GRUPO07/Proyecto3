package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import controller.BaseDatosEmpresa;
import controller.BaseDatosGaleria;
import controller.BaseDatosInventario;
import controller.ControllerCliente;
import controller.ControllerEmpleado;
import model.Compra;
import model.Empleado;


public class InterfazEmpleado {
	public static ControllerEmpleado elEmpleado;
	private static BaseDatosEmpresa datos;
	
	
	public static void correrEmpleado(BaseDatosEmpresa datos, BaseDatosInventario datosInventario, BaseDatosGaleria datosGaleria) throws IOException
	{
		System.out.println("Bienvenido cliente");
		elEmpleado= new ControllerEmpleado(datos);
		InterfazEmpleado.datos = elEmpleado.getDatos();
		boolean inicializacion = false;
		
		boolean continuar = true;
		while (continuar)
		{
			try
			{
				mostrarMenu();
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opcion"));
				
				if (opcion_seleccionada == 1)
					inicializacion = login();
				else if (opcion_seleccionada == 2 && inicializacion == true)
					crearCompra();
				
				else if (opcion_seleccionada == 3 && inicializacion == true)
				{
					actualizarCarro();
				}
				
				else if (opcion_seleccionada == 4 && inicializacion == true)
				{
					cumplimientoFechaCarro();
				}

				else if (opcion_seleccionada == 5)
				{
					cargarDatos();
					System.out.println("Saliendo de la aplicaci�n ...");
					continuar = false;
				}
				else if (inicializacion == false)
				{
					System.out.println("Para poder ejecutar esta opci�n primero debe iniciar sesi�n");
				}
				else
				{
					System.out.println("Por favor seleccione una opci�n v�lida.");
				}
			}
			catch (NumberFormatException e)
			{
				System.out.println("Debe seleccionar uno de los n�meros de las opciones.");
			}
		}
	}


//Actualizar la fecha de un carro que este lista
	
public static void actualizarCarro()
{
	String placa = input("Digite la placa del carro:");
	LocalDateTime fecha = LocalDateTime.now();
	int dias = Integer.parseInt(input("Inserte el n�mero de d�as que necesuta el carro en limpieza o manentenimiento (max 2)"));
	
	
	
}

//Cambiar las fechas de los carros que ya no estan en mantenimiento

private static void cumplimientoFechaCarro()
{
	LocalDateTime fecha = LocalDateTime.now();
	
	
}

//login del usuario y contrase�a
	
public static boolean login()	
{

	String usuario = input("Inserte su usuario: ");
	String contrasena = input ("Ingrese contrase�a: ");
	
	
	if ((InterfazEmpleado.datos.getMapaEmpleados().get(usuario)!= null))
	{
		Empleado empleado= InterfazEmpleado.datos.getMapaEmpleados().get(usuario);
		
		if (empleado.getUsuario().equals(usuario)&& empleado.getContrasena().equals(contrasena))
		{
			return true;
		}
		else { System.out.println("Usuario o contrase�a incorrectos");}
	}
	
	else {System.out.println("Usuario inexistente, por favor vuelva a intentarlo");}
	
	return false;
}

// Crear Alquiler

private static void crearCompra() 
{
	Compra compra;
	String reserva = input("El cliente realizo una compra? (SI/NO)");
	String cliente = input("Ingrese el usuario del cliente: ");
	String pieza = input("Ingrese el titulo de la pieza: ");
	//Verificar si ya existe reserva
	if (reserva.equals("SI"))
	{
		//compra = elEmpleado.crearRegistroPago(pieza, cliente);	
	}
	
	else 
	{
		//compra = elEmpleado.crearCompra(cliente);
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

// Actualizar datos
public static void cargarDatos() throws IOException {
	elEmpleado.actualizarDatos();
}

//Mostrar men�
public static void mostrarMenu()
{
		System.out.println("\nOpciones de la aplicaci�n\n");
		System.out.println("1. LogIn");
		System.out.println("2. Salir de la aplicacion");
}

public static void mostrarMenu1()
{
		System.out.println("\nOpciones del Empleado\n");
		System.out.println("1. Registrar Compra");
		System.out.println("2. ");
		System.out.println("5. LogOut");
}


}