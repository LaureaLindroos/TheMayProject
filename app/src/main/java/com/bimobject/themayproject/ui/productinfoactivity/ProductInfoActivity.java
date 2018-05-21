package com.bimobject.themayproject.ui.productinfoactivity;

import android.os.AsyncTask;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bimobject.themayproject.R;
import com.bimobject.themayproject.adapters.ViewPagerAdapter;
import com.bimobject.themayproject.dto.ProductDetails;
import com.bimobject.themayproject.helpers.RequestService;

public class ProductInfoActivity extends AppCompatActivity {

    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);

        ImageView productImage = findViewById(R.id.product_Image);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);

        String productId = getIntent().getStringExtra("productId");
        new getProductInfoTask().execute(productId);


    }

    private class getProductInfoTask extends AsyncTask<String, String, ProductDetails> {

        @Override
        protected void onPostExecute(ProductDetails product) {
            TextView product_info = findViewById(R.id.product_Info);
            String desc = ((ProductDetails) product).getDescriptionHtml();
            product_info.setText((CharSequence) product);
        }

        @Override
        protected ProductDetails doInBackground(String... strings) {
            return RequestService.getRequestdetails("/products/" + strings[0]);
        }
    }
}


