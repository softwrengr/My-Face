package com.techeasesol.myface.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.techeasesol.myface.R;
import com.techeasesol.myface.utilities.GeneralUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {
    View view;
    @BindView(R.id.layout_one)
    RelativeLayout layoutCardOne;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        initUI();
        return view;
    }

    private void initUI(){
        ButterKnife.bind(this,view);

        layoutCardOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.connectDrawerFragmentWithoutBack(getActivity(),new UpdateInfoFragment());
            }
        });
    }
}