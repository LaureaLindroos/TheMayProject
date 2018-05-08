package com.bimobject.themayproject;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

/**
 * Created by octoboss on 2018-05-07.
 */

public class RequestService {

    private static AsyncHttpClient client = new AsyncHttpClient();
    private static final String BASE_URL = "https://api.bimobject.com/search/v1/";

    public static void getRequest(String search, String path){

        //Adding some parameters (just for fun!)
        RequestParams params = new RequestParams();
        params.put("pageSize", "10");
        params.put("filter.fullText", search);
        params.put("fields", "name,brand");

        //TODO:Exchange hardcoded header with authorizationService
        client.addHeader("Authorization", "Bearer fa6013339715ad2ef27e2f8b6a950c31");
        client.get(BASE_URL + path, params, new TextHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("AsyncHttpClient", responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.e("AsyncHttpClient", responseString);
            }
        });


    }


}
