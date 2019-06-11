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


public class SettingFragment extends Fragment {
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
    String strData;

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
        final Dialog dialog = new Dialog(getActivity());
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

        checkLayout(check,"");

        tvOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strData = tvOne.getText().toString();
                checkLayout(check,strData);
                dialog.dismiss();
            }
        });

        tvTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strData = tvTwo.getText().toString();
                checkLayout(check,strData);
                dialog.dismiss();
            }
        });

        tvThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strData = tvThree.getText().toString();
                checkLayout(check,strData);
                dialog.dismiss();
            }
        });

        tvFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strData = tvFour.getText().toString();
                checkLayout(check,strData);
                dialog.dismiss();
            }
        });



        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void checkLayout(String check,String data){
        switch (check) {
            case "language":
                tvLanguages.setText(data);
                tvFour.setVisibility(View.GONE);
                break;
            case "unit":
                tvUnits.setText(strData);
                tvThree.setVisibility(View.GONE);
                tvFour.setVisibility(View.GONE);
                break;
            case "region":
                tvRegions.setText(data);
                break;

        }
    }

}
