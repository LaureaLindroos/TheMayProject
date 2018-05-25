package com.bimobject.themayproject.ui.productinfoactivity;

import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bimobject.themayproject.R;
import com.bimobject.themayproject.adapters.ViewPagerAdapter;
import com.bimobject.themayproject.constants.STRINGS;
import com.bimobject.themayproject.dto.ProductDetails;
import com.bimobject.themayproject.helpers.RequestService;

import java.lang.ref.WeakReference;

public class ProductInfoActivity extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    int dotscount;
    ImageView[] dots;

    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);

        ViewPager viewPager = findViewById(R.id.activity_product_info_view_pager);
        viewPagerAdapter = new ViewPagerAdapter(getApplicationContext(), null);
        viewPager.setAdapter(viewPagerAdapter);

        if(getIntent().hasExtra("productId")) {
            String productId = getIntent().getStringExtra("productId");
            new getProductDetailsTask(this).execute(productId);
        }
     
            viewPager = findViewById(R.id.activity_product_info_view_pager);
            sliderDotspanel = (LinearLayout) findViewById(R.id.activity_product_info_ll_slider_dots);

            viewPagerAdapter = new ViewPagerAdapter(getApplicationContext(), productDetails.getImageUrls());
            viewPager.setAdapter(viewPagerAdapter);
      
            dotscount = viewPagerAdapter.getCount();
            dots = new ImageView[dotscount];
    }

    private static class getProductDetailsTask extends AsyncTask<String, String, ProductDetails> {

        private WeakReference<ProductInfoActivity> activity;

        public getProductDetailsTask(ProductInfoActivity context) {
            this.activity = new WeakReference<>(context);
        }

        @Override
        protected void onPreExecute() {
            Toast.makeText(activity.get().getApplicationContext(), STRINGS.LOADING, Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(ProductDetails productDetails) {

            TextView product_title = activity.get().findViewById(R.id.activity_product_info_tv_product_title);
            product_title.setText(productDetails.getName());

            activity.get().viewPagerAdapter.setImages(productDetails.getImageUrls());
            activity.get().viewPagerAdapter.notifyDataSetChanged();

        

            for(int i = 0; i < dotscount; i++){

                dots[i] = new ImageView(getApplicationContext());
                dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tab_indicator_default));

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                params.setMargins(8, 0, 8, 0);

                sliderDotspanel.addView(dots[i], params);

            }

            dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tab_indicator_selected));

            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {

                    for(int i = 0; i< dotscount; i++){
                        dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tab_indicator_default));
                    }

                    dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tab_indicator_selected));

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });



        }

        @Override
        protected ProductDetails doInBackground(String... strings) {
            return RequestService.getProductDetails(strings[0]);
        }
    }
}