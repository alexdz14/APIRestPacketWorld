package ws;

import com.google.gson.Gson;
import dominio.SucursalImp;
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
import pojo.Sucursal;

@Path("sucursal")
public class SucursalWS {
    
    @POST
    @Path("registrar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String registrar(String json) {
        Gson gson = new Gson();
        Sucursal suc = gson.fromJson(json, Sucursal.class);
        Respuesta res = SucursalImp.registrar(suc);
        return gson.toJson(res);
    }
    
    @PUT
    @Path("editar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String editar(String json) {
        Gson gson = new Gson();
        Sucursal suc = gson.fromJson(json, Sucursal.class);
        Respuesta res = SucursalImp.editar(suc);
        return gson.toJson(res);
    }
    
    @DELETE
    @Path("eliminar/{idSucursal}")
    @Produces(MediaType.APPLICATION_JSON)
    public String eliminar(@PathParam("idSucursal") Integer idSucursal) {
        Respuesta res = SucursalImp.eliminar(idSucursal);
        Gson gson = new Gson();
        return gson.toJson(res);
    }
    
    @GET
    @Path("getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Sucursal> getAll() {
        return SucursalImp.obtenerTodas();
    }
}