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

public class ProductInfoFragment extends Fragment implements View.OnClickListener {
    private boolean check = false;
    View view;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    Bundle bundle;

    @BindView(R.id.layout_tds_booster)
    RelativeLayout layoutTDSBooster;
    @BindView(R.id.tv_tds_booster)
    TextView tvTDSBooster;
    @BindView(R.id.tv_sds_britex)
    TextView tvSDSBritex;
    @BindView(R.id.tv_tds_britex)
    TextView tvTDSBritex;
    @BindView(R.id.tv_ss_britex)
    TextView tvSSBritex;

    @BindView(R.id.layout_bulk_products)
    RelativeLayout layoutSDSBooster;

    @BindView(R.id.layout_detonation_cord)
    RelativeLayout layoutDetonationCord;
    @BindView(R.id.tv_tds_britacord)
    TextView tvTDSBritacord;
    @BindView(R.id.tv_sds_britacord)
    TextView tvSDSritacord;

    @BindView(R.id.layout_electronics)
    RelativeLayout layoutElectronics;
    @BindView(R.id.tv_daveytronic_sds)
    TextView tvDaveytronicSDS;
    @BindView(R.id.tv_daveytronic_op)
    TextView tvDaveytronicOP;
    @BindView(R.id.tv_daveytronic_sp)
    TextView tvDaveytronicSP;
    @BindView(R.id.tv_daveytronic_ug)
    TextView tvDaveytronicUG;

    @BindView(R.id.layout_thermo)
    RelativeLayout layoutThermo;
    @BindView(R.id.tv_thermo_tube_tds)
    TextView tvThermoTDS;
    @BindView(R.id.tv_thermo_tube_sds)
    TextView tvThermoSDS;

