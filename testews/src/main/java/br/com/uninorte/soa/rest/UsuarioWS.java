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
    @Path("/listar")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response listar() {
        List<Usuario> result = business.listar();
        System.out.println("doGET():");
        if (!result.isEmpty()) {
            return Response.ok(result).build();
        }
        return Response.noContent().build();
    }

    @POST
    @Path("/cadastrar")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrar(Usuario usuario) {
        business.cadastrar(usuario);

        System.out.println("POST(): ");

        return Response.ok("{\"resultado\":\"sucesso\"}").build();
    }

    @GET
    @Path("/consultar/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response consultar(@PathParam("id") Long id) {
        Usuario result = business.consultar(id);
        System.out.println("doGET():");
        if (result != null) {
            return Response.ok(result).build();
        }
        return Response.noContent().build();
    }

    @DELETE
    @Path("/deletar/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response deletar(@PathParam("id") Long id) {
        Usuario result = business.deletar(id);
        System.out.println("doDELETE():");
        if (result != null) {
            return Response.ok(result).build();
        }
        return Response.noContent().build();
    }

    @PUT
    @Path("/alterar/")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alterar(Usuario usuario) {

        System.out.println("PUT():");

        usuario = business.alterar(usuario);

        if (usuario != null) {
            return Response.ok(usuario).build();
        }
        return Response.noContent().build();

    }

}
