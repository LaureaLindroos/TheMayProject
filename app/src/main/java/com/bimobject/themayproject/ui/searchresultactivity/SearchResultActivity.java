package com.bimobject.themayproject.ui.searchresultactivity;

import android.content.Intent;
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

import android.widget.SearchView;

import android.widget.TextView;

import android.widget.Toast;


import com.bimobject.themayproject.adapters.RecycleViewAdapter;
import com.bimobject.themayproject.R;
import com.bimobject.themayproject.constants.STRINGS;
import com.bimobject.themayproject.helpers.OnNewRequestListener;
import com.bimobject.themayproject.helpers.RVAHelper;
import com.bimobject.themayproject.helpers.Request;
import com.bimobject.themayproject.helpers.TokenGenerator;
import com.bimobject.themayproject.ui.productinfoactivity.ProductInfoActivity;

public class SearchResultActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static RecycleViewAdapter adapter;
    private static String search;
    private RecyclerView recyclerView;
    private DrawerLayout drawer;
    private Request request;

    SearchView searchView;

    public TextView totalCountView;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

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
        //DRAWER END


        if (getIntent().hasExtra("search")) {
            search = getIntent().getStringExtra("search");
        }

        Request request = new Request();
        request.addSearch(search);

        adapter = new RecycleViewAdapter(this);
        adapter.getHelper().makeNewRequest(request);

        totalCountView = findViewById(R.id.activity_search_result_tv_total_count);
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
        adapter.setOnNewRequestListener(request1 -> Toast.makeText(SearchResultActivity.this, RVAHelper.getRequest().getTotalCount() + STRINGS.FOUND_PRODUCTS, Toast.LENGTH_LONG).show());
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        TokenGenerator.start(getString(R.string.client_id), getString(R.string.client_secret));
    }


    //DRAWER CONTINUE
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