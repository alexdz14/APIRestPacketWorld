package ws;

import com.google.gson.Gson;
import dominio.ColaboradorImp;
import dto.Respuesta;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pojo.Colaborador;

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
    
    @POST
    @Path("registrar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String registrarColaborador(String json) {
        Gson gson = new Gson();
        Colaborador nuevoColaborador = gson.fromJson(json, Colaborador.class);
        Respuesta respuesta = ColaboradorImp.registrar(nuevoColaborador);
        return gson.toJson(respuesta);
    }
    
    @PUT
    @Path("editar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String editarColaborador(String json) {
        Gson gson = new Gson();
        Colaborador colaborador = gson.fromJson(json, Colaborador.class);
        if (colaborador.getNoPersonal() == null || colaborador.getNoPersonal().isEmpty()) {
            return gson.toJson(new Respuesta(true, "El número de personal es obligatorio para editar."));
        }
        
        Respuesta respuesta = ColaboradorImp.editar(colaborador);
        return gson.toJson(respuesta);
    }
    
    @DELETE
    @Path("eliminar/{noPersonal}")
    @Produces(MediaType.APPLICATION_JSON)
    public String eliminarColaborador(@javax.ws.rs.PathParam("noPersonal") String noPersonal) {
        Respuesta respuesta = ColaboradorImp.eliminar(noPersonal);
        
        Gson gson = new Gson();
        return gson.toJson(respuesta);
    }
    
    @GET
    @Path("buscar/{filtro}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Colaborador> buscarColaborador(@javax.ws.rs.PathParam("filtro") String filtro) {
        return ColaboradorImp.buscar(filtro);
    }
}