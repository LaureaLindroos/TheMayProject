package com.bimobject.themayproject.ui.searchresultactivity;

import com.bimobject.themayproject.dto.Categories;
import com.bimobject.themayproject.dto.SubCategories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrepareCategoriesEXLV {

    private HashMap <String, String> childHashMapKeyValue;
    private List<Categories> collectedDataList;
    private HashMap <String, String> listCategoriesHeader;
    private HashMap<String, HashMap<String, String>> listCategoriesChild;
    private HashMap <String, ArrayList<String>> childHashMap;
    private ArrayList<String> childArray;
    private ArrayList<String> headerArray;



    public PrepareCategoriesEXLV(List<Categories> collectedDataList) {
        this.collectedDataList = collectedDataList;
        listCategoriesChild = new HashMap<>();
        listCategoriesHeader = new HashMap<>();
        headerArray= new ArrayList<>();
        childHashMap = new HashMap<>();
        childHashMapKeyValue = new HashMap<>();

        setListCategoriesHeader();
        setListCategoriesChild();

    }

    private void setListCategoriesChild() {

        for( Categories category: collectedDataList){
            ArrayList<SubCategories> children = category.getChildren();
            childArray = new ArrayList<>();
            for(SubCategories child : children) {
                childArray.add(child.getName());
                childHashMapKeyValue.put(child.getName(), child.getId());
            }

            childHashMap.put(category.getName(), childArray);
        }
    }

    private void printList() {

        for(Map.Entry<String, HashMap<String,String>> t :this.listCategoriesChild.entrySet()){
            String key = t.getKey();
            for (Map.Entry<String,String> e : t.getValue().entrySet())
                System.out.println("OuterKey:" + key + " InnerKey: " + e.getKey()+ " VALUE:" +e.getValue());
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

    public HashMap<String, HashMap<String, String>> getListCategoriesChild() {
        return listCategoriesChild;
    }

    public HashMap<String, ArrayList<String>> getChildHashMap() {
        return childHashMap;
    }

    public ArrayList<String> getHeaderArray() {
        return headerArray;
    }

}

