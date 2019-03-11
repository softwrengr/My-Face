package com.techeasesol.myface.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techeasesol.myface.R;
import com.techeasesol.myface.utilities.GeneralUtils;

public class AboutSendFragment extends Fragment {
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_about_send, container, false);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                GeneralUtils.connectDrawerFragmentWithoutBack(getActivity(),new HomeFragment());
            }
        },2000);
        return view;

    }
}
