package com.bimobject.themayproject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SearchResultActivity extends AppCompatActivity {

    static int page = 1;
    static ProductListAdapter adapter;
    String search;
    LoadListItemsTask loadListItemsTask;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        if(getIntent().hasExtra("search")){
            search = getIntent().getStringExtra("search");
        }

        adapter = new ProductListAdapter(SearchResultActivity.this,R.layout.list_item_layout, new ArrayList<Product>());
        ListView listView = findViewById(R.id.search_result_list);
        listView.setAdapter(adapter);

        loadListItemsTask = new LoadListItemsTask();
        loadListItemsTask.execute(search);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {


            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                if (view.getLastVisiblePosition() == adapter.getCount() - 1 && loadListItemsTask.getStatus() == AsyncTask.Status.FINISHED){
                    loadListItemsTask = new LoadListItemsTask();
                    loadListItemsTask.execute(search);
                }
            }
        });
    }


    private class LoadListItemsTask extends AsyncTask<String, String, List<Product>> {

        @Override
        protected void onPreExecute() {
            if(adapter.getCount() != 0) {
                Toast.makeText(SearchResultActivity.this, "Fetching more products..", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onPostExecute(List<Product> products) {

            adapter.addAll(products);
            adapter.notifyDataSetChanged();

        }

        @Override
        protected List<Product> doInBackground(String... strings) {
            return RequestService.getRequest(strings[0], "/products", page++);
        }
    }
}
