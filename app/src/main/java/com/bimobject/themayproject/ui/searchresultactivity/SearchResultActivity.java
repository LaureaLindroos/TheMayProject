package com.bimobject.themayproject.ui.searchresultactivity;

import android.content.Intent;
import android.graphics.Color;
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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;


import com.bimobject.themayproject.adapters.FilterListAdapter;
import com.bimobject.themayproject.adapters.RecycleViewAdapter;
import com.bimobject.themayproject.R;
import com.bimobject.themayproject.constants.STRINGS;
import com.bimobject.themayproject.helpers.Request;
import com.bimobject.themayproject.helpers.TokenGenerator;
import com.bimobject.themayproject.ui.productinfoactivity.ProductInfoActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchResultActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static RecycleViewAdapter adapter;
    private static String search;
    FilterListAdapter mMenuAdapter;
    ExpandableListView expandableList;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    private DrawerLayout drawer;
    private Request request;

    //Icons, use as you want
   /* static int[] icon = { R.drawable.ico1, R.drawable.ico1,
            R.drawable.ico1, R.drawable.ico1,
            R.drawable.ico1, R.drawable.ico1, R.drawable.ico1};*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        if(getIntent().hasExtra("search")){
            search = getIntent().getStringExtra("search");
        }

        //DRAWER START
        drawer = findViewById(R.id.drawer_layout);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        expandableList = findViewById(R.id.expandable_filter_list);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }
        prepareListData();
        mMenuAdapter = new FilterListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expandableList.setAdapter(mMenuAdapter);

        expandableList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long id) {
                String childValue = mMenuAdapter.getChild(groupPosition, childPosition).toString();

                if (childValue == "Category 137") {
                    request.addCategory("137");
                    adapter.getHelper().makeNewRequest(request);
                }
                view.setSelected(true);

                drawer.closeDrawers();
                return false;
            }
        });
        expandableList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                    return false;
            }
        });

        //DRAWER END

        request = new Request();
        request.addSearch(search);


        adapter = new RecycleViewAdapter();
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
    private void prepareListData() {
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();

        // Adding data header
        listDataHeader.add("Category");
        listDataHeader.add("Reset");


        // Adding child data
        List<String> heading1 = new ArrayList<>();
        heading1.add("Category 137");
        heading1.add("Submenu");
        heading1.add("Submenu");

        List<String> heading2 = new ArrayList<>();


        listDataChild.put(listDataHeader.get(0), heading1);// Header, Child data
        listDataChild.put(listDataHeader.get(1), heading2);
    }

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
    }
    //DRAWER FINISHED
}