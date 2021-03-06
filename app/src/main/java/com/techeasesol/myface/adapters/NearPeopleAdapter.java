package com.techeasesol.myface.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.techeasesol.myface.R;
import com.techeasesol.myface.classes.BottomSheetClass;
import com.techeasesol.myface.models.nearPeoplesDataModels.NearPeopleDetailModel;
import com.techeasesol.myface.utilities.GeneralUtils;

import java.util.ArrayList;
import java.util.List;

public class NearPeopleAdapter extends RecyclerView.Adapter<NearPeopleAdapter.MyViewHolder> implements Filterable {
    private Context context;
    private List<NearPeopleDetailModel> nearPeopleDetailModelList;
    private List<NearPeopleDetailModel> listFiltered;
    private NearPeopleAdapterListener listener;


    public NearPeopleAdapter(Context context, List<NearPeopleDetailModel> contactList, NearPeopleAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.nearPeopleDetailModelList = contactList;
        this.listFiltered = contactList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_layout_near_peoples, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final NearPeopleDetailModel model = listFiltered.get(position);
        holder.name.setText(model.getName());
        holder.phone.setText(model.getEmail());
        holder.checkBox.setChecked(false);


        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    holder.checkBox.setBackground(context.getResources().getDrawable(R.mipmap.clicked));
                    listener.onContactSelected(model.getId());
                }
                else {
                    Toast.makeText(context, "please select one user", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return listFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    listFiltered = nearPeopleDetailModelList;
                } else {
                    List<NearPeopleDetailModel> filteredList = new ArrayList<>();
                    for (NearPeopleDetailModel row : nearPeopleDetailModelList) {

                        if (row.getName().toLowerCase().contains(charString.toLowerCase()) || row.getEmail().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    listFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = listFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listFiltered = (ArrayList<NearPeopleDetailModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, phone;
        CheckBox checkBox;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.tv_user_name);
            phone = view.findViewById(R.id.tv_user_email);
            checkBox = view.findViewById(R.id.checkbox);

        }
    }


    public interface NearPeopleAdapterListener {
        void onContactSelected(String message);
    }


}