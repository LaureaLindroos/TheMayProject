package com.bimobject.themayproject.helpers;

import com.bimobject.themayproject.constants.URL;
import com.bimobject.themayproject.dto.Product;
import com.bimobject.themayproject.dto.ProductDetails;
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

    public static List<Product> getRequest(String search, String path, int page) {

        final ArrayList<Product> products = new ArrayList<>();

        RequestParams params = new RequestParams();
        params.put("pageSize", "20");
        params.put("filter.fullText", search);
        params.put("fields", "name,imageUrl,brand");
        params.put("page", page);

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


            });

            return products;

        }
        public static ProductDetails getProductDetails(String id){

        final ArrayList<ProductDetails> productDetails = new ArrayList<>();

        SyncClient.get(URL.GET_PRODUCTS + "/" + id, null, new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                Gson gson = new GsonBuilder().create();
                ProductDetails responseObject = gson.fromJson(response.toString(), ProductDetails.class);

                productDetails.add(responseObject);

            }
        });
        return productDetails.get(0);
        }

    }


