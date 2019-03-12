package com.techeasesol.myface.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.techeasesol.myface.R;
import com.techeasesol.myface.firebase.MyFirebaseInstanceIdService;
import com.techeasesol.myface.models.SignUpDataModels.SignupResponseModel;
import com.techeasesol.myface.utilities.AlertUtils;
import com.techeasesol.myface.utilities.GeneralUtils;
import com.techeasesol.myface.utilities.ShareUtils;
import com.techeasesol.networking.ApiClient;
import com.techeasesol.networking.ApiInterface;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpFragment extends Fragment {
    AlertDialog alertDialog;
    View view;
    @BindView(R.id.et_signup_name)
    EditText etName;
    @BindView(R.id.et_signup_email)
    EditText etEmail;
    @BindView(R.id.et_signup_password)
    EditText etPassword;
    @BindView(R.id.tv_aleady_login)
    TextView tvLogin;
    @BindView(R.id.btn_sign_up)
    Button btnSignup;

    boolean valid = false;
    String strName, strEmail,strPassword,strLat,strLng,deviceToken;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        initUI();
        return view;
    }

    private void initUI(){
        ButterKnife.bind(this,view);
        deviceToken = MyFirebaseInstanceIdService.DEVICE_TOKEN;
        GeneralUtils.putStringValueInEditor(getActivity(), "deviceToken", deviceToken);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    alertDialog = AlertUtils.createProgressDialog(getActivity());
                    alertDialog.show();
                    apiCallRegistration();
                }
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.connectFragmentWithoutBack(getActivity(),new LoginFragment());
            }
        });
    }

    private void apiCallRegistration(){
        String deviceToken = MyFirebaseInstanceIdService.DEVICE_TOKEN;
        ApiInterface services = ApiClient.getApiClient().create(ApiInterface.class);
        Call<SignupResponseModel> userLogin = services.userSignup(strName,strEmail, strPassword, strLat,strLng, ShareUtils.getDeviceToken(getActivity()));
        userLogin.enqueue(new Callback<SignupResponseModel>() {
            @Override
            public void onResponse(Call<SignupResponseModel> call, Response<SignupResponseModel> response) {
                alertDialog.dismiss();
                if (response.body() == null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(getActivity(), jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                } else if (response.body().getStatus()) {
                    GeneralUtils.connectFragmentWithoutBack(getActivity(), new LoginFragment());
                }
            }

            @Override
            public void onFailure(Call<SignupResponseModel> call, Throwable t) {
                alertDialog.dismiss();
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validate() {
        valid = true;

        strName = etName.getText().toString();
        strEmail = etEmail.getText().toString().trim();
        strPassword = etPassword.getText().toString();
        strLat = "34.006418";
        strLng = "71.502972";

        if (strName.isEmpty()) {
            etName.setError("enter your full name");
            valid = false;
        } else {
            etName.setError(null);
        }

        if (strEmail.isEmpty()) {
            etEmail.setError("enter a valid email address");
            valid = false;
        } else {
            etEmail.setError(null);
        }



        if (strPassword.isEmpty()) {
            etPassword.setError("Please enter a valid password");
            valid = false;
        } else {
            etPassword.setError(null);
        }
        return valid;
    }
}
