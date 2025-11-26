package dominio;

import dto.Respuesta;
import modelo.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Colaborador;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class ColaboradorImp {
    
    public static Respuesta validarLogin(String noPersonal, String password) {
        Respuesta respuesta = new Respuesta();
        respuesta.setError(true);
        
        SqlSession conexion = MyBatisUtil.getSessionFactory().openSession();
        
        try {
            HashMap<String, String> parametros = new HashMap<>();
            parametros.put("noPersonal", noPersonal);
            parametros.put("contrasenia", password);
            Colaborador colaborador = conexion.selectOne("colaborador.login", parametros);
            
            if (colaborador != null) {
                respuesta.setError(false);
                respuesta.setMensaje("Bienvenido al sistema " + colaborador.getNombre());
            } else {
                respuesta.setMensaje("Número de personal o contraseña incorrectos.");
            }
            
        } catch (Exception e) {
            respuesta.setMensaje("Error en el servidor: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (conexion != null) {
                conexion.close();
            }
        }
        
        return respuesta;
    }
    
    public static Respuesta registrar(Colaborador colaborador) {
        Respuesta respuesta = new Respuesta();
        respuesta.setError(true);
        
        SqlSession conexion = MyBatisUtil.getSessionFactory().openSession();
        
        try {
            int filasAfectadas = conexion.insert("colaborador.registrar", colaborador);
            conexion.commit(); 
            if (filasAfectadas > 0) {
                respuesta.setError(false);
                respuesta.setMensaje("Colaborador registrado correctamente.");
            } else {
                respuesta.setMensaje("No se pudo registrar el colaborador.");
            }
        } catch (Exception e) {
            respuesta.setMensaje("Error al guardar: " + e.getMessage());
        } finally {
            conexion.close();
        }
        
        return respuesta;
    }
    
    public static Respuesta editar(Colaborador colaborador) {
        Respuesta respuesta = new Respuesta();
        respuesta.setError(true);
        
        SqlSession conexion = MyBatisUtil.getSessionFactory().openSession();
        
        try {
            int filasAfectadas = conexion.update("colaborador.editar", colaborador);
            conexion.commit();
            
            if (filasAfectadas > 0) {
                respuesta.setError(false);
                respuesta.setMensaje("Información del colaborador actualizada correctamente.");
            } else {
                respuesta.setMensaje("No se encontró el colaborador para editar.");
            }
        } catch (Exception e) {
            respuesta.setMensaje("Error al editar: " + e.getMessage());
        } finally {
            conexion.close();
        }
        
        return respuesta;
    }
    
    public static Respuesta eliminar(String noPersonal) {
        Respuesta respuesta = new Respuesta();
        respuesta.setError(true);
        
        SqlSession conexion = MyBatisUtil.getSessionFactory().openSession();
        
        try {
            int filasAfectadas = conexion.delete("colaborador.eliminar", noPersonal);
            conexion.commit();
            
            if (filasAfectadas > 0) {
                respuesta.setError(false);
                respuesta.setMensaje("Colaborador eliminado correctamente.");
            } else {
                respuesta.setMensaje("No se encontró el colaborador para eliminar.");
            }
        } catch (Exception e) {
            respuesta.setMensaje("Error al eliminar: " + e.getMessage());
        } finally {
            conexion.close();
        }
        
        return respuesta;
    }
    
    public static List<Colaborador> buscar(String filtro) {
        List<Colaborador> lista = new ArrayList<>();
        SqlSession conexion = MyBatisUtil.getSessionFactory().openSession();
        
        try {
            String consulta = "%" + filtro + "%";
            lista = conexion.selectList("colaborador.buscar", consulta);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.close();
        }
        
        return lista;
    }
}