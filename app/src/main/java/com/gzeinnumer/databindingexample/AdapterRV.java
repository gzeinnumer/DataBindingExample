package com.gzeinnumer.databindingexample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.gzeinnumer.databindingexample.databinding.ItemAdapterRvBinding;

import java.util.ArrayList;

public class AdapterRV extends RecyclerView.Adapter<AdapterRV.MyHolder> {

    ArrayList<String> list;

    public AdapterRV() {
        list = new ArrayList<String>();
    }

    public MyOnClick onClick;
    public void setOnClick(MyOnClick onClick) { this.onClick = onClick; }
    interface MyOnClick{
        void myOnClick(int position);
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void addList(String data) {
        this.list.add(data);
        notifyItemChanged(list.size()-1); // untuk dinamis recyclerview
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemAdapterRvBinding binding = ItemAdapterRvBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.bindData(list.get(position), onClick);
    }

    @Override
    public int getItemCount() {
        return Math.max(list.size(), 0);
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        ItemAdapterRvBinding binding;

        public MyHolder(ItemAdapterRvBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void bindData(String data, final MyOnClick onClick){
            binding.text.setText(data);

            if(onClick!=null){
                binding.text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClick.myOnClick(getAdapterPosition());
                    }
                });
            }
        }
    }
}
