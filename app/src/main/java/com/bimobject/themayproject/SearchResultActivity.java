package com.bimobject.themayproject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class SearchResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        if(getIntent().hasExtra("search")){
        new setTextAsyncTask().execute(getIntent().getStringExtra("search"));
        }

    }


    private class setTextAsyncTask extends AsyncTask<String, String, List<Product>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(List<Product> products) {

            ProductListAdapter adapter = new ProductListAdapter(SearchResultActivity.this,R.layout.list_item_layout, products);
            ListView listView = findViewById(R.id.search_result_list);
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        }

        @Override
        protected List<Product> doInBackground(String... strings) {
            return RequestService.getRequest(strings[0], "/products");
        }
    }
}
