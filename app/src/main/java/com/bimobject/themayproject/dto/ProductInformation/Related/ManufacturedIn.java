package com.bimobject.themayproject.dto.ProductInformation.Related;

public class ManufacturedIn {
    public Integer Id;
    public String name;

    public ManufacturedIn(Integer id, String name) {
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
