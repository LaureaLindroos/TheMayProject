package com.bimobject.themayproject.adapters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.bimobject.themayproject.R;
import com.bimobject.themayproject.dto.Categories;
import com.bimobject.themayproject.dto.SubCategories;
import com.bimobject.themayproject.ui.searchresultactivity.PrepareCategoriesEXLV;
import com.bimobject.themayproject.helpers.Request;
import com.loopj.android.http.RequestParams;

// Eclipse wanted me to use a sparse array instead of my hashmaps, I just suppressed that suggestion
@SuppressLint("UseSparseArrays")
public class CheckedFilterAdapter extends BaseExpandableListAdapter {

    // Define activity context
    private Context mContext;
    private PrepareCategoriesEXLV prepareCategoriesEXLV;
    private Request request;
    private DrawerLayout drawer;
    private static RecycleViewAdapter adapter;
    /*
     * Here we have a Hashmap containing a String key
     * (can be Integer or other type but I was testing
     * with contacts so I used contact name as the key)
     */
    private HashMap<String, ArrayList<String>> mListDataChild;

    // ArrayList that is what each key in the above
    // hashmap points to
    private ArrayList<String> mListDataGroup;
    public ArrayList<CompoundButton> checkBoxes;
    public ArrayList<CompoundButton> checkBoxesGroup;

    public HashMap<Integer, boolean[]> getmChildCheckStates() {
        return mChildCheckStates;
    }


    // Hashmap for keeping track of our checkbox check states
    private HashMap<Integer, boolean[]> mChildCheckStates;
    private ArrayList<Boolean> mGroupCheckStates;
    // Our getChildView & getGroupView use the viewholder patter
    // Here are the viewholders defined, the inner classes are
    // at the bottom
    private ChildViewHolder childViewHolder;
    private GroupViewHolder groupViewHolder;

    /*
     *  For the purpose of this document, I'm only using a single
     *	textview in the group (parent) and child, but you're limited only
     *	by your XML view for each group item :)
     */
    private String groupText;
    private String childText;
    private ArrayList<String> categoryParams;

    /*  Here's the constructor we'll use to pass in our calling
     *  activity's context, group items, and child items
     */
    public CheckedFilterAdapter(Context context,
                                ArrayList<String> listDataGroup, HashMap<String, ArrayList<String>> listDataChild, PrepareCategoriesEXLV mprepareCategoriesEXLV, Request mRequest, RecycleViewAdapter mAdapter) {

        mContext = context;
        mListDataGroup = listDataGroup;
        mListDataChild = listDataChild;
        prepareCategoriesEXLV =mprepareCategoriesEXLV;
        request = mRequest;
        adapter = mAdapter;
        this.categoryParams = new ArrayList<>();
        this.checkBoxes = new ArrayList<>();
        this.checkBoxesGroup = new ArrayList<>();

        // Initialize our hashmap containing our check states here
        mChildCheckStates = new HashMap<Integer, boolean[]>();
        mGroupCheckStates = new ArrayList<>();
    }

    @Override
    public int getGroupCount() {
        return mListDataGroup.size();
    }

