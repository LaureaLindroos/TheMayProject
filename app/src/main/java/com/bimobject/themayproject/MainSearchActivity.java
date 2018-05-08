package com.bimobject.themayproject;

import android.os.StrictMode;
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

        //Setting the applications blocking policy, allowing the synchttphandler to block the main thread (really not a good thing)
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //Getting button from layout
        Button searchButton = findViewById(R.id.searchButton);

        //Setting onClickListener
        searchButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText searchBox = findViewById(R.id.searchBox);
                TextView searchResult = findViewById(R.id.searchResult);

                //Getting string value from searchbox
                String search = searchBox.getText().toString();
                //Setting text of searchResult to response from request
                searchResult.setText(RequestService.getRequest(search, "/products"));
            }
        });

    }
}
