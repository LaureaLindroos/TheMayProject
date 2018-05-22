package com.bimobject.themayproject.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bimobject.themayproject.dto.Product;
import com.bimobject.themayproject.R;
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

        TextView product_title = convertView.findViewById(R.id.layout_list_item_tv_product_name);
        TextView brand_name = convertView.findViewById(R.id.layout_list_item_tv_product_brand);
        ImageView image = convertView.findViewById(R.id.layout_list_item_img_product);
        ImageView brand_logo = convertView.findViewById(R.id.layout_list_item_img_product_logo);

        product_title.setText(product.getName());
        brand_name.setText(product.getBrand().getName());
        Picasso.with(parent.getContext())
                .load(product.getImageUrl())
                .placeholder(R.drawable.progress_animation)
                .into(image);
        Picasso.with(parent.getContext()).load(product.getBrand().getImageUrl()).into(brand_logo);

        convertView.setTag(product);

        return convertView;
    }

}
