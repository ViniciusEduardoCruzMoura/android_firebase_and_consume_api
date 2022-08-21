package com.example.a2_avaliativo_mobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    ArrayList<Cliente> clienteArrayList = new ArrayList<>();

    public RecyclerAdapter(ArrayList<Cliente> clienteArrayList) {
        this.clienteArrayList = clienteArrayList;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView mTextViewNome;
        TextView mTextViewEmail;
        ImageView mImageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewNome = itemView.findViewById(R.id.textViewNome);
            mTextViewEmail = itemView.findViewById(R.id.textViewEmail);
            mImageView = itemView.findViewById(R.id.imageView);

        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String nome = clienteArrayList.get(position).getFirstName();
        String email = clienteArrayList.get(position).getEmail();
        String imagem = clienteArrayList.get(position).getAvatar();

        holder.mTextViewNome.setText(nome);
        holder.mTextViewEmail.setText(email);
        Picasso.get().load(imagem).into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return clienteArrayList.size();
    }
}