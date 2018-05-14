package com.bimobject.themayproject;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;

/**
 * Created by octoboss on 2018-05-14.
 */

public class SyncClient {

    private static SyncHttpClient client = new SyncHttpClient();
    private static final String SEARCH_URL = "https://api.bimobject.com/search/v1/";
    private static RequestRetryHandler retryHandler = new RequestRetryHandler();

    private SyncClient(){
        TokenGenerator.generateNewAccess_token();
    }

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler){
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(url, params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl){
        return SEARCH_URL + relativeUrl;
    }

    public static void generateTokenAndRetry(String url, RequestParams params, AsyncHttpResponseHandler responseHandler){
        TokenGenerator.generateNewAccess_token();
        retryHandler.timeoutAndRetryRequest(url, params, responseHandler);
    }

    public static void setHeader(String auth, String key){
        client.removeAllHeaders();
        client.addHeader(auth, key);
    }

    private static class RequestRetryHandler {

        private static final int timeout = 2000;

        private void timeoutAndRetryRequest(String url, RequestParams params, AsyncHttpResponseHandler responseHandler){
            try {
                Thread.sleep(timeout);
            }catch (Exception e){
                e.printStackTrace();
            }

            SyncClient.get(url, params, responseHandler);

        }

    }

}
