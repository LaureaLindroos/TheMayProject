package com.bimobject.themayproject.helpers;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

public class RequestParameters {

    private static RequestParams finalparams;
    private static RequestParameters instance;

    public RequestParameters() {
    finalparams = new RequestParams();

    }

    public static RequestParameters getRequestParametersInstance() {
        if(instance==null){
            instance=new RequestParameters();
        }
        return instance;
    }

    public static RequestParams getRequestParameters() {
        return finalparams;
    }

    public void addBrand(String brand){
        finalparams.add("filter.brand.id",brand);
    }

    public void addCategory(String category){
        finalparams.add("filter.category.id",category);
    }

    public void addSearch(String search){
        finalparams.put("filter.fullText", search);
    }

    public void addPage(int page){
        finalparams.put("page", page);

    }

    public void addPageSize(){
        finalparams.put("pageSize", "20");
    }

    //TODO:fix a better clearer for when we have several categories and brands.
    public void clearParams(){
        finalparams.remove("filter.category.id");
    }




}
