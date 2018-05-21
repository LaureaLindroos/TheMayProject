package com.bimobject.themayproject.dto;

public class ProductItem {
    public String id;
    public String name;
    public String imageLink;

    public ProductItem(String id, String name, String imageLink) {
        this.id = id;
        this.name = name;
        this.imageLink = imageLink;
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

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
