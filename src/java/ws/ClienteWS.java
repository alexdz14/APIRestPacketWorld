package ws;

import com.google.gson.Gson;
import dominio.ClienteImp;
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
import pojo.Cliente;

@Path("cliente")
public class ClienteWS {

    @POST
    @Path("registrar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String registrar(String json) {
        Gson gson = new Gson();
        Cliente cliente = gson.fromJson(json, Cliente.class);
        if (cliente.getNombre() == null || cliente.getTelefono() == null) {
            return gson.toJson(new Respuesta(true, "Nombre y teléfono son obligatorios"));
        }
        Respuesta res = ClienteImp.registrar(cliente);
        return gson.toJson(res);
    }

    @PUT
    @Path("editar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String editar(String json) {
        Gson gson = new Gson();
        Cliente cliente = gson.fromJson(json, Cliente.class);
        Respuesta res = ClienteImp.editar(cliente);
        return gson.toJson(res);
    }

    @DELETE
    @Path("eliminar/{idCliente}")
    @Produces(MediaType.APPLICATION_JSON)
    public String eliminar(@PathParam("idCliente") Integer idCliente) {
        Respuesta res = ClienteImp.eliminar(idCliente);
        Gson gson = new Gson();
        return gson.toJson(res);
    }

    @GET
    @Path("getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> getAll() {
        return ClienteImp.getAll();
    }

    @GET
    @Path("buscar/{filtro}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> buscar(@PathParam("filtro") String filtro) {
        return ClienteImp.buscar(filtro);
    }
}
