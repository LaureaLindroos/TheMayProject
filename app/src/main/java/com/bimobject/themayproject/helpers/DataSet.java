package com.bimobject.themayproject.helpers;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.bimobject.themayproject.constants.URL;
import com.bimobject.themayproject.dto.Product;
import com.loopj.android.http.RequestParams;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by octoboss on 2018-05-22.
 */

//TODO: Make class extend AbstractCollection?
public class DataSet {

    private ArrayList<Product> data;
    private RequestParams params;

    public int size() {
        return data.size();
    }

    public Product get(int position){
        return data.get(position);
    }
}
