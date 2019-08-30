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
import com.techease.enaexblaster.models.VibrationModel;
import com.techease.enaexblaster.sqliteDatabase.EnaexDatabase;
import com.techease.enaexblaster.sqliteDatabase.ShotCrud;
import com.techease.enaexblaster.utilities.GeneralUtils;
import com.techease.enaexblaster.views.calculators.VibrationCalculatorFragment;

import java.util.ArrayList;
import java.util.List;

public class VibrationAdapter extends RecyclerView.Adapter<VibrationAdapter.MyViewHolder> {
    ShotCrud bossMoveCrud;
    List<VibrationModel> likeWallPaperModelList;
    Context context;
    ArrayList<String> alIndexPosition = new ArrayList<>();
    SQLiteDatabase sqLiteDatabase;

    public VibrationAdapter(Context context, List<VibrationModel> likeWallPaperModelList) {
        this.context = context;
        this.likeWallPaperModelList = likeWallPaperModelList;
        EnaexDatabase database = new EnaexDatabase(context);
        sqLiteDatabase = database.getWritableDatabase();

    }


    @NonNull
    @Override
    public VibrationAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_scaled_distance, parent, false);

        return new VibrationAdapter.MyViewHolder(itemView);
    }

    @Override
    public long getItemId(int position) {
        final VibrationModel model = likeWallPaperModelList.get(position);
        if (likeWallPaperModelList != null && likeWallPaperModelList.size() > position)
            return likeWallPaperModelList.size();
        return 0;
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull final VibrationAdapter.MyViewHolder viewHolder, final int position) {
        final VibrationModel model = likeWallPaperModelList.get(position);
        bossMoveCrud = new ShotCrud(context);

        viewHolder.tvRowName.setText(model.getRow_name());

        viewHolder.rowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("distance",model.getDistance());
                bundle.putString("mic",model.getMic());
                bundle.putString("scaling",model.getScallingFactor());
                bundle.putString("attenuation",model.getAttenuation());
                GeneralUtils.connectFragmentWithBack(context,new VibrationCalculatorFragment()).setArguments(bundle);
            }
        });

        viewHolder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(model.getRow_name());
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
        this.sqLiteDatabase.delete("VIBRATION", "ROW_NAME= '" + row_name + "'", null);
    }
}

