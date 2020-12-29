package com.testapp.travel.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.testapp.travel.R;
import com.testapp.travel.ui.auth.SignInActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import net.hockeyapp.android.UpdateManager;

public abstract class BaseActivity extends AppCompatActivity implements ProgressState, GoogleApiClient.OnConnectionFailedListener {

    @VisibleForTesting
    public ProgressDialog mProgressDialog;
    private FirebaseAuth mAuth;
    protected FirebaseUser mUser;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private static final int APP_SIGN_IN = 1000;

    //protected abstract FirebaseAuth.AuthStateListener createAuthStateListener();
    protected abstract void onAuthStateSignIn();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkForUpdates();

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    Intent intent = SignInActivity.newIntent(getBaseContext());
                    startActivityForResult(intent, APP_SIGN_IN);
                } else {
                    onAuthStateSignIn();
                }
            }
        };
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterManagers();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterManagers();
    }

    private void checkForUpdates() {
        // Remove this for store builds!
//        UpdateManager.register(this);
    }

    private void unregisterManagers() {
        // Remove this for store builds!
        UpdateManager.unregister();
    }

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }
        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        hideProgressDialog();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mAuthListener != null) {
            mAuth.addAuthStateListener(mAuthListener);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == APP_SIGN_IN) {
            if (resultCode != RESULT_OK) {
                finish();
            }
        }
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();
    }
}
