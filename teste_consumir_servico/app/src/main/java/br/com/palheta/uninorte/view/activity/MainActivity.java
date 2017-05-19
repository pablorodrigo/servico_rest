package br.com.palheta.uninorte.view.activity;

import android.os.Bundle;

import br.com.palheta.uninorte.R;
import br.com.palheta.uninorte.view.fragment.UsuariosFragment;

/**
 * Created by pablo on 4/9/17.
 */
public class MainActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpToolbar();
        setupNavDrawer();

        replaceFragment(new UsuariosFragment());
    }


}
