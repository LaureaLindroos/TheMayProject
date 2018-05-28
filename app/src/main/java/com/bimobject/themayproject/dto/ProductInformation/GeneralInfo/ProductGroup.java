package com.bimobject.themayproject.dto.ProductInformation.GeneralInfo;

public class ProductGroup {
    public String name;
    public Integer id;

    public ProductGroup(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
