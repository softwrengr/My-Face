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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.techeasesol.myface.R;
import com.techeasesol.myface.fragments.AboutSendFragment;
import com.techeasesol.myface.utilities.GeneralUtils;

public class BottomSheetClass extends BottomSheetDialogFragment {
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
                bottomSheetBehavior.setHideable(false);
                d.setCancelable(false);


            }
        });
        INSTANCE = this;
        View view = inflater.inflate(R.layout.bottom_sheet, container, false);
        Button btnSend = view.findViewById(R.id.btn_send_card);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mListener.onButtonClicked("clicked");
                GeneralUtils.connectDrawerFragment(getActivity(),new AboutSendFragment());
            }
        });
        return view;
    }

    public interface BottomSheetListener {
        void onButtonClicked(String message);
    }


}
