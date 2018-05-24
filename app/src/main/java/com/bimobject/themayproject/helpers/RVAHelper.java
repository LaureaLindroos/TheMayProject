package com.bimobject.themayproject.helpers;

import android.os.AsyncTask;

import com.bimobject.themayproject.adapters.RecycleViewAdapter;
import com.bimobject.themayproject.constants.URL;
import com.bimobject.themayproject.dto.Product;

import java.lang.ref.WeakReference;
import java.util.List;

public final class RVAHelper {

    private Request request;
    private RecycleViewAdapter adapter;

    public RVAHelper(RecycleViewAdapter adapter) {
        this.adapter = adapter;
    }

    public void makeNewRequest(Request req){
            this.request = req;
            adapter.clear();
            new LoadListItemsTask(this).execute(this.request);
    }

    public void loadNextPage(){

        if(this.request.hasNextPage()) {
            int page = this.request.getPage();

            this.request.addPage(page + 1);
            new LoadListItemsTask(this).execute(this.request);
        }
        //TODO: What else?
    }

    private static class LoadListItemsTask extends AsyncTask<Request, String, List<Product>> {

        WeakReference<RVAHelper> context;

        private LoadListItemsTask(RVAHelper helper) {
            context = new WeakReference<>(helper);
        }

        @Override
        protected void onPostExecute(List<Product> products) {
            context.get().adapter.addAll(products);
        }

        @Override
        protected List<Product> doInBackground(Request... requests) {
            return RequestService.getRequest(URL.GET_PRODUCTS, requests[0]);
        }
    }

}
