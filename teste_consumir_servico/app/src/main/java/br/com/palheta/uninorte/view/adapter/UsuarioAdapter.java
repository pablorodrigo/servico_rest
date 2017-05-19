package br.com.palheta.uninorte.view.adapter;

/**
 * Created by pablo on 08/05/2017.
 */

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.palheta.uninorte.R;
import br.com.palheta.uninorte.model.bean.Usuario;


public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.UsuariosViewHolder>{
    protected static final String TAG = "listagemUsuarios";
    private List<Usuario> listaUsuarios;
    private final Context context;
    private UsuarioOnClickListener usuarioOnClickListener;

    public UsuarioAdapter(Context context, List<Usuario> listaUsuarios, UsuarioOnClickListener
            eventoOnClickListener) {
        this.context = context;
        this.listaUsuarios = listaUsuarios;
        this.usuarioOnClickListener = eventoOnClickListener;
    }

    @Override
    public int getItemCount() {
        return this.listaUsuarios != null ? this.listaUsuarios.size() : 0;
    }

    @Override
    public UsuariosViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_usuarios, viewGroup, false);
        UsuariosViewHolder holder = new UsuariosViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final UsuariosViewHolder holder, final int position) {
        // Atualiza a view
        Usuario usuario = listaUsuarios.get(position);
        holder.tvId.setText(usuario.getId().toString());
        holder.tvNome.setText(usuario.getNome().toUpperCase());
        holder.tvTelone.setText(usuario.getTelefone().toUpperCase());
        holder.tvEmail.setText(usuario.getEmail().toUpperCase());

        // Click
        if (usuarioOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // A variável position é final
                    usuarioOnClickListener.onClickUsuario(holder.itemView, position);
                }
            });
        }
    }


    public interface UsuarioOnClickListener {
        public void onClickUsuario(View view, int idx);
    }

    // ViewHolder com as views
    public static class UsuariosViewHolder extends RecyclerView.ViewHolder {
        TextView tvId;
        TextView tvNome;
        TextView tvTelone;
        TextView tvEmail;
        CardView cardView;

        public UsuariosViewHolder(View view) {
            super(view);
            // Cria as views para salvar no ViewHolder
            tvId = (TextView) view.findViewById(R.id.tv_id);
            tvNome = (TextView) view.findViewById(R.id.tv_nome);
            tvTelone = (TextView) view.findViewById(R.id.tv_telefone);
            tvEmail = (TextView) view.findViewById(R.id.tv_email);
            cardView = (CardView) view.findViewById(R.id.card_view);
        }
    }




}

