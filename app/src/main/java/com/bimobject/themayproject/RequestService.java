package com.bimobject.themayproject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class RequestService {

    public static List<Product> getRequest(String search, String path) {

        final ArrayList<Product> products = new ArrayList<>();

        RequestParams params = new RequestParams();
        params.put("pageSize", "50");
        params.put("filter.fullText", search);
        params.put("fields", "name,imageUrl,brand");

            SyncClient.get(path, params, new JsonHttpResponseHandler() {

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    try {
                        JSONArray data = (JSONArray) response.get("data");

                        Gson gson = new GsonBuilder().create();

                        Type listType = new TypeToken<List<Product>>(){}.getType();
                        //TODO: Implement better solution for handling response
                        ArrayList<Product> responseArray = gson.fromJson(data.toString(), listType);
                        products.addAll(responseArray);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    super.onFailure(statusCode, headers, throwable, errorResponse);
                }

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