    /*
     * This defaults to "public object getGroup" if you auto import the methods
     * I've always make a point to change it from "object" to whatever item
     * I passed through the constructor
     */
    @Override
    public Object getGroup(int groupPosition) {
        return mListDataGroup.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        //  I passed a text string into an activity holding a getter/setter
        //  which I passed in through "ExpListGroupItems".
        //  Here is where I call the getter to get that text
        groupText =(String) getGroup(groupPosition);

        if (convertView == null) {

            LayoutInflater infallInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infallInflater.inflate(R.layout.expandable_filter_list_header, null);

            // Initialize the GroupViewHolder defined at the bottom of this document
            groupViewHolder = new GroupViewHolder();

            groupViewHolder.mGroupText = (TextView) convertView.findViewById(R.id.expandable_filter_sublist_item);

            groupViewHolder.mGroupCheckBox=(CheckBox) convertView.findViewById(R.id.check_Box_group);

            convertView.setTag(groupViewHolder);
        } else {

            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }


        groupViewHolder.mGroupText.setText(groupText);
        groupViewHolder.mGroupCheckBox.setOnCheckedChangeListener(null);

        boolean getChecked = false;
        mGroupCheckStates.add(getChecked);
        groupViewHolder.mGroupCheckBox.setChecked(false);

        checkBoxes.add(groupViewHolder.mGroupCheckBox);

        groupViewHolder.mGroupCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    boolean getChecked = mGroupCheckStates.get(groupPosition);
                    getChecked = isChecked;
                    mGroupCheckStates.add(getChecked);
                    categoryParams.add(prepareCategoriesEXLV.listCategoriesHeader.get(mListDataGroup.get(groupPosition).toString()).toString());
                    makeRequest();

                }
                else{
                    boolean getChecked = mGroupCheckStates.get(groupPosition);
                    getChecked = isChecked;
                    mGroupCheckStates.add(getChecked);
                    categoryParams.remove(prepareCategoriesEXLV.listCategoriesHeader.get(mListDataGroup.get(groupPosition).toString()).toString());
                    makeRequest();
                }
                }
        });

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mListDataChild.get(mListDataGroup.get(groupPosition)).size();
    }

    /*
     * This defaults to "public object getChild" if you auto import the methods
     * I've always make a point to change it from "object" to whatever item
     * I passed through the constructor
     */
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mListDataChild.get(mListDataGroup.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final int mGroupPosition = groupPosition;
        final int mChildPosition = childPosition;

        //  I passed a text string into an activity holding a getter/setter
        //  which I passed in through "ExpListChildItems".
        //  Here is where I call the getter to get that text
        childText = (String) getChild(mGroupPosition, mChildPosition).toString();

        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.expandable_filter_sublist, null);

            childViewHolder = new ChildViewHolder();

            childViewHolder.mChildText = (TextView) convertView
                    .findViewById(R.id.expandable_filter_sublist_item);

            childViewHolder.mCheckBox = (CheckBox) convertView
                    .findViewById(R.id.check_Box);

            convertView.setTag(R.layout.expandable_filter_sublist, childViewHolder);

        } else {

            childViewHolder = (ChildViewHolder) convertView
                    .getTag(R.layout.expandable_filter_sublist);
        }

        childViewHolder.mChildText.setText(childText);

        /*
         * You have to set the onCheckChangedListener to null
         * before restoring check states because each call to
         * "setChecked" is accompanied by a call to the
         * onCheckChangedListener
         */
        childViewHolder.mCheckBox.setOnCheckedChangeListener(null);

        if (mChildCheckStates.containsKey(mGroupPosition)) {
            /*
             * if the hashmap mChildCheckStates<Integer, Boolean[]> contains
             * the value of the parent view (group) of this child (aka, the key),
             * then retrive the boolean array getChecked[]
             */
            boolean getChecked[] = mChildCheckStates.get(mGroupPosition);

            // set the check state of this position's checkbox based on the
            // boolean value of getChecked[position]
            childViewHolder.mCheckBox.setChecked(getChecked[mChildPosition]);

        } else {

            /*
             * if the hashmap mChildCheckStates<Integer, Boolean[]> does not
             * contain the value of the parent view (group) of this child (aka, the key),
             * (aka, the key), then initialize getChecked[] as a new boolean array
             *  and set it's size to the total number of children associated with
             *  the parent group
             */
            boolean getChecked[] = new boolean[getChildrenCount(mGroupPosition)];

            // add getChecked[] to the mChildCheckStates hashmap using mGroupPosition as the key
            mChildCheckStates.put(mGroupPosition, getChecked);

            // set the check state of this position's checkbox based on the
            // boolean value of getChecked[position]
            childViewHolder.mCheckBox.setChecked(false);
        }

        checkBoxes.add(childViewHolder.mCheckBox);

        childViewHolder.mCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                    boolean getChecked[] = mChildCheckStates.get(mGroupPosition);
                    getChecked[mChildPosition] = isChecked;
                    mChildCheckStates.put(mGroupPosition, getChecked);
                    String outerVal = mListDataGroup.get(groupPosition).toString();
                    String innerVal = mListDataChild.get(outerVal).get(childPosition).toString();

                    if(categoryParams.contains(prepareCategoriesEXLV.listCategoriesHeader.get(mListDataGroup.get(groupPosition).toString()).toString()))
                    {
                        categoryParams.remove(prepareCategoriesEXLV.listCategoriesHeader.get(mListDataGroup.get(groupPosition).toString()).toString());
                        boolean getCheckedGroup = mGroupCheckStates.get(mGroupPosition);
                        getCheckedGroup = false;
                        checkBoxes.get(groupPosition).setChecked(false);
                    }


                    categoryParams.add(prepareCategoriesEXLV.catalogueSubcategories.get(outerVal).get(innerVal).toString());
                   makeRequest();

                } else {

                    boolean getChecked[] = mChildCheckStates.get(mGroupPosition);
                    getChecked[mChildPosition] = isChecked;
                    mChildCheckStates.put(mGroupPosition, getChecked);
                    String outerVal = mListDataGroup.get(groupPosition).toString();
                    String innerVal = mListDataChild.get(outerVal).get(childPosition).toString();
                    categoryParams.remove(prepareCategoriesEXLV.catalogueSubcategories.get(outerVal).get(innerVal).toString());
                    makeRequest();
                }
            }
        });

        return convertView;
    }

    private void makeRequest() {

            request.clearParams();

        for(String param : categoryParams){
            request.addCategory(param);
        }
        adapter.getHelper().makeNewRequest(request);

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    public final class GroupViewHolder {

        TextView mGroupText;
        CheckBox mGroupCheckBox;
    }

    public final class ChildViewHolder {

        TextView mChildText;
        CheckBox mCheckBox;
    }

    public ArrayList<CompoundButton> getCheckBoxes(){
        return this.checkBoxes;
    }

}
