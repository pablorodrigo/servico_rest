package br.com.uninorte.soa.business;

import java.util.List;

import br.com.uninorte.soa.model.bean.Usuario;
import br.com.uninorte.soa.model.dao.UsuarioDAO;

public class UsuarioBusiness {

    private UsuarioDAO dao = new UsuarioDAO();

    public List<Usuario> listar() {
        return dao.listar();
    }

    public void cadastrar(Usuario usuario) {
        dao.cadastrar(usuario);
    }

    public Usuario consultar(Long id) {

        return dao.consultar(id);
    }

    public Usuario deletar(Long id) {

        return dao.deletar(id);

    }

    public Usuario alterar(Usuario usuario) {
        return dao.alterar(usuario);
    }

}
