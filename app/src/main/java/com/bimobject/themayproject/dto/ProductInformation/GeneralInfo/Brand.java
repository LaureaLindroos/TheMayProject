package com.bimobject.themayproject.dto.ProductInformation.GeneralInfo;

public class Brand {
    private String id;
    private String name;
    private String permalink;
    private String visibility;
    private String imageUrl;

    public Brand() {
    }

    public Brand(String id, String name, String permalink, String visibility, String imageUrl) {
        this.id = id;
        this.name = name;
        this.permalink = permalink;
        this.visibility = visibility;
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

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
