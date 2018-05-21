package com.bimobject.themayproject.helpers;

import com.bimobject.themayproject.dto.Product;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;


public abstract class JSONParser {

    Gson gson = new GsonBuilder().create();

    public List<?> parseFromJSON(JsonElement element){

        //TODO: Make better solution to find out type of object
        if(element.isJsonArray()){
            Type listType = new TypeToken<List<Product>>(){}.getType();
            return gson.fromJson(element.toString(), listType);
        }
        else {
            return gson.fromJson(element.toString(), ProductDetails.class);
        }

    }
}
