package com.bimobject.themayproject.dto.ProductInformation.GeneralInfo;

public class ProductFamily {
    public String name;
    public Integer id;

    public ProductFamily(String name, Integer id) {
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
