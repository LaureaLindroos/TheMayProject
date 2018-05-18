package com.bimobject.themayproject;

import android.app.Activity;
import android.content.Context;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class TopBarFragment extends Fragment {
    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

      //  final View rootView = ;
     //   Button searchButtonTopBar = rootView.findViewById(R.id.searchButtonTopBar);

        return inflater.inflate(R.layout.top_bar_fragment, parent, false);


    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
      RelativeLayout searchButtonTopBar = view.findViewById(R.id.searchButtonTopBar);
        searchButtonTopBar.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText searchBox = view.getRootView().findViewById(R.id.searchBoxTopBar);
                String topBarSearch = searchBox.getText().toString();

                SearchResultActivity parentActivity = ((SearchResultActivity)getActivity());

                if(parentActivity.isTaskFinished()) {
                    parentActivity.getAdapter().clear();
                    parentActivity.setPage(1);
                    parentActivity.setSearch(topBarSearch);
                    parentActivity.setLoadListItemsTask(parentActivity.createNewTask());
                    parentActivity.getLoadListItemsTask().execute(topBarSearch);
                }

            }


        });

        ImageView bimLogo =view.findViewById(R.id.topBarBimLogo);

        bimLogo.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MainSearchActivity.class);
                startActivity(intent);
            }


        });
    }


}