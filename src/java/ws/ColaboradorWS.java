package ws;

import com.google.gson.Gson;
import dominio.ColaboradorImp;
import dto.Respuesta;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("colaborador")
public class ColaboradorWS {
    
    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String iniciarSesion(
            @FormParam("noPersonal") String noPersonal, 
            @FormParam("contrasenia") String contrasenia) {
        
        Respuesta respuesta = ColaboradorImp.validarLogin(noPersonal, contrasenia);
        
        Gson gson = new Gson();
        return gson.toJson(respuesta);
    }
}