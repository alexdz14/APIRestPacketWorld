package dominio;

import dto.Respuesta;
import java.util.ArrayList;
import java.util.List;
import modelo.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Paquete;

public class PaqueteImp {

    public static Respuesta registrar(Paquete paquete) {
        Respuesta res = new Respuesta();
        res.setError(true);
        SqlSession conexion = MyBatisUtil.getSessionFactory().openSession();
        try {
            int filas = conexion.insert("paquete.registrar", paquete);
            conexion.commit();
            if (filas > 0) {
                res.setError(false);
                res.setMensaje("Paquete registrado correctamente.");
            } else {
                res.setMensaje("No se pudo registrar el paquete.");
            }
        } catch (Exception e) {
            res.setMensaje("Error BD: " + e.getMessage());
        } finally {
            conexion.close();
        }
        return res;
    }

    public static Respuesta editar(Paquete paquete) {
        Respuesta res = new Respuesta();
        res.setError(true);
        SqlSession conexion = MyBatisUtil.getSessionFactory().openSession();
        try {
            int filas = conexion.update("paquete.editar", paquete);
            conexion.commit();
            if (filas > 0) {
                res.setError(false);
                res.setMensaje("Paquete editado correctamente.");
            } else {
                res.setMensaje("No se encontró el paquete con ese ID.");
            }
        } catch (Exception e) {
            res.setMensaje("Error BD: " + e.getMessage());
        } finally {
            conexion.close();
        }
        return res;
    }

    public static Respuesta eliminar(Integer idPaquete) {
        Respuesta res = new Respuesta();
        res.setError(true);
        SqlSession conexion = MyBatisUtil.getSessionFactory().openSession();
        try {
            int filas = conexion.delete("paquete.eliminar", idPaquete);
            conexion.commit();
            if (filas > 0) {
                res.setError(false);
                res.setMensaje("Paquete eliminado correctamente.");
            } else {
                res.setMensaje("No se encontró el paquete.");
            }
        } catch (Exception e) {
            res.setMensaje("Error BD: " + e.getMessage());
        } finally {
            conexion.close();
        }
        return res;
    }

    public static List<Paquete> obtenerPorEnvio(Integer idEnvio) {
        List<Paquete> lista = new ArrayList<>();
        SqlSession conexion = MyBatisUtil.getSessionFactory().openSession();
        try {
            lista = conexion.selectList("paquete.getByEnvio", idEnvio);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.close();
        }
        return lista;
    }
}
