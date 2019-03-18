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
import com.techeasesol.myface.models.acceptedCardsDataModel.AcceptedCardStatusModel;
import com.techeasesol.myface.models.acceptedCardsDataModel.UserCardsModel;
import com.techeasesol.myface.models.saveCardDataModel.SaveCardDataModel;

import java.util.List;

public class AcceptedCardAdapter extends RecyclerView.Adapter<AcceptedCardAdapter.MyViewHolder> {

    private Context context;
    private List<UserCardsModel> userCardsModelList;
    private List<AcceptedCardStatusModel> acceptedCardStatusModelList;


    public AcceptedCardAdapter(Context context, List<AcceptedCardStatusModel> acceptedCardStatusModelList, List<UserCardsModel> userCardsModelList) {
        this.context = context;
        this.acceptedCardStatusModelList = acceptedCardStatusModelList;
        this.userCardsModelList = userCardsModelList;
    }

    @Override
    public int getItemViewType(int position) {
        if (userCardsModelList != null) {
            UserCardsModel object = userCardsModelList.get(position);
            if (object != null) {
                return Integer.parseInt(object.getCardNumber());
            }
        }
        return 0;
    }

    @Override
    public AcceptedCardAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case 1:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.custom_layout_card_one, parent, false);
                return new AcceptedCardAdapter.MyViewHolder(view);
            case 2:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.custom_layout_card_two, parent, false);
                return new AcceptedCardAdapter.MyViewHolder(view);
            case 3:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.custom_layout_card_three, parent, false);
                return new AcceptedCardAdapter.MyViewHolder(view);
            case 4:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.custom_layout_card_four, parent, false);
                return new AcceptedCardAdapter.MyViewHolder(view);
            case 5:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.custom_layout_card_five, parent, false);
                return new AcceptedCardAdapter.MyViewHolder(view);
            case 6:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.custom_layout_card_six, parent, false);
                return new AcceptedCardAdapter.MyViewHolder(view);
            case 7:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.custom_layout_card_seven, parent, false);
                return new AcceptedCardAdapter.MyViewHolder(view);
            case 8:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.custom_layout_card_seven, parent, false);
                return new AcceptedCardAdapter.MyViewHolder(view);
            case 9:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.custom_layout_card_nine, parent, false);
                return new AcceptedCardAdapter.MyViewHolder(view);
            case 10:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.custom_layout_card_ten, parent, false);
                return new AcceptedCardAdapter.MyViewHolder(view);

        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AcceptedCardAdapter.MyViewHolder myViewHolder, int position) {
        final UserCardsModel model = userCardsModelList.get(position);

        if (model.getPicture() == null) {
            myViewHolder.ivCard.setImageDrawable(context.getResources().getDrawable(R.mipmap.image));
        } else {
            Glide.with(context).load(model.getPicture()).into(myViewHolder.ivCard);
        }
        myViewHolder.tvName.setText(model.getName());
        myViewHolder.tvDesignation.setText(model.getDesignation());
        myViewHolder.tvEmail.setText(model.getEmail());
        myViewHolder.tvPhoneNo.setText(model.getPhoneNumber());
        myViewHolder.tvAddress.setText(model.getAddress());

        if(model.getFacebook()==null || model.getFacebook().equals("")){
            myViewHolder.ivFacebook.setVisibility(View.GONE);
        }
        if(model.getTwitter()==null || model.getTwitter().equals("")){
            myViewHolder.ivTwitter.setVisibility(View.GONE);
        }
        if(model.getInstagram()==null || model.getInstagram().equals("")){
            myViewHolder.ivInsta.setVisibility(View.GONE);
        }
        if(model.getSkype()==null || model.getSkype().equals("")){
            myViewHolder.ivGoogle.setVisibility(View.GONE);
        }
        if(model.getLinkedin()==null || model.getLinkedin().equals("")){
            myViewHolder.ivLinkdin.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        return userCardsModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivFacebook,ivGoogle,ivInsta,ivLinkdin,ivTwitter;
        ImageView ivCard,ivShare;
        TextView tvName, tvDesignation, tvEmail,tvPhoneNo,tvAddress;
        RelativeLayout layoutSmallInfo;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_card_name);
            ivCard = itemView.findViewById(R.id.iv_card);
            tvDesignation = itemView.findViewById(R.id.tv_card_post);
            tvEmail = itemView.findViewById(R.id.tv_card_email);
            tvPhoneNo = itemView.findViewById(R.id.tv_card_no);
            ivFacebook = itemView.findViewById(R.id.card_fb);
            ivTwitter = itemView.findViewById(R.id.card_twitter);
            ivGoogle = itemView.findViewById(R.id.card_google);
            ivInsta = itemView.findViewById(R.id.card_insta);
            ivLinkdin = itemView.findViewById(R.id.card_in);
            tvAddress = itemView.findViewById(R.id.tv_card_address);
//            layoutSmallInfo = itemView.findViewById(R.id.info_layout);
//            ivShare = itemView.findViewById(R.id.iv_small_share);
        }
    }
}

