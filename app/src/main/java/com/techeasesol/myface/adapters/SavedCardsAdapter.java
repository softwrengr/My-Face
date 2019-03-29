package com.techeasesol.myface.adapters;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
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
            case 2:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.custom_layout_card_two, parent, false);
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
                        .inflate(R.layout.custom_layout_card_five, parent, false);
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
                        .inflate(R.layout.custom_layout_card_eight, parent, false);
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
        myViewHolder.tvPhoneNo.setText(model.getPhoneNumber());
        myViewHolder.tvAddress.setText(model.getAddress());


        if (model.getFacebook() == null || model.getFacebook().equals("")) {
            myViewHolder.ivFacebook.setVisibility(View.GONE);
        }
        if (model.getTwitter() == null || model.getTwitter().equals("")) {
            myViewHolder.ivTwitter.setVisibility(View.GONE);
        }
        if (model.getInstagram() == null || model.getInstagram().equals("")) {
            myViewHolder.ivInsta.setVisibility(View.GONE);
        }
        if (model.getLinkedin() == null || model.getLinkedin().equals("")) {
            myViewHolder.ivLinkdin.setVisibility(View.GONE);
        }

        myViewHolder.ivShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.putIntegerValueInEditor(context, "user_card_id", model.getId());
                GeneralUtils.connectDrawerFragment(context, new NearPeoplesFragment());
            }
        });

        myViewHolder.ivFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(newFacebookIntent(context.getPackageManager(), model.getFacebook()));
            }
        });

        myViewHolder.ivInsta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadinstagram(String.valueOf(model.getInstagram()));
            }
        });

        myViewHolder.ivTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadTwitter(String.valueOf(model.getTwitter()));
            }
        });

        myViewHolder.ivLinkdin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadLinkdin(String.valueOf(model.getLinkedin()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return saveCardDataModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivFacebook, ivInsta, ivLinkdin, ivTwitter;
        ImageView ivCard, ivShare;
        TextView tvName, tvDesignation, tvEmail, tvAddress, tvPhoneNo;
        RelativeLayout layoutSmallInfo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_card_name);
            ivCard = itemView.findViewById(R.id.iv_card);
            tvDesignation = itemView.findViewById(R.id.tv_card_post);
            tvEmail = itemView.findViewById(R.id.tv_card_email);
            ivFacebook = itemView.findViewById(R.id.card_fb);
            ivTwitter = itemView.findViewById(R.id.card_twitter);
            ivInsta = itemView.findViewById(R.id.card_insta);
            ivLinkdin = itemView.findViewById(R.id.card_in);
            tvPhoneNo = itemView.findViewById(R.id.tv_card_no);
            tvAddress = itemView.findViewById(R.id.tv_card_address);
            layoutSmallInfo = itemView.findViewById(R.id.info_layout);
            ivShare = itemView.findViewById(R.id.iv_small_share);
        }
    }

    private Intent newFacebookIntent(PackageManager pm, String url) {
        Uri uri = Uri.parse(url);
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo("com.facebook.katana", 0);
            if (applicationInfo.enabled) {
                uri = Uri.parse("fb://facewebmodal/f?href=" + "https://www.facebook.com/" + url);
            }
        } catch (PackageManager.NameNotFoundException ignored) {
            uri = Uri.parse("fb://facewebmodal/f?href=" + "https://www.facebook.com/");
        }
        return new Intent(Intent.ACTION_VIEW, uri);
    }

    private void loadinstagram(String url) {
        Uri uri = Uri.parse("https://www.instagram.com/" + url);
        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

        likeIng.setPackage("com.instagram.android");

        try {
            context.startActivity(likeIng);
        } catch (ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.instagram.com/" + url)));
        }
    }

    private void loadTwitter(String url) {
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/" + url)));
        } catch (Exception e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/")));
        }
    }

    private void loadLinkdin(String url) {
        Intent intent = null;
        try {
            context.getPackageManager().getPackageInfo("com.linkedin.android", 0);
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/" + url));
        } catch (Exception e) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/"));
        } finally {
            context.startActivity(intent);
        }
    }


}
