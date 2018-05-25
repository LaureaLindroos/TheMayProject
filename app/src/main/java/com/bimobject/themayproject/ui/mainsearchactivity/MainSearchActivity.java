package com.bimobject.themayproject.ui.mainsearchactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

import com.bimobject.themayproject.R;
import com.bimobject.themayproject.helpers.TokenGenerator;
import com.bimobject.themayproject.ui.searchresultactivity.SearchResultActivity;

public class MainSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TokenGenerator.start();
        setContentView(R.layout.activity_main_search);

        Button searchButton = findViewById(R.id.activity_main_btn_search);
        EditText searchBox = findViewById(R.id.activity_main_search_et_value);

        searchButton.setOnClickListener(view -> makeSearch());

        searchBox.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                makeSearch();
                return true;
            }
            return false;
        });

        /*
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

        private void makeSearch() {
            EditText searchBox = findViewById(R.id.activity_main_search_et_value);
            String search = searchBox.getText().toString();


            Intent intent = new Intent(MainSearchActivity.this, SearchResultActivity.class);
            intent.putExtra("search", search);
            startActivity(intent);
        }


}