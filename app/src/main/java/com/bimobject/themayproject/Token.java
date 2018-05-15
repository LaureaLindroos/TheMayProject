package com.bimobject.themayproject;

import android.os.Looper;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

import cz.msebera.android.httpclient.Header;

public class Token {

    private static String accessToken;
    final int expieryTime = 1350 * 1000;
    private static Token instance;
    Timer t;

    private Token(

    ) {
        t = new Timer();
        t.scheduleAtFixedRate(new CollectAccessToken(), 0, expieryTime);
    }

    public static Token getInstance(){
        if (instance==null){
            Token instance=new Token();
        }
        return instance;
    }


    public void setToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public static String getToken() {

        return accessToken;
    }

    //Method that collects AccessToken and sets the value.
    class CollectAccessToken extends TimerTask {
        SyncHttpClient client = new SyncHttpClient();

        @Override
        public void run() {
            //skapar våra request parameters
            RequestParams params = new RequestParams();
            params.put("grant_type", "client_credentials");
            params.put("scope", "search_api");
            params.put("client_id", "KaHKuGnJJduQ9Ek1ekXRw6PdLKTkdic7");
            params.put("client_secret", "3yUKdB0agDKJlw7ltRwQ4eRTeZC2Fw22KmMNcRYfvgzQ0WxekewFfUJxhkknM7Lb");


            //Vi hämtar nya access tokens genom att göra post-requests till API:t innehållandes våra client credentials
            //och nyckel

            client.post("https://accounts.bimobject.com/identity/connect/token", params, new JsonHttpResponseHandler(
            ) {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    System.out.println("success post");

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

