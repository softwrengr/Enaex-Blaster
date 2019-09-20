package com.techease.enaexblaster.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.techease.enaexblaster.R;
import com.techease.enaexblaster.models.ShotModel;
import com.techease.enaexblaster.sqliteDatabase.EnaexDatabase;
import com.techease.enaexblaster.sqliteDatabase.ShotCrud;
import com.techease.enaexblaster.utilities.GeneralUtils;
import com.techease.enaexblaster.views.calculators.CalculatorByShotFragment;

import java.util.ArrayList;
import java.util.List;

public class ShotAdapter extends RecyclerView.Adapter<ShotAdapter.MyViewHolder> {
    ShotCrud bossMoveCrud;
    List<ShotModel> likeWallPaperModelList;
    Context context;
    ArrayList<String> alIndexPosition = new ArrayList<>();
    SQLiteDatabase sqLiteDatabase;

    public ShotAdapter(Context context, List<ShotModel> likeWallPaperModelList) {
        this.context = context;
        this.likeWallPaperModelList = likeWallPaperModelList;
        EnaexDatabase database = new EnaexDatabase(context);
        sqLiteDatabase = database.getWritableDatabase();

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
        bossMoveCrud = new ShotCrud(context);

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
                bundle.putString("distance",model.getDistance());
                bundle.putString("scaling",model.getScaling());
                bundle.putString("attenuation",model.getAttenuation());

                bundle.putString("calculator",model.getCheckCalculator());
                bundle.putString("volume",model.getCheckVolume());
                bundle.putString("subdrill",model.getCheckSubdrill());
                bundle.putString("check_holes",model.getCheckHole());
                bundle.putString("vibration",model.getCheckVibration());

                GeneralUtils.connectFragmentWithBack(context,new CalculatorByShotFragment()).setArguments(bundle);
            }
        });

        viewHolder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(model.getRowName());
                likeWallPaperModelList.remove(position);
                notifyItemRemoved(position);
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
        ImageView ivDelete;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRowName = itemView.findViewById(R.id.tv_row_name);
            rowLayout = itemView.findViewById(R.id.row_layout);
            ivDelete = itemView.findViewById(R.id.iv_delete);
        }
    }

    public void delete(String row_name) {
        this.sqLiteDatabase.delete("BY_SHOT", "ROW_NAME= '" + row_name + "'", null);
    }
}

