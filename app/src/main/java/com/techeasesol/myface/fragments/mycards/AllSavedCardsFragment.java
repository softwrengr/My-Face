package com.techeasesol.myface.fragments.mycards;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.techeasesol.myface.R;
import com.techeasesol.myface.adapters.NearPeopleAdapter;
import com.techeasesol.myface.adapters.SavedCardsAdapter;
import com.techeasesol.myface.classes.BottomSheetClass;
import com.techeasesol.myface.fragments.NearPeoplesFragment;
import com.techeasesol.myface.models.nearPeoplesDataModels.NearPeopleResponseModel;
import com.techeasesol.myface.models.saveCardDataModel.SaveCardDataModel;
import com.techeasesol.myface.models.saveCardDataModel.SaveCardResponseModel;
import com.techeasesol.myface.utilities.AlertUtils;
import com.techeasesol.myface.utilities.GeneralUtils;
import com.techeasesol.networking.ApiClient;
import com.techeasesol.networking.ApiInterface;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllSavedCardsFragment extends Fragment {
    AlertDialog alertDialog;
    View view;
    @BindView(R.id.rv_saved_cards)
    RecyclerView rvSavedCards;

    ArrayList<SaveCardDataModel> saveCardDataModelArrayList;
    SavedCardsAdapter savedCardsAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_my_all_cards, container, false);
        initUI();
        return view;
    }

    private void initUI(){
        ButterKnife.bind(this,view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rvSavedCards.setLayoutManager(linearLayoutManager);
        saveCardDataModelArrayList = new ArrayList<>();
        alertDialog = AlertUtils.createProgressDialog(getActivity());
        alertDialog.show();
        savedCardsAdapter = new SavedCardsAdapter(getActivity(), saveCardDataModelArrayList);
        rvSavedCards.setAdapter(savedCardsAdapter);
        apiCallSavedCards();
    }

    private void apiCallSavedCards() {
        ApiInterface services = ApiClient.getApiClient(GeneralUtils.getApiToken(getActivity())).create(ApiInterface.class);
        Call<SaveCardResponseModel> savedCards = services.savedCards();
        savedCards.enqueue(new Callback<SaveCardResponseModel>() {
            @Override
            public void onResponse(Call<SaveCardResponseModel> call, Response<SaveCardResponseModel> response) {
                alertDialog.dismiss();
                if (response.body() == null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(getActivity(), jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else if (response.body().getStatus()) {
                    saveCardDataModelArrayList.addAll(response.body().getData());
                    savedCardsAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<SaveCardResponseModel> call, Throwable t) {
                Log.d("fail", t.getMessage());
            }
        });
    }


}
