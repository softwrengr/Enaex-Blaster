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

import com.techease.enaexblaster.R;
import com.techease.enaexblaster.utilities.GeneralUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductInfoFragment extends Fragment {
    private boolean check = false;
    View view;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    Bundle bundle;

    @BindView(R.id.layout_tds_booster)
    RelativeLayout layoutTDSBooster;
    @BindView(R.id.tv_tds_booster)
    TextView tvTDSBooster;
    @BindView(R.id.layout_sds_booster)
    RelativeLayout layoutSDSBooster;
    @BindView(R.id.tv_sds_booster)
    TextView tvSDSBooster;
    @BindView(R.id.tv_sds_britex)
    TextView tvSDSBritex;
    @BindView(R.id.tv_tds_britex)
    TextView tvTDSBritex;
    @BindView(R.id.tv_ss_britex)
    TextView tvSSBritex;

    @BindView(R.id.layout_detonation_cord)
    RelativeLayout layoutDetonationCord;
    @BindView(R.id.tv_tds_britacord)
    TextView tvTDSBritacord;
    @BindView(R.id.tv_sds_britacord)
    TextView tvSDSritacord;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_product_info, container, false);
        ButterKnife.bind(this,view);
        initViews();
        initLayout();
        return view;
    }

    private void initViews(){
        bundle = new Bundle();

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.connectFragment(getActivity(),new HomeFragment());
            }
        });

        tvTDSBooster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("check_pdf","apd_p_series_boosters.pdf");
                GeneralUtils.connectFragmentWithBack(getActivity(),new OpenPdfFragment()).setArguments(bundle);
            }
        });

        tvSDSBooster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("check_pdf","apd_p_series_boosters.pdf");
                GeneralUtils.connectFragmentWithBack(getActivity(),new OpenPdfFragment()).setArguments(bundle);
            }
        });

        tvTDSBritex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("check_pdf","apd_p_series_boosters.pdf");
                GeneralUtils.connectFragmentWithBack(getActivity(),new OpenPdfFragment()).setArguments(bundle);
            }
        });
        tvSSBritex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("check_pdf","apd_p_series_boosters.pdf");
                GeneralUtils.connectFragmentWithBack(getActivity(),new OpenPdfFragment()).setArguments(bundle);
            }
        });
    }

    private void initLayout(){
        layoutTDSBooster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check){
                    tvTDSBooster.setVisibility(View.GONE);
                    tvSDSBritex.setVisibility(View.GONE);
                    tvTDSBritex.setVisibility(View.GONE);
                    tvSSBritex.setVisibility(View.GONE);
                    check = false;
                }
                else {
                    tvTDSBooster.setVisibility(View.VISIBLE);
                    tvSDSBritex.setVisibility(View.VISIBLE);
                    tvTDSBritex.setVisibility(View.VISIBLE);
                    tvSSBritex.setVisibility(View.VISIBLE);
                    check = true;
                }
            }
        });

        layoutSDSBooster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check){
                    check = false;
                }
                else {
                    check = true;
                }
            }
        });

        layoutDetonationCord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check){
                    tvTDSBritacord.setVisibility(View.GONE);
                    tvSDSritacord.setVisibility(View.GONE);
                    check = false;
                }
                else {
                    tvTDSBritacord.setVisibility(View.VISIBLE);
                    tvSDSritacord.setVisibility(View.VISIBLE);
                    check = true;
                }
            }
        });

    }
}
