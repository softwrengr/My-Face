package com.techeasesol.myface.classes;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.techeasesol.myface.R;
import com.techeasesol.myface.fragments.AboutSendFragment;
import com.techeasesol.myface.fragments.mycards.CardsFragment;
import com.techeasesol.myface.models.cardDataModel.CardResponseModel;
import com.techeasesol.myface.models.sendCardDataModel.SendCardResponseModel;
import com.techeasesol.myface.models.shareCardDataModels.ShareResponseModel;
import com.techeasesol.myface.utilities.AlertUtils;
import com.techeasesol.myface.utilities.GeneralUtils;
import com.techeasesol.networking.ApiClient;
import com.techeasesol.networking.ApiInterface;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BottomSheetClass extends BottomSheetDialogFragment {
    AlertDialog alertDialog;
    private BottomSheetListener mListener;
    private static BottomSheetBehavior bottomSheetBehavior;
    private static View bottomSheetInternal;
    private static BottomSheetClass INSTANCE;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().setOnShowListener(new DialogInterface.OnShowListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onShow(DialogInterface dialog) {
                BottomSheetDialog d = (BottomSheetDialog) dialog;
                CoordinatorLayout coordinatorLayout = (CoordinatorLayout) d.findViewById(R.id.locUXCoordinatorLayout);
                bottomSheetInternal = d.findViewById(R.id.bottomSheet);
                bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetInternal);
                BottomSheetBehavior.from((View) coordinatorLayout.getParent()).setPeekHeight(bottomSheetInternal.getHeight());
                bottomSheetBehavior.setPeekHeight(bottomSheetInternal.getHeight());
                coordinatorLayout.getParent().requestLayout();
                bottomSheetBehavior.setHideable(true);
                d.setCancelable(true);


            }
        });
        INSTANCE = this;
        View view = inflater.inflate(R.layout.bottom_sheet, container, false);
        Button btnSend = view.findViewById(R.id.btn_send_card);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mListener.onButtonClicked("clicked");
                alertDialog = AlertUtils.createProgressDialog(getActivity());
                alertDialog.show();
                apiCallSendCard();

            }
        });
        return view;
    }

    public interface BottomSheetListener {
        void onButtonClicked(String message);
    }

    private void apiCallSendCard() {
        ApiInterface services = ApiClient.getApiClient(GeneralUtils.getApiToken(getActivity())).create(ApiInterface.class);
        Call<SendCardResponseModel> shareCard = services.shareCard(GeneralUtils.getShareUserID(getActivity()), GeneralUtils.getShareCardID(getActivity()));
        shareCard.enqueue(new Callback<SendCardResponseModel>() {
            @Override
            public void onResponse(Call<SendCardResponseModel> call, Response<SendCardResponseModel> response) {
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
                    GeneralUtils.connectDrawerFragment(getActivity(), new AboutSendFragment());
                    getDialog().dismiss();
                }
            }

            @Override
            public void onFailure(Call<SendCardResponseModel> call, Throwable t) {
                alertDialog.dismiss();
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
