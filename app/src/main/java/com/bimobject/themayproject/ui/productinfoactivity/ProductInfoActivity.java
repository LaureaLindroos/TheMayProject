package com.bimobject.themayproject.ui.productinfoactivity;

import android.os.AsyncTask;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bimobject.themayproject.R;
import com.bimobject.themayproject.adapters.ExpandableListAdapter;
import com.bimobject.themayproject.adapters.ViewPagerAdapter;
import com.bimobject.themayproject.constants.URL;
import com.bimobject.themayproject.dto.ProductDetails;
import com.bimobject.themayproject.helpers.RequestService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductInfoActivity extends AppCompatActivity {

    ViewPager viewPager;
    ArrayList<String> listDataHeader;
    HashMap <String,List<String>> listDataChild;
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
            prepareListData();
            ExpandableListAdapter listAdapter = new ExpandableListAdapter(getApplicationContext(), listDataHeader, listDataChild);
            expandableListView.setAdapter(listAdapter);


        }

        @Override
        protected ProductDetails doInBackground(String... strings) {
            return RequestService.getProductDetails(strings[0]);
        }
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        listDataHeader.add("Description");

        ArrayList <String> description = new ArrayList<String>();
        description.add("Here is an awesome Description");

        listDataChild.put(listDataHeader.get(0), description);

    }
}