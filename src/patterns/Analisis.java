package patterns;

import model.Muestra;

public abstract class Analisis {

    protected ModoProcesamiento processingMode;

    public Analisis(ModoProcesamiento processingMode) {
        this.processingMode = processingMode;
    }

    public abstract String getAnalysisName();

    protected abstract String interpretResult(String baseResult);

    public String process(Muestra sample, EquipoLaboratorio equipment) {

        String baseResult = processingMode.execute(sample, equipment, getAnalysisName());

        return interpretResult(baseResult);
    }

    public String getProcessingModeName() {
        return processingMode.getModeName();
    }

    public static Analisis create(String type, ModoProcesamiento mode) {

        switch (type) {

            case "Hematology":
                return new HematologyAnalysis(mode);

            case "Blood chemistry":
                return new BloodChemistryAnalysis(mode);

            case "Microbiology":
                return new MicrobiologyAnalysis(mode);

            default:
                throw new IllegalArgumentException("Unknown analysis type");
        }
    }
}

class HematologyAnalysis extends Analisis {

    public HematologyAnalysis(ModoProcesamiento mode) {
        super(mode);
    }

    @Override
    public String getAnalysisName() {
        return "Hematology";
    }

    @Override
    protected String interpretResult(String result) {
        return result + "\nInterpretation: Blood cell counts within normal range.";
    }
}

class BloodChemistryAnalysis extends Analisis {

    public BloodChemistryAnalysis(ModoProcesamiento mode) {
        super(mode);
    }

    @Override
    public String getAnalysisName() {
        return "Blood chemistry";
    }

    @Override
    protected String interpretResult(String result) {
        return result + "\nInterpretation: Metabolic parameters normal.";
    }
}

class MicrobiologyAnalysis extends Analisis {

    public MicrobiologyAnalysis(ModoProcesamiento mode) {
        super(mode);
    }

    @Override
    public String getAnalysisName() {
        return "Microbiology";
    }

    @Override
    protected String interpretResult(String result) {
        return result + "\nInterpretation: No microbiological risk detected.";
    }
}