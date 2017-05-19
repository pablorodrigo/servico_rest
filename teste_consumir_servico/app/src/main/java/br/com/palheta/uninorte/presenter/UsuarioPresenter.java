package br.com.palheta.uninorte.presenter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;
import org.parceler.apache.commons.collections.bag.SynchronizedSortedBag;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import br.com.palheta.uninorte.R;
import br.com.palheta.uninorte.model.bean.Usuario;
import br.com.palheta.uninorte.model.task.AsynsUsuarioHttpClient;
import br.com.palheta.uninorte.util.Urls;
import br.com.palheta.uninorte.view.activity.ConsultarActivity;
import br.com.palheta.uninorte.view.adapter.UsuarioAdapter;
import butterknife.BindView;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;

/**
 * Created by pablo on 5/10/17.
 */

public class UsuarioPresenter {

    private Context context;

    private List<Usuario> listaUsuarios = new ArrayList<>();

    public UsuarioPresenter(Context context) {
        this.context = context;
    }

    /**
     * metodo busca no webservice todos os eventos cadastrados e atualiza a view
     *
     * @param recyclerView_usuarios
     */
    public void carregarUsuarios(final RecyclerView recyclerView_usuarios) {
        AsynsUsuarioHttpClient asynsEventoHttpClient = new AsynsUsuarioHttpClient();

        asynsEventoHttpClient.get(Urls.BASE_URL + Urls.LISTAR, null, new JsonHttpResponseHandler() {


            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                // called when response HTTP status is "200 OK"
                Log.d("response", "" + response.toString());

                Usuario usuario = new Usuario();

                try {

                    for (int i = 0; i < response.length(); i++) {

                        //pegar um produto da lista

                        JSONObject eventoJson = response.getJSONObject(i);
                        usuario.setId(eventoJson.getLong("id"));
                        Log.d("id", "" + eventoJson.getLong("id"));
                        usuario.setNome(eventoJson.getString("nome"));
                        Log.d("nome", "" + eventoJson.getString("nome"));
                        usuario.setTelefone(eventoJson.getString("telefone"));
                        Log.d("telefone", "" + eventoJson.getString("telefone"));
                        usuario.setEmail(eventoJson.getString("email"));
                        Log.d("email", "" + eventoJson.getString("email"));
                        listaUsuarios.add(usuario);
                        usuario = new Usuario();
                    }

                    recyclerView_usuarios.setAdapter(new UsuarioAdapter(context, listaUsuarios, onClickUsuario()));

                } catch (JSONException e) {


                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }


        });

    }

    /**
     * metodo consulta no webservice o usuario por id e mostra na tela o usuario
     *
     * @param id
     */
    public void consultarUsuario(String id, final TextView edNome, final TextView edTelefone, final TextView edEmail) {


        AsynsUsuarioHttpClient asynsEventoHttpClient = new AsynsUsuarioHttpClient();

        asynsEventoHttpClient.get(Urls.BASE_URL + Urls.CONSULTAR + id, null, new JsonHttpResponseHandler() {


            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // called when response HTTP status is "200 OK"
                Log.d("response", "" + response.toString());


                try {

                    edNome.setText(response.get("nome").toString().toUpperCase());
                    edTelefone.setText(response.get("telefone").toString().toUpperCase());
                    edEmail.setText(response.get("email").toString().toUpperCase());

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(context, "Usuario não existe", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }

        });

    }

    /**
     * metodo consulta no webservice o usuario por id e deleta esse usuario
     *
     * @param id
     */
    public void deletarUsuario(String id) {


        AsynsUsuarioHttpClient asynsEventoHttpClient = new AsynsUsuarioHttpClient();

        asynsEventoHttpClient.delete(Urls.BASE_URL + Urls.DELETAR + id, null, new JsonHttpResponseHandler() {


            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // called when response HTTP status is "200 OK"
                Log.d("response", "" + response.toString());



            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }

        });

    }

    /**
     * metodo que enviar um usuario pro WS usando RequestParams
     *
     * @param usuario
     */
    public void salvarUsuario(Usuario usuario) {

        RequestParams params = new RequestParams();
        params.setUseJsonStreamer(true);
        //params.put("id", 10);
        params.put("nome", usuario.getNome());
        params.put("telefone", usuario.getTelefone());
        params.put("email", usuario.getEmail());

        AsynsUsuarioHttpClient asynsEventoHttpClient = new AsynsUsuarioHttpClient();

        asynsEventoHttpClient.post(Urls.BASE_URL + Urls.CADASTRAR, params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // called when response HTTP status is "200 OK"
                Log.d("response", "" + response.toString());


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.d("responseError", "" + responseString);
            }

        });

    }

    /**
     * metodo para enviar um usuario pro WS no formato Json
     *
     * @param usuario
     */
    public void salvarUsuarioJson(Usuario usuario) {

        JSONObject jsonParams = new JSONObject();
        try {
            //jsonParams.put("id", 10);
            jsonParams.put("nome", usuario.getNome());
            jsonParams.put("telefone", usuario.getTelefone());
            jsonParams.put("email", usuario.getEmail());
        } catch (JSONException e) {
            e.printStackTrace();
        }


        StringEntity entity = null;
        try {
            entity = new StringEntity(jsonParams.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));

        AsynsUsuarioHttpClient asynsEventoHttpClient = new AsynsUsuarioHttpClient();

        asynsEventoHttpClient.postJson(null, Urls.BASE_URL + Urls.CADASTRAR, entity, "application/json", new JsonHttpResponseHandler() {


            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // called when response HTTP status is "200 OK"
                Log.d("response", "" + response.toString());


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.d("responseError", "" + responseString);
            }

        });

    }

    /**
     * metodo para enviar um usuario pro WS no formato Json a alterar
     *
     * @param usuario
     */
    public void alterarUsuarioJson(Usuario usuario) {

        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("id", usuario.getId());
            jsonParams.put("nome", usuario.getNome());
            jsonParams.put("telefone", usuario.getTelefone());
            jsonParams.put("email", usuario.getEmail());
        } catch (JSONException e) {
            e.printStackTrace();
        }


        StringEntity entity = null;
        try {
            entity = new StringEntity(jsonParams.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));

        AsynsUsuarioHttpClient asynsEventoHttpClient = new AsynsUsuarioHttpClient();

        asynsEventoHttpClient.putJson(null, Urls.BASE_URL + Urls.ALTERAR, entity, "application/json", new JsonHttpResponseHandler() {


            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // called when response HTTP status is "200 OK"
                Log.d("response", "" + response.toString());


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.d("responseError", "" + responseString);
            }

        });

    }


    /**
     * Para cada item da lista executa esse metodo
     */
    private UsuarioAdapter.UsuarioOnClickListener onClickUsuario() {
        return new UsuarioAdapter.UsuarioOnClickListener() {
            @Override
            public void onClickUsuario(View view, int idx) {

                //alguma ação

            }

        };
    }

}
