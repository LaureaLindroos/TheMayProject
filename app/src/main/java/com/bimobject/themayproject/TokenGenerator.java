package com.bimobject.themayproject;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public abstract class TokenGenerator {

    private static String access_token;
    private static SyncHttpClient client = new SyncHttpClient();

    private static RequestParams params = new RequestParams();
    static {
        //TODO: Implement better solution for first generating token
        generateNewAccess_token();

        //TODO: Encrypt client crendentials
        params.put("grant_type", "client_credentials");
        params.put("scope", "search_api");
        params.put("client_id", "KaHKuGnJJduQ9Ek1ekXRw6PdLKTkdic7");
        params.put("client_secret", "3yUKdB0agDKJlw7ltRwQ4eRTeZC2Fw22KmMNcRYfvgzQ0WxekewFfUJxhkknM7Lb");
    }

    //Method that collects AccessToken and sets the value.
    public static void generateNewAccess_token() {

            client.post("https://accounts.bimobject.com/identity/connect/token", params, new JsonHttpResponseHandler(
            ) {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);

                    try {
                        setToken(response.get("access_token").toString());

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

    private static void setToken(String new_token) {
        access_token = new_token;
    }

    public static String getAccess_token() {
        return access_token;
    }
}

