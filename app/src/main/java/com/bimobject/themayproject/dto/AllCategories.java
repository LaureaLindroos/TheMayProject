package com.bimobject.themayproject.dto;

import java.util.ArrayList;

public class AllCategories {
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
