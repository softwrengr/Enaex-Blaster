package com.techease.enaexblaster.views.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.techease.enaexblaster.R;
import com.techease.enaexblaster.utilities.GeneralUtils;
import com.techease.enaexblaster.views.activities.OpenPDFActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuideLineFragment extends Fragment {
    View view;
    @BindView(R.id.iv_back)
    ImageView ivBack;

    @BindView(R.id.tv_loading_imperial)
    TextView tvImperail;
    @BindView(R.id.tv_loading_metric)
    TextView tvMetric;

    @BindView(R.id.bench_def)
    LinearLayout LayoutBench;
    @BindView(R.id.loading_layout)
    LinearLayout layoutLoading;
    @BindView(R.id.rock_layout)
    LinearLayout layoutRock;
    @BindView(R.id.sdob_layout)
    LinearLayout layoutSdob;


    private boolean checkCalculator = false;
    Bundle bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_guide_line, container, false);
        ButterKnife.bind(this, view);
        initViews();

        checkCalculator = GeneralUtils.getSharedPreferences(getActivity()).getBoolean("check_unit", true);
        if(!checkCalculator){
            tvMetric.setVisibility(View.GONE);
        }
        else {
            tvImperail.setVisibility(View.GONE);
        }
        return view;
    }

    private void initViews() {
        bundle = new Bundle();

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.connectFragment(getActivity(), new HomeFragment());
            }
        });

        LayoutBench.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("check_pdf", "bench_definition.pdf");
                bundle.putBoolean("screen", true);
                startActivity(new Intent(getActivity(), OpenPDFActivity.class).putExtras(bundle));
            }
        });

        layoutLoading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkCalculator) {
                    bundle.putString("check_pdf", "loading_imperial.pdf");
                    bundle.putBoolean("screen", true);
                    startActivity(new Intent(getActivity(), OpenPDFActivity.class).putExtras(bundle));
                } else {
                    bundle.putString("check_pdf", "loading_metric.pdf");
                    bundle.putBoolean("screen", true);
                    startActivity(new Intent(getActivity(), OpenPDFActivity.class).putExtras(bundle));
                }
            }
        });


        layoutRock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("check_pdf", "rock_property.pdf");
                bundle.putBoolean("screen", true);
                startActivity(new Intent(getActivity(), OpenPDFActivity.class).putExtras(bundle));
            }
        });

        layoutSdob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!checkCalculator) {
                    bundle.putString("check_pdf", "sdob_imperial.pdf");
                    bundle.putBoolean("screen", true);
                    bundle.putInt("checkPage", 1);
                    startActivity(new Intent(getActivity(), OpenPDFActivity.class).putExtras(bundle));
                } else {
                    bundle.putString("check_pdf", "sdob_metric.pdf");
                    bundle.putBoolean("screen", true);
                    bundle.putInt("checkPage", 1);
                    startActivity(new Intent(getActivity(), OpenPDFActivity.class).putExtras(bundle));
                }
            }
        });

    }
}
