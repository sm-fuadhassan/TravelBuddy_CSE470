package com.testapp.travel.ui.places;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.testapp.travel.R;
import com.testapp.travel.ui.TabFragmentsActivity;
import com.testapp.travel.ui.navigation.ViewPagerAdapter;

public class PlacesActivity extends TabFragmentsActivity {


    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, PlacesActivity.class);
        return intent;
    }


    @Override
    protected void onAuthStateSignIn() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected ViewPagerAdapter createViewPagerAdapter() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(BucketlistFragment.newInstance(), getResources().getString(R.string.tab_places_bucketlist));
        adapter.addFrag(LikedFragment.newInstance(), getResources().getString(R.string.tab_places_liked));
        adapter.addFrag(RecommendedFragment.newInstance(), getResources().getString(R.string.tab_places_recommended));
        adapter.addFrag(VisitedFragment.newInstance(), getResources().getString(R.string.tab_places_visited));
        return adapter;
    }



    @Override
    public void onResume() {
        super.onResume();
        this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }


    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
