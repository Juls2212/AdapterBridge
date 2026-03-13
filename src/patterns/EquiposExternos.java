package patterns;

class LegacyHematologyMachine {
    private String ultimoReporte;

    public void bootLegacySystem() {
        // Simulación de arranque
    }

    public void runBloodTest(String codigoMuestra) {
        ultimoReporte = "Reporte legado hematológico para muestra " + codigoMuestra +
                ": Hemoglobina y leucocitos en rango aceptable.";
    }

    public String fetchLegacyReport() {
        return ultimoReporte;
    }
}

class ExternalMicrobiologyDevice {
    private String archivoResultado;

    public void connectDevice() {
        // Simulación de conexión
    }

    public void startCultureProcess(String codigoMuestra) {
        archivoResultado = "Archivo microbiológico para muestra " + codigoMuestra +
                ": Cultivo sin crecimiento bacteriano significativo.";
    }

    public String exportResultsFile() {
        return archivoResultado;
    }
}