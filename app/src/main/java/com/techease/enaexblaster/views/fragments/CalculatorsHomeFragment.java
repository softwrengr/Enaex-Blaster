package com.techease.enaexblaster.views.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.techease.enaexblaster.R;
import com.techease.enaexblaster.utilities.GeneralUtils;
import com.techease.enaexblaster.views.calculators.CalculatorByHoleFragment;
import com.techease.enaexblaster.views.calculators.CalculatorByShotFragment;
import com.techease.enaexblaster.views.calculators.ExplosiveWeightFragment;
import com.techease.enaexblaster.views.calculators.ScaledDistanceFragment;
import com.techease.enaexblaster.views.calculators.VibrationCalculatorFragment;
import com.techease.enaexblaster.views.calculators.VolumeCalculatorFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CalculatorsHomeFragment extends Fragment {
    View view;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.calculator_layout)
    FrameLayout layoutByHole;
    @BindView(R.id.shot_calculator_layout)
    FrameLayout layoutByShot;
    @BindView(R.id.volume_calculator)
    FrameLayout layoutVolume;
    @BindView(R.id.sdob_layout)
    FrameLayout sdobLayout;
    @BindView(R.id.vibration_layout)
    FrameLayout vibrationLayout;
    @BindView(R.id.explosive_layout)
    FrameLayout explosiveLayout;
    @BindView(R.id.scaled_layout)
    FrameLayout scaledLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view  = inflater.inflate(R.layout.fragment_calculators_home, container, false);
        ButterKnife.bind(this,view);
         initViews();
        return view;
    }

    private void initViews(){
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.connectFragment(getActivity(),new HomeFragment());
            }
        });

        layoutByHole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.connectFragmentWithBack(getActivity(),new CalculatorByHoleFragment());
            }
        });

        layoutByShot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.connectFragmentWithBack(getActivity(),new CalculatorByShotFragment());
            }
        });

        layoutVolume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.connectFragmentWithBack(getActivity(),new VolumeCalculatorFragment());
            }
        });

        scaledLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.connectFragmentWithBack(getActivity(),new ScaledDistanceFragment());
            }
        });

        vibrationLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.connectFragmentWithBack(getActivity(),new VibrationCalculatorFragment());
            }
        });

        explosiveLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.connectFragmentWithBack(getActivity(),new ExplosiveWeightFragment());
            }
        });
    }
}
