package ws;

import com.google.gson.Gson;
import dominio.EnvioImp;
import dto.Respuesta;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pojo.Envio;

@Path("envio")
public class EnvioWS {

    @POST
    @Path("registrar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String registrar(String json) {
        Gson gson = new Gson();
        try {
            Envio envio = gson.fromJson(json, Envio.class);
            Respuesta res = EnvioImp.registrar(envio);
            return gson.toJson(res);
        } catch (Exception e) {
            return gson.toJson(new Respuesta(true, "JSON inválido: " + e.getMessage()));
        }
    }

    @GET
    @Path("rastrear/{guia}")
    @Produces(MediaType.APPLICATION_JSON)
    public String rastrear(@PathParam("guia") String guia) {
        Envio envio = EnvioImp.buscarPorGuia(guia);
        Gson gson = new Gson();
        if (envio != null) {
            return gson.toJson(envio);
        } else {
            return gson.toJson(new Respuesta(true, "Número de guía no encontrado."));
        }
    }
}
