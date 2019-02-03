package com.bimobject.themayproject.helpers;

import com.bimobject.themayproject.constants.FILTER;
import com.bimobject.themayproject.constants.VALUES;
import com.loopj.android.http.RequestParams;

public class Request {

    private boolean hasNextPage;
    private RequestParams params;
    private int page;
    private int totalCount;


    private String search;

    public Request() {
        params = new RequestParams();
        page = 1;
        this.params.put("pageSize", VALUES.PAGE_SIZE);
    }

    public RequestParams getParams() {
        return params;
    }

    public void addBrand(String brand) {
        this.params.add(FILTER.BRAND, brand);
    }

    public void addCategory(String category) {
        this.params.add(FILTER.CATEGORY, category);
    }

    public void addSearch(String search) {
        this.params.put(FILTER.FULLTEXT, search);
        this.search = search;
    }

    public void addPage(int page) {
        this.params.put("page", page);

    }

    //TODO:fix a better clearer for when we have several categories and brands.
    public void clearParams() {
        this.params.remove(FILTER.CATEGORY);
    }



    public boolean hasNextPage() {
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

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public String getSearch() {
        return search;
    }
}
