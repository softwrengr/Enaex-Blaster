package com.techease.enaexblaster.views.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.techease.enaexblaster.R;
import com.techease.enaexblaster.utilities.GeneralUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuideLineFragment extends Fragment {
    View view;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_definition)
    TextView tvDefinition;
    @BindView(R.id.tv_loading_imperial)
    TextView tvLoadingImperial;
    @BindView(R.id.tv_loading_metric)
    TextView tvLoadingMetric;
    @BindView(R.id.tv_sdob)
    TextView tvSdob;
    @BindView(R.id.tv_rock_definition)
    TextView tvRock;

    @BindView(R.id.bench_def)
    RelativeLayout LayoutBench;
    @BindView(R.id.loading_layout)
    RelativeLayout layoutLoading;
    @BindView(R.id.rock_layout)
    RelativeLayout layoutRock;
    @BindView(R.id.sdob_layout)
    RelativeLayout layoutSdob;
    private boolean check = false;
    Bundle bundle;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_guide_line, container, false);
        ButterKnife.bind(this,view);
        bundle = new Bundle();
        initLayout();
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

        tvDefinition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("check_pdf","bench_definition.pdf");
                GeneralUtils.connectFragmentWithBack(getActivity(),new OpenPdfFragment()).setArguments(bundle);
            }
        });

        tvLoadingImperial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("check_pdf","loading_imperial.pdf");
                GeneralUtils.connectFragmentWithBack(getActivity(),new OpenPdfFragment()).setArguments(bundle);
            }
        });

        tvLoadingMetric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("check_pdf","loading_metric.pdf");
                GeneralUtils.connectFragmentWithBack(getActivity(),new OpenPdfFragment()).setArguments(bundle);
            }
        });

        tvRock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("check_pdf","rock_property.pdf");
                GeneralUtils.connectFragmentWithBack(getActivity(),new OpenPdfFragment()).setArguments(bundle);
            }
        });

        tvSdob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("check_pdf","sdob.pdf");
                GeneralUtils.connectFragmentWithBack(getActivity(),new OpenPdfFragment()).setArguments(bundle);
            }
        });




    }

    private void initLayout(){
        LayoutBench.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check){
                    tvDefinition.setVisibility(View.GONE);
                    check = false;
                }
                else {
                    tvDefinition.setVisibility(View.VISIBLE);
                    check = true;
                }
            }
        });


        layoutLoading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check){
                    tvLoadingImperial.setVisibility(View.GONE);
                    tvLoadingMetric.setVisibility(View.GONE);
                    check = false;
                }
                else {
                    tvLoadingImperial.setVisibility(View.VISIBLE);
                    tvLoadingMetric.setVisibility(View.VISIBLE);
                    check = true;
                }
            }
        });
        layoutRock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(check){
                    tvRock.setVisibility(View.GONE);
                    check = false;
                }
                else {
                    tvRock.setVisibility(View.VISIBLE);
                    check = true;
                }
            }
        });

        layoutSdob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check){
                    tvSdob.setVisibility(View.GONE);
                    check = false;
                }
                else {
                    tvSdob.setVisibility(View.VISIBLE);
                    check = true;
                }
            }
        });
    }
}
