package com.bimobject.themayproject.ui.mainsearchactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bimobject.themayproject.R;
import com.bimobject.themayproject.helpers.TokenGenerator;
import com.bimobject.themayproject.ui.searchresultactivity.SearchResultActivity;

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

        searchButton.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                switch (actionId) {

                    case EditorInfo.IME_ACTION_DONE:
                        return true;

                    default:
                        return false;
                }
            }
        });
        searchButton.setOnKeyListener(new View.OnKeyListener() {

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    onRightButtonClicked();
                    return true;
                }
                return false;
            }

            private void onRightButtonClicked() {
                EditText searchBox = findViewById(R.id.searchBox);
                String search = searchBox.getText().toString();


                Intent intent = new Intent(MainSearchActivity.this, SearchResultActivity.class);
                intent.putExtra("search", search);
                startActivity(intent);
            }
        });

    }

}