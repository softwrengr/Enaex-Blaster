package com.techease.enaexblaster.views.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.techease.enaexblaster.R;
import com.techease.enaexblaster.utilities.GeneralUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CalculatorsHomeFragment extends Fragment {
    View view;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.calculator_layout)
    FrameLayout layoutByHole;
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
    }
}
