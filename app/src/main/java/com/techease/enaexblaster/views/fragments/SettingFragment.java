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
    String strLanguage,strRegion,strUnit,strData;
    Dialog dialog;

    TextView tvOne, tvTwo, tvThree, tvFour, tvCancel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_setting, container, false);
        ButterKnife.bind(this, view);
        initViews();
        return view;
    }

    private void initViews() {
        layoutLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog("English", "Francais", "Espanol", "", "language");
            }
        });

        layoutRegion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog("North America", "South America", "Asia Pacific", "EMEA", "region");
            }
        });

        layoutUnits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog("Metric", "Imperial", "", "", "unit");
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.connectFragment(getActivity(),new HomeFragment());
            }
        });
    }

    private void showDialog(String strOne, String strTwo, String strThree, String strFour, final String check) {
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_layout);
        tvOne = dialog.findViewById(R.id.tv_one);
        tvTwo = dialog.findViewById(R.id.tv_two);
        tvThree = dialog.findViewById(R.id.tv_three);
        tvFour = dialog.findViewById(R.id.tv_four);
        tvCancel = dialog.findViewById(R.id.tv_cancel);

        tvOne.setText(strOne);
        tvTwo.setText(strTwo);
        tvThree.setText(strThree);
        tvFour.setText(strFour);

        tvOne.setOnClickListener(this);
        tvTwo.setOnClickListener(this);
        tvThree.setOnClickListener(this);
        tvFour.setOnClickListener(this);

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
          case R.id.tv_one:
              strData = tvOne.getText().toString();
              dialog.dismiss();
              break;
          case R.id.tv_two:
              strData = tvOne.getText().toString();
              dialog.dismiss();
              break;
          case R.id.tv_three:
              strData = tvOne.getText().toString();
              dialog.dismiss();
              break;
          case R.id.tv_four:
              strData = tvOne.getText().toString();
              dialog.dismiss();
              break;
      }
    }
}
