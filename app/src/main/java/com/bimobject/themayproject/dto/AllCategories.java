package com.bimobject.themayproject.dto;

import java.util.ArrayList;
import java.util.stream.Stream;

public class AllCategories extends ArrayList<AllCategories> {
    ArrayList<Categories> allCategories;

    public AllCategories(ArrayList<Categories> allCategories) {
        this.allCategories = allCategories;
    }

    public ArrayList<Categories> getAllCategories() {
        return allCategories;
    }

    public void setAllCategories(ArrayList<Categories> allCategories) {
        this.allCategories = allCategories;
    }

}
