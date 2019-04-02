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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.techeasesol.myface.R;
import com.techeasesol.myface.activities.DrawerActivity;
import com.techeasesol.myface.fragments.mycards.AllSavedCardsFragment;
import com.techeasesol.myface.fragments.mycards.CardsFragment;
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

    ImageView ivCard;
    TextView tvCardName, tvCardAddress, tvCardEmail, tvDesignation, tvNumber;
    LinearLayout layoutSaveShare, layoutShareCard, layoutSaveCard, layoutEditCard;
    ImageView ivFacebook, ivInsta, ivLinkdin, ivTwitter;
    private String token;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        switch (GeneralUtils.getCardID(getActivity())) {
            case 1:
                view = inflater.inflate(R.layout.custom_layout_card_one, container, false);
                break;
            case 2:
                view = inflater.inflate(R.layout.custom_layout_card_two, container, false);
                break;
            case 3:
                view = inflater.inflate(R.layout.custom_layout_card_three, container, false);
                break;
            case 4:
                view = inflater.inflate(R.layout.custom_layout_card_four, container, false);
                break;
            case 5:
                view = inflater.inflate(R.layout.custom_layout_card_five, container, false);
                break;
            case 6:
                view = inflater.inflate(R.layout.custom_layout_card_six, container, false);
                break;
            case 7:
                view = inflater.inflate(R.layout.custom_layout_card_seven, container, false);
                break;
            case 8:
                view = inflater.inflate(R.layout.custom_layout_card_eight, container, false);
                break;
            case 9:
                view = inflater.inflate(R.layout.custom_layout_card_seven, container, false);
                break;
            case 10:
                view = inflater.inflate(R.layout.custom_layout_card_ten, container, false);
                break;
        }

        getActivity().setTitle("YOUR CARD");
        token = GeneralUtils.getApiToken(getActivity());
        ivCard = view.findViewById(R.id.iv_card);
        layoutSaveShare = view.findViewById(R.id.save_share_layout);
        layoutSaveShare.setVisibility(View.VISIBLE);
        tvCardName = view.findViewById(R.id.tv_card_name);
        tvCardAddress = view.findViewById(R.id.tv_card_address);
        tvCardEmail = view.findViewById(R.id.tv_card_email);
        tvNumber = view.findViewById(R.id.tv_card_no);
        tvDesignation = view.findViewById(R.id.tv_card_post);
        ivFacebook = view.findViewById(R.id.card_fb);
        ivTwitter = view.findViewById(R.id.card_twitter);
        ivInsta = view.findViewById(R.id.card_insta);
        ivLinkdin = view.findViewById(R.id.card_in);
        layoutShareCard = view.findViewById(R.id.layout_share_card);
        layoutSaveCard = view.findViewById(R.id.layout_save_card);
        layoutEditCard = view.findViewById(R.id.layout_edit_card);

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

        layoutSaveCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog = AlertUtils.createProgressDialog(getActivity());
                alertDialog.show();
                apiCallStoreCard();
            }
        });

        layoutEditCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.putBooleanValueInEditor(getActivity(),"check_fragment",true);
                GeneralUtils.connectDrawerFragment(getActivity(), new UpdateCardInfoFragment());
            }
        });

    }

    private void apiCallCardDetail() {
        ApiInterface services = ApiClient.getApiClient(token).create(ApiInterface.class);
        Call<CardResponseModel> userLogin = services.getCardDetail(GeneralUtils.getShareCardID(getActivity()));
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
                    GeneralUtils.putIntegerValueInEditor(getActivity(), "update_card_id", response.body().getData().getId());
                    Glide.with(getActivity()).load(response.body().getData().getPicture()).into(ivCard);
                    tvCardName.setText(response.body().getData().getName());
                    tvCardAddress.setText(response.body().getData().getAddress());
                    tvCardEmail.setText(response.body().getData().getEmail());
                    tvNumber.setText(response.body().getData().getPhoneNumber());
                    tvDesignation.setText(response.body().getData().getDesignation());

                    if (response.body().getData().getFacebook() == null || response.body().getData().getFacebook().equals("")) {
                        ivFacebook.setVisibility(View.GONE);
                    }
                    if (response.body().getData().getTwitter() == null || response.body().getData().getTwitter().equals("")) {
                        ivTwitter.setVisibility(View.GONE);
                    }
                    if (response.body().getData().getInstagram() == null || response.body().getData().getInstagram().equals("")) {
                        ivInsta.setVisibility(View.GONE);
                    }
                    if (response.body().getData().getLinkedin() == null || response.body().getData().getLinkedin().equals("")) {
                        ivLinkdin.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<CardResponseModel> call, Throwable t) {
                alertDialog.dismiss();
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void apiCallStoreCard() {
        ApiInterface services = ApiClient.getApiClient(token).create(ApiInterface.class);
        Call<CardResponseModel> storeCard = services.saveCard(GeneralUtils.getShareCardID(getActivity()));
        storeCard.enqueue(new Callback<CardResponseModel>() {
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
                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    GeneralUtils.connectDrawerFragment(getActivity(), new CardsFragment());
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
