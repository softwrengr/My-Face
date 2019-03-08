package com.techeasesol.myface.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.techeasesol.myface.R;
import com.techeasesol.myface.activities.DrawerActivity;
import com.techeasesol.myface.models.cardDataModel.CardResponseModel;
import com.techeasesol.myface.models.loginDataModels.LoginResponseModel;
import com.techeasesol.myface.utilities.AlertUtils;
import com.techeasesol.myface.utilities.GeneralUtils;
import com.techeasesol.networking.ApiClient;
import com.techeasesol.networking.ApiInterface;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class YourCardFragment extends Fragment {
    AlertDialog alertDialog;
    View view;

    TextView tvCardName, tvCardAddress, tvCardEmail, tvDesignation;
    LinearLayout layoutSaveShare, layoutShareCard, layoutSaveCard;
    private int cardNumber;
    private String token;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        switch (cardNumber) {
            case 1:
                view = inflater.inflate(R.layout.custom_layout_card_one, container, false);
                break;
            case 3:
                view = inflater.inflate(R.layout.custom_layout_card_three, container, false);
                break;
            default:
                view = inflater.inflate(R.layout.fragment_your_card, container, false);
        }
        getActivity().setTitle("YOUR CARD");
        token = GeneralUtils.getApiToken(getActivity());
        cardNumber = GeneralUtils.getCardID(getActivity());

        layoutSaveShare = view.findViewById(R.id.save_share_layout);
        layoutSaveShare.setVisibility(View.VISIBLE);
        tvCardName = view.findViewById(R.id.tv_card_name);
        tvCardAddress = view.findViewById(R.id.tv_card_address);
        tvCardEmail = view.findViewById(R.id.tv_card_email);
        tvDesignation = view.findViewById(R.id.tv_card_post);
        layoutShareCard = view.findViewById(R.id.layout_share_card);
        layoutSaveCard = view.findViewById(R.id.layout_save_card);

        initUI();

        return view;
    }

    private void initUI() {
        ButterKnife.bind(this, view);


        alertDialog = AlertUtils.createProgressDialog(getActivity());
        alertDialog.show();
        apiCallCardDetail();

        layoutShareCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.connectDrawerFragment(getActivity(), new NearPeoplesFragment());
            }
        });

    }

    private void apiCallCardDetail() {
        ApiInterface services = ApiClient.getApiClient(token).create(ApiInterface.class);
        Call<CardResponseModel> userLogin = services.getCardDetail(1);
        userLogin.enqueue(new Callback<CardResponseModel>() {
            @Override
            public void onResponse(Call<CardResponseModel> call, Response<CardResponseModel> response) {
                alertDialog.dismiss();
                if (response.body() == null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(getActivity(), jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                } else if (response.body().getStatus()) {
                    tvCardName.setText(response.body().getData().getName());
                    tvCardAddress.setText(response.body().getData().getAddress());
                    tvCardEmail.setText(response.body().getData().getEmail());
                    tvDesignation.setText(response.body().getData().getDesignation());
                }
            }

            @Override
            public void onFailure(Call<CardResponseModel> call, Throwable t) {
                alertDialog.dismiss();
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
