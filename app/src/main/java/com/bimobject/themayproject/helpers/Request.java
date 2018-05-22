package com.bimobject.themayproject.helpers;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

public class Request {
    private boolean hasNextPage;
    private RequestParams params;
    private int page;

    private Request() {
        hasNextPage=true;
        params = new RequestParams();
        page = 1;
    }

    public RequestParams getParams() {
        return params;
    }

    public void addBrand(String brand){ this.params.add("filter.brand.id",brand);
    }

    public void addCategory(String category){
        this.params.add("filter.category.id",category);
    }

    public void addSearch(String search){
        this.params.put("filter.fullText", search);
    }

    public void addPage(int page){
        this.params.put("page", page);

    }

    public void addPageSize(){
        this.params.put("pageSize", "20");
    }

    //TODO:fix a better clearer for when we have several categories and brands.
    public void clearParams(){
        this.params.remove("filter.category.id");
    }


    public boolean getHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
