package com.bimobject.themayproject;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by octoboss on 2018-05-07.
 */

public class RequestService {

    private static SyncHttpClient client = new SyncHttpClient();
    private static final String BASE_URL = "https://api.bimobject.com/search/v1/";

    public static String getRequest(String search, String path){

        //Stringbuilder is final as callback-methods are in inner class
        final StringBuilder responseBuilder = new StringBuilder();

        //Adding some parameters, pagesize=1 for testing purposes
        RequestParams params = new RequestParams();
        params.put("pageSize", "1");
        params.put("filter.fullText", search);
        params.put("fields", "name,brand");

        //TODO:Exchange hardcoded header with authorizationService
        client.addHeader("Authorization", "Bearer " + TokenGenerator.getAccess_token());
        client.get(BASE_URL + path, params, new JsonHttpResponseHandler() {

            //If response is a JSONObject
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    JSONArray data = (JSONArray) response.get("data");
                    responseBuilder.append(data.getJSONObject(0).getJSONObject("brand").get("imageUrl").toString());
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }

            //If response is a JSONArray
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });

        return responseBuilder.toString();

    }


}
