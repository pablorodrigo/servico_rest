package br.com.palheta.uninorte.view.activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import br.com.palheta.uninorte.R;
import br.com.palheta.uninorte.presenter.UsuarioPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ConsultarActivity extends BaseActivity {

    @BindView(R.id.edt_id)
    EditText edId;

    @BindView(R.id.tv_nome)
    TextView tvNome;

    @BindView(R.id.tv_telefone)
    TextView tvTelefone;

    @BindView(R.id.tv_email)
    TextView tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);
        ButterKnife.bind(this);
        setUpToolbar();
        getSupportActionBar().setTitle(R.string.consultar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    /**
     * evento executado ao clicar no botao consoltar
     */
    @OnClick(R.id.btnConsultar)
    public void consultar() {

        UsuarioPresenter usuarioPresenter = new UsuarioPresenter(this);
        usuarioPresenter.consultarUsuario(edId.getText().toString(), tvNome, tvTelefone, tvEmail);

    }

}
