package com.bimobject.themayproject.ui.searchresultactivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.ExpandableListView;
import android.widget.Toast;


import com.bimobject.themayproject.adapters.CheckedFilterAdapter;
import com.bimobject.themayproject.adapters.FilterListAdapter;
import com.bimobject.themayproject.adapters.RecycleViewAdapter;

import com.bimobject.themayproject.R;
import com.bimobject.themayproject.adapters.RecycleViewAdapter;
import com.bimobject.themayproject.constants.STRINGS;
import com.bimobject.themayproject.dto.Categories;
import com.bimobject.themayproject.helpers.Request;
import com.bimobject.themayproject.helpers.RequestService;
import com.bimobject.themayproject.helpers.TokenGenerator;
import com.bimobject.themayproject.ui.productinfoactivity.ProductInfoActivity;

import java.util.ArrayList;
import java.util.HashMap;

import com.bimobject.themayproject.helpers.RequestService;

import java.util.List;
import java.util.Map;

public class SearchResultActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static RecycleViewAdapter adapter;
    private static String search;
    CheckedFilterAdapter mMenuAdapter;
    ExpandableListView expandableList;
    ArrayList<String> listDataHeader;
    HashMap<String, ArrayList<String>> listDataChild;
    private DrawerLayout drawer;
    private Request request;
    SearchView searchView;
    PrepareCategoriesEXLV prepareCategoriesEXLV;

    //Icons, use as you want
   /* static int[] icon = { R.drawable.ico1, R.drawable.ico1,
            R.drawable.ico1, R.drawable.ico1,
            R.drawable.ico1, R.drawable.ico1, R.drawable.ico1};*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        if (getIntent().hasExtra("search")) {
            search = getIntent().getStringExtra("search");
            new getCategoriesTask(this).execute();
        }

        //DRAWER START
        drawer = findViewById(R.id.drawer_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        toolbar.setLogo(R.drawable.ic_logo_bimobject_black);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }





        //DRAWER END

        request = new Request();
        request.addSearch(search);

        adapter = new RecycleViewAdapter();

        if (getIntent().hasExtra("search")) {
            search = getIntent().getStringExtra("search");
        }

        Request request = new Request();
        request.addSearch(search);

        adapter.getHelper().makeNewRequest(request);

        RecyclerView recyclerView = findViewById(R.id.activity_search_result_rv_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener((view, productId) -> {
            Intent intent = new Intent(SearchResultActivity.this, ProductInfoActivity.class);
            intent.putExtra("productId", productId);
            startActivity(intent);
        });

        adapter.setOnBottomReachedListener(position -> {
            Toast.makeText(SearchResultActivity.this, STRINGS.FETCH_MORE_PRODUCTS, Toast.LENGTH_LONG).show();
            adapter.getHelper().loadNextPage();
        });
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        TokenGenerator.start(getString(R.string.client_id), getString(R.string.client_secret));
    }


    //DRAWER CONTINUE

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        drawer.closeDrawers();
                        return true;
                    }
                });
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);


        searchView = (SearchView) searchItem.getActionView();
        searchView.setIconifiedByDefault(false);
        searchView.setIconified(false);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Request request = new Request();
                request.addSearch(query);
                adapter.getHelper().makeNewRequest(request);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case R.id.action_drawer:
                drawer.openDrawer(GravityCompat.END);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        /*int id = item.getItemId();

        if (id == R.id.drawer_filter_search_action) {
            request.addCategory("137");
            adapter.getHelper().makeNewRequest(request);
        } else if (id == R.id.drawer_filter_search_clear) {
            request.clearParams();
            adapter.getHelper().makeNewRequest(request);
        }

        drawer.closeDrawer(GravityCompat.END);*/
        return true;
    }

    private class getCategoriesTask extends AsyncTask<String, String, List<Categories>>{
        public getCategoriesTask(SearchResultActivity searchResultActivity)  {
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);

        }

        @Override
        protected void onPostExecute(List<Categories> categories) {
            super.onPostExecute(categories);
            expandableList = findViewById(R.id.expandable_filter_list);
            prepareCategoriesEXLV = new PrepareCategoriesEXLV(categories);
            listDataHeader = prepareCategoriesEXLV.getHeaderArray();
            listDataChild = prepareCategoriesEXLV.getChildHashMap();
            mMenuAdapter = new CheckedFilterAdapter(getApplicationContext(), listDataHeader, listDataChild, prepareCategoriesEXLV, request, adapter);

            // setting list adapter
            expandableList.setAdapter(mMenuAdapter);
            expandableList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                @Override
                public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long id) {


/*
                    String outerVal = listDataHeader.get(groupPosition).toString();
                    String innerVal = listDataChild.get(outerVal).get(childPosition).toString();

                    request.clearParam(outerVal);
                    request.addCategory(prepareCategoriesEXLV.catalogueSubcategories.get(outerVal).get(innerVal).toString());
                    adapter.getHelper().makeNewRequest(request);

                    drawer.closeDrawers();*/
                    return false;
                }
            });
            expandableList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                @Override
                public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                    /*if (listDataHeader.toString().equals("Reset")) {
                        request.clearParams();
                        adapter.getHelper().makeNewRequest(request);
                    }
                    else {
                        request.addCategory(prepareCategoriesEXLV.listCategoriesHeader.get(listDataHeader.get(i).toString()));

                       adapter.getHelper().makeNewRequest(request);
                    }*/
                    return false;
                }
            });


        }

        @Override
        protected List<Categories> doInBackground(String... strings) {
            return RequestService.getcategories();
        }
    }
    //DRAWER FINISHED
}