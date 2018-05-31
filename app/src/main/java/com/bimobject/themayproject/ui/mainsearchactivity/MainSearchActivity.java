package com.bimobject.themayproject.ui.mainsearchactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SearchView;

import com.bimobject.themayproject.R;




import com.bimobject.themayproject.adapters.RecycleViewAdapter;

import com.bimobject.themayproject.dto.Categories;

import com.bimobject.themayproject.helpers.Request;
import com.bimobject.themayproject.helpers.RequestService;

import com.bimobject.themayproject.helpers.TokenGenerator;
import com.bimobject.themayproject.ui.searchresultactivity.SearchResultActivity;

public class MainSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TokenGenerator.start(getString(R.string.client_id), getString(R.string.client_secret));
        setContentView(R.layout.activity_main_search);

        Button searchButton = findViewById(R.id.activity_main_btn_search);
        SearchView searchBox = findViewById(R.id.activity_main_search_et_value);
        searchBox.setIconifiedByDefault(false);
        searchBox.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                makeSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchButton.setOnClickListener(view -> makeSearch(searchBox.getQuery().toString()));

        /*
        searchBox.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                makeSearch();
                return true;
            }
            return false;
        });


        searchButton.setOnEditorActionListener((v, actionId, event) -> {

            switch (actionId) {

                case EditorInfo.IME_ACTION_DONE:
                    return true;

                default:
                    return false;
            }
        });
        */

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        TokenGenerator.start(getString(R.string.client_id), getString(R.string.client_secret));
    }

    private void makeSearch(String query) {
            Intent intent = new Intent(MainSearchActivity.this, SearchResultActivity.class);
            intent.putExtra("search", query);
            startActivity(intent);
        }

}