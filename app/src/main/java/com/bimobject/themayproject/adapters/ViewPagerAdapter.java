package com.bimobject.themayproject.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bimobject.themayproject.R;
import com.squareup.picasso.Picasso;

public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private String[] images;

    public ViewPagerAdapter(Context context, String[] images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public int getCount() {
        if(images != null) {

            return images.length;
        }
        else
            return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.view_pager_image_view);
        Picasso.with(context)
                .load(images[position])
                .fit()
                .into(imageView);

        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }

    public void setImages(String[] images) {
        this.images = images;
    }
}
