package com.bimobject.themayproject.ui.productinfoactivity;

import com.bimobject.themayproject.dto.ProductInformation.ProductDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PrepareProductInfo {
   private ArrayList<String> listDataHeader;
    private HashMap<String, List<String>>listDataChild;
    private ProductDetails productDetails;

    public PrepareProductInfo(ProductDetails productDetails) {
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();
        this.productDetails = productDetails;
        setListDataHeader();
        setListDataChild();
    }


    private void setListDataHeader(){
        listDataHeader.add("General information");
        listDataHeader.add("Description");
        listDataHeader.add("Links");
        listDataHeader.add("Related");

    }
    private void setListDataChild(){

        setDataGeneralInfo();
        setDataDescription();
        setDataLinks();
        setDataRelated();
    }

    private void setDataGeneralInfo() {

        ArrayList<String> generalInfo = new ArrayList<String>(Arrays.asList(
                "Unique ref: " +ifNotNull( productDetails.getPermalink()),
                "Brand: " + ifNotNull(productDetails.getBrand().getName()),
                "Product Family: " + ifNotNull(productDetails.getProductFamily().getName()),
                "Width(mm): " + ifNotNull(productDetails.getWidth()),
                "Height(mm): " + ifNotNull(productDetails.getHeight()),
                "Depth(mm): " + ifNotNull(productDetails.getDepth()),
                "Date of publishing: " + ifNotNull(productDetails.getDateOfPublishing()),
                "Edition Number: " + ifNotNull(productDetails.getEditionNumber().toString())));

        listDataChild.put(listDataHeader.get(0),generalInfo);
    }

    private void setDataDescription() {
        ArrayList <String> description = new ArrayList<String>(Arrays.asList(
                productDetails.getDescriptionPlainText()));

        listDataChild.put(listDataHeader.get(1), description);
    }

    private void setDataLinks() {

        ArrayList <String> links = new ArrayList<>(Arrays.asList(
                "Product URL: " + ifNotNull(productDetails.getLinks().getExternalProductUrl()),
                "Installation instructions: " + ifNotNull(productDetails.getLinks().getInstallationInstructionUrl()),
                "COBie Product Data Sheet: " + ifNotNull(productDetails.getLinks().getCobieProductDataSheetUrl()),
                "Product certification: " + ifNotNull(productDetails.getLinks().getProductCertificationUrl()),
                "Technical description: " + ifNotNull(productDetails.getLinks().getTechnicalDescriptionUrl()),
                "Instruction video: " + ifNotNull(productDetails.getLinks().getInstructionVideoUrl()),
                "EAN code: " + ifNotNull(productDetails.getEanCode())
        ));

        listDataChild.put(listDataHeader.get(2), links);
    }

    private void setDataRelated() {
        ArrayList <String> related = new ArrayList<String>(Arrays.asList(
                "Material main: " + ifNotNull(productDetails.getMaterialMain().getName()),
                "Material secondary: " + ifNotNull(productDetails.getMaterialSecondary().getName()),
                "Designed in: " + ifNotNull(productDetails.getDesignedIn().getName()),
                "Weight Net(Kg): " + ifNotNull(productDetails.getWeight())
        ));

        listDataChild.put(listDataHeader.get(3), related);
    }

    public ArrayList<String> getListDataHeader() {
        return listDataHeader;
    }

    public HashMap<String, List<String>> getListDataChild() {
        return listDataChild;
    }

    private String ifNotNull(String productDetail){
        if(productDetail == null){
            return "n/a";
        }
        return productDetail;
    }
}
