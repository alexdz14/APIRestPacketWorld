package dominio;

import dto.Respuesta;
import modelo.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Colaborador;
import java.util.HashMap;

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
}