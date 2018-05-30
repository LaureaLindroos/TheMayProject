package com.bimobject.themayproject.ui.searchresultactivity;

import com.bimobject.themayproject.dto.Categories;
import com.bimobject.themayproject.dto.SubCategories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrepareCategoriesEXLV {

    public HashMap <String, String> childHashMapKeyValue;
    private List<Categories> collectedDataList;
    public HashMap <String, String> listCategoriesHeader;
    private HashMap <String, ArrayList<String>> childHashMap;
    public ArrayList<String> childArray;
    private ArrayList<String> headerArray;
    public HashMap <String, HashMap<String, String>> catalogueSubcategories;



    public PrepareCategoriesEXLV(List<Categories> collectedDataList) {
        this.collectedDataList = collectedDataList;
        listCategoriesHeader = new HashMap<>();
        headerArray= new ArrayList<>();
        childHashMap = new HashMap<>();
        childHashMapKeyValue = new HashMap<>();
        catalogueSubcategories = new HashMap<>();

        setListCategoriesHeader();
        setListCategoriesChild();

    }

    private void setListCategoriesChild() {

        for( Categories category: collectedDataList){
            ArrayList<SubCategories> children = category.getChildren();
            childArray = new ArrayList<>();
            childHashMapKeyValue = new HashMap<>();
            for(SubCategories child : children) {
                childArray.add(child.getName());
                childHashMapKeyValue.put(child.getName(), child.getId());
            }

            childHashMap.put(category.getName(), childArray);

            catalogueSubcategories.put(category.getName(), childHashMapKeyValue);
        }
    }

    private void setListCategoriesHeader(){
       for( Categories category: collectedDataList){
           listCategoriesHeader.put(category.getName(), category.getId());
           headerArray.add(category.getName());
       }
    }


    public HashMap<String, String> getListCategoriesHeader() {
        return listCategoriesHeader;
    }


    public HashMap<String, ArrayList<String>> getChildHashMap() {
        return childHashMap;
    }

    public ArrayList<String> getHeaderArray() {
        return headerArray;
    }

}

