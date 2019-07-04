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
import android.widget.ScrollView;
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
    @BindView(R.id.scrollview)
    ScrollView scrollView;
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
    RelativeLayout layoutBulkProducts;
    @BindView(R.id.bulk_layout)
    RelativeLayout layoutBulk;
    @BindView(R.id.tv_an_prill_tds)
    TextView tvAnPrillTDS;
    @BindView(R.id.tv_an_prill_sds)
    TextView tvAnPrillSDS;
    @BindView(R.id.tv_anfomax_tds)
    TextView tvAnfomaxTDS;
    @BindView(R.id.tv_anfomax_sds)
    TextView tvAnfomaxSDS;
    @BindView(R.id.tv_ae_59_tds)
    TextView tvAeTDS;
    @BindView(R.id.tv_ae_59_sds)
    TextView tvAeSDS;
    @BindView(R.id.tv_emultex_tds)
    TextView tvEmultexTDS;
    @BindView(R.id.tv_emultex_sds)
    TextView tvEmultexSDS;
    @BindView(R.id.tv_dl_seriec_tds)
    TextView tvDlSeriesTDS;
    @BindView(R.id.tv_dl_seriec_sds)
    TextView tvDlSeriesSDS;
    @BindView(R.id.tv_emultex_dl_tds)
    TextView tvEmultexDlTDS;
    @BindView(R.id.tv_emultex_dl_sds)
    TextView tvEmultexDlSDS;
    @BindView(R.id.tv_blendex_tds)
    TextView tvBlendexTDS;
    @BindView(R.id.tv_blendex_sds)
    TextView tvBlendexSDS;
    @BindView(R.id.tv_pirex_tds)
    TextView tvPirexTDS;
    @BindView(R.id.tv_pirex_ms_sds)
    TextView tvPirexMsSDS;


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

    @BindView(R.id.iv_booster)
    ImageView ivBooster;
    @BindView(R.id.iv_bulk)
    ImageView ivBulk;
    @BindView(R.id.iv_cord)
    ImageView ivCord;
    @BindView(R.id.iv_electronic)
    ImageView ivElectronics;
    @BindView(R.id.iv_non_electronic)
    ImageView ivNonElectronics;
    @BindView(R.id.iv_thermo)
    ImageView ivThermo;


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

        tvAnPrillTDS.setOnClickListener(this);
        tvAnPrillSDS.setOnClickListener(this);
        tvAnfomaxTDS.setOnClickListener(this);
        tvAnfomaxSDS.setOnClickListener(this);
        tvAeTDS.setOnClickListener(this);
        tvAeSDS.setOnClickListener(this);
        tvEmultexTDS.setOnClickListener(this);
        tvEmultexSDS.setOnClickListener(this);
        tvDlSeriesTDS.setOnClickListener(this);
        tvDlSeriesSDS.setOnClickListener(this);
        tvEmultexDlTDS.setOnClickListener(this);
        tvEmultexDlSDS.setOnClickListener(this);
        tvBlendexTDS.setOnClickListener(this);
        tvBlendexSDS.setOnClickListener(this);
        tvPirexTDS.setOnClickListener(this);
        tvPirexMsSDS.setOnClickListener(this);

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
                    ivBooster.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                    tvTDSBooster.setVisibility(View.GONE);
                    tvSDSBritex.setVisibility(View.GONE);
                    tvTDSBritex.setVisibility(View.GONE);
                    tvSSBritex.setVisibility(View.GONE);
                    check = false;
                } else {
                    ivBooster.setImageDrawable(getResources().getDrawable(R.drawable.up));
                    tvTDSBooster.setVisibility(View.VISIBLE);
                    tvSDSBritex.setVisibility(View.VISIBLE);
                    tvTDSBritex.setVisibility(View.VISIBLE);
                    tvSSBritex.setVisibility(View.VISIBLE);
                    check = true;
                }
            }
        });

        layoutBulkProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check) {
                    check = false;
                    ivBulk.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                    layoutBulk.setVisibility(View.GONE);
                } else {
                    check = true;
                    ivBulk.setImageDrawable(getResources().getDrawable(R.drawable.up));
                    layoutBulk.setVisibility(View.VISIBLE);
                }
            }
        });

        layoutDetonationCord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check) {
                    ivCord.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                    tvTDSBritacord.setVisibility(View.GONE);
                    tvSDSritacord.setVisibility(View.GONE);
                    check = false;
                } else {
                    ivCord.setImageDrawable(getResources().getDrawable(R.drawable.up));
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
                    ivElectronics.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                    tvDaveytronicOP.setVisibility(View.GONE);
                    tvDaveytronicSDS.setVisibility(View.GONE);
                    tvDaveytronicUG.setVisibility(View.GONE);
                    tvDaveytronicSP.setVisibility(View.GONE);
                    check = false;
                } else {
                    ivElectronics.setImageDrawable(getResources().getDrawable(R.drawable.up));
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
                    ivNonElectronics.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                    tvDaveynelTDS.setVisibility(View.GONE);
                    tvDaveynelSurfaceTDS.setVisibility(View.GONE);
                    tvDaveynelDualTDS.setVisibility(View.GONE);
                    tvDaveynelNonElectronics.setVisibility(View.GONE);
                    check = false;
                } else {
                    ivNonElectronics.setImageDrawable(getResources().getDrawable(R.drawable.up));
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
                    ivThermo.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                    tvThermoSDS.setVisibility(View.GONE);
                    tvThermoTDS.setVisibility(View.GONE);
                    check = false;
                } else {
                    ivThermo.setImageDrawable(getResources().getDrawable(R.drawable.up));
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

            //bulk products
            case R.id.tv_an_prill_tds:
                bundle.putString("check_pdf", "tds_an_prill.pdf");
                GeneralUtils.connectFragmentWithBack(getActivity(), new OpenPdfFragment()).setArguments(bundle);
                break;
            case R.id.tv_an_prill_sds:
                bundle.putString("check_pdf", "sds_an_prill.pdf");
                GeneralUtils.connectFragmentWithBack(getActivity(), new OpenPdfFragment()).setArguments(bundle);
                break;
            case R.id.tv_anfomax_tds:
                bundle.putString("check_pdf", "tds_anfomax.pdf");
                GeneralUtils.connectFragmentWithBack(getActivity(), new OpenPdfFragment()).setArguments(bundle);
                break;
            case R.id.tv_anfomax_sds:
                bundle.putString("check_pdf", "sds_anfomax.pdf");
                GeneralUtils.connectFragmentWithBack(getActivity(), new OpenPdfFragment()).setArguments(bundle);
                break;
            case R.id.tv_ae_59_tds:
                bundle.putString("check_pdf", "tds_ae_59.pdf");
                GeneralUtils.connectFragmentWithBack(getActivity(), new OpenPdfFragment()).setArguments(bundle);
                break;
            case R.id.tv_ae_59_sds:
                bundle.putString("check_pdf", "sds_ae_59.pdf");
                GeneralUtils.connectFragmentWithBack(getActivity(), new OpenPdfFragment()).setArguments(bundle);
                break;
            case R.id.tv_emultex_tds:
                bundle.putString("check_pdf", "tds_emultex_ms.pdf");
                GeneralUtils.connectFragmentWithBack(getActivity(), new OpenPdfFragment()).setArguments(bundle);
                break;
            case R.id.tv_emultex_sds:
                bundle.putString("check_pdf", "sds_emultex_ms.pdf");
                GeneralUtils.connectFragmentWithBack(getActivity(), new OpenPdfFragment()).setArguments(bundle);
                break;
            case R.id.tv_dl_seriec_tds:
                bundle.putString("check_pdf", "tds_dl_series.pdf");
                GeneralUtils.connectFragmentWithBack(getActivity(), new OpenPdfFragment()).setArguments(bundle);
                break;
            case R.id.tv_dl_seriec_sds:
                bundle.putString("check_pdf", "sds_dl_series.pdf");
                GeneralUtils.connectFragmentWithBack(getActivity(), new OpenPdfFragment()).setArguments(bundle);
                break;
            case R.id.tv_emultex_dl_tds:
                bundle.putString("check_pdf", "tds_emultex_ms_dl.pdf");
                GeneralUtils.connectFragmentWithBack(getActivity(), new OpenPdfFragment()).setArguments(bundle);
                break;
            case R.id.tv_emultex_dl_sds:
                bundle.putString("check_pdf", "sds_emultex_ms_dl.pdf");
                GeneralUtils.connectFragmentWithBack(getActivity(), new OpenPdfFragment()).setArguments(bundle);
                break;
            case R.id.tv_blendex_tds:
                bundle.putString("check_pdf", "tds_blendex.pdf");
                GeneralUtils.connectFragmentWithBack(getActivity(), new OpenPdfFragment()).setArguments(bundle);
                break;
            case R.id.tv_blendex_sds:
                bundle.putString("check_pdf", "tds_blendex_ms.pdf");
                GeneralUtils.connectFragmentWithBack(getActivity(), new OpenPdfFragment()).setArguments(bundle);
                break;
            case R.id.tv_pirex_tds:
                bundle.putString("check_pdf", "tds_pirex.pdf");
                GeneralUtils.connectFragmentWithBack(getActivity(), new OpenPdfFragment()).setArguments(bundle);
                break;
            case R.id.tv_pirex_ms_sds:
                bundle.putString("check_pdf", "tds_pirex_ms.pdf");
                GeneralUtils.connectFragmentWithBack(getActivity(), new OpenPdfFragment()).setArguments(bundle);
                break;
        }
    }
}
