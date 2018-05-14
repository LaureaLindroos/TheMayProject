package com.bimobject.themayproject;

import com.loopj.android.http.SyncHttpClient;

public class AsyncClient {
    private static SyncHttpClient client = new SyncHttpClient();
    private static final String BASE_URL = "https://api.bimobject.com/search/v1/";
}
