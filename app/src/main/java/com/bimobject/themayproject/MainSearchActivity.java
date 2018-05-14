package com.bimobject.themayproject;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MainSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_search);

        //Getting button from layout
        Button searchButton = findViewById(R.id.searchButton);

        //Setting onClickListener
        searchButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText searchBox = findViewById(R.id.searchBox);
                String search = searchBox.getText().toString();

                new setTextAsyncTask().execute(search);

            }
        });

    }


    private class setTextAsyncTask extends AsyncTask<String, String, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                ImageView searchResult = findViewById(R.id.searchResult);
                Picasso.with(MainSearchActivity.this).load(s).into(searchResult);
            }catch (IllegalArgumentException e){
                e.printStackTrace();
            }

        }

        @Override
        protected String doInBackground(String... strings) {
            return RequestService.getImageUrl(strings[0], "/products");
        }
    }

}