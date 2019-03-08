package com.techeasesol.myface.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.techeasesol.myface.R;
import com.techeasesol.myface.models.nearPeoplesDataModels.NearPeopleDetailModel;
import com.techeasesol.myface.models.saveCardDataModel.SaveCardDataModel;

import java.util.List;

public class SavedCardsAdapter extends RecyclerView.Adapter<SavedCardsAdapter.MyViewHolder> {

    private Context context;
    private List<SaveCardDataModel> saveCardDataModelList;


    public SavedCardsAdapter(Context context, List<SaveCardDataModel> saveCardDataModelList) {
        this.context = context;
        this.saveCardDataModelList = saveCardDataModelList;
    }


    @Override
    public SavedCardsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_layout_card_one, parent, false);

        return new SavedCardsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        final SaveCardDataModel contact = saveCardDataModelList.get(position);



    }

    @Override
    public int getItemCount() {
        return saveCardDataModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_card_name);
        }
    }
}
