package com.bimobject.themayproject;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import static com.bimobject.themayproject.SyncClient.client;

/**
 * Created by octoboss on 2018-05-07.
 */

public class RequestService {
    static String accessToken;


    public static List<Product> getRequest(String search, String path) {

        //Stringbuilder is final as callback-methods are in inner class
        final ArrayList<Product> products = new ArrayList<>();
        accessToken = Token.getToken();
        //Adding some parameters, pagesize=1 for testing purposes
        RequestParams params = new RequestParams();
        params.put("pageSize", "10");
        params.put("filter.fullText", search);
        params.put("fields", "name,imageUrl,brand");

            //TODO:Exchange hardcoded header with authorizationService

            SyncClient.client.addHeader("Authorization", "Bearer " + accessToken);
            SyncClient.get(path, params, new JsonHttpResponseHandler() {

                //If response is a JSONObject
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    try {
                        JSONArray data = (JSONArray) response.get("data");

                        Gson gson = new GsonBuilder().create();

                        Type listType = new TypeToken<List<Product>>(){}.getType();
                        ArrayList<Product> products1 = gson.fromJson(data.toString(), listType);
                        products.addAll(products1);

                    } catch (JSONException e) {
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

            return products;

        }
    }


