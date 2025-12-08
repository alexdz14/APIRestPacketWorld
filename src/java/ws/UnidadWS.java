package ws;

/**
 *
 * @author stivm
 */
import com.google.gson.Gson;
import dominio.UnidadImp;
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
import pojo.Unidad;

@Path("unidad")
public class UnidadWS {

    @POST
    @Path("registrar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String registrar(String json) {
        Gson gson = new Gson();
        Unidad unidad = gson.fromJson(json, Unidad.class);
        Respuesta res = UnidadImp.registrar(unidad);
        return gson.toJson(res);
    }

    @PUT
    @Path("editar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String editar(String json) {
        Gson gson = new Gson();
        Unidad unidad = gson.fromJson(json, Unidad.class);
        Respuesta res = UnidadImp.editar(unidad);
        return gson.toJson(res);
    }

    @PUT
    @Path("darBaja")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String darBaja(String json) {
        Gson gson = new Gson();
        Unidad unidad = gson.fromJson(json, Unidad.class);
        Respuesta res = UnidadImp.darBaja(unidad);
        return gson.toJson(res);
    }

    @GET
    @Path("getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Unidad> getAll() {
        return UnidadImp.obtenerTodas();
    }

    @GET
    @Path("buscar/{filtro}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Unidad> buscar(@PathParam("filtro") String filtro) {
        return UnidadImp.buscar(filtro);
    }
}
