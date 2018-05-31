package com.bimobject.themayproject.ui.searchresultactivity;

import android.content.Intent;
import android.graphics.Color;


import android.app.Activity;


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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.bimobject.themayproject.adapters.RecycleViewAdapter;

import com.bimobject.themayproject.R;
import com.bimobject.themayproject.adapters.RecycleViewAdapter;
import com.bimobject.themayproject.constants.FILTER;
import com.bimobject.themayproject.dto.Categories;

import com.bimobject.themayproject.helpers.OnLoadListener;
import com.bimobject.themayproject.helpers.OnNewRequestListener;
import com.bimobject.themayproject.helpers.RVAHelper;

import com.bimobject.themayproject.helpers.Request;
import com.bimobject.themayproject.helpers.RequestService;
import com.bimobject.themayproject.helpers.TokenGenerator;
import com.bimobject.themayproject.ui.productinfoactivity.ProductInfoActivity;


import java.util.ArrayList;
import java.util.HashMap;

import com.bimobject.themayproject.helpers.RequestService;

import java.util.List;
import java.util.Map;

import java.lang.ref.WeakReference;


public class SearchResultActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private static RecycleViewAdapter adapter;
    private static String search;
    CheckedFilterAdapter mMenuAdapter;
    ExpandableListView expandableList;
    ArrayList<String> listDataHeader;
    HashMap<String, ArrayList<String>> listDataChild;
    private DrawerLayout drawer;
    SearchView searchView;
    PrepareCategoriesEXLV prepareCategoriesEXLV;
    Request request;

    //Icons, use as you want
   /* static int[] icon = { R.drawable.ico1, R.drawable.ico1,
            R.drawable.ico1, R.drawable.ico1,
            R.drawable.ico1, R.drawable.ico1, R.drawable.ico1};*/




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        request = new Request();
        if(getIntent().hasExtra("search")){
            request.addSearch(getIntent().getStringExtra("search"));
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

        adapter = new RecycleViewAdapter();
        RecyclerView recyclerView = findViewById(R.id.activity_search_result_rv_list);
        TextView emptyView = findViewById(R.id.activity_search_result_empty_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener((view, productId) -> {
            Intent intent = new Intent(SearchResultActivity.this, ProductInfoActivity.class);
            intent.putExtra("productId", productId);
            startActivity(intent);
        });

        adapter.setOnBottomReachedListener(position -> {
            Toast.makeText(SearchResultActivity.this, getString(R.string.load_more_products), Toast.LENGTH_LONG).show();
            adapter.getHelper().loadNextPage();
        });

        adapter.setOnNewRequestListener(request1 -> {
            if (request1.getTotalCount() == 0) {
                Toast.makeText(SearchResultActivity.this, getString(R.string.zero_results), Toast.LENGTH_LONG).show();
                recyclerView.setVisibility(View.GONE);
                emptyView.setVisibility(View.VISIBLE);
            } else {
                Toast.makeText(SearchResultActivity.this, RVAHelper.getRequest().getTotalCount() + getString(R.string.found_product), Toast.LENGTH_LONG).show();

                /*
                TextView responseTextValue = findViewById(R.id.activity_search_response_search_term);
                String search_term = request1.getSearch();
                if (search_term.length() > 20) {
                    search_term = search_term.substring(0, 20) + "...";
                }
                responseTextValue.setText("\"" + search_term + "\"");

                TextView responseTotalCount = findViewById(R.id.activity_search_response_total_count);
                responseTotalCount.setText(String.valueOf(request1.getTotalCount()));

                TextView responseTotalFilters = findViewById(R.id.activity_search_response_total_filters_applied);
                responseTotalFilters.setText("10");
                */

                recyclerView.setVisibility(View.VISIBLE);
                emptyView.setVisibility(View.GONE);
            }
        });

        adapter.getHelper().makeNewRequest(request);

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


        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setIconified(false);
        searchView.setQuery(RVAHelper.getRequest().getSearch(), false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                request = new Request();
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


            Button resetFilter = findViewById(R.id.filter_reset_btn);
            resetFilter.setOnClickListener(view -> makeCleanFilter());

            // setting list adapter
            expandableList.setAdapter(mMenuAdapter);
            expandableList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                @Override
                public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long id) {
                    return false;
                }
            });
            expandableList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                @Override
                public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                    return false;
                }
            });


        }

        @Override
        protected List<Categories> doInBackground(String... strings) {
            return RequestService.getcategories();
        }
    }

    private void makeCleanFilter() {

        mMenuAdapter.getCheckBoxes().forEach(c -> c.setChecked(false));
        request.clearParams();
        adapter.getHelper().makeNewRequest(request);


    /*
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.drawer_filter_search_action) {
            request.addCategory("137");
            adapter.getHelper().makeNewRequest(request);
        } else if (id == R.id.drawer_filter_search_clear) {
            request.clearParams();
            adapter.getHelper().makeNewRequest(request);
        }

        drawer.closeDrawer(GravityCompat.END);
        return true;
          */

    }

    //DRAWER FINISHED

}