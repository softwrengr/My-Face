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
import android.widget.Button;
import android.widget.Toast;

import com.techeasesol.myface.R;
import com.techeasesol.myface.activities.DrawerActivity;
import com.techeasesol.myface.activities.MainActivity;
import com.techeasesol.myface.activities.RecievedCardActivity;
import com.techeasesol.myface.fragments.mycards.CardsFragment;
import com.techeasesol.myface.models.cardDataModel.CardResponseModel;
import com.techeasesol.myface.models.cardStatusDataModels.CardStatusResponseModel;
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


public class RecievedCardFragment extends Fragment {
    AlertDialog alertDialog;
    @BindView(R.id.btn_delete_card)
    Button btnDeleteCard;
    @BindView(R.id.btn_accept_card)
    Button btnAcceptCard;
    View view;

    int cardID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_recieved_card, container, false);
        ((RecievedCardActivity)getActivity()).getSupportActionBar().hide();

        initUI();
        return view;
    }

    private void initUI() {
        ButterKnife.bind(this, view);
        cardID = Integer.parseInt(GeneralUtils.getSendCardID(getActivity()));

        btnAcceptCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog = AlertUtils.createProgressDialog(getActivity());
                alertDialog.show();
                apiCallAcceptRejectCard(cardID, 1);
            }
        });

        btnDeleteCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog = AlertUtils.createProgressDialog(getActivity());
                alertDialog.show();
                apiCallAcceptRejectCard(cardID, 0);
            }
        });
    }

    private void apiCallAcceptRejectCard(int cardID, int status) {
        ApiInterface services = ApiClient.getApiClient(GeneralUtils.getApiToken(getActivity())).create(ApiInterface.class);
        Call<CardStatusResponseModel> storeCard = services.acceptDeleteCard(cardID, status);
        storeCard.enqueue(new Callback<CardStatusResponseModel>() {
            @Override
            public void onResponse(Call<CardStatusResponseModel> call, Response<CardStatusResponseModel> response) {
                alertDialog.dismiss();
                if (response.body() == null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(getActivity(), jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                } else if (response.body().getStatus()) {
                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    GeneralUtils.putStringValueInEditor(getActivity(), "send_card_id", "");
                    startActivity(new Intent(getActivity(), DrawerActivity.class));
                }
            }

            @Override
            public void onFailure(Call<CardStatusResponseModel> call, Throwable t) {
                alertDialog.dismiss();
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
