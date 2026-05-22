/**
 *
 * @author Nappe
 */
public class consumoMensual {
    private String numeroDepartamento;
    private String nombreResponsable;
    private String mes;
    private float lecturaAnterior;
    private float lecturaActual;
    private float tarifaMetroCubico;

    public consumoMensual(String numeroDepartamento, String nombreResponsable, String mes, float lecturaAnterior, float lecturaActual, float tarifaMetroCubico) {
        this.numeroDepartamento = numeroDepartamento;
        this.nombreResponsable = nombreResponsable;
        this.mes = mes;
        this.lecturaAnterior = lecturaAnterior;
        this.lecturaActual = lecturaActual;
        this.tarifaMetroCubico = tarifaMetroCubico;
    }

    public String getNumeroDepartamento() {
        return numeroDepartamento;
    }

    public String getNombreResponsable() {
        return nombreResponsable;
    }

    public String getMes() {
        return mes;
    }

    public float getLecturaAnterior() {
        return lecturaAnterior;
    }

    public float getLecturaActual() {
        return lecturaActual;
    }

    public float getTarifaMetroCubico() {
        return tarifaMetroCubico;
    }

    public void setNumeroDepartamento(String numeroDepartamento) {
        this.numeroDepartamento = numeroDepartamento;
    }

    public void setNombreResponsable(String nombreResponsable) {
        this.nombreResponsable = nombreResponsable;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public void setLecturaAnterior(float lecturaAnterior) {
        this.lecturaAnterior = lecturaAnterior;
    }

    public void setLecturaActual(float lecturaActual) {
        this.lecturaActual = lecturaActual;
    }

    public void setTarifaMetroCubico(float tarifaMetroCubico) {
        this.tarifaMetroCubico = tarifaMetroCubico;
    }

    public String transformarRenglon() {
        return numeroDepartamento + "," + nombreResponsable + "," + mes + "," + lecturaAnterior + "," + lecturaActual + "," + tarifaMetroCubico;
    }
    
}