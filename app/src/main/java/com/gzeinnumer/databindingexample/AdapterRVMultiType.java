package com.gzeinnumer.databindingexample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gzeinnumer.databindingexample.databinding.ItemAdapterRvBinding;

import java.util.ArrayList;

public class AdapterRVMultiType extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<String> list;

    public AdapterRVMultiType() {
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
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_NORMAL){
            ItemAdapterRvBinding binding = ItemAdapterRvBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new AdapterRV.MyHolder(binding);
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == TYPE_NORMAL) {
            ((MyHolder) holder).bindData(list.get(position), onClick);
        } else {
            throw new IllegalStateException("Unexpected value: " + holder.getItemViewType());
        }
    }

    @Override
    public int getItemCount() {
        return Math.max(list.size(), 0);
    }

    private static int TYPE_NORMAL = 1;

    @Override
    public int getItemViewType(int position) {
        if (position!=-1){
            return TYPE_NORMAL;
        } else {
            return 0;
        }
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
