package patterns;

import model.Muestra;

public interface ModoProcesamiento {

    String execute(Muestra sample, EquipoLaboratorio equipment, String analysisType);

    String getModeName();

    static ModoProcesamiento create(String mode) {

        switch (mode) {

            case "Automatic":
                return new AutomaticProcessing();

            case "Manual supervised":
                return new ManualProcessing();

            case "Urgent":
                return new UrgentProcessing();

            default:
                throw new IllegalArgumentException("Unknown processing mode");
        }
    }
}

class AutomaticProcessing implements ModoProcesamiento {

    @Override
    public String execute(Muestra sample, EquipoLaboratorio equipment, String analysisType) {

        equipment.initialize();
        equipment.analyzeSample(sample, analysisType);

        return "Automatic processing completed.\n" + equipment.getResult();
    }

    @Override
    public String getModeName() {
        return "Automatic";
    }
}

class ManualProcessing implements ModoProcesamiento {

    @Override
    public String execute(Muestra sample, EquipoLaboratorio equipment, String analysisType) {

        equipment.initialize();
        equipment.analyzeSample(sample, analysisType);

        return "Manual supervised processing completed.\n" +
                equipment.getResult();
    }

    @Override
    public String getModeName() {
        return "Manual supervised";
    }
}

class UrgentProcessing implements ModoProcesamiento {

    @Override
    public String execute(Muestra sample, EquipoLaboratorio equipment, String analysisType) {

        equipment.initialize();
        equipment.analyzeSample(sample, analysisType);

        return "Urgent processing completed.\n" + equipment.getResult();
    }

    @Override
    public String getModeName() {
        return "Urgent";
    }
}