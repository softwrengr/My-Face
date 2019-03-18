package com.techeasesol.myface.fragments.mycards;

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


public class CardsFragment extends Fragment {
    View view;
    @BindView(R.id.btn_my_cards)
    Button btnMyCards;
    @BindView(R.id.btn_shared_cards)
    Button btnSharedCards;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_cards, container, false);
        getActivity().setTitle("Card Organizer");
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String checkFragment = bundle.getString("fragment" );
            if(checkFragment.equals("share_frament")){
                GeneralUtils.connectCardsFragmentWithBack(getActivity(),new SharedCardsFragment());
            }
            else {
                GeneralUtils.connectCardsFragmentWithBack(getActivity(),new AllSavedCardsFragment());
            }
        }
        else {
            GeneralUtils.connectCardsFragmentWithBack(getActivity(),new AllSavedCardsFragment());
        }


        initUI();
        return view;
    }

    private void initUI(){
        ButterKnife.bind(this,view);

        btnMyCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.connectCardsFragmentWithBack(getActivity(),new AllSavedCardsFragment());
            }
        });

        btnSharedCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.connectCardsFragmentWithBack(getActivity(),new SharedCardsFragment());
            }
        });

    }
}
