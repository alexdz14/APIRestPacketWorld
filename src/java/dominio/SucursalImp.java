package dominio;

import dto.Respuesta;
import java.util.ArrayList;
import java.util.List;
import modelo.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Sucursal;

public class SucursalImp {

    public static Respuesta registrar(Sucursal sucursal) {
        Respuesta msj = new Respuesta();
        msj.setError(true);
        SqlSession conexion = MyBatisUtil.getSessionFactory().openSession();
        try {
            int filas = conexion.insert("sucursal.registrar", sucursal);
            conexion.commit();
            if (filas > 0) {
                msj.setError(false);
                msj.setMensaje("Sucursal registrada con éxito.");
            } else {
                msj.setMensaje("No se pudo registrar la sucursal.");
            }
        } catch (Exception e) {
            msj.setMensaje("Error: " + e.getMessage());
        } finally {
            conexion.close();
        }
        return msj;
    }

    public static Respuesta editar(Sucursal sucursal) {
        Respuesta msj = new Respuesta();
        msj.setError(true);
        SqlSession conexion = MyBatisUtil.getSessionFactory().openSession();
        try {
            int filas = conexion.update("sucursal.editar", sucursal);
            conexion.commit();
            if (filas > 0) {
                msj.setError(false);
                msj.setMensaje("Sucursal actualizada (Código y Estatus no se modifican).");
            } else {
                msj.setMensaje("No se encontró la sucursal.");
            }
        } catch (Exception e) {
            msj.setMensaje("Error: " + e.getMessage());
        } finally {
            conexion.close();
        }
        return msj;
    }

    public static Respuesta eliminar(Integer idSucursal) {
        Respuesta msj = new Respuesta();
        msj.setError(true);
        SqlSession conexion = MyBatisUtil.getSessionFactory().openSession();
        try {
            int filas = conexion.update("sucursal.darBaja", idSucursal);
            conexion.commit();
            if (filas > 0) {
                msj.setError(false);
                msj.setMensaje("Sucursal dada de baja correctamente.");
            } else {
                msj.setMensaje("No se encontró la sucursal.");
            }
        } catch (Exception e) {
            msj.setMensaje("Error: " + e.getMessage());
        } finally {
            conexion.close();
        }
        return msj;
    }

    public static List<Sucursal> obtenerTodas() {
        List<Sucursal> lista = new ArrayList<>();
        SqlSession conexion = MyBatisUtil.getSessionFactory().openSession();
        try {
            lista = conexion.selectList("sucursal.getAll");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.close();
        }
        return lista;
    }
}