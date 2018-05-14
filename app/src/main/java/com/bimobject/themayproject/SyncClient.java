package com.bimobject.themayproject;

import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;
import com.loopj.android.http.SyncHttpClient;

public class SyncClient {
    static final SyncHttpClient client = new SyncHttpClient();
    static final String BASE_URL = "https://api.bimobject.com/search/v1/";

    public static void get(String url, RequestParams params, SyncHttpClient responseHandler) {
        client.get(getAbsoluteUrl(url), params, (ResponseHandlerInterface) responseHandler);
    }

    public static void post(String url, RequestParams params, SyncHttpClient responseHandler) {
        client.post(getAbsoluteUrl(url), params, (ResponseHandlerInterface) responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}

