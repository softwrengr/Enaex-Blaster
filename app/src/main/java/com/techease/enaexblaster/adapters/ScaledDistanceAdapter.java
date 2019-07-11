package com.techease.enaexblaster.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.techease.enaexblaster.R;
import com.techease.enaexblaster.models.ScaledDistanceModel;
import com.techease.enaexblaster.sqliteDatabase.EnaexCrud;
import com.techease.enaexblaster.utilities.GeneralUtils;
import com.techease.enaexblaster.views.calculators.ScaledDistanceFragment;

import java.util.ArrayList;
import java.util.List;

public class ScaledDistanceAdapter extends RecyclerView.Adapter<ScaledDistanceAdapter.MyViewHolder> {
    EnaexCrud bossMoveCrud;
    List<ScaledDistanceModel> likeWallPaperModelList;
    Context context;
    ArrayList<String> alIndexPosition = new ArrayList<>();

    public ScaledDistanceAdapter(Context context, List<ScaledDistanceModel> likeWallPaperModelList) {
        this.context = context;
        this.likeWallPaperModelList = likeWallPaperModelList;

    }


    @NonNull
    @Override
    public ScaledDistanceAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_scaled_distance, parent, false);

        return new ScaledDistanceAdapter.MyViewHolder(itemView);
    }

    @Override
    public long getItemId(int position) {
        final ScaledDistanceModel model = likeWallPaperModelList.get(position);
        if (likeWallPaperModelList != null && likeWallPaperModelList.size() > position)
            return likeWallPaperModelList.size();
        return 0;
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull final ScaledDistanceAdapter.MyViewHolder viewHolder, final int position) {
        final ScaledDistanceModel model = likeWallPaperModelList.get(position);
        bossMoveCrud = new EnaexCrud(context);

        viewHolder.tvRowName.setText(model.getRow_name());

        viewHolder.rowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("distance",model.getDistance());
                bundle.putString("mic",model.getMic());
                GeneralUtils.connectFragmentWithBack(context,new ScaledDistanceFragment()).setArguments(bundle);
            }
        });

    }

    @Override
    public int getItemCount() {
        return likeWallPaperModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvRowName;
        RelativeLayout rowLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRowName = itemView.findViewById(R.id.tv_row_name);
            rowLayout = itemView.findViewById(R.id.row_layout);
        }
    }
}

