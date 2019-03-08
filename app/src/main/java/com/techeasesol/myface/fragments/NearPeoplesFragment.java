package com.techeasesol.myface.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.techeasesol.myface.R;
import com.techeasesol.myface.adapters.NearPeopleAdapter;
import com.techeasesol.myface.classes.BottomSheetClass;
import com.techeasesol.myface.models.nearPeoplesDataModels.NearPeopleDetailModel;
import com.techeasesol.myface.models.nearPeoplesDataModels.NearPeopleResponseModel;
import com.techeasesol.myface.utilities.AlertUtils;
import com.techeasesol.myface.utilities.GeneralUtils;
import com.techeasesol.networking.ApiClient;
import com.techeasesol.networking.ApiInterface;

import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NearPeoplesFragment extends Fragment implements NearPeopleAdapter.NearPeopleAdapterListener{
    View view;
    AlertDialog alertDialog;
    @BindView(R.id.rv_near_peoples)
    RecyclerView rvNearPeoples;
    @BindView(R.id.et_search_peoples)
    EditText etSearchPeoples;
    ArrayList<NearPeopleDetailModel> nearPeopleDetailModelArrayList;
    NearPeopleAdapter nearPeopleAdapter;

    String strToken, strSearchPeoples;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_near_peoples, container, false);
        getActivity().setTitle("Share Card");
        initUI();

        return view;
    }

    private void initUI() {
        strToken = GeneralUtils.getApiToken(getActivity());
        ButterKnife.bind(this, view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rvNearPeoples.setLayoutManager(linearLayoutManager);
        nearPeopleDetailModelArrayList = new ArrayList<>();
        alertDialog = AlertUtils.createProgressDialog(getActivity());
        alertDialog.show();
        nearPeopleAdapter = new NearPeopleAdapter(getActivity(), nearPeopleDetailModelArrayList, this);
        rvNearPeoples.setAdapter(nearPeopleAdapter);
        apiCallNearPeoples();


        etSearchPeoples.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                strSearchPeoples = etSearchPeoples.getText().toString();
                nearPeopleAdapter.getFilter().filter(strSearchPeoples);
            }
        });

    }

    private void apiCallNearPeoples() {
        ApiInterface services = ApiClient.getApiClient(strToken).create(ApiInterface.class);
        Call<NearPeopleResponseModel> allUsers = services.nearPeoples();
        allUsers.enqueue(new Callback<NearPeopleResponseModel>() {
            @Override
            public void onResponse(Call<NearPeopleResponseModel> call, Response<NearPeopleResponseModel> response) {
                alertDialog.dismiss();
                if (response.body() == null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(getActivity(), jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else if (response.body().getStatus()) {
                    nearPeopleDetailModelArrayList.addAll(response.body().getData());
                    nearPeopleAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<NearPeopleResponseModel> call, Throwable t) {
                Log.d("fail", t.getMessage());
            }
        });
    }

    @Override
    public void onContactSelected(String shareID) {
        GeneralUtils.putIntegerValueInEditor(getActivity(),"share_user_id",Integer.parseInt(shareID));
        new BottomSheetClass().show(getActivity().getSupportFragmentManager(), "open");
    }

}
