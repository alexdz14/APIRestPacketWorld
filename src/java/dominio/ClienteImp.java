package dominio;

import dto.Respuesta;
import java.util.ArrayList;
import java.util.List;
import modelo.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Cliente;

public class ClienteImp {

    public static Respuesta registrar(Cliente cliente) {
        Respuesta res = new Respuesta();
        res.setError(true);
        SqlSession conexion = MyBatisUtil.getSessionFactory().openSession();
        try {
            int filas = conexion.insert("cliente.registrar", cliente);
            conexion.commit();
            if (filas > 0) {
                res.setError(false);
                res.setMensaje("Cliente registrado correctamente.");
            } else {
                res.setMensaje("No se pudo registrar el cliente.");
            }
        } catch (Exception e) {
            res.setMensaje("Error: " + e.getMessage());
        } finally {
            conexion.close();
        }
        return res;
    }

    public static Respuesta editar(Cliente cliente) {
        Respuesta res = new Respuesta();
        res.setError(true);
        SqlSession conexion = MyBatisUtil.getSessionFactory().openSession();
        try {
            int filas = conexion.update("cliente.editar", cliente);
            conexion.commit();
            if (filas > 0) {
                res.setError(false);
                res.setMensaje("Cliente actualizado correctamente.");
            } else {
                res.setMensaje("No se encontró el cliente.");
            }
        } catch (Exception e) {
            res.setMensaje("Error: " + e.getMessage());
        } finally {
            conexion.close();
        }
        return res;
    }

    public static Respuesta eliminar(Integer idCliente) {
        Respuesta res = new Respuesta();
        res.setError(true);
        SqlSession conexion = MyBatisUtil.getSessionFactory().openSession();
        try {
            int filas = conexion.delete("cliente.eliminar", idCliente);
            conexion.commit();
            if (filas > 0) {
                res.setError(false);
                res.setMensaje("Cliente eliminado correctamente.");
            } else {
                res.setMensaje("No se encontró el cliente.");
            }
        } catch (Exception e) {
            res.setMensaje("Error: " + e.getMessage());
        } finally {
            conexion.close();
        }
        return res;
    }

    public static List<Cliente> getAll() {
        List<Cliente> lista = new ArrayList<>();
        SqlSession conexion = MyBatisUtil.getSessionFactory().openSession();
        try {
            lista = conexion.selectList("cliente.getAll");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.close();
        }
        return lista;
    }

    public static List<Cliente> buscar(String filtro) {
        List<Cliente> lista = new ArrayList<>();
        SqlSession conexion = MyBatisUtil.getSessionFactory().openSession();
        try {
            lista = conexion.selectList("cliente.buscar", "%" + filtro + "%");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.close();
        }
        return lista;
    }
}
