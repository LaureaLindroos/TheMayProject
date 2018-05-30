package com.bimobject.themayproject.helpers;

import com.bimobject.themayproject.constants.URL;
import com.bimobject.themayproject.dto.AllCategories;
import com.bimobject.themayproject.dto.Categories;
import com.bimobject.themayproject.dto.Product;
import com.bimobject.themayproject.dto.ProductInformation.ProductDetails;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import cz.msebera.android.httpclient.Header;


public class RequestService {

    public static List<Product> getRequest(String path, Request request) {

        final ArrayList<Product> products = new ArrayList<>();


        SyncClient.get(path, request.getParams(), new JsonHttpResponseHandler() {


            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {


                    int totalCount = ((JSONObject)response.get("meta")).getInt("totalCount");
                    request.setTotalCount(totalCount);
                    Boolean hasNextPage = ((JSONObject)response.get("meta")).getBoolean("hasNextPage");
                    request.setHasNextPage(hasNextPage);

                    products.addAll(JSONParser.parseToProductList(response));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        return products;


    }

    //TODO: Remove code duplication
    public static ProductDetails getProductDetails(String id){

        final ArrayList<ProductDetails> productDetails = new ArrayList<>();

        /*RequestParams params = new RequestParams();
        params.put("fields", "name,imageUrls");*/

        SyncClient.get(URL.GET_PRODUCTS + id, null, new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                try {
                    productDetails.add(JSONParser.parseToProductDetails(response));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        //TODO: Implement better solution for returning single object
        return productDetails.get(0);

    }

    public void getcategories() {

            final ArrayList<AllCategories> categories = new ArrayList<>();

        /*RequestParams params = new RequestParams();
        params.put("fields", "name,imageUrls");*/

            SyncClient.get(URL.GET_CATEGORIES, null, new JsonHttpResponseHandler(){

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);

                    try {
                        categories.add(JSONParser.parseToCategories(response));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });
            //TODO: Implement better solution for returning single object


        }
    }





