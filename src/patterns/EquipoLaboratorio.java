package src.patterns;

import src.model.Muestra;

public interface EquipoLaboratorio {
    void inicializar();
    void analizarMuestra(Muestra muestra, String tipoAnalisis);
    String obtenerResultado();
    String getNombreEquipo();
    boolean usaAdaptador();
}