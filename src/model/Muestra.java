package model;

public class Muestra {
    private String codigo;
    private String paciente;
    private String tipoMuestra;
    private String prioridad;
    private String estado;

    public Muestra(String codigo, String paciente, String tipoMuestra, String prioridad) {
        this.codigo = codigo;
        this.paciente = paciente;
        this.tipoMuestra = tipoMuestra;
        this.prioridad = prioridad;
        this.estado = "Registrada";
    }

    public String getCodigo() {
        return codigo;
    }

    public String getPaciente() {
        return paciente;
    }

    public String getTipoMuestra() {
        return tipoMuestra;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public String getEstado() {
        return estado;
    }

    public void actualizarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public String getResumen() {
        return "Código: " + codigo +
               "\nPaciente: " + paciente +
               "\nTipo de muestra: " + tipoMuestra +
               "\nPrioridad: " + prioridad +
               "\nEstado: " + estado;
    }
}