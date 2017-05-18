package br.com.uninorte.soa.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.uninorte.soa.model.bean.Usuario;

public class UsuarioDAO {

    private static List<Usuario> listaUsuarios = new ArrayList<>();

    public UsuarioDAO() {
        if (listaUsuarios.size() == 0) {
            Usuario usuario1 = new Usuario();
            usuario1.setId(1);
            usuario1.setNome("Luiz");
            usuario1.setEmail("llcastro@prohaska.com.br");
            usuario1.setTelefone("92996778598");
            listaUsuarios.add(usuario1);
            Usuario usuario2 = new Usuario();
            usuario2.setId(2);
            usuario2.setNome("Igor");
            usuario2.setEmail("igor_henry@naressi.com.br");
            usuario2.setTelefone("21981892981");
            listaUsuarios.add(usuario2);
            Usuario usuario3 = new Usuario();
            usuario3.setId(3);
            usuario3.setNome("Nina");
            usuario3.setEmail("nlmartins@ept.com.br");
            usuario3.setTelefone("84991361178");
            listaUsuarios.add(usuario3);
            Usuario usuario4 = new Usuario();
            usuario4.setId(4);
            usuario4.setNome("Mariana");
            usuario4.setEmail("mariana.ana.paula@lordello.com.br");
            usuario4.setTelefone("65996180385");
            listaUsuarios.add(usuario4);
        }
    }

    public void cadastrar(Usuario usuario) {

        listaUsuarios.add(usuario);

    }

    public Usuario consultar(Long id) {

        for (Usuario usuario : listaUsuarios) {

            if (usuario.getId() == id) {
                return usuario;
            }

        }
        return null;
    }


    public List<Usuario> listar() {
        return listaUsuarios;
    }

    public Usuario deletar(Long id) {

        Usuario usuario = consultar(id);

        if (usuario != null) {
            listaUsuarios.remove(usuario);
        }
        return usuario;

    }

    public Usuario alterar(Usuario usuarioNovo) {

        Usuario usuarioVelho = consultar(usuarioNovo.getId());

        if (usuarioVelho != null) {

            usuarioVelho.setNome(usuarioNovo.getNome());
            usuarioVelho.setTelefone(usuarioNovo.getTelefone());
            usuarioVelho.setEmail(usuarioNovo.getEmail());

        }
        return usuarioVelho;

    }

}
