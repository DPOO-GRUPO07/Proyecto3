package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
	
	   private List<Cliente> clientes;

	    public ClientService() {
	        clientes = new ArrayList<>();
	        cargarClientes();
	    }

	    private void cargarClientes() {
	        try (BufferedReader br = new BufferedReader(new FileReader("data/clientes.txt"))) {
	            String linea;
	            while ((linea = br.readLine()) != null) {
	                String[] partes = linea.split(";");
	                if (partes.length == 5) {
	                    String username = partes[0];
	                    String password = partes[1];
	                    String nombreCompleto = partes[2];
	                    int maxCompras = Integer.parseInt(partes[3]);
	                    boolean validado = Boolean.parseBoolean(partes[4]);
	                    clientes.add(new Cliente(username, password, nombreCompleto, maxCompras, validado));
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public Cliente buscarCliente(String username, String password) {
	        for (Cliente cliente : clientes) {
	            if (cliente.getUsuario().equals(username) && cliente.getContrasena().equals(password)) {
	                return cliente;
	            }
	        }
	        return null;
	    }
}
