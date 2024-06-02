package controller;

import javax.swing.JOptionPane;

import interfaz.Cliente.InterfazCliente;
import model.ClientService;
import model.Cliente;

public class LoginController {
	
	   private ClientService clienteService;

	    public LoginController() {
	        clienteService = new ClientService();
	    }

	    public void login(String username, String password) {
	        Cliente cliente = clienteService.buscarCliente(username, password);
	        if (cliente != null) {                  
	        InterfazCliente welcomeWindow = new InterfazCliente(cliente.getNombre());
            welcomeWindow.setVisible(true);

	        } else {
	            JOptionPane.showMessageDialog(null,
	                    "Username o Password incorrecto", "Login Fallido", JOptionPane.ERROR_MESSAGE);
	        }
	    }
}
