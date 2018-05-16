package com.bimobject.themayproject;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

import cz.msebera.android.httpclient.Header;

public abstract class TokenGenerator {

    private static String accessToken;
    private final static int expieryTime = 1350 * 1000;
    private final static RequestParams params = new RequestParams();
    private final static Timer t = new Timer();

    static {
        params.put("grant_type", "client_credentials");
        params.put("scope", "search_api");
        params.put("client_id", "KaHKuGnJJduQ9Ek1ekXRw6PdLKTkdic7");
        params.put("client_secret", "3yUKdB0agDKJlw7ltRwQ4eRTeZC2Fw22KmMNcRYfvgzQ0WxekewFfUJxhkknM7Lb");
    }

    public static void start(){
        t.scheduleAtFixedRate(new CollectAccessToken(), 0, expieryTime);
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

            SyncClient.post("https://accounts.bimobject.com/identity/connect/token", params, new JsonHttpResponseHandler(
            ) {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);

                    try {
                        String responseToken = response.get("access_token").toString();
                        setToken(responseToken);

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

