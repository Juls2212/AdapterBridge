package patterns;

class LegacyHematologyMachine {

    private String lastReport;

    public void bootLegacySystem() {}

    public void runBloodTest(String sampleCode) {
        lastReport = "Legacy hematology report for sample " + sampleCode;
    }

    public String fetchLegacyReport() {
        return lastReport;
    }
}

class ExternalMicrobiologyDevice {

    private String resultFile;

    public void connectDevice() {}

    public void startCultureProcess(String sampleCode) {
        resultFile = "Microbiology culture result for sample " + sampleCode;
    }

    public String exportResultsFile() {
        return resultFile;
    }
}