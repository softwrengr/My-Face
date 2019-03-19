package com.techeasesol.myface.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.techeasesol.myface.R;
import com.techeasesol.myface.models.profileDataModels.ProfileResponseModel;
import com.techeasesol.myface.utilities.AlertUtils;
import com.techeasesol.myface.utilities.GeneralUtils;
import com.techeasesol.networking.ApiClient;
import com.techeasesol.networking.ApiInterface;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {
    AlertDialog alertDialog;
    View view;
    @BindView(R.id.iv_user_profile)
    ImageView ivUserImage;
    @BindView(R.id.tv_user_email)
    TextView tvEmail;
    @BindView(R.id.tv_user_name)
    TextView tvName;
    @BindView(R.id.tv_user_phone_number)
    TextView tvUserPhoneNumber;
    @BindView(R.id.btn_edit)
    Button btnEditProfile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        getActivity().setTitle("Profile");
        onback(view);
        initUI();
        return view;
    }

    private void initUI() {
        ButterKnife.bind(this, view);

        alertDialog = AlertUtils.createProgressDialog(getActivity());
        alertDialog.show();
        apiCallUserProfile();

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            GeneralUtils.connectDrawerFragment(getActivity(),new UpdateProfileFragment());
            }
        });
    }

    private void apiCallUserProfile() {
        ApiInterface services = ApiClient.getApiClient(GeneralUtils.getApiToken(getActivity())).create(ApiInterface.class);
        Call<ProfileResponseModel> allUsers = services.getUserProfile();
        allUsers.enqueue(new Callback<ProfileResponseModel>() {
            @Override
            public void onResponse(Call<ProfileResponseModel> call, Response<ProfileResponseModel> response) {
                alertDialog.dismiss();

                if (response.body() == null) {

                } else if (response.body().getStatus()) {
                    tvName.setText(response.body().getData().getName());
                    tvEmail.setText(response.body().getData().getEmail());
                    tvUserPhoneNumber.setText(String.valueOf(response.body().getData().getPhoneNumber()));
                    Glide.with(getActivity()).load(response.body().getData().getProfilePicture()).into(ivUserImage);
                }


            }

            @Override
            public void onFailure(Call<ProfileResponseModel> call, Throwable t) {
                Log.d("fail", t.getMessage());
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
                    getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    GeneralUtils.connectDrawerFragmentWithoutBack(getActivity(), new HomeFragment());
                    return true;
                }
                return false;
            }
        });

    }

}
