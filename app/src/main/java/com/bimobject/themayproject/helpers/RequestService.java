package com.bimobject.themayproject.helpers;

import com.bimobject.themayproject.constants.URL;
import com.bimobject.themayproject.dto.Product;
import com.bimobject.themayproject.dto.ProductDetails;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class RequestService {

    public static List<Product> getRequest(String search, String path, int page) {

        final ArrayList<Product> products = new ArrayList<>();

        RequestParams params = new RequestParams();
        params.put("pageSize", "20");
        params.put("filter.fullText", search);
        params.put("page", page);

        SyncClient.get(path, params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    products.addAll(JSONParser.parseToProductList(response));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });

        return products;

    }
    public static ProductDetails getProductDetails(String id){

        final ArrayList<ProductDetails> productDetails = new ArrayList<>();

        SyncClient.get(URL.GET_PRODUCTS + id, null, new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                try {
                    productDetails.add(JSONParser.parseToProductDetails(response));
                }catch (JSONException e){
                    e.printStackTrace();
                }

            }
        });
        //TODO: Implement better solution for returning single object
        return productDetails.get(0);
    }

}

