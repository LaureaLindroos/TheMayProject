package com.bimobject.themayproject;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

/**
 * Created by octoboss on 2018-05-07.
 */

public class RequestService {

    private static SyncHttpClient client = new SyncHttpClient();
    private static final String BASE_URL = "https://api.bimobject.com/search/v1/";

    public static String getRequest(String search, String path){

        //Stringbuilder is final as callback-methods are in inner class
        final StringBuilder response = new StringBuilder();

        //Adding some parameters, pagesize=1 for testing purposes
        RequestParams params = new RequestParams();
        params.put("pageSize", "1");
        params.put("filter.fullText", search);
        params.put("fields", "name,brand");

        //TODO:Exchange hardcoded header with authorizationService
        client.addHeader("Authorization", "Bearer 85273e0bf16083c710941b473225ca94");
        client.get(BASE_URL + path, params, new TextHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("SyncHttpClient", responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                //Appending responsestring to stringBuilder
                response.append(responseString);
            }

        });

        return response.toString();


    }


}
