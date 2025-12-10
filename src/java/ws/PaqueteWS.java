package ws;

import com.google.gson.Gson;
import dominio.PaqueteImp;
import dto.Respuesta;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pojo.Paquete;

@Path("paquete")
public class PaqueteWS {

    @POST
    @Path("registrar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String registrar(String json) {
        Gson gson = new Gson();
        Paquete paquete = gson.fromJson(json, Paquete.class);
        if (paquete.getIdEnvio() == null) {
            return gson.toJson(new Respuesta(true, "Error: Debes indicar el ID del Envío al que pertenece."));
        }

        Respuesta res = PaqueteImp.registrar(paquete);
        return gson.toJson(res);
    }

    @PUT
    @Path("editar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String editar(String json) {
        Gson gson = new Gson();
        Paquete paquete = gson.fromJson(json, Paquete.class);
        Respuesta res = PaqueteImp.editar(paquete);
        return gson.toJson(res);
    }

    @DELETE
    @Path("eliminar/{idPaquete}")
    @Produces(MediaType.APPLICATION_JSON)
    public String eliminar(@PathParam("idPaquete") Integer idPaquete) {
        Respuesta res = PaqueteImp.eliminar(idPaquete);
        Gson gson = new Gson();
        return gson.toJson(res);
    }

    @GET
    @Path("porEnvio/{idEnvio}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Paquete> obtenerPorEnvio(@PathParam("idEnvio") Integer idEnvio) {
        return PaqueteImp.obtenerPorEnvio(idEnvio);
    }
}