    @BindView(R.id.layout_non_electronic)
    RelativeLayout layoutNonElectronic;
    @BindView(R.id.tv_daveynel_tds)
    TextView tvDaveynelTDS;
    @BindView(R.id.tv_daveynel_surface_tds)
    TextView tvDaveynelSurfaceTDS;
    @BindView(R.id.tv_daveynel_dual_tds)
    TextView tvDaveynelDualTDS;
    @BindView(R.id.tv_daveynel_nonelectronic_sds)
    TextView tvDaveynelNonElectronics;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_product_info, container, false);
        ButterKnife.bind(this, view);
        initLayout();
        initViews();
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

        tvTDSBooster.setOnClickListener(this);
        tvSDSBritex.setOnClickListener(this);
        tvTDSBritex.setOnClickListener(this);
        tvSSBritex.setOnClickListener(this);

        tvTDSBritacord.setOnClickListener(this);
        tvSDSritacord.setOnClickListener(this);

        tvDaveytronicSDS.setOnClickListener(this);
        tvDaveytronicOP.setOnClickListener(this);
        tvDaveytronicSP.setOnClickListener(this);
        tvDaveytronicUG.setOnClickListener(this);

        tvThermoTDS.setOnClickListener(this);
        tvThermoSDS.setOnClickListener(this);

        tvDaveynelTDS.setOnClickListener(this);
        tvDaveynelSurfaceTDS.setOnClickListener(this);
        tvDaveynelDualTDS.setOnClickListener(this);
        tvDaveynelNonElectronics.setOnClickListener(this);
    }

    private void initLayout() {
        layoutTDSBooster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check) {
                    tvTDSBooster.setVisibility(View.GONE);
                    tvSDSBritex.setVisibility(View.GONE);
                    tvTDSBritex.setVisibility(View.GONE);
                    tvSSBritex.setVisibility(View.GONE);
                    check = false;
                } else {
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
                if (check) {
                    check = false;
                } else {
                    check = true;
                }
            }
        });

        layoutDetonationCord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check) {
                    tvTDSBritacord.setVisibility(View.GONE);
                    tvSDSritacord.setVisibility(View.GONE);
                    check = false;
                } else {
                    tvTDSBritacord.setVisibility(View.VISIBLE);
                    tvSDSritacord.setVisibility(View.VISIBLE);
                    check = true;
                }
            }
        });

        layoutElectronics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check) {
                    tvDaveytronicOP.setVisibility(View.GONE);
                    tvDaveytronicSDS.setVisibility(View.GONE);
                    tvDaveytronicUG.setVisibility(View.GONE);
                    tvDaveytronicSP.setVisibility(View.GONE);
                    check = false;
                } else {
                    tvDaveytronicOP.setVisibility(View.VISIBLE);
                    tvDaveytronicSDS.setVisibility(View.VISIBLE);
                    tvDaveytronicUG.setVisibility(View.VISIBLE);
                    tvDaveytronicSP.setVisibility(View.VISIBLE);
                    check = true;
                }
            }
        });

        layoutNonElectronic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check) {
                    tvDaveynelTDS.setVisibility(View.GONE);
                    tvDaveynelSurfaceTDS.setVisibility(View.GONE);
                    tvDaveynelDualTDS.setVisibility(View.GONE);
                    tvDaveynelNonElectronics.setVisibility(View.GONE);
                    check = false;
                } else {
                    tvDaveynelTDS.setVisibility(View.VISIBLE);
                    tvDaveynelSurfaceTDS.setVisibility(View.VISIBLE);
                    tvDaveynelDualTDS.setVisibility(View.VISIBLE);
                    tvDaveynelNonElectronics.setVisibility(View.VISIBLE);
                    check = true;
                }
            }
        });

        layoutThermo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check) {
                    tvThermoSDS.setVisibility(View.GONE);
                    tvThermoTDS.setVisibility(View.GONE);
                    check = false;
                } else {
                    tvThermoSDS.setVisibility(View.VISIBLE);
                    tvThermoTDS.setVisibility(View.VISIBLE);
                    check = true;
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_tds_booster:
                bundle.putString("check_pdf", "apd_p_series_boosters.pdf");
                GeneralUtils.connectFragmentWithBack(getActivity(), new OpenPdfFragment()).setArguments(bundle);
                break;
            case R.id.tv_sds_britex:
                bundle.putString("check_pdf", "sds_britex.pdf");
                GeneralUtils.connectFragmentWithBack(getActivity(), new OpenPdfFragment()).setArguments(bundle);
                break;
            case R.id.tv_tds_britex:
                bundle.putString("check_pdf", "tds_britex_cl.pdf");
                GeneralUtils.connectFragmentWithBack(getActivity(), new OpenPdfFragment()).setArguments(bundle);
                break;
            case R.id.tv_ss_britex:
                bundle.putString("check_pdf", "tds_britex.pdf");
                GeneralUtils.connectFragmentWithBack(getActivity(), new OpenPdfFragment()).setArguments(bundle);
                break;

            case R.id.tv_tds_britacord:
                bundle.putString("check_pdf", "tds_britacord.pdf");
                GeneralUtils.connectFragmentWithBack(getActivity(), new OpenPdfFragment()).setArguments(bundle);
                break;
            case R.id.tv_sds_britacord:
                bundle.putString("check_pdf", "sds_britacord.pdf");
                GeneralUtils.connectFragmentWithBack(getActivity(), new OpenPdfFragment()).setArguments(bundle);
                break;

            case R.id.tv_thermo_tube_sds:
                bundle.putString("check_pdf", "sds_thermo.pdf");
                GeneralUtils.connectFragmentWithBack(getActivity(), new OpenPdfFragment()).setArguments(bundle);
                break;
            case R.id.tv_thermo_tube_tds:
                bundle.putString("check_pdf", "tds_thermo.pdf");
                GeneralUtils.connectFragmentWithBack(getActivity(), new OpenPdfFragment()).setArguments(bundle);
                break;

            case R.id.tv_daveytronic_sp:
                bundle.putString("check_pdf", "tds_daveytronic_sp.pdf");
                GeneralUtils.connectFragmentWithBack(getActivity(), new OpenPdfFragment()).setArguments(bundle);
                break;
            case R.id.tv_daveytronic_ug:
                bundle.putString("check_pdf", "tds_daveytronic_ug.pdf");
                GeneralUtils.connectFragmentWithBack(getActivity(), new OpenPdfFragment()).setArguments(bundle);
                break;

            case R.id.tv_daveytronic_op:
                bundle.putString("check_pdf", "tds_daveytronic_op.pdf");
                GeneralUtils.connectFragmentWithBack(getActivity(), new OpenPdfFragment()).setArguments(bundle);
                break;
            case R.id.tv_daveytronic_sds:
                bundle.putString("check_pdf", "apd_p_series_boosters.pdf");
                GeneralUtils.connectFragmentWithBack(getActivity(), new OpenPdfFragment()).setArguments(bundle);
                break;

            case R.id.tv_daveynel_tds:
                bundle.putString("check_pdf", "sds_daveytronics_detonators.pdf");
                GeneralUtils.connectFragmentWithBack(getActivity(), new OpenPdfFragment()).setArguments(bundle);
                break;
            case R.id.tv_daveynel_surface_tds:
                bundle.putString("check_pdf", "tds_daveynel_surface.pdf");
                GeneralUtils.connectFragmentWithBack(getActivity(), new OpenPdfFragment()).setArguments(bundle);
                break;

            case R.id.tv_daveynel_dual_tds:
                bundle.putString("check_pdf", "tds_daveyquick_dual.pdf");
                GeneralUtils.connectFragmentWithBack(getActivity(), new OpenPdfFragment()).setArguments(bundle);
                break;
            case R.id.tv_daveynel_nonelectronic_sds:
                bundle.putString("check_pdf", "sds_daveytronics_detonators.pdf");
                GeneralUtils.connectFragmentWithBack(getActivity(), new OpenPdfFragment()).setArguments(bundle);
                break;
        }
    }
}
