package interfaz.Cliente;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.*;

public class InterfazCliente extends JFrame{
	   private JLabel lblWelcome;
	    private JButton btnCompra;
	    private JButton btnConsignacion;
	    private JButton btnSubasta;
	    private JButton btnConsultaPieza;
	    private JButton btnConsultaArtista;

	    public InterfazCliente(String username) {
	        setTitle("Bienvenido");
	        setSize(400, 200);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null);

	        JPanel panel = new JPanel();
	        panel.setLayout(new BorderLayout());

	        lblWelcome = new JLabel("¡Bienvenido, " + username + "!");
	        lblWelcome.setHorizontalAlignment(JLabel.CENTER);
	        panel.add(lblWelcome, BorderLayout.NORTH);

	        JPanel btnPanel = new JPanel(new GridLayout(5, 1));
	        btnCompra = new JButton("Comprar una pieza por precio fijo");
	        btnCompra.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                // Acción cuando se presiona el botón de compra
	            }
	        });
	        btnPanel.add(btnCompra);

	        btnConsignacion = new JButton("Poner en consignación una pieza");
	        btnConsignacion.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                // Acción cuando se presiona el botón de consignación
	            }
	        });
	        btnPanel.add(btnConsignacion);

	        btnSubasta = new JButton("Participar en una subasta");
	        btnSubasta.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                // Acción cuando se presiona el botón de subasta
	            }
	        });
	        btnPanel.add(btnSubasta);

	        btnConsultaPieza = new JButton("Consultar una pieza específica");
	        btnConsultaPieza.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                // Acción cuando se presiona el botón de consulta de pieza
	            }
	        });
	        btnPanel.add(btnConsultaPieza);

	        btnConsultaArtista = new JButton("Consultar sobre un artista");
	        btnConsultaArtista.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                // Acción cuando se presiona el botón de consulta de artista
	            }
	        });
	        btnPanel.add(btnConsultaArtista);

	        panel.add(btnPanel, BorderLayout.CENTER);

	        add(panel);
	    }
}
