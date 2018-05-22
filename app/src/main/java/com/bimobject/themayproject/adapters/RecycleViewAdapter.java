package com.bimobject.themayproject.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bimobject.themayproject.R;
import com.bimobject.themayproject.dto.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {

    private List<Product> products;

    public RecycleViewAdapter() {
        products = createNewDataSet();
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Product product = products.get(position);
        holder.product_title.setText(product.getName());
        holder.brand_name.setText(product.getBrand().getName());

        //TODO: Find better solution to find context
        Picasso.with(holder.image.getContext())
                .load(product.getImageUrl())
                .placeholder(R.drawable.progress_animation)
                .into(holder.image);

        Picasso.with(holder.brand_logo.getContext())
                .load(product.getBrand().getImageUrl())
                .placeholder(R.drawable.progress_animation)
                .into(holder.brand_logo);
    }

    @Override
    public int getItemCount() {
        //Return size of list
        return products.size();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView product_title;
        TextView brand_name;
        ImageView image;
        ImageView brand_logo;

        public MyViewHolder(View itemView) {
            super(itemView);
            product_title = itemView.findViewById(R.id.layout_list_item_tv_product_name);
            brand_name = itemView.findViewById(R.id.layout_list_item_tv_product_brand);
            image = itemView.findViewById(R.id.layout_list_item_img_product);
            brand_logo = itemView.findViewById(R.id.layout_list_item_img_product_logo);
        }
    }

    public ArrayList<Product> createNewDataSet(){
        return new ArrayList<>();
    }

    public void addAll(List<Product> objects){
        products.addAll(objects);
    }
}