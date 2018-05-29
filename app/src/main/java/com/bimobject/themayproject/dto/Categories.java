package com.bimobject.themayproject.dto;


import java.util.ArrayList;

public class Categories {
    public String id;
    public String name;
    public ArrayList<SubCategories> children;

    public Categories() {
    }

    public Categories(String id, String name, ArrayList<SubCategories> children) {
        this.id = id;
        this.name = name;
        this.children = children;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList <SubCategories> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<SubCategories> children) {
        this.children = children;
    }
}