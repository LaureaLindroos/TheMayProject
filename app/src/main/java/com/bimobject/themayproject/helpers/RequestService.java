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
<<<<<<< HEAD

=======
>>>>>>> 67569628087920a27cc222041bd93cddd490f7e1
    public static List<Product> getRequest(String search, String path, int page) {

        final ArrayList<Product> products = new ArrayList<>();
        RequestParameters requestParams=RequestParameters.getRequestParametersInstance();
        requestParams.addPage(page);
        requestParams.addSearch(search);
        requestParams.addPageSize();
        RequestParams params = requestParams.getRequestParameters();
        System.out.println(params.toString());

<<<<<<< HEAD
=======
        RequestParams params = new RequestParams();
        params.put("pageSize", "20");
        params.put("filter.fullText", search);
        params.put("page", page);
>>>>>>> 67569628087920a27cc222041bd93cddd490f7e1

        SyncClient.get(path, params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
<<<<<<< HEAD

=======
>>>>>>> 67569628087920a27cc222041bd93cddd490f7e1
                    products.addAll(JSONParser.parseToProductList(response));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        return products;

<<<<<<< HEAD
            });

            return products;

        }
        public static ProductDetails getProductDetails(String id){

        final ArrayList<ProductDetails> productDetails = new ArrayList<>();

        SyncClient.get(URL.GET_PRODUCTS + id, null, new JsonHttpResponseHandler(){
=======
    }

    public static ProductDetails getProductDetails(String id) {

        final ArrayList<ProductDetails> productDetails = new ArrayList<>();

        SyncClient.get(URL.GET_PRODUCTS + id, null, new JsonHttpResponseHandler() {
>>>>>>> 67569628087920a27cc222041bd93cddd490f7e1

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
<<<<<<< HEAD
        }

    }

=======
    }
>>>>>>> 67569628087920a27cc222041bd93cddd490f7e1

}

