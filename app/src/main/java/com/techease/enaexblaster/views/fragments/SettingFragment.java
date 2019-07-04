package com.techease.enaexblaster.views.fragments;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.techease.enaexblaster.R;
import com.techease.enaexblaster.utilities.GeneralUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SettingFragment extends Fragment implements View.OnClickListener {
    View view;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.layout_language)
    LinearLayout layoutLanguage;
    @BindView(R.id.layout_region)
    LinearLayout layoutRegion;
    @BindView(R.id.layout_units)
    LinearLayout layoutUnits;
    @BindView(R.id.tv_languages)
    TextView tvLanguages;
    @BindView(R.id.tv_regions)
    TextView tvRegions;
    @BindView(R.id.tv_units)
    TextView tvUnits;
    String strMeaurmentUnit;
    Dialog dialog;

    TextView tvMetric, tvImperial, tvCancel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_setting, container, false);
        ButterKnife.bind(this, view);
        initViews();

        if(GeneralUtils.getSharedPreferences(getActivity()).getBoolean("check_unit",true)){
            tvUnits.setText("Metric");
        }
        else {
            tvUnits.setText("Imperial");
        }
        return view;
    }

    private void initViews() {
        layoutLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        layoutRegion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        layoutUnits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog("Metric", "Imperial");
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.connectFragment(getActivity(),new HomeFragment());
            }
        });
    }

    private void showDialog(String strOne, String strTwo) {
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_layout);
        tvMetric = dialog.findViewById(R.id.tv_metric);
        tvImperial = dialog.findViewById(R.id.tv_imperail);
        tvCancel = dialog.findViewById(R.id.tv_cancel);

        tvMetric.setText(strOne);
        tvImperial.setText(strTwo);


        tvMetric.setOnClickListener(this);
        tvImperial.setOnClickListener(this);


        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    @Override
    public void onClick(View v) {
      switch (v.getId()){
          case R.id.tv_metric:
              GeneralUtils.putBooleanValueInEditor(getActivity(),"check_unit",true);
              strMeaurmentUnit = tvMetric.getText().toString();
              tvUnits.setText(strMeaurmentUnit);
              dialog.dismiss();
              break;
          case R.id.tv_imperail:
              GeneralUtils.putBooleanValueInEditor(getActivity(),"check_unit",false);
              strMeaurmentUnit = tvImperial.getText().toString();
              tvUnits.setText(strMeaurmentUnit);
              dialog.dismiss();
              break;
      }
    }
}
