package com.bimobject.themayproject.ui.productinfoactivity;

import com.bimobject.themayproject.dto.ProductInformation.ProductDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PrepareProductInfo {
    ArrayList<String> listDataHeader;
    HashMap<String, List<String>>listDataChild;
    ProductDetails productDetails;

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
        ArrayList<String> generalInfo = new ArrayList<String>(Arrays.asList(
                "Unique ref: " + productDetails.getPermalink(),
                "Brand: " + productDetails.getBrand().getName(),
                "Product Family: " + productDetails.getProductFamily().getName(),
                "Width(mm): " + productDetails.getWidth(),
                "Height(mm): " + productDetails.getHeight(),
                "Depth(mm): " + productDetails.getDepth(),
                "Date of publishing: " + productDetails.getDateOfPublishing(),
                "Edition Number: " + productDetails.getEditionNumber()));

        ArrayList <String> description = new ArrayList<String>();
        ArrayList <String> links = new ArrayList<>(Arrays.asList(
                "Product URL: " + productDetails.getLinks().getExternalProductUrl(),
                "Installation instructions: " + productDetails.getLinks().getInstallationInstructionUrl(),
                "COBie Product Data Sheet: " + productDetails.getLinks().getCobieProductDataSheetUrl(),
                "Product certification: " + productDetails.getLinks().getProductCertificationUrl(),
                "Technical description: " + productDetails.getLinks().getTechnicalDescriptionUrl(),
                "Instruction video: " + productDetails.getLinks().getInstructionVideoUrl(),
                "EAN code: " + productDetails.getEanCode()
        ));
        ArrayList <String> related = new ArrayList<>(Arrays.asList(
               "Material main: " + productDetails.getMaterialMain().getName(),
                "Material secondary: " + ifNotNull(productDetails.getMaterialSecondary().getName()),
                "Designed in: " + productDetails.getDesignedIn().getName(),

                "Weight Net(Kg): " + ifNotNull(productDetails.getWeight())
        ));


        description.add(productDetails.getDescriptionPlainText());
        listDataChild.put(listDataHeader.get(0),generalInfo);
        listDataChild.put(listDataHeader.get(1), description);
        listDataChild.put(listDataHeader.get(2), links);
        listDataChild.put(listDataHeader.get(3), related);



    }

    public ArrayList<String> getListDataHeader() {
        return listDataHeader;
    }

    public HashMap<String, List<String>> getListDataChild() {
        return listDataChild;
    }
    public String ifNotNull(String productDetail){
        if(productDetail == null){
            return "n/a";
        }
        return productDetail;
    }
}
