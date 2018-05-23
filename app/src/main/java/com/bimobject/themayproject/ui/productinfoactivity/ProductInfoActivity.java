package com.bimobject.themayproject.ui.productinfoactivity;

import android.os.AsyncTask;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.bimobject.themayproject.R;
import com.bimobject.themayproject.adapters.ViewPagerAdapter;
import com.bimobject.themayproject.dto.ProductDetails;
import com.bimobject.themayproject.helpers.RequestService;

public class ProductInfoActivity extends AppCompatActivity {

    ViewPager viewPager;
    private String[] images = new String[]{
            "https://cdn.atwilltech.com/flowerdatabase/s/sweetly-scented-bouquet-of-flowers-VA02810.425.jpg",
            "http://images.shopflowers.net/images/products/VL_768618.jpg",
            "https://fyf.tac-cdn.net/images/products/large/TEV49-1.jpg"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);

        viewPager = (ViewPager) findViewById(R.id.activity_product_info_view_pager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this, images);
        viewPager.setAdapter(viewPagerAdapter);

        String productId = getIntent().getStringExtra("productId");
        new getProductDetailsTask().execute(productId);

    }

    private class getProductDetailsTask extends AsyncTask<String, String, ProductDetails> {
        @Override
        protected void onPostExecute(ProductDetails productDetails) {

            TextView product_title = findViewById(R.id.activity_product_info_tv_product_title);
            product_title.setText(productDetails.getName());
        }

        @Override
        protected ProductDetails doInBackground(String... strings) {
            return RequestService.getProductDetails(strings[0]);
        }
    }
}