package com.bimobject.themayproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        ArrayList<String> testResultList = new ArrayList<>(Arrays.asList("Lorem", "Ipsum", "Dolor", "Sit", "Amet"));

        ArrayAdapter<String> adapter = new ArrayAdapter(SearchResultActivity.this, R.layout.temporary_list_item_layout, R.id.test_textView, testResultList);

        ListView listView = findViewById(R.id.search_result_list);
        listView.setAdapter(adapter);

    }
}
