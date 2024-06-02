package interfaz;

import javax.swing.*;

import controller.ControllerAdministrador;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import view.*;


public class VentanaAdministrador extends JFrame {
	
    private ControllerAdministrador elAdministrador;
    
    

    public VentanaAdministrador() {
        setTitle("Ventana de Administrador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        // Panel de inicio de sesión
        JPanel loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel usuarioLabel = new JLabel("Usuario:");
        JTextField usuarioField = new JTextField(15);
        JLabel contrasenaLabel = new JLabel("Contraseña:");
        JPasswordField contrasenaField = new JPasswordField(15);
        JButton loginButton = new JButton("Iniciar Sesión");

        gbc.gridx = 0;
        gbc.gridy = 0;
        loginPanel.add(usuarioLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        loginPanel.add(usuarioField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        loginPanel.add(contrasenaLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        loginPanel.add(contrasenaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(loginButton, gbc);

        add(loginPanel, BorderLayout.CENTER);

        //Inicio de sesion
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuario = usuarioField.getText();
                String contrasena = new String(contrasenaField.getPassword());
                
                try {
                    boolean loginExitoso = elAdministrador.LogIn(usuario, contrasena);

                    if (loginExitoso) {
                        // Crear y mostrar la ventana del menú del administrador
                    	dispose();
                    	cargarMenuAdministrador();
                    	
 
                    } else {
                        JOptionPane.showMessageDialog(VentanaAdministrador.this, "Usuario o contraseña incorrectos", "Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(VentanaAdministrador.this, "Ocurrió un error al iniciar sesión");
                }
            }
        });
        
        elAdministrador = new ControllerAdministrador();
        elAdministrador.setDatosEmpresa(Interfaz.cargarDatosEmpresa());
        elAdministrador.setDatosInventario(Interfaz.cargarDatosInventario());
        elAdministrador.setDatosGaleria(Interfaz.cargarDatosGaleria());


    }

    private void cargarMenuAdministrador() {
        JFrame menuFrame = new JFrame("Menú del Administrador");
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setSize(500, 500);

        // Panel principal del menú
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Título del menú
        JLabel titleLabel = new JLabel("¡Bienvenido, Administrador!");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        menuPanel.add(titleLabel);

        // Botones del menú
        JButton registrarPiezaButton = new JButton("Registrar Pieza");
        JButton confirmarVentaButton = new JButton("Confirmar Venta");
        JButton verificarCompradorButton = new JButton("Verificar Comprador");
        JButton crearSubastaButton = new JButton("Crear Subasta");
        JButton verHistorialButton = new JButton("Ver Historial");
        JButton ventasAnualesButton = new JButton("Cantidad Ventas Anual (Gráfica)");

        // Estilo de los botones
        Dimension buttonSize = new Dimension(300, 40); // Tamaño uniforme para los botones
        registrarPiezaButton.setMaximumSize(buttonSize);
        confirmarVentaButton.setMaximumSize(buttonSize);
        verificarCompradorButton.setMaximumSize(buttonSize);
        crearSubastaButton.setMaximumSize(buttonSize);
        verHistorialButton.setMaximumSize(buttonSize);
        ventasAnualesButton.setMaximumSize(buttonSize);

        // Alineación de los botones
        registrarPiezaButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmarVentaButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        verificarCompradorButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        crearSubastaButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        verHistorialButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        ventasAnualesButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Espacios entre los botones
        menuPanel.add(registrarPiezaButton);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        menuPanel.add(confirmarVentaButton);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        menuPanel.add(verificarCompradorButton);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        menuPanel.add(crearSubastaButton);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        menuPanel.add(verHistorialButton);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        menuPanel.add(ventasAnualesButton);

        // Añadir el panel al marco y hacer visible
        menuFrame.add(menuPanel);
        menuFrame.setVisible(true);
        
        // Acciones de los botones del menú
        registrarPiezaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame agregarPiezaFrame = new JFrame("Agregar Pieza");
                agregarPiezaFrame.setSize(500, 600);
                agregarPiezaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                agregarPiezaFrame.add(new VentanaAgregarPieza(elAdministrador));
                agregarPiezaFrame.setVisible(true);
            }
        });

        confirmarVentaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userName = JOptionPane.showInputDialog(VentanaAdministrador.this, "Ingrese el usuario del comprador:", "Confirmar Venta", JOptionPane.QUESTION_MESSAGE);
                if (userName == null || userName.isEmpty()) {
                    JOptionPane.showMessageDialog(VentanaAdministrador.this, "El usuario del comprador es requerido.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String tituloPieza = JOptionPane.showInputDialog(VentanaAdministrador.this, "Ingrese el título de la pieza:", "Confirmar Venta", JOptionPane.QUESTION_MESSAGE);
                if (tituloPieza == null || tituloPieza.isEmpty()) {
                    JOptionPane.showMessageDialog(VentanaAdministrador.this, "El título de la pieza es requerido.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
					if (elAdministrador.ConfirmarVenta(userName, tituloPieza)) {
					    JOptionPane.showMessageDialog(VentanaAdministrador.this, "La venta del comprador " + userName + " fue aceptada.", "Venta Confirmada", JOptionPane.INFORMATION_MESSAGE);
					} else {
					    JOptionPane.showMessageDialog(VentanaAdministrador.this, "La confirmación no fue aceptada.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });

        verificarCompradorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userName = JOptionPane.showInputDialog(VentanaAdministrador.this, "Ingrese el nombre de usuario del cliente que va entrar a la subasta:", "Verificar Comprador", JOptionPane.QUESTION_MESSAGE);
                if (userName == null || userName.isEmpty()) {
                    JOptionPane.showMessageDialog(VentanaAdministrador.this, "El nombre de usuario es requerido.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                boolean estadoVerificacion = elAdministrador.verificarComprador(userName);
                if (estadoVerificacion) {
                    JOptionPane.showMessageDialog(VentanaAdministrador.this, "El usuario " + userName + " fue verificado exitosamente.", "Verificación Exitosa", JOptionPane.INFORMATION_MESSAGE);
                    int maximoCompras = Integer.parseInt(JOptionPane.showInputDialog(VentanaAdministrador.this, "Ingrese el valor máximo de compras para el comprador:", "Ajustar Máximo de Compras", JOptionPane.QUESTION_MESSAGE));
                    boolean maximoValue=elAdministrador.maximoCompras(userName, maximoCompras);
                    if (maximoValue) {
                    	JOptionPane.showMessageDialog(VentanaAdministrador.this, "El usuario " + userName + " fue verificado.", "Usuario Confirmado", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(VentanaAdministrador.this, "El usuario no está registrado en el sistema y no se puede verificar.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        crearSubastaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(VentanaAdministrador.this, "Para crear una subasta se debe ingresar una Pieza y asignar un valor mínimo y un valor inicial.", "Crear Subasta", JOptionPane.INFORMATION_MESSAGE);
                String tituloPieza = JOptionPane.showInputDialog(VentanaAdministrador.this, "Ingrese el título de la pieza que va a ingresar a la subasta:", "Crear Subasta", JOptionPane.QUESTION_MESSAGE);
                if (tituloPieza == null || tituloPieza.isEmpty()) {
                    JOptionPane.showMessageDialog(VentanaAdministrador.this, "El título de la pieza es requerido.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String valorMinimo = JOptionPane.showInputDialog(VentanaAdministrador.this, "Ingrese el valor mínimo que debe tener la pieza:", "Crear Subasta", JOptionPane.QUESTION_MESSAGE);
                if (valorMinimo == null || valorMinimo.isEmpty()) {
                    JOptionPane.showMessageDialog(VentanaAdministrador.this, "El valor mínimo es requerido.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String valorInicial = JOptionPane.showInputDialog(VentanaAdministrador.this, "Ingrese el valor inicial (este es el valor que se va a incrementar):", "Crear Subasta", JOptionPane.QUESTION_MESSAGE);
                if (valorInicial == null || valorInicial.isEmpty()) {
                    JOptionPane.showMessageDialog(VentanaAdministrador.this, "El valor inicial es requerido.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                boolean estadoVerificacion;
				try {
					estadoVerificacion = elAdministrador.crearSubasta(tituloPieza, valorMinimo, valorInicial);
	                if (estadoVerificacion) {
	                    JOptionPane.showMessageDialog(VentanaAdministrador.this, "Subasta creada exitosamente.", "Subasta Creada", JOptionPane.INFORMATION_MESSAGE);
	                } else {
	                    JOptionPane.showMessageDialog(VentanaAdministrador.this, "No se pudo crear la subasta. Verifique los datos ingresados.", "Error", JOptionPane.ERROR_MESSAGE);
	                }
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

            }
        });

        verHistorialButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuario = JOptionPane.showInputDialog(VentanaAdministrador.this, "Ingrese el nombre de usuario del cliente para ver su historial de compras:", "Ver Historial", JOptionPane.QUESTION_MESSAGE);
                if (usuario == null || usuario.isEmpty()) {
                    JOptionPane.showMessageDialog(VentanaAdministrador.this, "El nombre de usuario es requerido.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                List<String> historialCompras = elAdministrador.historialComprasCliente(usuario);

                if (historialCompras.isEmpty()) {
                    JOptionPane.showMessageDialog(VentanaAdministrador.this, "El cliente no tiene historial de compras.", "Historial de Compras", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Crear y configurar la tabla
                    String[] columnas = {"Fecha", "Título", "Precio"};
                    Object[][] data = new Object[historialCompras.size()][3];
                    int index = 0;
                    for (String compra : historialCompras) {
                        String[] detallesCompra = compra.split(",");
                        data[index][0] = detallesCompra[0]; // Fecha
                        data[index][1] = detallesCompra[1]; // Título
                        data[index][2] = detallesCompra[2]; // Precio
                        index++;
                    }

                    JTable table = new JTable(data, columnas);
                    JScrollPane scrollPane = new JScrollPane(table);

                    // Mostrar la tabla en un cuadro de diálogo
                    JOptionPane.showMessageDialog(VentanaAdministrador.this, scrollPane, "Historial de Compras para " + usuario, JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        ventasAnualesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DatosVentasAnuales datos = new DatosVentasAnuales();
                datos.cargarDatosDesdeArchivo("./data/ventasAnuales.txt");

                VentanaGraficoVentas ventana = new VentanaGraficoVentas(datos);
                ventana.mostrarVentana();
            }
        });
      
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                VentanaAdministrador ventana = new VentanaAdministrador();
                ventana.setVisible(true);
            }
        });
    }
}
