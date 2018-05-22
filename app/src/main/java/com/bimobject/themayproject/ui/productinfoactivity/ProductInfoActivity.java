package com.bimobject.themayproject.ui.productinfoactivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bimobject.themayproject.R;
import com.bimobject.themayproject.adapters.ViewPagerAdapter;
import com.bimobject.themayproject.dto.ProductDetails;
import com.bimobject.themayproject.helpers.RequestService;

import java.util.ArrayList;

public class ProductInfoActivity extends AppCompatActivity {

    ViewPager viewPager;
    ImageView imageView;
    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);

        viewPager = (ViewPager) findViewById(R.id.activity_product_info_vp_slide);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this, R.layout.custom_layout);
        viewPager.setAdapter(viewPagerAdapter);

        String productId = getIntent().getStringExtra("productId");
        new getProductDetailsTask().execute(productId);

        imageView = findViewById(R.id.layout_product_info_img_display);





    }
    private class getProductDetailsTask extends AsyncTask<String, String, ProductDetails>{

        @Override
        protected void onPostExecute(ProductDetails productDetails) {

            TextView product_title = findViewById(R.id.activity_product_info_tv_product_title);
            product_title.setText(productDetails.getName());


            /*if (productDetails != null) {
                product_image.setImageBitmap(productDetails.getImageUrls());
                dialog.dismiss();
            } else {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Image Does Not Exist...",
                        Toast.LENGTH_LONG).show();
            }*/


        }

        @Override
        protected ProductDetails doInBackground(String... strings) {
            return RequestService.getProductDetails(strings[0]);
        }



    }
    private class AsyncGettingBitmapFromUrl extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(ProductInfoActivity.this);
            dialog.setMessage("Loading Image...");
            dialog.show();
        }

        @Override
        protected Bitmap doInBackground(String... params) {

            System.out.println("doInBackground");

            Bitmap bitmap = null;


            bitmap = AppMethods.downloadImage(params[0]);

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            ImageView product_image = findViewById(R.id.layout_product_info_img_display);
            product_image.setImageBitmap(bitmap.ge());

            System.out.println("bitmap" + bitmap);

        }
    }
}