package com.bimobject.themayproject.dto.ProductInformation.Related;

public class MaterialSecondary {
    public Integer Id;
    public String name;

    public MaterialSecondary(Integer id, String name) {
        Id = id;
        this.name = name;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
