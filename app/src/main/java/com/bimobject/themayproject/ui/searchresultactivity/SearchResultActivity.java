package com.bimobject.themayproject.ui.searchresultactivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.bimobject.themayproject.adapters.RecycleViewAdapter;
import com.bimobject.themayproject.R;
import com.bimobject.themayproject.helpers.Request;

public class SearchResultActivity extends AppCompatActivity {

    private static RecycleViewAdapter adapter;
    private static String search;
    private RecyclerView recyclerView;
    Request request;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        if(getIntent().hasExtra("search")){
            search = getIntent().getStringExtra("search");
        }

        request = new Request();
        request.addSearch(search);


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


        adapter = new RecycleViewAdapter();
        recyclerView = findViewById(R.id.activity_search_result_rv_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        adapter.makeNewRequest(request);


    }


}
