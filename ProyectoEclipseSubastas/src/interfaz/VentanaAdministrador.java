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
                // Lógica para registrar una pieza en el inventario
            }
        });

        confirmarVentaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para confirmar una venta
            }
        });

        verificarCompradorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para verificar un comprador
            }
        });

        crearSubastaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para crear una subasta
            }
        });

        verHistorialButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para ver el historial de compras
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
