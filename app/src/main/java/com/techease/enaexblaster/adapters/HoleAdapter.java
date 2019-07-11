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
import com.techease.enaexblaster.models.HoleModel;
import com.techease.enaexblaster.models.ShotModel;
import com.techease.enaexblaster.sqliteDatabase.EnaexCrud;
import com.techease.enaexblaster.utilities.GeneralUtils;
import com.techease.enaexblaster.views.calculators.CalculatorByHoleFragment;
import com.techease.enaexblaster.views.calculators.CalculatorByShotFragment;

import java.util.ArrayList;
import java.util.List;

public class HoleAdapter extends RecyclerView.Adapter<HoleAdapter.MyViewHolder> {
    EnaexCrud bossMoveCrud;
    List<HoleModel> likeWallPaperModelList;
    Context context;
    ArrayList<String> alIndexPosition = new ArrayList<>();

    public HoleAdapter(Context context, List<HoleModel> likeWallPaperModelList) {
        this.context = context;
        this.likeWallPaperModelList = likeWallPaperModelList;

    }


    @NonNull
    @Override
    public HoleAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_scaled_distance, parent, false);

        return new HoleAdapter.MyViewHolder(itemView);
    }

    @Override
    public long getItemId(int position) {
        final HoleModel model = likeWallPaperModelList.get(position);
        if (likeWallPaperModelList != null && likeWallPaperModelList.size() > position)
            return likeWallPaperModelList.size();
        return 0;
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull final HoleAdapter.MyViewHolder viewHolder, final int position) {
        final HoleModel model = likeWallPaperModelList.get(position);
        bossMoveCrud = new EnaexCrud(context);

        viewHolder.tvRowName.setText(model.getRowName());

        viewHolder.rowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();

                bundle.putString("diameter",model.getDiameter());
                bundle.putString("density",model.getDensity());
                bundle.putString("burden",model.getBurden());
                bundle.putString("spacing",model.getSpacing());
                bundle.putString("holeLength",model.getHoleLength());
                bundle.putString("stemLength",model.getStemLength());
                bundle.putString("rockDensity",model.getRockDensity());
                bundle.putString("distance",model.getRockDensity());
                bundle.putString("scaling",model.getScaling());
                bundle.putString("attenuation",model.getAttenuation());
                GeneralUtils.connectFragmentWithBack(context,new CalculatorByHoleFragment()).setArguments(bundle);
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

