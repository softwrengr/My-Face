package com.techeasesol.myface.fragments;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
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
import com.techeasesol.myface.activities.DrawerActivity;
import com.techeasesol.myface.models.loginDataModels.LoginResponseModel;
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

public class LoginFragment extends Fragment {
    AlertDialog alertDialog;
    View view;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_sign_up)
    TextView tvSignUp;
    @BindView(R.id.forgot_password)
    TextView tvForgotPassword;

    String strEmail, strPassword, strToken, deviceToken, strLatitude, strLongitude;
    boolean valid = false;

    public static double lattitude, longitude;
    LocationManager locationManager;
    private static final int REQUEST_LOCATION = 100;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_login, container, false);
        initUI();
        return view;
    }

    private void initUI() {
        ButterKnife.bind(this, view);

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();

        } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            getLocation();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    alertDialog = AlertUtils.createProgressDialog(getActivity());
                    alertDialog.show();
                    apiCallLogin();
                }
            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.connectFragment(getActivity(), new SignUpFragment());
            }
        });

        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.connectFragment(getActivity(), new ForgotPasswordFragment());
            }
        });


    }

    private void apiCallLogin() {

        ApiInterface services = ApiClient.getApiClient().create(ApiInterface.class);
        Call<LoginResponseModel> userLogin = services.userLogin(strEmail, strPassword, strLatitude, strLongitude, ShareUtils.getDeviceToken(getActivity()));
        userLogin.enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                alertDialog.dismiss();
                if (response.body() == null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(getActivity(), jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                } else if (response.body().getStatus()) {
                    strToken = response.body().getData().getUser().getToken();
                    GeneralUtils.putBooleanValueInEditor(getContext(), "isLogin", true);
                    GeneralUtils.putStringValueInEditor(getActivity(), "api_token", strToken);
                    startActivity(new Intent(getActivity(), DrawerActivity.class));
                }
            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                alertDialog.dismiss();
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validate() {
        valid = true;
        strEmail = etEmail.getText().toString().trim();
        strPassword = etPassword.getText().toString().trim();


        if (strEmail.isEmpty()) {
            etEmail.setError("enter a valid email address");
            valid = false;
        } else {
            etEmail.setError(null);
        }


        if (strPassword.isEmpty()) {
            etPassword.setError("Please enter a your password");
            valid = false;
        } else {
            etPassword.setError(null);
        }
        return valid;
    }

    //getting current location
    public void getLocation() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        } else {
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Location location1 = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Location location2 = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

            if (location != null) {
                double latti = location.getLatitude();
                double longi = location.getLongitude();

                lattitude = latti;
                longitude = longi;

            } else if (location1 != null) {
                double latti = location1.getLatitude();
                double longi = location1.getLongitude();

                lattitude = latti;
                longitude = longi;

            } else if (location2 != null) {
                double latti = location2.getLatitude();
                double longi = location2.getLongitude();
                lattitude = latti;
                longitude = longi;

            } else {
                Toast.makeText(getActivity(), "Unble to Trace your location", Toast.LENGTH_SHORT).show();
            }

            strLatitude = String.valueOf(lattitude);
            strLongitude = String.valueOf(longitude);
            GeneralUtils.putStringValueInEditor(getActivity(), "latitude", strLatitude);
            GeneralUtils.putStringValueInEditor(getActivity(), "longitude", strLongitude);
        }
    }


    protected void buildAlertMessageNoGps() {

        final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
        builder.setMessage("Please Turn ON your GPS Connection")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final android.app.AlertDialog alert = builder.create();
        alert.show();
    }

}
