package com.bimobject.themayproject.helpers;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;

public class SyncClient {
    static final SyncHttpClient client = new SyncHttpClient();
    static final String BASE_URL = "https://api.bimobject.com/search/v1";

    public static void get(String url, RequestParams params, JsonHttpResponseHandler responseHandler) {
        client.removeAllHeaders();
        client.addHeader("Authorization", "Bearer " + TokenGenerator.getToken());
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, JsonHttpResponseHandler responseHandler) {
        client.post(url, params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
