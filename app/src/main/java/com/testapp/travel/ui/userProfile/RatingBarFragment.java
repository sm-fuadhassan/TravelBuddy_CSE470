package com.testapp.travel.ui.userProfile;


import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.testapp.travel.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class RatingBarFragment extends DialogFragment {

    private RatingBar ratingBar;
    private TextView btnDone;
    private float rating = Float.MAX_EXPONENT;

    public RatingBarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_rating_bar, container, false);
        ratingBar = (RatingBar) rootView.findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                rating = v;
            }
        });
        btnDone = (TextView) rootView.findViewById(R.id.done);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendBackResult();
            }
        });
        return rootView;
    }

    // Defines the listener interface
    public interface RatingDialogListener {
        void onFinishEditDialog(float rating);
    }

    // Call this method to send the data back to the parent fragment
    public void sendBackResult() {
        // Notice the use of `getTargetFragment` which will be set when the dialog is displayed
        RatingDialogListener listener = (RatingDialogListener) getTargetFragment();
        listener.onFinishEditDialog(rating);
        dismiss();
    }


}
