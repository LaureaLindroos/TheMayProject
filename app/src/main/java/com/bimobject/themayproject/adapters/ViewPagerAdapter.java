package com.bimobject.themayproject.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bimobject.themayproject.R;
import com.bimobject.themayproject.dto.Product;
import com.bimobject.themayproject.dto.ProductDetails;
import com.bimobject.themayproject.ui.productinfoactivity.ProductInfoActivity;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private Objects objects;
    private LayoutInflater layoutInflater;
    private static Integer[] images = {R.drawable.slide1, R.drawable.slide2, R.drawable.slide3};

    ArrayList<String> arrayList;

    public ViewPagerAdapter(ProductInfoActivity context, ArrayList<String> arrayList, int custom_layout) {
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.arrayList = arrayList;
    }

    public ViewPagerAdapter(ProductInfoActivity productInfoActivity, int custom_layout) {
    }


    @Override
    public int getCount() {
        if(arrayList != null){
            return arrayList.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.layout_product_info_img_display);
        imageView.setImageResource(images[position]);

        Picasso.with(context).load(arrayList.get(position))
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        // loaded bitmap is here (bitmap)
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });

        container.addView(view);
        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);

        return view;




    }
    private {

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }
   /* @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent,) {

        ProductDetails productDetails = get;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_layout, parent, false);
        }
        ImageView images = convertView.findViewById(R.id.layout_product_info_img_display);
        Picasso.with(parent.getContext())
                .load(Arrays.toString(productDetails.getImageUrls()))
                .placeholder(R.drawable.progress_animation)
                .into(images);

    }*/
}
