package br.com.palheta.uninorte.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import br.com.palheta.uninorte.R;
import br.com.palheta.uninorte.model.bean.Usuario;
import br.com.palheta.uninorte.presenter.UsuarioPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AlterarActivity extends BaseActivity {

    @BindView(R.id.edt_id)
    EditText edtId;

    @BindView(R.id.edt_nome)
    EditText edtNome;

    @BindView(R.id.edt_telefone)
    EditText edtTelefone;

    @BindView(R.id.edt_email)
    EditText edtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar);
        ButterKnife.bind(this);
        setUpToolbar();
        getSupportActionBar().setTitle(R.string.alterar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    /**
     * evento executado ao clicar no botao alterar
     */
    @OnClick(R.id.btnAlterar)
    public void alterar() {

        Usuario usuario = new Usuario();
        usuario.setId(Long.parseLong(edtId.getText().toString()));
        usuario.setNome(edtNome.getText().toString());
        usuario.setTelefone(edtTelefone.getText().toString());
        usuario.setEmail(edtEmail.getText().toString());

        UsuarioPresenter usuarioPresenter = new UsuarioPresenter(this);
        //usuarioPresenter.salvarUsuario(usuario);
        usuarioPresenter.alterarUsuarioJson(usuario);
        Intent intent = new Intent(getContext(), MainActivity.class);
        getActivity().startActivity(intent);

    }

}
