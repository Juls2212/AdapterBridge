package model;

public class Muestra {

    private String code;
    private String patient;
    private String sampleType;
    private String priority;
    private String status;

    public Muestra(String code, String patient, String sampleType, String priority) {
        this.code = code;
        this.patient = patient;
        this.sampleType = sampleType;
        this.priority = priority;
        this.status = "Registered";
    }

    public String getCode() {
        return code;
    }

    public String getPatient() {
        return patient;
    }

    public String getSampleType() {
        return sampleType;
    }

    public String getPriority() {
        return priority;
    }

    public String getStatus() {
        return status;
    }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }

    public String getSummary() {
        return "Code: " + code +
                "\nPatient: " + patient +
                "\nSample Type: " + sampleType +
                "\nPriority: " + priority +
                "\nStatus: " + status;
    }
}