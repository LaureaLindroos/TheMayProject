package com.bimobject.themayproject.helpers;

import android.os.AsyncTask;

import com.bimobject.themayproject.adapters.RecycleViewAdapter;
import com.bimobject.themayproject.constants.URL;
import com.bimobject.themayproject.dto.Product;

import java.lang.ref.WeakReference;
import java.util.List;

public final class RVAHelper {

    private static Request request;
    private RecycleViewAdapter adapter;

    public RVAHelper(RecycleViewAdapter adapter) {
        this.adapter = adapter;
    }

    public void makeNewRequest(Request req){
            request = req;
            adapter.clear();
            new LoadListItemsTask(this).execute(request);
    }

    public void loadNextPage(){

        if(request.hasNextPage()) {
            int page = request.getPage();
            request.setPage(page + 1);

            request.addPage(request.getPage());
            new LoadListItemsTask(this).execute(request);
        }
        //TODO: What else?
    }

    public static Request getRequest() {
        return request;
    }

    private static class LoadListItemsTask extends AsyncTask<Request, String, List<Product>> {

        WeakReference<RVAHelper> context;

        private LoadListItemsTask(RVAHelper helper) {
            context = new WeakReference<>(helper);
        }

        @Override
        protected void onPostExecute(List<Product> products) {
            context.get().adapter.addAll(products);

            if(request.getPage() == 1)
                context.get().adapter.onNewRequest(request);

        }

        @Override
        protected List<Product> doInBackground(Request... requests) {
            return RequestService.getRequest(URL.GET_PRODUCTS, requests[0]);
        }
    }

}
