package com.bimobject.themayproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TokenGenerator.start();
        setContentView(R.layout.activity_main_search);

        Button searchButton = findViewById(R.id.searchButton);

        searchButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText searchBox = findViewById(R.id.searchBox);
                String search = searchBox.getText().toString();

                Intent intent = new Intent(MainSearchActivity.this, SearchResultActivity.class);
                intent.putExtra("search", search);
                startActivity(intent);

            }
        });

    }

}