package interfaz;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import controller.BaseDatosEmpresa;
import controller.BaseDatosGaleria;
import controller.BaseDatosInventario;
import model.Usuario;
//import interfaz.VentanaPrincipal;
import view.Interfaz;

public class VentanaInicial extends JFrame {
	private static BaseDatosEmpresa datosEmpresa;
	private static BaseDatosInventario datosInventario;
	private static BaseDatosGaleria datosGaleria;
	
    private JButton clienteButton;
    private JButton empleadoButton;
    private JButton administradorButton;
    private JButton salirButton;
	
	
	public void setDatosEmpresa(BaseDatosEmpresa datos) {
		this.datosEmpresa=datos;
	}
	
	public void setDatosGaleria(BaseDatosGaleria datosGaleria) {
		this.datosGaleria=datosGaleria;
	}
	
	public void setDatosInventario(BaseDatosInventario datosInventario) {
		this.datosInventario=datosInventario;
	}
	
    public VentanaInicial() {
        setTitle("Casa de Ventas y Subastas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        // Panel de título
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.BLUE);
        JLabel titleLabel = new JLabel("BIENVENIDO A LA CASA DE SUBASTAS");
        titleLabel.setForeground(Color.WHITE);
        //titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1, 0, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        clienteButton = new JButton("Ingresar como Cliente");
        empleadoButton = new JButton("Ingresar como Empleado");
        administradorButton = new JButton("Ingresar como Administrador");
        salirButton = new JButton("Salir de la Aplicación");

        clienteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(VentanaInicial.this, "Funcionalidad de Cliente no implementada", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        });

        empleadoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(VentanaInicial.this, "Funcionalidad de Empleado no implementada", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        });

        administradorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VentanaAdministrador administrador = new VentanaAdministrador();
                administrador.setVisible(true);
                dispose(); 
            	//JOptionPane.showMessageDialog(VentanaInicial.this, "Funcionalidad de Administrador no implementada", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        });

        salirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int opcion = JOptionPane.showConfirmDialog(VentanaInicial.this, "¿Estás seguro de que deseas salir?", "Salir de la Aplicación", JOptionPane.YES_NO_OPTION);
                if (opcion == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        buttonPanel.add(clienteButton);
        buttonPanel.add(empleadoButton);
        buttonPanel.add(administradorButton);
        buttonPanel.add(salirButton);

        add(buttonPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
    	datosInventario=Interfaz.cargarDatosInventario();
    	datosGaleria=Interfaz.cargarDatosGaleria();
    	datosEmpresa=Interfaz.cargarDatosEmpresa();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                VentanaInicial ventana = new VentanaInicial();
                ventana.setVisible(true);
            }
        });
    }
    
}