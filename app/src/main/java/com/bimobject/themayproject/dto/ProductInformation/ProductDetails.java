package com.bimobject.themayproject.dto.ProductInformation;

import com.bimobject.themayproject.dto.ProductInformation.GeneralInfo.ProductFamily;
import com.bimobject.themayproject.dto.ProductInformation.GeneralInfo.ProductGroup;
import com.bimobject.themayproject.dto.ProductInformation.Links.Links;
import com.bimobject.themayproject.dto.ProductInformation.Related.DesignedIn;
import com.bimobject.themayproject.dto.ProductInformation.Related.ManufacturedIn;
import com.bimobject.themayproject.dto.ProductInformation.Related.MaterialMain;
import com.bimobject.themayproject.dto.ProductInformation.Related.MaterialSecondary;

public class ProductDetails {
    public String id;
    public String status;
    public String dateOfPublishing;
    public Integer editionNumber;
    public String name;
    public String permalink;
    public String width;
    public String height;
    public String depth;
    public String descriptionHtml;
    public String descriptionPlainText;
    public String[] imageUrls;
    public String eanCode;
    public String weight;

    public DesignedIn designedIn;
    public ManufacturedIn manufacturedIn;
    public MaterialMain materialMain;
    public MaterialSecondary materialSecondary;
    public Brand brand;
    public Files[] files;
    public ProductFamily productFamily;
    public ProductGroup productGroup;
    public Links links;


    public ProductDetails(String id, String status, String dateOfPublishing, Integer editionNumber, String name, String permalink, String width, String height, String depth, String descriptionHtml, String descriptionPlainText, String[] imageUrls, Brand brand, Files[] files, ProductFamily productFamily, ProductGroup productGroup, Links links, String eanCode, String weight, DesignedIn designedIn, ManufacturedIn manufacturedIn, MaterialMain materialMain, MaterialSecondary materialSecondary) {
        this.id = id;
        this.status = status;
        this.dateOfPublishing = dateOfPublishing;
        this.editionNumber = editionNumber;
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
        this.productFamily = productFamily;
        this.productGroup = productGroup;
        this.links = links;
        this.eanCode = eanCode;
        this.weight = weight;
        this.designedIn = designedIn;
        this.manufacturedIn = manufacturedIn;
        this.materialMain = materialMain;
        this.materialSecondary = materialSecondary;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateOfPublishing() {
        return dateOfPublishing;
    }

    public void setDateOfPublishing(String dateOfPublishing) {
        this.dateOfPublishing = dateOfPublishing;
    }

    public Integer getEditionNumber() {
        return editionNumber;
    }

    public void setEditionNumber(Integer editionNumber) {
        this.editionNumber = editionNumber;
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

    public String getEanCode() {
        return eanCode;
    }

    public void setEanCode(String eanCode) {
        this.eanCode = eanCode;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public DesignedIn getDesignedIn() {
        if(designedIn==null)
        {
            return new DesignedIn(null,null);
        }
        return designedIn;
    }

    public void setDesignedIn(DesignedIn designedIn) {
        this.designedIn = designedIn;
    }

    public ManufacturedIn getManufacturedIn() {
        if(manufacturedIn==null)
        {
            return new ManufacturedIn(null,null);
        }
        return manufacturedIn;
    }

    public void setManufacturedIn(ManufacturedIn manufacturedIn) {
        this.manufacturedIn = manufacturedIn;
    }

    public MaterialMain getMaterialMain() {
        if(materialMain==null)
        {
            return new MaterialMain(null,null);
        }
        return materialMain;
    }

    public void setMaterialMain(MaterialMain materialMain) {
        this.materialMain = materialMain;
    }

    public MaterialSecondary getMaterialSecondary() {
        if(materialSecondary==null)
        {
           return new MaterialSecondary(null,null);
        }
        return materialSecondary;
    }


    public void setMaterialSecondary(MaterialSecondary materialSecondary) {
        this.materialSecondary = materialSecondary;
    }

    public Brand getBrand() {
        if(brand==null)
        {
          return new Brand(null, null, null, null, null);
        }
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Files[] getFiles() {
        if(files==null)
        {
           return new Files[]{null,null,null,null};
        }
        return files;
    }

    public void setFiles(Files[] files) {
        this.files = files;
    }

    public ProductFamily getProductFamily() {

        if(productFamily==null)
        {
            return new ProductFamily(null, null);
        }
        return productFamily;
    }

    public void setProductFamily(ProductFamily productFamily) {
        this.productFamily = productFamily;
    }

    public ProductGroup getProductGroup() {
        if(productGroup==null)
        {
            return new ProductGroup(null, null);
        }
        return productGroup;
    }

    public void setProductGroup(ProductGroup productGroup) {
        this.productGroup = productGroup;
    }

    public Links getLinks() {
        if(links==null)
        {
            return new Links(null, null, null,null,null,null);
        }
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }
}
