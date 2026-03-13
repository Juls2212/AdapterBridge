package service;

import model.Muestra;
import patterns.*;

public class LaboratorioService {

    public Muestra registerSample(String code, String patient, String sampleType, String priority) {

        if (code.isEmpty() || patient.isEmpty()) {
            throw new IllegalArgumentException("Sample code and patient are required.");
        }

        return new Muestra(code, patient, sampleType, priority);
    }

    public String processSample(
            Muestra sample,
            String analysisType,
            String mode,
            String equipmentType) {

        ModoProcesamiento processingMode = ModoProcesamiento.create(mode);

        Analisis analysis = Analisis.create(analysisType, processingMode);

        EquipoLaboratorio equipment = Adaptadores.createEquipment(equipmentType);

        sample.updateStatus("Processing");

        String result = analysis.process(sample, equipment);

        sample.updateStatus("Completed");

        StringBuilder report = new StringBuilder();

        report.append("LABORATORY REPORT\n\n");
        report.append(sample.getSummary()).append("\n\n");

        report.append("Analysis: ").append(analysis.getAnalysisName()).append("\n");
        report.append("Processing mode: ").append(analysis.getProcessingModeName()).append("\n");
        report.append("Equipment: ").append(equipment.getEquipmentName()).append("\n\n");

        report.append(result).append("\n\n");

        report.append("PATTERNS USED\n");
        report.append("Bridge Pattern: analysis separated from processing mode.\n");

        if (equipment.usesAdapter()) {
            report.append("Adapter Pattern: external equipment adapted to system interface.\n");
        } else {
            report.append("Adapter Pattern: not required for modern equipment.\n");
        }

        return report.toString();
    }
}