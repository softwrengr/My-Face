package com.techeasesol.myface.fragments.mycards;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.techeasesol.myface.R;
import com.techeasesol.myface.fragments.NearPeoplesFragment;
import com.techeasesol.myface.utilities.GeneralUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SharedCardsFragment extends Fragment {
   View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_shared_cards, container, false);
        initUI();
        return view;
    }

    private void initUI(){
        ButterKnife.bind(this,view);

    }

}
