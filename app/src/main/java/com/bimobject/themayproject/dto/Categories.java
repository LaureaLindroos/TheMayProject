package com.bimobject.themayproject.dto;


public class Categories {
    public String id;
    public String name;
    private SubCategories[] children;

    public Categories() {
    }

    public Categories(String id, String name, SubCategories[] children) {
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

    public SubCategories[] getChildren() {
        return children;
    }

    public void setChildren(SubCategories[] children) {
        this.children = children;
    }
}