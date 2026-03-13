package patterns;

import model.Muestra;

public interface EquipoLaboratorio {

    void initialize();

    void analyzeSample(Muestra sample, String analysisType);

    String getResult();

    String getEquipmentName();

    boolean usesAdapter();
}