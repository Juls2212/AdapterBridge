package patterns;

import model.Muestra;

public interface EquipoLaboratorio {
    void inicializar();
    void analizarMuestra(Muestra muestra, String tipoAnalisis);
    String obtenerResultado();
    String getNombreEquipo();
    boolean usaAdaptador();
}