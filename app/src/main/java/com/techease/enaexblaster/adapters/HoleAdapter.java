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
import android.widget.Toast;

import com.techease.enaexblaster.R;
import com.techease.enaexblaster.models.HoleModel;
import com.techease.enaexblaster.sqliteDatabase.EnaexDatabase;
import com.techease.enaexblaster.sqliteDatabase.ShotCrud;
import com.techease.enaexblaster.utilities.GeneralUtils;
import com.techease.enaexblaster.views.calculators.CalculatorByHoleFragment;

import java.util.ArrayList;
import java.util.List;

public class HoleAdapter extends RecyclerView.Adapter<HoleAdapter.MyViewHolder> {
    ShotCrud bossMoveCrud;
    List<HoleModel> likeWallPaperModelList;
    Context context;
     SQLiteDatabase sqLiteDatabase;

    public HoleAdapter(Context context, List<HoleModel> likeWallPaperModelList) {
        this.context = context;
        this.likeWallPaperModelList = likeWallPaperModelList;
        EnaexDatabase database = new EnaexDatabase(context);
        sqLiteDatabase = database.getWritableDatabase();

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
        bossMoveCrud = new ShotCrud(context);

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
                bundle.putString("distance",model.getDistance());
                bundle.putString("scaling",model.getScaling());
                bundle.putString("attenuation",model.getAttenuation());
                bundle.putString("checkCalculator",model.getCheckCalculator());
                bundle.putString("checkVolume",model.getCheckVolume());
                bundle.putString("vibration",model.getCheckVibration());
                GeneralUtils.connectFragmentWithBack(context,new CalculatorByHoleFragment()).setArguments(bundle);
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
        this.sqLiteDatabase.delete("BY_HOLE", "ROW_NAME= '" + row_name + "'", null);
    }
}

