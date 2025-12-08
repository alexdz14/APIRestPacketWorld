package pojo;

/**
 *
 * @author stivm
 */
public class Unidad {

    private Integer idUnidad;
    private String marca;
    private String modelo;
    private String anio;
    private String vin;
    private String tipoUnidad;
    private String nii;
    private String estatus;
    private Integer idConductorAsignado;
    private String motivo;

    public Unidad() {
    }

    public Unidad(Integer idUnidad, String marca, String modelo, String anio, String vin, String tipoUnidad, String nii, String estatus, Integer idConductorAsignado, String motivo) {
        this.idUnidad = idUnidad;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.vin = vin;
        this.tipoUnidad = tipoUnidad;
        this.nii = nii;
        this.estatus = estatus;
        this.idConductorAsignado = idConductorAsignado;
        this.motivo = motivo;
    }

    public Integer getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(Integer idUnidad) {
        this.idUnidad = idUnidad;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getTipoUnidad() {
        return tipoUnidad;
    }

    public void setTipoUnidad(String tipoUnidad) {
        this.tipoUnidad = tipoUnidad;
    }

    public String getNii() {
        return nii;
    }

    public void setNii(String nii) {
        this.nii = nii;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Integer getIdConductorAsignado() {
        return idConductorAsignado;
    }

    public void setIdConductorAsignado(Integer idConductorAsignado) {
        this.idConductorAsignado = idConductorAsignado;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    
}
