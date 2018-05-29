package com.bimobject.themayproject.ui.productinfoactivity;

import android.os.AsyncTask;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bimobject.themayproject.DotIndicator;
import com.bimobject.themayproject.R;
import com.bimobject.themayproject.adapters.ExpandableListAdapter;
import com.bimobject.themayproject.adapters.ViewPagerAdapter;
import com.bimobject.themayproject.constants.STRINGS;
import com.bimobject.themayproject.dto.ProductInformation.ProductDetails;
import com.bimobject.themayproject.helpers.RequestService;

import java.lang.ref.WeakReference;


public class ProductInfoActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);

        if (getIntent().hasExtra("productId")) {
            String productId = getIntent().getStringExtra("productId");
            new getProductDetailsTask(this).execute(productId);
        }


        viewPager = findViewById(R.id.activity_product_info_view_pager);
        viewPagerAdapter = new ViewPagerAdapter(getApplicationContext(), null);
        viewPager.setAdapter(viewPagerAdapter);
    }

    public ViewPager getViewPager() {
        return viewPager;
    }

    public ViewPagerAdapter getViewPagerAdapter() {
        return viewPagerAdapter;

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


            DotIndicator dotIndicator = new DotIndicator(activity);
            dotIndicator.createIndicator();

            ExpandableListView expandableListView = activity.get().findViewById(R.id.activity_product_info_lvExp);
            PrepareProductInfo prepareProductInfo = new PrepareProductInfo(productDetails);
            ExpandableListAdapter listAdapter = new ExpandableListAdapter(activity.get(), prepareProductInfo.getListDataHeader(), prepareProductInfo.getListDataChild());
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