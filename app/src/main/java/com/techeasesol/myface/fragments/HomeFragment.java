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
    @BindView(R.id.card_one)
    RelativeLayout cardOne;
    @BindView(R.id.card_three)
    RelativeLayout cardThree;
    @BindView(R.id.card_four)
    RelativeLayout cardFour;
    @BindView(R.id.card_six)
    RelativeLayout cardSix;
    @BindView(R.id.card_seven)
    RelativeLayout cardSeven;
    @BindView(R.id.card_ten)
    RelativeLayout cardTen;
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

        cardOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.putIntegerValueInEditor(getActivity(),"card_id",1);
                GeneralUtils.connectDrawerFragmentWithoutBack(getActivity(),new UpdateInfoFragment());
            }
        });

        cardThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.putIntegerValueInEditor(getActivity(),"card_id",3);
                GeneralUtils.connectDrawerFragmentWithoutBack(getActivity(),new UpdateInfoFragment());
            }
        });

        cardFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.putIntegerValueInEditor(getActivity(),"card_id",4);
                GeneralUtils.connectDrawerFragmentWithoutBack(getActivity(),new UpdateInfoFragment());
            }
        });

        cardSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.putIntegerValueInEditor(getActivity(),"card_id",6);
                GeneralUtils.connectDrawerFragmentWithoutBack(getActivity(),new UpdateInfoFragment());
            }
        });

        cardSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.putIntegerValueInEditor(getActivity(),"card_id",7);
                GeneralUtils.connectDrawerFragmentWithoutBack(getActivity(),new UpdateInfoFragment());
            }
        });

        cardTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.putIntegerValueInEditor(getActivity(),"card_id",10);
                GeneralUtils.connectDrawerFragmentWithoutBack(getActivity(),new UpdateInfoFragment());
            }
        });
    }
}
