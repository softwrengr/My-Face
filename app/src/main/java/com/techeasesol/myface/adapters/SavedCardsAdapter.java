package com.techeasesol.myface.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.techeasesol.myface.R;
import com.techeasesol.myface.fragments.NearPeoplesFragment;
import com.techeasesol.myface.models.nearPeoplesDataModels.NearPeopleDetailModel;
import com.techeasesol.myface.models.saveCardDataModel.SaveCardDataModel;
import com.techeasesol.myface.utilities.GeneralUtils;

import java.util.List;

public class SavedCardsAdapter extends RecyclerView.Adapter<SavedCardsAdapter.MyViewHolder> {

    private Context context;
    private List<SaveCardDataModel> saveCardDataModelList;


    public SavedCardsAdapter(Context context, List<SaveCardDataModel> saveCardDataModelList) {
        this.context = context;
        this.saveCardDataModelList = saveCardDataModelList;
    }

    @Override
    public int getItemViewType(int position) {
        if (saveCardDataModelList != null) {
            SaveCardDataModel object = saveCardDataModelList.get(position);
            if (object != null) {
                return Integer.parseInt(object.getCardNumber());
            }
        }
        return 0;
    }

    @Override
    public SavedCardsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case 1:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.custom_layout_card_one, parent, false);
                return new SavedCardsAdapter.MyViewHolder(view);
            case 3:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.custom_layout_card_three, parent, false);
                return new SavedCardsAdapter.MyViewHolder(view);
            case 4:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.custom_layout_card_four, parent, false);
                return new SavedCardsAdapter.MyViewHolder(view);
            case 5:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.custom_layout_card_seven, parent, false);
                return new SavedCardsAdapter.MyViewHolder(view);
            case 6:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.custom_layout_card_six, parent, false);
                return new SavedCardsAdapter.MyViewHolder(view);
            case 7:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.custom_layout_card_seven, parent, false);
                return new SavedCardsAdapter.MyViewHolder(view);
            case 8:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.custom_layout_card_seven, parent, false);
                return new SavedCardsAdapter.MyViewHolder(view);
            case 9:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.custom_layout_card_nine, parent, false);
                return new SavedCardsAdapter.MyViewHolder(view);
            case 10:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.custom_layout_card_ten, parent, false);
                return new SavedCardsAdapter.MyViewHolder(view);

        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        final SaveCardDataModel model = saveCardDataModelList.get(position);

        if (model.getPicture() == null) {
            myViewHolder.ivCard.setImageDrawable(context.getResources().getDrawable(R.mipmap.image));
        } else {
            Glide.with(context).load(model.getPicture()).into(myViewHolder.ivCard);
        }

        myViewHolder.layoutSmallInfo.setVisibility(View.VISIBLE);
        myViewHolder.tvName.setText(model.getName());
        myViewHolder.tvDesignation.setText(model.getDesignation());
        myViewHolder.tvEmail.setText(model.getEmail());

        myViewHolder.ivShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.putIntegerValueInEditor(context, "user_card_id", model.getId()     );
                GeneralUtils.connectDrawerFragment(context, new NearPeoplesFragment());
            }
        });

    }

    @Override
    public int getItemCount() {
        return saveCardDataModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivCard,ivShare;
        TextView tvName, tvDesignation, tvEmail;
        RelativeLayout layoutSmallInfo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_card_name);
            ivCard = itemView.findViewById(R.id.iv_card);
            tvDesignation = itemView.findViewById(R.id.tv_card_post);
            tvEmail = itemView.findViewById(R.id.tv_card_email);
            layoutSmallInfo = itemView.findViewById(R.id.info_layout);
            ivShare = itemView.findViewById(R.id.iv_small_share);
        }
    }
}
