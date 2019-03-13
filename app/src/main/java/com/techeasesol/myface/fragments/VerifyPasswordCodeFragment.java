package com.techeasesol.myface.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.techeasesol.myface.R;
import com.techeasesol.myface.models.resetPasswordDataModels.ChangePasswordModel;
import com.techeasesol.myface.models.resetPasswordDataModels.verifycodemodel.VerifyCodeResponseModel;
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

public class VerifyPasswordCodeFragment extends Fragment {
    AlertDialog alertDialog;
    View view;
    @BindView(R.id.et_verify_code)
    EditText etVerifyCode;
    @BindView(R.id.btn_verify_code)
    Button btnVerifyCode;
    @BindView(R.id.et_new_password)
    EditText etNewPassword;
    @BindView(R.id.et_conifrm_new_password)
    EditText etConfirmNewPassword;
    @BindView(R.id.btn_new_password)
    Button btnSetNewPassword;
    @BindView(R.id.code_layout)
    RelativeLayout codeLayout;
    @BindView(R.id.new_password_layout)
    RelativeLayout newPassLayout;

    private String strCode, strNewPassword, strConfirmNewPassword;
    private boolean valid = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_verify_password_code, container, false);
        initUI();
        return view;
    }

    private void initUI() {
        ButterKnife.bind(this, view);

        btnVerifyCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    alertDialog = AlertUtils.createProgressDialog(getActivity());
                    alertDialog.show();
                    apiCallForgotPassword();
                }
            }
        });

        btnSetNewPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid()) {
                    alertDialog = AlertUtils.createProgressDialog(getActivity());
                    alertDialog.show();
                    apiCallChangePassword();
                }
            }
        });
    }

    private void apiCallForgotPassword() {
        ApiInterface services = ApiClient.getApiClient().create(ApiInterface.class);
        Call<VerifyCodeResponseModel> userLogin = services.verifyPasswordCode(strCode, GeneralUtils.getForgotEmail(getActivity()));
        userLogin.enqueue(new Callback<VerifyCodeResponseModel>() {
            @Override
            public void onResponse(Call<VerifyCodeResponseModel> call, Response<VerifyCodeResponseModel> response) {
                alertDialog.dismiss();
                if (response.body() == null) {
                    Toast.makeText(getActivity(), "something went wrong", Toast.LENGTH_SHORT).show();
                } else if (response.body().getStatus()) {
                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    GeneralUtils.putStringValueInEditor(getActivity(), "api_token", response.body().getData().getUser().getToken());
                    codeLayout.setVisibility(View.GONE);
                    newPassLayout.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(getActivity(), "something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<VerifyCodeResponseModel> call, Throwable t) {
                alertDialog.dismiss();
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void apiCallChangePassword() {
        ApiInterface services = ApiClient.getApiClient(GeneralUtils.getApiToken(getActivity())).create(ApiInterface.class);
        Call<ChangePasswordModel> userLogin = services.changePassword(strNewPassword, strConfirmNewPassword);
        userLogin.enqueue(new Callback<ChangePasswordModel>() {
            @Override
            public void onResponse(Call<ChangePasswordModel> call, Response<ChangePasswordModel> response) {
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
                    GeneralUtils.connectFragmentWithoutBack(getActivity(), new LoginFragment());
                }
            }

            @Override
            public void onFailure(Call<ChangePasswordModel> call, Throwable t) {
                alertDialog.dismiss();
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private boolean validate() {
        valid = true;

        strCode = etVerifyCode.getText().toString();

        if (strCode.isEmpty()) {
            etVerifyCode.setError("please enter your code to verify");
            valid = false;
        } else {
            etVerifyCode.setError(null);
        }
        return valid;
    }

    private boolean isValid() {
        valid = true;

        strNewPassword = etNewPassword.getText().toString();
        strConfirmNewPassword = etConfirmNewPassword.getText().toString();

        if (strNewPassword.isEmpty()) {
            etNewPassword.setError("enter your new password");
            valid = false;
        } else {
            etNewPassword.setError(null);
        }

        if (strConfirmNewPassword.isEmpty()) {
            etConfirmNewPassword.setError("confirm your new password");
            valid = false;
        } else {
            etConfirmNewPassword.setError(null);
        }
        return valid;
    }
}

