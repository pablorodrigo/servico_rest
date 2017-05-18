package br.com.uninorte.soa.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.uninorte.soa.model.bean.Usuario;

public class UsuarioDAO {

    private static List<Usuario> listaUsuarios = new ArrayList<>();

    public UsuarioDAO() {
        if (listaUsuarios.size() == 0) {
            Usuario usuario = new Usuario();
            usuario.setNome("Maria");
            usuario.setEmail("maria@gmail.com");
            usuario.setTelefone("92981112233");
            listaUsuarios.add(usuario);
        }
    }

    public void cadastrar(Usuario usuario) {

        listaUsuarios.add(usuario);

    }

    public Usuario consultar(String nome) {

        for (Usuario usuario : listaUsuarios) {

            if (usuario.getNome().equals(nome)) {
                return usuario;
            }

        }
        return null;
    }


    public List<Usuario> listar() {
        return listaUsuarios;
    }

    public Usuario deletar(String nome) {

        Usuario usuario = consultar(nome);

        if (usuario != null) {
            listaUsuarios.remove(usuario);
        }
        return usuario;

    }

    public Usuario alterar(Usuario usuarioNovo) {

        Usuario usuarioVelho = consultar(usuarioNovo.getNome());

        if (usuarioVelho != null) {

            usuarioVelho.setTelefone(usuarioNovo.getEmail());
            usuarioVelho.setEmail(usuarioNovo.getEmail());

        }
        return usuarioVelho;

    }

}
