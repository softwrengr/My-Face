package com.techeasesol.myface.fragments.mycards;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.techeasesol.myface.R;
import com.techeasesol.myface.adapters.AcceptedCardAdapter;
import com.techeasesol.myface.adapters.SavedCardsAdapter;
import com.techeasesol.myface.models.acceptedCardsDataModel.AcceptdCardResponseModel;
import com.techeasesol.myface.models.acceptedCardsDataModel.AcceptedCardStatusModel;
import com.techeasesol.myface.models.acceptedCardsDataModel.UserCardsModel;
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


public class SharedCardsFragment extends Fragment {
    AlertDialog alertDialog;
    View view;
    @BindView(R.id.rv_accepted)
    RecyclerView rvAcceptedCards;

    ArrayList<AcceptedCardStatusModel> acceptedCardStatusModelList;
    ArrayList<UserCardsModel> userCardsModelList;
    AcceptedCardAdapter acceptedCardAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_shared_cards, container, false);
        initUI();
        return view;
    }

    private void initUI() {
        ButterKnife.bind(this, view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rvAcceptedCards.setLayoutManager(linearLayoutManager);
        userCardsModelList = new ArrayList<>();
        acceptedCardStatusModelList = new ArrayList<>();
        alertDialog = AlertUtils.createProgressDialog(getActivity());
        alertDialog.show();
        acceptedCardAdapter = new AcceptedCardAdapter(getActivity(), acceptedCardStatusModelList,userCardsModelList);
        rvAcceptedCards.setAdapter(acceptedCardAdapter);
        apiCallAcceptedCard();
    }

    private void apiCallAcceptedCard() {
        ApiInterface services = ApiClient.getApiClient(GeneralUtils.getApiToken(getActivity())).create(ApiInterface.class);
        Call<AcceptdCardResponseModel> savedCards = services.acceptedCards();
        savedCards.enqueue(new Callback<AcceptdCardResponseModel>() {
            @Override
            public void onResponse(Call<AcceptdCardResponseModel> call, Response<AcceptdCardResponseModel> response) {
                alertDialog.dismiss();
                if (response.body() == null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(getActivity(), jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else if (response.body().getStatus()) {
                    acceptedCardStatusModelList.addAll(response.body().getData());
                    for (int i = 0; i < acceptedCardStatusModelList.size(); i++) {
                        userCardsModelList.add(response.body().getData().get(i).getUserCard());
                    }

                    acceptedCardAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<AcceptdCardResponseModel> call, Throwable t) {
                Log.d("fail", t.getMessage());
            }
        });
    }

}
