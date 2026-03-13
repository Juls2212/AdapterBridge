package patterns;

import model.Muestra;

public class Adaptadores {

    public static EquipoLaboratorio createEquipment(String equipmentType) {

        switch (equipmentType) {

            case "Modern equipment":
                return new ModernEquipment();

            case "Legacy hematology machine":
                return new LegacyHematologyAdapter(new LegacyHematologyMachine());

            case "External microbiology device":
                return new ExternalMicrobiologyAdapter(new ExternalMicrobiologyDevice());

            default:
                throw new IllegalArgumentException("Unknown equipment type");
        }
    }
}

class ModernEquipment implements EquipoLaboratorio {

    private String result;

    @Override
    public void initialize() {
        result = "";
    }

    @Override
    public void analyzeSample(Muestra sample, String analysisType) {
        result = "Modern equipment processed sample " + sample.getCode() +
                " for " + analysisType + " analysis.";
    }

    @Override
    public String getResult() {
        return result;
    }

    @Override
    public String getEquipmentName() {
        return "Modern equipment";
    }

    @Override
    public boolean usesAdapter() {
        return false;
    }
}

class LegacyHematologyAdapter implements EquipoLaboratorio {

    private LegacyHematologyMachine machine;

    public LegacyHematologyAdapter(LegacyHematologyMachine machine) {
        this.machine = machine;
    }

    @Override
    public void initialize() {
        machine.bootLegacySystem();
    }

    @Override
    public void analyzeSample(Muestra sample, String analysisType) {
        machine.runBloodTest(sample.getCode());
    }

    @Override
    public String getResult() {
        return machine.fetchLegacyReport();
    }

    @Override
    public String getEquipmentName() {
        return "Legacy hematology machine";
    }

    @Override
    public boolean usesAdapter() {
        return true;
    }
}

class ExternalMicrobiologyAdapter implements EquipoLaboratorio {

    private ExternalMicrobiologyDevice device;

    public ExternalMicrobiologyAdapter(ExternalMicrobiologyDevice device) {
        this.device = device;
    }

    @Override
    public void initialize() {
        device.connectDevice();
    }

    @Override
    public void analyzeSample(Muestra sample, String analysisType) {
        device.startCultureProcess(sample.getCode());
    }

    @Override
    public String getResult() {
        return device.exportResultsFile();
    }

    @Override
    public String getEquipmentName() {
        return "External microbiology device";
    }

    @Override
    public boolean usesAdapter() {
        return true;
    }
}