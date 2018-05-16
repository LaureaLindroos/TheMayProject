package com.bimobject.themayproject;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class ProductDeserializer implements JsonDeserializer<Product> {

    @Override
    public Product deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        return new Product(
                jsonObject.get("id").getAsString(),
                jsonObject.get("name").getAsString(),
                jsonObject.get("status").getAsString(),
                jsonObject.get("permalink").getAsString(),
                jsonObject.get("imageUrl").getAsString()
        );
    }

}


