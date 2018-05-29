package com.bimobject.themayproject.ui.searchresultactivity;

import com.bimobject.themayproject.dto.Categories;
import com.bimobject.themayproject.dto.SubCategories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrepareCategoriesEXLV {
    private HashMap <String, String> listCategoriesHeader;
    private HashMap <String, String> childArray;
    private List<Categories> collectedDataList;
    private HashMap<String, HashMap<String, String>> listCategoriesChild;


    public PrepareCategoriesEXLV(List<Categories> collectedDataList) {
        this.collectedDataList = collectedDataList;
        listCategoriesChild = new HashMap<>();
        listCategoriesHeader = new HashMap<>();
        childArray = new HashMap<>();

        setListCategoriesHeader();

        setListCategoriesChild();
        printList();

    }

    private void setListCategoriesChild() {

        for( Categories category: collectedDataList){
            ArrayList<SubCategories> children = category.getChildren();
            for(SubCategories child : children)
                    childArray.put(child.getName(), child.getId());
        listCategoriesChild.put(category.getName(),childArray);
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
       }
    }



}

