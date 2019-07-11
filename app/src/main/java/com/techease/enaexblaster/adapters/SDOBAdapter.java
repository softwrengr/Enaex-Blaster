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
import com.techease.enaexblaster.models.SdobModel;
import com.techease.enaexblaster.models.VibrationModel;
import com.techease.enaexblaster.sqliteDatabase.EnaexCrud;
import com.techease.enaexblaster.utilities.GeneralUtils;
import com.techease.enaexblaster.views.calculators.SDOBCalculatorFragment;
import com.techease.enaexblaster.views.calculators.ScaledDistanceFragment;
import com.techease.enaexblaster.views.calculators.VibrationCalculatorFragment;

import java.util.ArrayList;
import java.util.List;

public class SDOBAdapter extends RecyclerView.Adapter<SDOBAdapter.MyViewHolder> {
    EnaexCrud bossMoveCrud;
    List<SdobModel> likeWallPaperModelList;
    Context context;
    ArrayList<String> alIndexPosition = new ArrayList<>();

    public SDOBAdapter(Context context, List<SdobModel> likeWallPaperModelList) {
        this.context = context;
        this.likeWallPaperModelList = likeWallPaperModelList;

    }


    @NonNull
    @Override
    public SDOBAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_scaled_distance, parent, false);

        return new SDOBAdapter.MyViewHolder(itemView);
    }

    @Override
    public long getItemId(int position) {
        final SdobModel model = likeWallPaperModelList.get(position);
        if (likeWallPaperModelList != null && likeWallPaperModelList.size() > position)
            return likeWallPaperModelList.size();
        return 0;
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull final SDOBAdapter.MyViewHolder viewHolder, final int position) {
        final SdobModel model = likeWallPaperModelList.get(position);
        bossMoveCrud = new EnaexCrud(context);

        viewHolder.tvRowName.setText(model.getRow_name());

        viewHolder.rowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("diameter",model.getDiameter());
                bundle.putString("density",model.getDensity());
                bundle.putString("holeLength",model.getHoleLength());
                bundle.putString("stemLength",model.getStemLength());
                GeneralUtils.connectFragmentWithBack(context,new SDOBCalculatorFragment()).setArguments(bundle);
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

