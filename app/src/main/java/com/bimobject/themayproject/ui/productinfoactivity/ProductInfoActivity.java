package com.bimobject.themayproject.ui.productinfoactivity;

import android.os.AsyncTask;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.bimobject.themayproject.R;
import com.bimobject.themayproject.adapters.ExpandableListAdapter;
import com.bimobject.themayproject.adapters.ViewPagerAdapter;
import com.bimobject.themayproject.dto.ProductInformation.ProductDetails;
import com.bimobject.themayproject.helpers.RequestService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ProductInfoActivity extends AppCompatActivity {

    ViewPager viewPager;
    ArrayList<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    String[] imageUrls;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);


        String productId = getIntent().getStringExtra("productId");
        new getProductDetailsTask().execute(productId);

    }

    private class getProductDetailsTask extends AsyncTask<String, String, ProductDetails> {
        @Override
        protected void onPostExecute(ProductDetails productDetails) {

            TextView product_title = findViewById(R.id.activity_product_info_tv_product_title);
            product_title.setText(productDetails.getName());

            viewPager = (ViewPager) findViewById(R.id.activity_product_info_view_pager);
            ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getApplicationContext(), productDetails.getImageUrls());
            viewPager.setAdapter(viewPagerAdapter);

            ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.activity_product_info_lvExp);
            PrepareProductInfo prepareProductInfo = new PrepareProductInfo(productDetails);
            ExpandableListAdapter listAdapter = new ExpandableListAdapter(getApplicationContext(), prepareProductInfo.getListDataHeader(), prepareProductInfo.getListDataChild());
            expandableListView.setAdapter(listAdapter);


            final int[] prevExpandPosition = {-1};
            expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
                @Override
                public void onGroupExpand(int groupPosition) {
                    if (prevExpandPosition[0] >= 0 && prevExpandPosition[0] != groupPosition) {
                        expandableListView.collapseGroup(prevExpandPosition[0]);
                    }
                    prevExpandPosition[0] = groupPosition;

                }
            });


        }


        @Override
        protected ProductDetails doInBackground(String... strings) {
            return RequestService.getProductDetails(strings[0]);
        }

    }

}