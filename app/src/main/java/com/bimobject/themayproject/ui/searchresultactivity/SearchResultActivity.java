package com.bimobject.themayproject.ui.searchresultactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.bimobject.themayproject.adapters.RecycleViewAdapter;
import com.bimobject.themayproject.R;
import com.bimobject.themayproject.constants.STRINGS;
import com.bimobject.themayproject.helpers.Request;
import com.bimobject.themayproject.ui.productinfoactivity.ProductInfoActivity;


public class SearchResultActivity extends AppCompatActivity {

    private static String search;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        /*
        Button buttonCategory = findViewById(R.id.activity_serch_result_btn_filter);
        buttonCategory.setOnClickListener(view -> {

            request.addCategory("137");
            adapter.makeNewRequest(request);

        });

        Button btnClear = findViewById(R.id.activity_search_result_btn_filter_clear);
        btnClear.setOnClickListener(view -> {
            request.clearParams();
            adapter.makeNewRequest(request);
        });
        */


        RecycleViewAdapter adapter = new RecycleViewAdapter();

        if(getIntent().hasExtra("search")){
            search = getIntent().getStringExtra("search");
        }

        Request request = new Request();
        request.addSearch(search);

        adapter.getHelper().makeNewRequest(request);

        RecyclerView recyclerView = findViewById(R.id.activity_search_result_rv_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        adapter.setOnItemClickListener((view, productId) -> {
            Intent intent = new Intent(SearchResultActivity.this, ProductInfoActivity.class);
            intent.putExtra("productId", productId);
            startActivity(intent);
        });

        adapter.setOnBottomReachedListener(position -> {
            adapter.getHelper().loadNextPage();
            Toast.makeText(SearchResultActivity.this, STRINGS.FETCH_MORE_PRODUCTS, Toast.LENGTH_LONG).show();
        });


    }


}