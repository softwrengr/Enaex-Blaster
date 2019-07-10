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

    @BindView(R.id.iv_bench)
    ImageView ivBench;
    @BindView(R.id.iv_loading)
    ImageView ivLoading;
    @BindView(R.id.iv_rock)
    ImageView ivRock;
    @BindView(R.id.iv_sdob)
    ImageView ivSDOB;

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
                bundle.putBoolean("screen",true);
                GeneralUtils.connectFragmentWithBack(getActivity(),new OpenPdfFragment()).setArguments(bundle);
            }
        });

        tvLoadingImperial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("check_pdf","loading_imperial.pdf");
                bundle.putBoolean("screen",true);
                GeneralUtils.connectFragmentWithBack(getActivity(),new OpenPdfFragment()).setArguments(bundle);
            }
        });

        tvLoadingMetric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("check_pdf","loading_metric.pdf");
                bundle.putBoolean("screen",true);
                GeneralUtils.connectFragmentWithBack(getActivity(),new OpenPdfFragment()).setArguments(bundle);
            }
        });

        tvRock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("check_pdf","rock_property.pdf");
                bundle.putBoolean("screen",true);
                GeneralUtils.connectFragmentWithBack(getActivity(),new OpenPdfFragment()).setArguments(bundle);
            }
        });

        tvSdob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("check_pdf","sdob.pdf");
                bundle.putBoolean("screen",true);
                bundle.putInt("checkPage",2);
                GeneralUtils.connectFragmentWithBack(getActivity(),new OpenPdfFragment()).setArguments(bundle);
            }
        });




    }

    private void initLayout(){
        LayoutBench.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check){
                    ivBench.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                    tvDefinition.setVisibility(View.GONE);
                    check = false;
                }
                else {
                    ivBench.setImageDrawable(getResources().getDrawable(R.drawable.up));
                    tvDefinition.setVisibility(View.VISIBLE);
                    check = true;
                }
            }
        });


        layoutLoading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check){
                    ivLoading.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                    tvLoadingImperial.setVisibility(View.GONE);
                    tvLoadingMetric.setVisibility(View.GONE);
                    check = false;
                }
                else {
                    ivLoading.setImageDrawable(getResources().getDrawable(R.drawable.up));
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
                    ivRock.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                    tvRock.setVisibility(View.GONE);
                    check = false;
                }
                else {
                    ivRock.setImageDrawable(getResources().getDrawable(R.drawable.up));
                    tvRock.setVisibility(View.VISIBLE);
                    check = true;
                }
            }
        });

        layoutSdob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check){
                    ivSDOB.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                    tvSdob.setVisibility(View.GONE);
                    check = false;
                }
                else {
                    ivSDOB.setImageDrawable(getResources().getDrawable(R.drawable.up));
                    tvSdob.setVisibility(View.VISIBLE);
                    check = true;
                }
            }
        });
    }
}
