package com.bimobject.themayproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_search);

        Button searchButton = findViewById(R.id.searchButton);

        searchButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText searchBox = findViewById(R.id.searchBox);
                TextView searchResult = findViewById(R.id.searchResult);
                String search = searchBox.getText().toString();
                searchResult.setText(RequestService.getRequest(search, "/products"));
            }
        });

    }
}
