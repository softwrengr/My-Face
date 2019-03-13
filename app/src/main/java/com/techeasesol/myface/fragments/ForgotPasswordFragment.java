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
import android.widget.Toast;

import com.techeasesol.myface.R;
import com.techeasesol.myface.models.resetPasswordDataModels.ResetPasswordModel;
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


public class ForgotPasswordFragment extends Fragment {

    AlertDialog alertDialog;
    View view;
    @BindView(R.id.et_forgot_email)
    EditText etEmail;
    @BindView(R.id.btn_reset_password)
    Button btnResetPassword;
    private boolean valid = false;
    private String strEmail;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_forgot_password, container, false);
        initUI();
        return view;
    }
    private void initUI() {
        ButterKnife.bind(this, view);

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    alertDialog = AlertUtils.createProgressDialog(getActivity());
                    alertDialog.show();
                    apiCallForgotPassword();
                }
            }
        });
    }

    private void apiCallForgotPassword() {
        ApiInterface services = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ResetPasswordModel> userLogin = services.resetPassword(strEmail);
        userLogin.enqueue(new Callback<ResetPasswordModel>() {
            @Override
            public void onResponse(Call<ResetPasswordModel> call, Response<ResetPasswordModel> response) {
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
                    GeneralUtils.connectFragment(getActivity(),new VerifyPasswordCodeFragment());
                }
            }

            @Override
            public void onFailure(Call<ResetPasswordModel> call, Throwable t) {
                alertDialog.dismiss();
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private boolean validate() {
        valid = true;

        strEmail = etEmail.getText().toString();

        GeneralUtils.putStringValueInEditor(getContext(),"forgot_email",strEmail);

        if (strEmail.isEmpty()) {
            etEmail.setError("enter a valid email address");
            valid = false;
        } else {
            etEmail.setError(null);
        }
        return valid;
    }
}
