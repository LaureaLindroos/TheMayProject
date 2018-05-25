package com.bimobject.themayproject.dto;

import com.bimobject.themayproject.dto.ProductInformation.Brand;

public class Product{
    private String id;
    private String name;
    private String status;
    private String permalink;
    private String imageUrl;

    private Brand brand;

    public Product() {
    }

    public Product(String id, String name, String status, String permalink, String imageUrl) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.permalink = permalink;
        this.imageUrl = imageUrl;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Brand getBrand() {
        return brand;
    }
}
