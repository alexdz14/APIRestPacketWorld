package pojo;

import java.util.List;

public class Envio {

    private Integer idEnvio;
    private String numeroGuia;
    private Double costo;
    private String estatus;
    private Integer idSucursalOrigen;
    private Integer idClienteRemitente;
    private Integer idConductorAsignado;
    private String nombreDestinatario;
    private String apellidoDestinatario;
    private String calleDestino;
    private String numeroDestino;
    private String coloniaDestino;
    private String cpDestino;
    private String ciudadDestino;
    private String estadoDestino;

    private List<Paquete> listaPaquetes;

    public Envio() {
    }

    public Envio(Integer idEnvio, String numeroGuia, Double costo, String estatus, Integer idSucursalOrigen, Integer idClienteRemitente, Integer idConductorAsignado, String nombreDestinatario, String apellidoDestinatario, String calleDestino, String numeroDestino, String coloniaDestino, String cpDestino, String ciudadDestino, String estadoDestino, List<Paquete> listaPaquetes) {
        this.idEnvio = idEnvio;
        this.numeroGuia = numeroGuia;
        this.costo = costo;
        this.estatus = estatus;
        this.idSucursalOrigen = idSucursalOrigen;
        this.idClienteRemitente = idClienteRemitente;
        this.idConductorAsignado = idConductorAsignado;
        this.nombreDestinatario = nombreDestinatario;
        this.apellidoDestinatario = apellidoDestinatario;
        this.calleDestino = calleDestino;
        this.numeroDestino = numeroDestino;
        this.coloniaDestino = coloniaDestino;
        this.cpDestino = cpDestino;
        this.ciudadDestino = ciudadDestino;
        this.estadoDestino = estadoDestino;
        this.listaPaquetes = listaPaquetes;
    }

    public Integer getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(Integer idEnvio) {
        this.idEnvio = idEnvio;
    }

    public String getNumeroGuia() {
        return numeroGuia;
    }

    public void setNumeroGuia(String numeroGuia) {
        this.numeroGuia = numeroGuia;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Integer getIdSucursalOrigen() {
        return idSucursalOrigen;
    }

    public void setIdSucursalOrigen(Integer idSucursalOrigen) {
        this.idSucursalOrigen = idSucursalOrigen;
    }

    public Integer getIdClienteRemitente() {
        return idClienteRemitente;
    }

    public void setIdClienteRemitente(Integer idClienteRemitente) {
        this.idClienteRemitente = idClienteRemitente;
    }

    public Integer getIdConductorAsignado() {
        return idConductorAsignado;
    }

    public void setIdConductorAsignado(Integer idConductorAsignado) {
        this.idConductorAsignado = idConductorAsignado;
    }

    public String getNombreDestinatario() {
        return nombreDestinatario;
    }

    public void setNombreDestinatario(String nombreDestinatario) {
        this.nombreDestinatario = nombreDestinatario;
    }

    public String getApellidoDestinatario() {
        return apellidoDestinatario;
    }

    public void setApellidoDestinatario(String apellidoDestinatario) {
        this.apellidoDestinatario = apellidoDestinatario;
    }

    public String getCalleDestino() {
        return calleDestino;
    }

    public void setCalleDestino(String calleDestino) {
        this.calleDestino = calleDestino;
    }

    public String getNumeroDestino() {
        return numeroDestino;
    }

    public void setNumeroDestino(String numeroDestino) {
        this.numeroDestino = numeroDestino;
    }

    public String getColoniaDestino() {
        return coloniaDestino;
    }

    public void setColoniaDestino(String coloniaDestino) {
        this.coloniaDestino = coloniaDestino;
    }

    public String getCpDestino() {
        return cpDestino;
    }

    public void setCpDestino(String cpDestino) {
        this.cpDestino = cpDestino;
    }

    public String getCiudadDestino() {
        return ciudadDestino;
    }

    public void setCiudadDestino(String ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    public String getEstadoDestino() {
        return estadoDestino;
    }

    public void setEstadoDestino(String estadoDestino) {
        this.estadoDestino = estadoDestino;
    }

    public List<Paquete> getListaPaquetes() {
        return listaPaquetes;
    }

    public void setListaPaquetes(List<Paquete> listaPaquetes) {
        this.listaPaquetes = listaPaquetes;
    }
}
