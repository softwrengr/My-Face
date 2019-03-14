package com.techeasesol.myface.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.techeasesol.myface.R;
import com.techeasesol.myface.firebase.MyFirebaseMessagingService;
import com.techeasesol.myface.utilities.GeneralUtils;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {
    View view;
    @BindView(R.id.card_one)
    RelativeLayout cardOne;
    @BindView(R.id.card_two)
    LinearLayout cardTwo;
    @BindView(R.id.card_three)
    RelativeLayout cardThree;
    @BindView(R.id.card_four)
    RelativeLayout cardFour;
    @BindView(R.id.card_six)
    RelativeLayout cardSix;
    @BindView(R.id.card_seven)
    RelativeLayout cardSeven;
    @BindView(R.id.card_nine)
    RelativeLayout cardNine;
    @BindView(R.id.card_ten)
    RelativeLayout cardTen;

    int cardID;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        getActivity().setTitle("Cards");
        initUI();
        onback(view);
        return view;
    }

    private void initUI(){
        ButterKnife.bind(this,view);

        if(GeneralUtils.getCardMessage(getActivity())!=null && GeneralUtils.getCardMessage(getActivity()).contains("your Card has been accepted")){
            showMessage(GeneralUtils.getCardMessage(getActivity()),"message");
        }

        if(GeneralUtils.getCardMessage(getActivity())!=null && GeneralUtils.getCardMessage(getActivity()).contains("your Card has been rejected")){
            showMessage(GeneralUtils.getCardMessage(getActivity()),"message");
        }

        if(GeneralUtils.getCardMessage(getActivity())!=null && GeneralUtils.getCardMessage(getActivity()).contains("You have new card from")){
            showMessage(GeneralUtils.getCardMessage(getActivity()),"new card");

        }


        cardOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.putIntegerValueInEditor(getActivity(),"card_id",1);
                GeneralUtils.connectDrawerFragment(getActivity(),new UpdateInfoFragment());
            }
        });

        cardTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.putIntegerValueInEditor(getActivity(),"card_id",2);
                GeneralUtils.connectDrawerFragment(getActivity(),new UpdateInfoFragment());
            }
        });

        cardThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.putIntegerValueInEditor(getActivity(),"card_id",3);
                GeneralUtils.connectDrawerFragment(getActivity(),new UpdateInfoFragment());
            }
        });

        cardFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.putIntegerValueInEditor(getActivity(),"card_id",4);
                GeneralUtils.connectDrawerFragment(getActivity(),new UpdateInfoFragment());
            }
        });

        cardSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.putIntegerValueInEditor(getActivity(),"card_id",6);
                GeneralUtils.connectDrawerFragment(getActivity(),new UpdateInfoFragment());
            }
        });

        cardSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.putIntegerValueInEditor(getActivity(),"card_id",7);
                GeneralUtils.connectDrawerFragment(getActivity(),new UpdateInfoFragment());
            }
        });

        cardNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.putIntegerValueInEditor(getActivity(),"card_id",9);
                GeneralUtils.connectDrawerFragment(getActivity(),new UpdateInfoFragment());
            }
        });

        cardTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.putIntegerValueInEditor(getActivity(),"card_id",10);
                GeneralUtils.connectDrawerFragment(getActivity(),new UpdateInfoFragment());
            }
        });
    }

    private void onback(View view) {
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    getActivity().finish();
                    return true;
                }
                return false;
            }
        });

    }

    private void showMessage(String message, final String card){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("My Face");
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(card.equals("new card")){
                    GeneralUtils.connectDrawerFragment(getActivity(),new RecievedCardFragment());
                }
                else if(card.equals("message")){
                    GeneralUtils.putStringValueInEditor(getActivity(),"card_message","");
                }

            }
        });
        builder.show();
    }
}
