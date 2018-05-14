package com.bimobject.themayproject;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpStatus;

/**
 * Created by octoboss on 2018-05-07.
 */

public class RequestService {

    public static final StringBuilder responseBuilder = new StringBuilder();

    public static String getImageUrl(String search, final String path){

        responseBuilder.setLength(0);

        //Adding some parameters, pagesize=1 for testing purposes
        final RequestParams params = new RequestParams();
        params.put("pageSize", "1");
        params.put("filter.fullText", search);
        params.put("fields", "name,brand");

        SyncClient.get(path, params, new JsonHttpResponseHandler() {

                    //If response is a JSONObject
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        try {
                            JSONArray data = (JSONArray) response.get("data");
                            responseBuilder.append(data.getJSONObject(0).getJSONObject("brand").get("imageUrl").toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);

                        //If request is Unauthorized, token probably expired
                        if (statusCode == HttpStatus.SC_UNAUTHORIZED) {
                            SyncClient.generateTokenAndRetry(path, params, this);
                        }

                    }

                });

        return responseBuilder.toString();

    }


}
