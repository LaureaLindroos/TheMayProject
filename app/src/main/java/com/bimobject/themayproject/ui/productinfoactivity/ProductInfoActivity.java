package com.bimobject.themayproject.ui.productinfoactivity;

import android.os.AsyncTask;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bimobject.themayproject.DotIndicator;
import com.bimobject.themayproject.R;
import com.bimobject.themayproject.adapters.ViewPagerAdapter;
import com.bimobject.themayproject.constants.STRINGS;
import com.bimobject.themayproject.dto.ProductDetails;
import com.bimobject.themayproject.helpers.RequestService;

import java.lang.ref.WeakReference;

public class ProductInfoActivity extends AppCompatActivity {

    public  ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    public LinearLayout sliderDotspanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);

        viewPager = findViewById(R.id.activity_product_info_view_pager);
        viewPagerAdapter = new ViewPagerAdapter(getApplicationContext(), null);
        viewPager.setAdapter(viewPagerAdapter);
        sliderDotspanel = (LinearLayout) findViewById(R.id.activity_product_info_ll_slider_dots);

        if (getIntent().hasExtra("productId")) {
            String productId = getIntent().getStringExtra("productId");
            new getProductDetailsTask(this).execute(productId);
        }
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

            int dotsCount = activity.get().viewPagerAdapter.getCount();
            ImageView[] dots = new ImageView[dotsCount];

            // Unsure of which I need to send in....
            DotIndicator dotIndicator = new DotIndicator(dotsCount, dots, activity);
            dotIndicator.createIndicator();

        }

        @Override
        protected ProductDetails doInBackground(String... strings) {
            return RequestService.getProductDetails(strings[0]);
        }
    }
}