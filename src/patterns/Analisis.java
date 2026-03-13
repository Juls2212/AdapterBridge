package patterns;

import model.Muestra;

public abstract class Analisis {
    protected ModoProcesamiento modoProcesamiento;

    public Analisis(ModoProcesamiento modoProcesamiento) {
        this.modoProcesamiento = modoProcesamiento;
    }

    public abstract String getNombreAnalisis();

    protected abstract String generarInterpretacion(String resultadoBase);

    public String procesar(Muestra muestra, EquipoLaboratorio equipo) {
        String resultadoBase = modoProcesamiento.ejecutar(muestra, equipo, getNombreAnalisis());
        return generarInterpretacion(resultadoBase);
    }

    public String getNombreModoProcesamiento() {
        return modoProcesamiento.getNombreModo();
    }

    public static Analisis crear(String tipoAnalisis, ModoProcesamiento modoProcesamiento) {
        if (tipoAnalisis == null) {
            throw new IllegalArgumentException("Debe seleccionar un tipo de análisis.");
        }

        switch (tipoAnalisis) {
            case "Hematología":
                return new AnalisisHematologia(modoProcesamiento);
            case "Química sanguínea":
                return new AnalisisQuimicaSanguinea(modoProcesamiento);
            case "Microbiología":
                return new AnalisisMicrobiologia(modoProcesamiento);
            default:
                throw new IllegalArgumentException("Tipo de análisis no reconocido: " + tipoAnalisis);
        }
    }
}

class AnalisisHematologia extends Analisis {
    public AnalisisHematologia(ModoProcesamiento modoProcesamiento) {
        super(modoProcesamiento);
    }

    @Override
    public String getNombreAnalisis() {
        return "Hematología";
    }

    @Override
    protected String generarInterpretacion(String resultadoBase) {
        return resultadoBase + "\nInterpretación: Serie roja y blanca sin alteraciones relevantes.";
    }
}

class AnalisisQuimicaSanguinea extends Analisis {
    public AnalisisQuimicaSanguinea(ModoProcesamiento modoProcesamiento) {
        super(modoProcesamiento);
    }

    @Override
    public String getNombreAnalisis() {
        return "Química sanguínea";
    }

    @Override
    protected String generarInterpretacion(String resultadoBase) {
        return resultadoBase + "\nInterpretación: Glucosa y perfil básico dentro de límites esperados.";
    }
}

class AnalisisMicrobiologia extends Analisis {
    public AnalisisMicrobiologia(ModoProcesamiento modoProcesamiento) {
        super(modoProcesamiento);
    }

    @Override
    public String getNombreAnalisis() {
        return "Microbiología";
    }

    @Override
    protected String generarInterpretacion(String resultadoBase) {
        return resultadoBase + "\nInterpretación: No se observan hallazgos microbiológicos de alarma.";
    }
}
