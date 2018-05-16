package com.bimobject.themayproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductListAdapter extends ArrayAdapter<Product>{

    public ProductListAdapter(@NonNull Context context, int resource, @NonNull List<Product> objects) {
        super(context, resource, objects);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Product product = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_layout, parent, false);
        }

        TextView product_title = convertView.findViewById(R.id.list_item_product_name);
        TextView brand_name = convertView.findViewById(R.id.list_item_product_brand);
        ImageView image = convertView.findViewById(R.id.list_item_product_image);
        ImageView brand_logo = convertView.findViewById(R.id.list_item_product_logo);

        product_title.setText(product.getName());
        brand_name.setText(product.getBrand().getName());
        Picasso.with(parent.getContext()).load(product.getImageUrl()).into(image);
        Picasso.with(parent.getContext()).load(product.getBrand().getImageUrl()).into(brand_logo);

        return convertView;
    }

}
