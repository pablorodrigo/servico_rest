package br.com.palheta.uninorte.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.palheta.uninorte.R;
import br.com.palheta.uninorte.presenter.UsuarioPresenter;

/**
 * Created by pablo on 5/9/17.
 */
public class UsuariosFragment extends BaseFragment {
    private String categoria;
    protected RecyclerView recyclerView;


    // Método para instanciar esse fragment pelo tipo.
    public static UsuariosFragment newInstance(String categoria) {
        Bundle args = new Bundle();
        args.putString("categoria", categoria);
        UsuariosFragment f = new UsuariosFragment();
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // Lê o tipo dos argumentos.
            this.categoria = getArguments().getString("categoria");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_usuarios, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        //getActivity().setTitle(categoria.toUpperCase());

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        taskUsuarios();

    }

    private void taskUsuarios() {

        // Busca os eventos atraves da url
        UsuarioPresenter usuarioPresenter = new UsuarioPresenter(getContext());
        usuarioPresenter.carregarUsuarios(recyclerView);

    }

}
