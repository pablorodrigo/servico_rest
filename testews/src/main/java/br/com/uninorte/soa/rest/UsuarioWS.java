package br.com.uninorte.soa.rest;

import br.com.uninorte.soa.business.UsuarioBusiness;
import br.com.uninorte.soa.model.bean.Usuario;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/usuario")
public class UsuarioWS {

    private UsuarioBusiness business = new UsuarioBusiness();

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response listar() {
        List<Usuario> result = business.listar();
        System.out.println("doGET(): /rest/usuario/");
        if (!result.isEmpty()) {
            return Response.ok(result).build();
        }
        return Response.noContent().build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrar(Usuario usuario) {
        business.cadastrar(usuario);

        System.out.println("POST(): /rest/usuario/");

        return Response.ok("{\"resultado\":\"funfou\"}").build();
    }

    @GET
    @Path("/{nome}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response consultar(@PathParam("nome") String nome) {
        Usuario result = business.consultar(nome);
        System.out.println("doGET(): /rest/usuario/{nome}");
        if (result != null) {
            return Response.ok(result).build();
        }
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{nome}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response deletar(@PathParam("nome") String nome) {
        Usuario result = business.deletar(nome);
        System.out.println("doDELETE(): /rest/usuario/{nome}");
        if (result != null) {
            return Response.ok(result).build();
        }
        return Response.noContent().build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alterar(Usuario usuario) {

        System.out.println("PUT(): /rest/usuario/");

        usuario = business.alterar(usuario);

        if (usuario != null) {
            return Response.ok(usuario).build();
        }
        return Response.noContent().build();

    }

}
