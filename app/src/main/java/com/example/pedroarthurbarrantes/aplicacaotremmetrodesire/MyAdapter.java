package com.example.pedroarthurbarrantes.aplicacaotremmetrodesire;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

    List<EstacaoData> list = new ArrayList<>();
    ItemClickListener itemClickListener;

    public MyAdapter(List<EstacaoData> list) {
        this.list = list;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {

        final EstacaoData userData = list.get(position);

        holder.tv_name.setText(userData.getNome());
        holder.tv_email.setText(userData.getUrl());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.OnItemClick(position,userData);
            }
        });

        holder.tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder{

        TextView tv_name,tv_email,tv_delete;

        public MyHolder(View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_name_item);
            tv_email = itemView.findViewById(R.id.civ_image);
            tv_delete = itemView.findViewById(R.id.civ_delete);
        }
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    public void UpdateData(int position,EstacaoData userData){

        list.remove(position);
        list.add(userData);
        notifyItemChanged(position);
        notifyDataSetChanged();
    }

}
