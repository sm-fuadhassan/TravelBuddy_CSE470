package com.testapp.travel.ui.media;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.testapp.travel.R;
import com.testapp.travel.ui.SingleFragmentActivity;


public class MediaActivity extends SingleFragmentActivity {


    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, MediaActivity.class);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return MediaFragment.newInstance();
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
