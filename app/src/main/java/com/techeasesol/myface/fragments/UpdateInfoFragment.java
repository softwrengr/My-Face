package com.techeasesol.myface.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.techeasesol.myface.R;
import com.techeasesol.myface.utilities.GeneralUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


public class UpdateInfoFragment extends Fragment {
    View view;
    @BindView(R.id.btn_update)
    Button btnUpdate;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_update_info, container, false);
        getActivity().setTitle("Editing");
        initUI();
        return view;
    }

    private void initUI(){
        ButterKnife.bind(this,view);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.connectDrawerFragment(getActivity(),new YourCardFragment());
            }
        });
    }
}
