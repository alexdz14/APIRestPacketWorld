package dominio;

/**
 *
 * @author stivm
 */
import dto.Respuesta;
import java.util.ArrayList;
import java.util.List;
import modelo.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Unidad;

public class UnidadImp {

    public static Respuesta registrar(Unidad unidad) {
        Respuesta res = new Respuesta();
        res.setError(true);

        if (unidad.getVin() == null || unidad.getVin().length() < 4) {
            res.setMensaje("El VIN debe tener al menos 4 caracteres para generar el NII.");
            return res;
        }
        String niiGenerado = unidad.getAnio() + unidad.getVin().substring(0, 4).toUpperCase();
        unidad.setNii(niiGenerado);
        SqlSession conexion = MyBatisUtil.getSessionFactory().openSession();
        try {
            int filas = conexion.insert("unidad.registrar", unidad);
            conexion.commit();
            if (filas > 0) {
                res.setError(false);
                res.setMensaje("Unidad registrada correctamente. NII asignado: " + niiGenerado);
            } else {
                res.setMensaje("No se pudo registrar la unidad.");
            }
        } catch (Exception e) {
            res.setMensaje("Error (Posible VIN o NII duplicado): " + e.getMessage());
        } finally {
            conexion.close();
        }
        return res;
    }

    public static Respuesta editar(Unidad unidad) {
        Respuesta res = new Respuesta();
        res.setError(true);
        SqlSession conexion = MyBatisUtil.getSessionFactory().openSession();
        try {
            int filas = conexion.update("unidad.editar", unidad);
            conexion.commit();
            if (filas > 0) {
                res.setError(false);
                res.setMensaje("Unidad actualizada correctamente.");
            } else {
                res.setMensaje("No se encontró la unidad.");
            }
        } catch (Exception e) {
            res.setMensaje("Error: " + e.getMessage());
        } finally {
            conexion.close();
        }
        return res;
    }

    public static Respuesta darBaja(Unidad unidad) {
        Respuesta res = new Respuesta();
        res.setError(true);
        SqlSession conexion = MyBatisUtil.getSessionFactory().openSession();
        try {
            if (unidad.getIdUnidad() == null || unidad.getMotivo() == null || unidad.getMotivo().isEmpty()) {
                res.setMensaje("El ID de la unidad y el motivo de baja son obligatorios.");
                return res;
            }

            int filas = conexion.update("unidad.darBaja", unidad);
            conexion.commit();
            if (filas > 0) {
                res.setError(false);
                res.setMensaje("Unidad dada de baja correctamente.");
            } else {
                res.setMensaje("No se encontró la unidad.");
            }
        } catch (Exception e) {
            res.setMensaje("Error: " + e.getMessage());
        } finally {
            conexion.close();
        }
        return res;
    }

    public static List<Unidad> obtenerTodas() {
        List<Unidad> lista = new ArrayList<>();
        SqlSession conexion = MyBatisUtil.getSessionFactory().openSession();
        try {
            lista = conexion.selectList("unidad.getAll");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.close();
        }
        return lista;
    }

    public static List<Unidad> buscar(String filtro) {
        List<Unidad> lista = new ArrayList<>();
        SqlSession conexion = MyBatisUtil.getSessionFactory().openSession();
        try {
            lista = conexion.selectList("unidad.buscar", "%" + filtro + "%");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.close();
        }
        return lista;
    }
}
