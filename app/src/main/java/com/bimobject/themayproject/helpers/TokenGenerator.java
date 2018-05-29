package com.bimobject.themayproject.helpers;

import com.bimobject.themayproject.R;
import com.bimobject.themayproject.constants.URL;
import com.bimobject.themayproject.constants.VALUES;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;


import cz.msebera.android.httpclient.Header;

public abstract class TokenGenerator {

    private static String accessToken;
    private final static int expiryTime = VALUES.EXPIRY_TIME * VALUES.SECOND_TO_MILLISECONDS;
    private final static RequestParams params = new RequestParams();
    private static Timer t;

    //TODO: Encrypt client credentials
    static {
        params.put("grant_type", "client_credentials");
        params.put("scope", "search_api");

    }

    public static void start(String client_id, String client_secret){

        params.put("client_id", client_id);
        params.put("client_secret", client_secret);

        if(t != null){
            t.cancel();
        }

        t = new Timer();
        t.scheduleAtFixedRate(new CollectAccessToken(), 0, expiryTime);

    }


    private static void setToken(String newAccessToken) {
        accessToken = newAccessToken;
    }

    public static String getToken() {

        return accessToken;
    }

    private static class CollectAccessToken extends TimerTask {

        @Override
        public void run() {

            SyncClient.post(URL.CONNECT_TOKEN, params, new JsonHttpResponseHandler(
            ) {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);

                    try {

                        setToken(JSONParser.parseToAccessToken(response));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    super.onFailure(statusCode, headers, throwable, errorResponse);
                }

            });
        }
    }

}

