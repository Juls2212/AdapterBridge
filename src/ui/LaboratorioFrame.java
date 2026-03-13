package src.ui;

import src.model.Muestra;
import src.service.LaboratorioService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LaboratorioFrame extends JFrame {
    private final LaboratorioService service;
    private Muestra muestraActual;

    private JTextField txtCodigo;
    private JTextField txtPaciente;
    private JTextField txtTipoMuestra;

    private JComboBox<String> cbPrioridad;
    private JComboBox<String> cbAnalisis;
    private JComboBox<String> cbModo;
    private JComboBox<String> cbEquipo;

    private JTextArea areaResultado;

    public LaboratorioFrame() {
        this.service = new LaboratorioService();
        inicializarVentana();
        inicializarComponentes();
    }

    private void inicializarVentana() {
        setTitle("Sistema de Procesamiento de Muestras Clínicas");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
    }

    private void inicializarComponentes() {
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(new EmptyBorder(15, 15, 15, 15));
        panelPrincipal.setBackground(new Color(245, 247, 250));

        JLabel titulo = new JLabel("Laboratorio Clínico - Demo de Bridge y Adapter", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setForeground(new Color(33, 37, 41));
        panelPrincipal.add(titulo, BorderLayout.NORTH);

        JPanel centro = new JPanel(new GridLayout(1, 2, 15, 15));
        centro.setOpaque(false);

        centro.add(crearPanelFormulario());
        centro.add(crearPanelResultados());

        panelPrincipal.add(centro, BorderLayout.CENTER);
        add(panelPrincipal, BorderLayout.CENTER);
    }

    private JPanel crearPanelFormulario() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder("Registro y procesamiento"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        txtCodigo = new JTextField();
        txtPaciente = new JTextField();
        txtTipoMuestra = new JTextField();

        cbPrioridad = new JComboBox<>(new String[]{"Normal", "Alta", "Crítica"});
        cbAnalisis = new JComboBox<>(new String[]{"Hematología", "Química sanguínea", "Microbiología"});
        cbModo = new JComboBox<>(new String[]{"Automático", "Manual supervisado", "Urgente"});
        cbEquipo = new JComboBox<>(new String[]{"Equipo moderno", "Equipo legado hematológico", "Equipo externo microbiológico"});

        int y = 0;
        agregarCampo(panel, gbc, y++, "Código de muestra:", txtCodigo);
        agregarCampo(panel, gbc, y++, "Paciente:", txtPaciente);
        agregarCampo(panel, gbc, y++, "Tipo de muestra:", txtTipoMuestra);
        agregarCampo(panel, gbc, y++, "Prioridad:", cbPrioridad);
        agregarCampo(panel, gbc, y++, "Tipo de análisis:", cbAnalisis);
        agregarCampo(panel, gbc, y++, "Modo de procesamiento:", cbModo);
        agregarCampo(panel, gbc, y++, "Equipo:", cbEquipo);

        JPanel panelBotones = new JPanel(new GridLayout(1, 3, 10, 0));
        panelBotones.setOpaque(false);

        JButton btnRegistrar = new JButton("Registrar muestra");
        JButton btnProcesar = new JButton("Procesar");
        JButton btnLimpiar = new JButton("Limpiar");

        btnRegistrar.addActionListener(e -> registrarMuestra());
        btnProcesar.addActionListener(e -> procesarMuestra());
        btnLimpiar.addActionListener(e -> limpiarFormulario());

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnProcesar);
        panelBotones.add(btnLimpiar);

        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 2;
        panel.add(panelBotones, gbc);

        return panel;
    }

    private JPanel crearPanelResultados() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder("Salida del sistema"));

        areaResultado = new JTextArea();
        areaResultado.setEditable(false);
        areaResultado.setFont(new Font("Monospaced", Font.PLAIN, 13));
        areaResultado.setLineWrap(true);
        areaResultado.setWrapStyleWord(true);
        areaResultado.setText("Aquí aparecerá el reporte del laboratorio...\n");

        JScrollPane scrollPane = new JScrollPane(areaResultado);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private void agregarCampo(JPanel panel, GridBagConstraints gbc, int fila, String etiqueta, JComponent componente) {
        gbc.gridx = 0;
        gbc.gridy = fila;
        gbc.gridwidth = 1;
        panel.add(new JLabel(etiqueta), gbc);

        gbc.gridx = 1;
        panel.add(componente, gbc);
    }

    private void registrarMuestra() {
        try {
            muestraActual = service.registrarMuestra(
                    txtCodigo.getText(),
                    txtPaciente.getText(),
                    txtTipoMuestra.getText(),
                    cbPrioridad.getSelectedItem().toString()
            );

            areaResultado.setText("Muestra registrada correctamente.\n\n" + muestraActual.getResumen());
            JOptionPane.showMessageDialog(this, "Muestra registrada con éxito.");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de validación", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void procesarMuestra() {
        try {
            String reporte = service.procesarMuestra(
                    muestraActual,
                    cbAnalisis.getSelectedItem().toString(),
                    cbModo.getSelectedItem().toString(),
                    cbEquipo.getSelectedItem().toString()
            );

            areaResultado.setText(reporte);
            JOptionPane.showMessageDialog(this, "Muestra procesada correctamente.");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error del sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarFormulario() {
        txtCodigo.setText("");
        txtPaciente.setText("");
        txtTipoMuestra.setText("");
        cbPrioridad.setSelectedIndex(0);
        cbAnalisis.setSelectedIndex(0);
        cbModo.setSelectedIndex(0);
        cbEquipo.setSelectedIndex(0);
        areaResultado.setText("Aquí aparecerá el reporte del laboratorio...\n");
        muestraActual = null;
    }
}