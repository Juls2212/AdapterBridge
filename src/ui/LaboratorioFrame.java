package ui;

import model.Muestra;
import service.LaboratorioService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LaboratorioFrame extends JFrame {

    private final LaboratorioService service;
    private Muestra currentSample;

    private JTextField txtCode;
    private JTextField txtPatient;
    private JTextField txtSampleType;

    private JComboBox<String> cbPriority;
    private JComboBox<String> cbAnalysis;
    private JComboBox<String> cbProcessingMode;
    private JComboBox<String> cbEquipment;

    private JTextArea resultArea;

    public LaboratorioFrame() {
        this.service = new LaboratorioService();
        initializeWindow();
        initializeComponents();
    }

    private void initializeWindow() {
        setTitle("Clinical Sample Processing System");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
    }

    private void initializeComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(new Color(245, 247, 250));

        JLabel titleLabel = new JLabel("Clinical Laboratory - Bridge and Adapter Demo", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(new Color(33, 37, 41));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 15, 15));
        centerPanel.setOpaque(false);

        centerPanel.add(createFormPanel());
        centerPanel.add(createResultPanel());

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);
    }

    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder("Registration and Processing"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        txtCode = new JTextField();
        txtPatient = new JTextField();
        txtSampleType = new JTextField();

        cbPriority = new JComboBox<>(new String[]{"Normal", "High", "Critical"});
        cbAnalysis = new JComboBox<>(new String[]{"Hematology", "Blood chemistry", "Microbiology"});
        cbProcessingMode = new JComboBox<>(new String[]{"Automatic", "Manual supervised", "Urgent"});
        cbEquipment = new JComboBox<>(new String[]{
                "Modern equipment",
                "Legacy hematology machine",
                "External microbiology device"
        });

        int row = 0;
        addField(panel, gbc, row++, "Sample code:", txtCode);
        addField(panel, gbc, row++, "Patient:", txtPatient);
        addField(panel, gbc, row++, "Sample type:", txtSampleType);
        addField(panel, gbc, row++, "Priority:", cbPriority);
        addField(panel, gbc, row++, "Analysis type:", cbAnalysis);
        addField(panel, gbc, row++, "Processing mode:", cbProcessingMode);
        addField(panel, gbc, row++, "Equipment:", cbEquipment);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        buttonPanel.setOpaque(false);

        JButton btnRegister = new JButton("Register Sample");
        JButton btnProcess = new JButton("Process");
        JButton btnClear = new JButton("Clear");

        btnRegister.addActionListener(e -> registerSample());
        btnProcess.addActionListener(e -> processSample());
        btnClear.addActionListener(e -> clearForm());

        buttonPanel.add(btnRegister);
        buttonPanel.add(btnProcess);
        buttonPanel.add(btnClear);

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);

        return panel;
    }

    private JPanel createResultPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder("System Output"));

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setText("The laboratory report will appear here...\n");

        JScrollPane scrollPane = new JScrollPane(resultArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private void addField(JPanel panel, GridBagConstraints gbc, int row, String labelText, JComponent component) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        panel.add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        panel.add(component, gbc);
    }

    private void registerSample() {
        try {
            currentSample = service.registerSample(
                    txtCode.getText().trim(),
                    txtPatient.getText().trim(),
                    txtSampleType.getText().trim(),
                    cbPriority.getSelectedItem().toString()
            );

            resultArea.setText("Sample registered successfully.\n\n" + currentSample.getSummary());
            JOptionPane.showMessageDialog(this, "Sample registered successfully.");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void processSample() {
        try {
            String report = service.processSample(
                    currentSample,
                    cbAnalysis.getSelectedItem().toString(),
                    cbProcessingMode.getSelectedItem().toString(),
                    cbEquipment.getSelectedItem().toString()
            );

            resultArea.setText(report);
            JOptionPane.showMessageDialog(this, "Sample processed successfully.");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "System Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        txtCode.setText("");
        txtPatient.setText("");
        txtSampleType.setText("");
        cbPriority.setSelectedIndex(0);
        cbAnalysis.setSelectedIndex(0);
        cbProcessingMode.setSelectedIndex(0);
        cbEquipment.setSelectedIndex(0);
        resultArea.setText("The laboratory report will appear here...\n");
        currentSample = null;
    }
}