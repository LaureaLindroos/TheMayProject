package com.bimobject.themayproject.ui.productinfoactivity;

import android.os.AsyncTask;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.bimobject.themayproject.R;
import com.bimobject.themayproject.adapters.ExpandableListAdapter;
import com.bimobject.themayproject.adapters.ViewPagerAdapter;
import com.bimobject.themayproject.dto.ProductDetails;
import com.bimobject.themayproject.helpers.RequestService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductInfoActivity extends AppCompatActivity {

    ViewPager viewPager;
    ArrayList<String> listDataHeader;
    HashMap <String,List<String>> listDataChild;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);

        viewPager = findViewById(R.id.activity_product_info_vp_slide);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);

        // get the listview
        ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.activity_product_info_lvExp);

        // preparing list data
        prepareListData();

        ExpandableListAdapter listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expandableListView.setAdapter(listAdapter);


        String productId = getIntent().getStringExtra("productId");
        new getProductDetailsTask().execute(productId);


    }
    private class getProductDetailsTask extends AsyncTask<String, String, ProductDetails>{
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

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        listDataHeader.add("Description");

        ArrayList <String> description = new ArrayList<String>();
        description.add("Here is an awesome Description");

        listDataChild.put(listDataHeader.get(0), description);

    }
}