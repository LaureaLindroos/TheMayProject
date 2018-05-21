package com.bimobject.themayproject.dto;


public class Files {
    public String id;
    public String name;
    public String description;
    public File filetype;

    public Files(String id, String name, String description, File filetype) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.filetype = filetype;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public File getFiletype() {
        return filetype;
    }

    public void setFiletype(File filetype) {
        this.filetype = filetype;
    }
}
