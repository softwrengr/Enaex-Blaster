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
import com.techease.enaexblaster.models.PowderFactorModel;
import com.techease.enaexblaster.models.ShotModel;
import com.techease.enaexblaster.sqliteDatabase.EnaexCrud;
import com.techease.enaexblaster.utilities.GeneralUtils;
import com.techease.enaexblaster.views.calculators.CalculatorByShotFragment;
import com.techease.enaexblaster.views.calculators.PFCalculatorFragment;

import java.util.ArrayList;
import java.util.List;

public class ShotAdapter extends RecyclerView.Adapter<ShotAdapter.MyViewHolder> {
    EnaexCrud bossMoveCrud;
    List<ShotModel> likeWallPaperModelList;
    Context context;
    ArrayList<String> alIndexPosition = new ArrayList<>();

    public ShotAdapter(Context context, List<ShotModel> likeWallPaperModelList) {
        this.context = context;
        this.likeWallPaperModelList = likeWallPaperModelList;

    }


    @NonNull
    @Override
    public ShotAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_scaled_distance, parent, false);

        return new ShotAdapter.MyViewHolder(itemView);
    }

    @Override
    public long getItemId(int position) {
        final ShotModel model = likeWallPaperModelList.get(position);
        if (likeWallPaperModelList != null && likeWallPaperModelList.size() > position)
            return likeWallPaperModelList.size();
        return 0;
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull final ShotAdapter.MyViewHolder viewHolder, final int position) {
        final ShotModel model = likeWallPaperModelList.get(position);
        bossMoveCrud = new EnaexCrud(context);

        viewHolder.tvRowName.setText(model.getRowName());

        viewHolder.rowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("rows",model.getRows());
                bundle.putString("holes",model.getHoles());
                bundle.putString("diameter",model.getDiameter());
                bundle.putString("density",model.getDensity());
                bundle.putString("burden",model.getBurden());
                bundle.putString("spacing",model.getSpacing());
                bundle.putString("benchHeight",model.getBenchHeigh());
                bundle.putString("subDrill",model.getSubDrill());
                bundle.putString("stemLength",model.getStemLength());
                bundle.putString("rockDensity",model.getRockDensity());
                bundle.putString("hole_ms",model.getHoleMs());
                bundle.putString("distance",model.getRockDensity());
                bundle.putString("scaling",model.getScaling());
                bundle.putString("attenuation",model.getAttenuation());
                GeneralUtils.connectFragmentWithBack(context,new CalculatorByShotFragment()).setArguments(bundle);
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

