package com.bimobject.themayproject;

import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bimobject.themayproject.ui.productinfoactivity.ProductInfoActivity;

import java.lang.ref.WeakReference;

public class DotIndicator {

    private int dotscount;
    private ImageView[] dots;
    private WeakReference<ProductInfoActivity> activity;

    public DotIndicator(int dotscount, ImageView[] dots, WeakReference<ProductInfoActivity> activity) {
        this.dotscount = dotscount;
        this.dots = dots;
        this.activity = activity;
    }

    public void createIndicator() {

        for (int i = 0; i < dotscount; i++) {

            dots[i] = new ImageView(activity.get().getApplicationContext());
            dots[i].setImageDrawable(ContextCompat.getDrawable(activity.get().getApplicationContext(), R.drawable.tab_indicator_default));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            activity.get().sliderDotspanel.addView(dots[i], params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(activity.get().getApplicationContext(), R.drawable.tab_indicator_selected));

        activity.get().viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < dotscount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(activity.get().getApplicationContext(), R.drawable.tab_indicator_default));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(activity.get().getApplicationContext(), R.drawable.tab_indicator_selected));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    public int getDotscount() {
        return dotscount;
    }

    public void setDotscount(int dotscount) {
        this.dotscount = dotscount;
    }

    public ImageView[] getDots() {
        return dots;
    }

    public void setDots(ImageView[] dots) {
        this.dots = dots;
    }

    public WeakReference<ProductInfoActivity> getActivity() {
        return activity;
    }

    public void setActivity(WeakReference<ProductInfoActivity> activity) {
        this.activity = activity;
    }
}
