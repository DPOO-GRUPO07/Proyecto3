package interfaz;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.ControllerAdministrador;
import pieza.PiezaEscultura;
import pieza.PiezaFotografia;
import pieza.PiezaPintura;
import pieza.PiezaVideo;

public class VentanaAgregarPieza extends JPanel implements ActionListener {

    private ControllerAdministrador elAdministrador;
    private JTextField tituloField, anoField, lugarField, artistaField, propietarioField, valorFijoField, tipoField;
    private JPanel esculturaPanel, pinturaPanel, fotografiaPanel, videoPanel;
    private JButton agregarPiezaButton;
    private JPanel mainPanel;

    public VentanaAgregarPieza(ControllerAdministrador elAdministrador) {
        this.elAdministrador = elAdministrador;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Título centrado
        JLabel titleLabel = new JLabel("Registrar nueva pieza");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Panel principal
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        add(mainPanel, BorderLayout.CENTER);

        // Campos para agregar una pieza
        tituloField = new JTextField(10);
        anoField = new JTextField(10);
        lugarField = new JTextField(10);
        artistaField = new JTextField(10);
        propietarioField = new JTextField(10);
        valorFijoField = new JTextField(10);
        tipoField = new JTextField(10);

        addFormField("Título:", tituloField);
        addFormField("Año:", anoField);
        addFormField("Lugar de creación:", lugarField);
        addFormField("Artista:", artistaField);
        addFormField("Propietario:", propietarioField);
        addFormField("Valor fijo:", valorFijoField);
        addFormField("Tipo de pieza (escultura, pintura, fotografía, video):", tipoField);

        // Paneles adicionales para tipos específicos de piezas
        esculturaPanel = createEsculturaPanel();
        pinturaPanel = createPanelWithFields("Técnica:", "Dimensiones:");
        fotografiaPanel = createPanelWithFields("Tipo de fotografía:", "Resolución:");
        videoPanel = createPanelWithFields("Duración:", "Formato:");

        mainPanel.add(esculturaPanel);
        mainPanel.add(pinturaPanel);
        mainPanel.add(fotografiaPanel);
        mainPanel.add(videoPanel);

        // Inicialmente ocultamos los paneles específicos
        esculturaPanel.setVisible(false);
        pinturaPanel.setVisible(false);
        fotografiaPanel.setVisible(false);
        videoPanel.setVisible(false);

        // Botón "Agregar Pieza"
        agregarPiezaButton = new JButton("Agregar Pieza");
        agregarPiezaButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        agregarPiezaButton.addActionListener(this);

        // Panel para centrar el botón
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(agregarPiezaButton);
        buttonPanel.add(Box.createHorizontalGlue());

        // Agregar el panel del botón al final
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(buttonPanel);

        tipoField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String tipo = tipoField.getText().toLowerCase();
                updateVisibility(tipo);
            }
        });
    }

    private void addFormField(String label, JTextField field) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        JLabel labelField = new JLabel(label);
        labelField.setPreferredSize(new Dimension(150, 30)); // Tamaño preferido del label
        labelField.setHorizontalAlignment(SwingConstants.LEFT);
        field.setPreferredSize(new Dimension(200, 25)); // Tamaño preferido del campo
        panel.add(labelField);
        panel.add(Box.createRigidArea(new Dimension(10, 0))); // Espacio entre label y campo
        panel.add(field);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT); // Alinear el panel a la izquierda
        mainPanel.add(panel);
    }

    private JPanel createPanelWithFields(String label1, String label2) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        addFormFieldToPanel(panel, label1, new JTextField(10));
        addFormFieldToPanel(panel, label2, new JTextField(10));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT); // Alinear el panel a la izquierda
        return panel;
    }

    private JPanel createEsculturaPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        addFormFieldToPanel(panel, "Alto:", new JTextField(10));
        addFormFieldToPanel(panel, "Ancho:", new JTextField(10));
        addFormFieldToPanel(panel, "Profundidad:", new JTextField(10));
        addFormFieldToPanel(panel, "Material:", new JTextField(10));
        addFormFieldToPanel(panel, "Peso:", new JTextField(10));
        addFormFieldToPanel(panel, "Necesita Electricidad (true/false):", new JTextField(10));
        addFormFieldToPanel(panel, "Detalles de Instalación:", new JTextField(10));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT); // Alinear el panel a la izquierda
        return panel;
    }
    
    private JPanel createPinturaPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        addFormFieldToPanel(panel, "Tipo de Pintura:", new JTextField(10));
        addFormFieldToPanel(panel, "Dimensiones:", new JTextField(10));
        addFormFieldToPanel(panel, "Detalles:", new JTextField(10));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT); // Alinear el panel a la izquierda
        return panel;
    }

    private JPanel createFotografiaPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        addFormFieldToPanel(panel, "Alto:", new JTextField(10));
        addFormFieldToPanel(panel, "Largo:", new JTextField(10));
        addFormFieldToPanel(panel, "Color:", new JTextField(10));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT); // Alinear el panel a la izquierda
        return panel;
    }

    private JPanel createVideoPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        addFormFieldToPanel(panel, "Duración:", new JTextField(10));
        addFormFieldToPanel(panel, "Formato:", new JTextField(10));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT); // Alinear el panel a la izquierda
        return panel;
    }

    private void addFormFieldToPanel(JPanel panel, String label, JTextField field) {
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.X_AXIS));
        JLabel labelField = new JLabel(label);
        labelField.setPreferredSize(new Dimension(150, 30)); // Tamaño preferido del label
        labelField.setHorizontalAlignment(SwingConstants.LEFT);
        field.setPreferredSize(new Dimension(200, 25)); // Tamaño preferido del campo
        fieldPanel.add(labelField);
        fieldPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Espacio entre label y campo
        fieldPanel.add(field);
        fieldPanel.setAlignmentX(Component.LEFT_ALIGNMENT); // Alinear el panel a la izquierda
        panel.add(fieldPanel);
    }

    private void updateVisibility(String tipo) {
        esculturaPanel.setVisible(tipo.equals("escultura"));
        pinturaPanel.setVisible(tipo.equals("pintura"));
        fotografiaPanel.setVisible(tipo.equals("fotografía"));
        videoPanel.setVisible(tipo.equals("video"));
        agregarPiezaButton.setVisible(true); // Asegurarse de que el botón siempre esté visible
        revalidate();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String titulo = tituloField.getText();
        int ano = Integer.parseInt(anoField.getText());
        String lugar = lugarField.getText();
        String artista = artistaField.getText();
        String propietario = propietarioField.getText();
        int valorFijo = Integer.parseInt(valorFijoField.getText());
        String tipo = tipoField.getText().toLowerCase();

        // Aquí agregarías la lógica para guardar la pieza en el inventario según el tipo
        switch (tipo) {
            case "escultura":
                int alto = Integer.parseInt(((JTextField) ((JPanel) esculturaPanel.getComponent(0)).getComponent(2)).getText());
                int ancho = Integer.parseInt(((JTextField) ((JPanel) esculturaPanel.getComponent(1)).getComponent(2)).getText());
                int profundidad = Integer.parseInt(((JTextField) ((JPanel) esculturaPanel.getComponent(2)).getComponent(2)).getText());
                String material = ((JTextField) ((JPanel) esculturaPanel.getComponent(3)).getComponent(2)).getText();
                int peso = Integer.parseInt(((JTextField) ((JPanel) esculturaPanel.getComponent(4)).getComponent(2)).getText());
                boolean necesitaElectricidad = Boolean.parseBoolean(((JTextField) ((JPanel) esculturaPanel.getComponent(5)).getComponent(2)).getText());
                String detallesInstalacion = ((JTextField) ((JPanel) esculturaPanel.getComponent(6)).getComponent(2)).getText();
                // Guardar escultura
	            PiezaEscultura escultura = new PiezaEscultura(titulo, ano, artista, lugar, propietario,valorFijo, true, tipo, false, alto, ancho, profundidad, material, peso, necesitaElectricidad, detallesInstalacion);
	            elAdministrador.agregarPiezaAInventario(escultura);
                break;
            case "pintura":
                String tipoPintura = ((JTextField) ((JPanel) pinturaPanel.getComponent(0)).getComponent(2)).getText();
                String dimensiones = ((JTextField) ((JPanel) pinturaPanel.getComponent(1)).getComponent(2)).getText();
                String detallesPintura = ((JTextField) ((JPanel) pinturaPanel.getComponent(2)).getComponent(2)).getText();
                // Lógica para guardar una pintura
                PiezaPintura pintura = new PiezaPintura(titulo, ano, lugar, propietario, null, valorFijo, true, tipo, false, tipoPintura, dimensiones, detallesPintura);
	            elAdministrador.agregarPiezaAInventario(pintura);
                break;
            case "fotografia":
                int altoFoto = Integer.parseInt(((JTextField) ((JPanel) fotografiaPanel.getComponent(0)).getComponent(2)).getText());
                int largo = Integer.parseInt(((JTextField) ((JPanel) fotografiaPanel.getComponent(1)).getComponent(2)).getText());
                String color = ((JTextField) ((JPanel) fotografiaPanel.getComponent(2)).getComponent(2)).getText();
                PiezaFotografia fotografia = new PiezaFotografia(titulo, ano, lugar, propietario, null, valorFijo, true, tipo, false, altoFoto, largo, color);
	            elAdministrador.agregarPiezaAInventario(fotografia);
                break;
            case "video":
                float duracion = Float.parseFloat(((JTextField) ((JPanel) videoPanel.getComponent(0)).getComponent(2)).getText());
                String formato = ((JTextField) ((JPanel) videoPanel.getComponent(1)).getComponent(2)).getText();
                // Lógica para guardar un video
                PiezaVideo video = new PiezaVideo(titulo, ano, lugar, propietario, null, valorFijo, true, tipo, false, duracion, formato);
	            elAdministrador.agregarPiezaAInventario(video);
                break;
            default:
                JOptionPane.showMessageDialog(this, "Tipo de pieza no válido", "Error", JOptionPane.ERROR_MESSAGE);
                return;
        }

        JOptionPane.showMessageDialog(this, "Pieza agregada exitosamente");
    }
}