package dominio;

import dto.Respuesta;
import java.util.UUID;
import modelo.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Envio;
import pojo.Paquete;
import pojo.Sucursal;
import utilidades.ConsumoAPI;

public class EnvioImp {
    
    public static Respuesta registrar(Envio envio) {
        Respuesta res = new Respuesta();
        res.setError(true);
        
        if(envio.getListaPaquetes() == null || envio.getListaPaquetes().isEmpty()){
            res.setMensaje("El envío debe tener al menos un paquete.");
            return res;
        }

        String guia = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        envio.setNumeroGuia(guia);
        
        SqlSession conexion = MyBatisUtil.getSessionFactory().openSession();
        Double distanciaKm = 0.0;
        String cpOrigen = "";

        try {
            Sucursal sucursalOrigen = conexion.selectOne("sucursal.getById", envio.getIdSucursalOrigen());
            
            if(sucursalOrigen != null){
                cpOrigen = sucursalOrigen.getCp();
            } else {
                cpOrigen = "91000"; 
            }
            
            String cpDestino = envio.getCpDestino();
            distanciaKm = ConsumoAPI.obtenerDistancia(cpOrigen, cpDestino);
            
            if(distanciaKm == 0.0){
                distanciaKm = 10.0; 
            }
            
            double costoPorKm = 0;
            if(distanciaKm <= 200) {
                costoPorKm = 4.00;
            } else if(distanciaKm <= 500) {
                costoPorKm = 3.00;
            } else if(distanciaKm <= 1000) {
                costoPorKm = 2.00;
            } else if(distanciaKm <= 2000) {
                costoPorKm = 1.00;
            } else {
                costoPorKm = 0.50;
            }
            
            double totalDistancia = distanciaKm * costoPorKm;
            
            int cantidadPaquetes = envio.getListaPaquetes().size();
            double costoAdicional = 0;
            
            if(cantidadPaquetes == 2){
                costoAdicional = 50.00;
            } else if(cantidadPaquetes >= 3){
                costoAdicional = 80.00; 
            } else if(cantidadPaquetes >= 4){
                costoAdicional = 110.00; 
            } else if(cantidadPaquetes >= 5){
                costoAdicional = 150.00; 
            }
            
            double costoTotal = totalDistancia + costoAdicional;
            envio.setCosto(costoTotal);

            int filas = conexion.insert("envio.registrarEnvio", envio);
            
            for(Paquete p : envio.getListaPaquetes()){
                p.setIdEnvio(envio.getIdEnvio());
                conexion.insert("envio.registrarPaquete", p);
            }
            
            conexion.commit();
            
            if(filas > 0){
                res.setError(false);
                res.setMensaje("Envío registrado. Guía: " + guia + 
                               " | Costo: $" + String.format("%.2f", costoTotal) + 
                               " (" + distanciaKm + " km desde CP " + cpOrigen + ")");
            } else {
                res.setMensaje("No se pudo registrar.");
            }
            
        } catch (Exception e) {
            conexion.rollback();
            res.setMensaje("Error interno: " + e.getMessage());
            e.printStackTrace();
        } finally {
            conexion.close();
        }
        return res;
    }
    
    public static Envio buscarPorGuia(String guia){
        Envio envio = null;
        SqlSession conexion = MyBatisUtil.getSessionFactory().openSession();
        try {
            envio = conexion.selectOne("envio.buscarPorGuia", guia);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.close();
        }
        return envio;
    }
}