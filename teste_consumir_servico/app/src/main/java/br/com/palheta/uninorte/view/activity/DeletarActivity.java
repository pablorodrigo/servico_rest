package br.com.palheta.uninorte.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import br.com.palheta.uninorte.R;
import br.com.palheta.uninorte.presenter.UsuarioPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class DeletarActivity extends BaseActivity {

    @BindView(R.id.edt_id)
    EditText edId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deletar);
        ButterKnife.bind(this);
        setUpToolbar();
        getSupportActionBar().setTitle(R.string.deletar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    /**
     * evento executado ao clicar no botao deletar
     */
    @OnClick(R.id.btnConsultar)
    public void deletar() {

        UsuarioPresenter usuarioPresenter = new UsuarioPresenter(this);
        usuarioPresenter.deletarUsuario(edId.getText().toString());
        Intent intent = new Intent(getContext(), MainActivity.class);
        getActivity().startActivity(intent);

    }

}
