package com.bimobject.themayproject.ui.searchresultactivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.bimobject.themayproject.adapters.RecycleViewAdapter;
import com.bimobject.themayproject.constants.URL;
import com.bimobject.themayproject.dto.Product;
import com.bimobject.themayproject.adapters.ProductListAdapter;
import com.bimobject.themayproject.R;
import com.bimobject.themayproject.helpers.Request;
import com.bimobject.themayproject.helpers.RequestService;
import com.bimobject.themayproject.ui.productinfoactivity.ProductInfoActivity;

import java.util.ArrayList;
import java.util.List;

public class SearchResultActivity extends AppCompatActivity {

    private static RecycleViewAdapter adapter;
    private static String search;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        /*
        Button buttonCategory = findViewById(R.id.activity_serch_result_btn_filter);
        buttonCategory.setOnClickListener(view -> {
                requestParams.addCategory("137");
                });

        Button btnClear = findViewById(R.id.activity_search_result_btn_filter_clear);
        btnClear.setOnClickListener(view -> {
            requestParams.clearParams();
        });
        */

        adapter = new RecycleViewAdapter();
        recyclerView = findViewById(R.id.activity_search_result_rv_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        if(getIntent().hasExtra("search")){
            search = getIntent().getStringExtra("search");
        }

        Request request = new Request();
        request.addSearch(search);

        adapter.makeNewRequest(request);


    }


}
