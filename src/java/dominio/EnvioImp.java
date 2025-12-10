package dominio;

import dto.Respuesta;
import java.util.Random;
import java.util.UUID;
import modelo.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Envio;
import pojo.Paquete;

public class EnvioImp {

    public static Respuesta registrar(Envio envio) {
        Respuesta res = new Respuesta();
        res.setError(true);

        if (envio.getListaPaquetes() == null || envio.getListaPaquetes().isEmpty()) {
            res.setMensaje("El envío debe contener al menos un paquete.");
            return res;
        }

        String guia = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        envio.setNumeroGuia(guia);
        int distanciaKm = new Random().nextInt(790) + 10;
        double tarifaPorKm = (distanciaKm < 50) ? 10.0 : 5.0;
        double costoTraslado = distanciaKm * tarifaPorKm;

        double costoPeso = 0;
        for (Paquete p : envio.getListaPaquetes()) {
            if (p.getPeso() != null) {
                costoPeso += (p.getPeso() * 10);
            }
        }

        double total = costoTraslado + costoPeso;
        if (envio.getListaPaquetes().size() > 3) {
            total = total * 0.90;
        }

        envio.setCosto(total);

        SqlSession conexion = MyBatisUtil.getSessionFactory().openSession();
        try {
            int filas = conexion.insert("envio.registrarEnvio", envio);
            for (Paquete p : envio.getListaPaquetes()) {
                p.setIdEnvio(envio.getIdEnvio());
                conexion.insert("envio.registrarPaquete", p);
            }
            conexion.commit();

            if (filas > 0) {
                res.setError(false);
                res.setMensaje("Envío registrado. Guía: " + guia + " | Costo Total: $" + String.format("%.2f", total) + " (Distancia: " + distanciaKm + "km)");
            } else {
                res.setMensaje("No se pudo registrar el envío.");
            }
        } catch (Exception e) {
            conexion.rollback();
            res.setMensaje("Error al guardar: " + e.getMessage());
            e.printStackTrace();
        } finally {
            conexion.close();
        }
        return res;
    }

    public static Envio buscarPorGuia(String guia) {
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
