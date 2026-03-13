package src.patterns;

import src.model.Muestra;

public interface ModoProcesamiento {
    String ejecutar(Muestra muestra, EquipoLaboratorio equipo, String tipoAnalisis);
    String getNombreModo();

    static ModoProcesamiento crear(String modo) {
        if (modo == null) {
            throw new IllegalArgumentException("Debe seleccionar un modo de procesamiento.");
        }

        switch (modo) {
            case "Automático":
                return new ProcesamientoAutomatico();
            case "Manual supervisado":
                return new ProcesamientoManual();
            case "Urgente":
                return new ProcesamientoUrgente();
            default:
                throw new IllegalArgumentException("Modo no reconocido: " + modo);
        }
    }
}

class ProcesamientoAutomatico implements ModoProcesamiento {
    @Override
    public String ejecutar(Muestra muestra, EquipoLaboratorio equipo, String tipoAnalisis) {
        equipo.inicializar();
        equipo.analizarMuestra(muestra, tipoAnalisis);
        return "Procesamiento automático completado.\n" + equipo.obtenerResultado();
    }

    @Override
    public String getNombreModo() {
        return "Automático";
    }
}

class ProcesamientoManual implements ModoProcesamiento {
    @Override
    public String ejecutar(Muestra muestra, EquipoLaboratorio equipo, String tipoAnalisis) {
        equipo.inicializar();
        equipo.analizarMuestra(muestra, tipoAnalisis);
        return "Procesamiento manual supervisado completado.\nVerificación del analista realizada.\n"
                + equipo.obtenerResultado();
    }

    @Override
    public String getNombreModo() {
        return "Manual supervisado";
    }
}

class ProcesamientoUrgente implements ModoProcesamiento {
    @Override
    public String ejecutar(Muestra muestra, EquipoLaboratorio equipo, String tipoAnalisis) {
        equipo.inicializar();
        equipo.analizarMuestra(muestra, tipoAnalisis);
        return "Procesamiento urgente completado con prioridad alta.\n" + equipo.obtenerResultado();
    }

    @Override
    public String getNombreModo() {
        return "Urgente";
    }
}