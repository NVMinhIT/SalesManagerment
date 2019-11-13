package com.example.salesmanagerment.screen.sales.choosetable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.listeners.IOnItemClickListener;
import com.example.salesmanagerment.data.model.response.base.Table;


import java.util.List;


public class AdapterOrderFood extends RecyclerView.Adapter<AdapterOrderFood.MyViewHolder> {
    private Context mContext;
    private List<Table> list;
    private IOnItemClickListener<Table> iOnClickListener;

    public AdapterOrderFood(Context mContext, List<Table> list) {
        this.mContext = mContext;
        this.list = list;
        if (mContext instanceof IOnItemClickListener) {
            this.iOnClickListener = (IOnItemClickListener<Table>) mContext;
        }
    }

    public void setOnClickListeners(IOnItemClickListener<Table> listTable) {
        iOnClickListener = listTable;
    }

    @NonNull
    @Override
    public AdapterOrderFood.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_table_status, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterOrderFood.MyViewHolder holder, int position) {

        final Table table = list.get(position);
        holder.textViewNameTable.setText(table.getNameTable());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iOnClickListener.onItemClick(table);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNameTable;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNameTable = itemView.findViewById(R.id.tv_Name_Table);
        }
    }
}
