package com.bimobject.themayproject.dto;

public class ProductDetails {
    public String name;
    public String permalink;
    public String width;
    public String height;
    public String depth;
    public String descriptionHtml;
    public String descriptionPlainText;
    public String[] imageUrls;
    public Brand brand;
    public Files[] files;

    public ProductDetails(String name, String permalink, String width, String height, String depth, String descriptionHtml, String descriptionPlainText, String[] imageUrls, Brand brand, Files[] files) {
        this.name = name;
        this.permalink = permalink;
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.descriptionHtml = descriptionHtml;
        this.descriptionPlainText = descriptionPlainText;
        this.imageUrls = imageUrls;
        this.brand = brand;
        this.files = files;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public String getDescriptionHtml() {
        return descriptionHtml;
    }

    public void setDescriptionHtml(String descriptionHtml) {
        this.descriptionHtml = descriptionHtml;
    }
    public String getDescriptionPlainText() {
        return descriptionPlainText;
    }

    public void setDescriptionPlainText(String descriptionPlainText) {
        this.descriptionPlainText = descriptionPlainText;
    }

    public String[] getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(String[] imageUrls) {
        this.imageUrls = imageUrls;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Files[] getFiles() {
        return files;
    }

    public void setFiles(Files[] files) {
        this.files = files;
    }
}

