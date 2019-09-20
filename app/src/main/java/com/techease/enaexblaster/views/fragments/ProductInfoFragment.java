package com.techease.enaexblaster.views.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.techease.enaexblaster.R;
import com.techease.enaexblaster.utilities.GeneralUtils;
import com.techease.enaexblaster.views.activities.OpenPDFActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductInfoFragment extends Fragment implements View.OnClickListener {
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
    @BindView(R.id.tv_sds_booster)
    TextView tvSDSBooster;
    @BindView(R.id.tv_sds_britex)
    TextView tvSDSBritex;
    @BindView(R.id.tv_tds_britex)
    TextView tvTDSBritex;
    @BindView(R.id.tv_ss_britex)
    TextView tvSSBritex;

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

    @BindView(R.id.booster_one)
    LinearLayout layoutBoosterOne;
    @BindView(R.id.booster_two)
    LinearLayout layoutBoosterTwo;

    @BindView(R.id.layout_bulk_products)
    RelativeLayout layoutBulkProducts;
    @BindView(R.id.bulk_layout)
    LinearLayout layoutBulk;

    @BindView(R.id.layout_detonation_cord)
    RelativeLayout layoutDetonationCord;
    @BindView(R.id.ditonation_layout)
    LinearLayout layoutDitonation;

    @BindView(R.id.electronic_layout)
    LinearLayout layoutElectronic;

    @BindView(R.id.non_electronic_layout)
    LinearLayout layoutNonElectric;

    @BindView(R.id.thermo_layout)
    LinearLayout layoutThermoTube;

    private boolean check1 = false, check2 = false, check3 = false, check4 = false, check5 = false, check6 = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_product_info, container, false);
        ButterKnife.bind(this, view);

        initViews();

        initLayout();
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
        tvSDSBooster.setOnClickListener(this);
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
                if (check1) {
                    ivBooster.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                    layoutBoosterOne.setVisibility(View.GONE);
                    layoutBoosterTwo.setVisibility(View.GONE);
                    check1 = false;
                } else {
                    openBoosterDropDown();

                    check1 = true;
                    check2 = false;
                    check3 = false;
                    check4 = false;
                    check5 = false;
                    check6 = false;
                }
            }
        });

        layoutBulkProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check2) {
                    check2 = false;
                    ivBulk.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                    layoutBulk.setVisibility(View.GONE);
                } else {
                    check2 = true;
                    ivBulk.setImageDrawable(getResources().getDrawable(R.drawable.up));
                    layoutBulk.setVisibility(View.VISIBLE);

                    layoutBoosterOne.setVisibility(View.GONE);
                    layoutBoosterTwo.setVisibility(View.GONE);
                    layoutDitonation.setVisibility(View.GONE);
                    layoutElectronic.setVisibility(View.GONE);
                    layoutNonElectric.setVisibility(View.GONE);
                    layoutThermoTube.setVisibility(View.GONE);

                    ivBooster.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                    ivCord.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                    ivElectronics.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                    ivNonElectronics.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                    ivThermo.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));

                }

                check1 = false;
                check3 = false;
                check4 = false;
                check5 = false;
                check6 = false;
            }
        });

        layoutDetonationCord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check3) {
                    ivCord.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                    layoutDitonation.setVisibility(View.GONE);
                    check3 = false;
                } else {
                    ivCord.setImageDrawable(getResources().getDrawable(R.drawable.up));
                    layoutDitonation.setVisibility(View.VISIBLE);
                    check3 = true;

                    layoutBoosterOne.setVisibility(View.GONE);
                    layoutBoosterTwo.setVisibility(View.GONE);
                    layoutBulk.setVisibility(View.GONE);
                    layoutElectronic.setVisibility(View.GONE);
                    layoutNonElectric.setVisibility(View.GONE);
                    layoutThermoTube.setVisibility(View.GONE);

                    ivBooster.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                    ivBulk.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                    ivElectronics.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                    ivNonElectronics.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                    ivThermo.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));

                }

                check1 = false;
                check2 = false;
                check4 = false;
                check5 = false;
                check6 = false;
            }
        });

        layoutElectronics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check4) {
                    ivElectronics.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                    layoutElectronic.setVisibility(View.GONE);
                    check4 = false;
                } else {
                    ivElectronics.setImageDrawable(getResources().getDrawable(R.drawable.up));
                    layoutElectronic.setVisibility(View.VISIBLE);
                    check4 = true;


                    layoutBoosterOne.setVisibility(View.GONE);
                    layoutBoosterTwo.setVisibility(View.GONE);
                    layoutBulk.setVisibility(View.GONE);
                    layoutDitonation.setVisibility(View.GONE);
                    layoutNonElectric.setVisibility(View.GONE);
                    layoutThermoTube.setVisibility(View.GONE);

                    ivBooster.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                    ivBulk.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                    ivCord.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                    ivNonElectronics.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                    ivThermo.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                }

                check1 = false;
                check2 = false;
                check3 = false;
                check5 = false;
                check6 = false;
            }
        });

        layoutNonElectronic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check5) {
                    ivNonElectronics.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                    layoutNonElectric.setVisibility(View.GONE);
                    check5 = false;
                } else {
                    ivNonElectronics.setImageDrawable(getResources().getDrawable(R.drawable.up));
                    layoutNonElectric.setVisibility(View.VISIBLE);
                    check5 = true;

                    layoutBoosterOne.setVisibility(View.GONE);
                    layoutBoosterTwo.setVisibility(View.GONE);
                    layoutBulk.setVisibility(View.GONE);
                    layoutDitonation.setVisibility(View.GONE);
                    layoutElectronic.setVisibility(View.GONE);
                    layoutThermoTube.setVisibility(View.GONE);

                    ivBooster.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                    ivBulk.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                    ivCord.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                    ivElectronics.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                    ivThermo.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                }

                check1 = false;
                check3 = false;
                check4 = false;
                check2 = false;
                check6 = false;
            }
        });

        layoutThermo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check6) {
                    ivThermo.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                    layoutThermoTube.setVisibility(View.GONE);
                    check6 = false;
                } else {
                    layoutThermoTube.setVisibility(View.VISIBLE);
                    check6 = true;


                    layoutBoosterOne.setVisibility(View.GONE);
                    layoutBoosterTwo.setVisibility(View.GONE);
                    layoutBulk.setVisibility(View.GONE);
                    layoutDitonation.setVisibility(View.GONE);
                    layoutElectronic.setVisibility(View.GONE);
                    layoutNonElectric.setVisibility(View.GONE);

                    ivThermo.setImageDrawable(getResources().getDrawable(R.drawable.up));
                    ivBooster.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                    ivBulk.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                    ivCord.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                    ivElectronics.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                    ivNonElectronics.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                }

                check1 = false;
                check3 = false;
                check4 = false;
                check5 = false;
                check2 = false;
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_tds_booster:
                bundle.putString("check_pdf", "apd_p_series_boosters.pdf");
                startActivity(new Intent(getActivity(), OpenPDFActivity.class).putExtras(bundle));
                break;
            case R.id.tv_sds_booster:
                bundle.putString("check_pdf", "apd_p_series_sds.pdf");
                startActivity(new Intent(getActivity(), OpenPDFActivity.class).putExtras(bundle));
                break;
            case R.id.tv_sds_britex:
                bundle.putString("check_pdf", "sds_britex.pdf");
                startActivity(new Intent(getActivity(), OpenPDFActivity.class).putExtras(bundle));
                break;
            case R.id.tv_tds_britex:
                bundle.putString("check_pdf", "tds_britex_cl.pdf");
                startActivity(new Intent(getActivity(), OpenPDFActivity.class).putExtras(bundle));
                break;
            case R.id.tv_ss_britex:
                bundle.putString("check_pdf", "tds_britex.pdf");
                startActivity(new Intent(getActivity(), OpenPDFActivity.class).putExtras(bundle));
                break;

            case R.id.tv_tds_britacord:
                bundle.putString("check_pdf", "tds_britacord.pdf");
                startActivity(new Intent(getActivity(), OpenPDFActivity.class).putExtras(bundle));
                break;
            case R.id.tv_sds_britacord:
                bundle.putString("check_pdf", "sds_britacord.pdf");
                startActivity(new Intent(getActivity(), OpenPDFActivity.class).putExtras(bundle));
                break;

            case R.id.tv_thermo_tube_sds:
                bundle.putString("check_pdf", "sds_thermo.pdf");
                startActivity(new Intent(getActivity(), OpenPDFActivity.class).putExtras(bundle));
                break;
            case R.id.tv_thermo_tube_tds:
                bundle.putString("check_pdf", "tds_thermo.pdf");
                startActivity(new Intent(getActivity(), OpenPDFActivity.class).putExtras(bundle));
                break;

            case R.id.tv_daveytronic_sp:
                bundle.putString("check_pdf", "tds_daveytronic_sp.pdf");
                startActivity(new Intent(getActivity(), OpenPDFActivity.class).putExtras(bundle));
                break;
            case R.id.tv_daveytronic_ug:
                bundle.putString("check_pdf", "tds_daveytronic_ug.pdf");
                startActivity(new Intent(getActivity(), OpenPDFActivity.class).putExtras(bundle));
                break;

            case R.id.tv_daveytronic_op:
                bundle.putString("check_pdf", "tds_daveytronic_op.pdf");
                startActivity(new Intent(getActivity(), OpenPDFActivity.class).putExtras(bundle));
                break;
            case R.id.tv_daveytronic_sds:
                bundle.putString("check_pdf", "sds_daveytronics_detonators.pdf");
                startActivity(new Intent(getActivity(), OpenPDFActivity.class).putExtras(bundle));
                break;

            case R.id.tv_daveynel_tds:
                bundle.putString("check_pdf", "tds_aveynel_downholes.pdf");
                startActivity(new Intent(getActivity(), OpenPDFActivity.class).putExtras(bundle));
                break;
            case R.id.tv_daveynel_surface_tds:
                bundle.putString("check_pdf", "tds_daveynel_surface.pdf");
                startActivity(new Intent(getActivity(), OpenPDFActivity.class).putExtras(bundle));
                break;

            case R.id.tv_daveynel_dual_tds:
                bundle.putString("check_pdf", "tds_daveyquick_dual.pdf");
                startActivity(new Intent(getActivity(), OpenPDFActivity.class).putExtras(bundle));
                break;
            case R.id.tv_daveynel_nonelectronic_sds:
                bundle.putString("check_pdf", "sds_daveytronics_detonators.pdf");
                startActivity(new Intent(getActivity(), OpenPDFActivity.class).putExtras(bundle));
                break;

            //bulk products
            case R.id.tv_an_prill_tds:
                bundle.putString("check_pdf", "tds_an_prill.pdf");
                startActivity(new Intent(getActivity(), OpenPDFActivity.class).putExtras(bundle));
                break;
            case R.id.tv_an_prill_sds:
                bundle.putString("check_pdf", "sds_an_prill.pdf");
                startActivity(new Intent(getActivity(), OpenPDFActivity.class).putExtras(bundle));
                break;
            case R.id.tv_anfomax_tds:
                bundle.putString("check_pdf", "tds_anfomax.pdf");
                startActivity(new Intent(getActivity(), OpenPDFActivity.class).putExtras(bundle));
                break;
            case R.id.tv_anfomax_sds:
                bundle.putString("check_pdf", "sds_anfomax.pdf");
                startActivity(new Intent(getActivity(), OpenPDFActivity.class).putExtras(bundle));
                break;
            case R.id.tv_ae_59_tds:
                bundle.putString("check_pdf", "tds_ae_59.pdf");
                startActivity(new Intent(getActivity(), OpenPDFActivity.class).putExtras(bundle));
                break;
            case R.id.tv_ae_59_sds:
                bundle.putString("check_pdf", "sds_ae_59.pdf");
                startActivity(new Intent(getActivity(), OpenPDFActivity.class).putExtras(bundle));
                break;
            case R.id.tv_emultex_tds:
                bundle.putString("check_pdf", "tds_emultex_ms.pdf");
                startActivity(new Intent(getActivity(), OpenPDFActivity.class).putExtras(bundle));
                break;
            case R.id.tv_emultex_sds:
                bundle.putString("check_pdf", "sds_emultex_ms.pdf");
                startActivity(new Intent(getActivity(), OpenPDFActivity.class).putExtras(bundle));
                break;
            case R.id.tv_dl_seriec_tds:
                bundle.putString("check_pdf", "tds_dl_series.pdf");
                startActivity(new Intent(getActivity(), OpenPDFActivity.class).putExtras(bundle));
                break;
            case R.id.tv_dl_seriec_sds:
                bundle.putString("check_pdf", "sds_dl_series.pdf");
                startActivity(new Intent(getActivity(), OpenPDFActivity.class).putExtras(bundle));
                break;
            case R.id.tv_emultex_dl_tds:
                bundle.putString("check_pdf", "tds_emultex_ms_dl.pdf");
                startActivity(new Intent(getActivity(), OpenPDFActivity.class).putExtras(bundle));
                break;
            case R.id.tv_emultex_dl_sds:
                bundle.putString("check_pdf", "sds_emultex_ms_dl.pdf");
                startActivity(new Intent(getActivity(), OpenPDFActivity.class).putExtras(bundle));
                break;
            case R.id.tv_blendex_tds:
                bundle.putString("check_pdf", "tds_blendex.pdf");
                startActivity(new Intent(getActivity(), OpenPDFActivity.class).putExtras(bundle));
                break;
            case R.id.tv_blendex_sds:
                bundle.putString("check_pdf", "tds_blendex_ms.pdf");
                startActivity(new Intent(getActivity(), OpenPDFActivity.class).putExtras(bundle));
                break;
            case R.id.tv_pirex_tds:
                bundle.putString("check_pdf", "tds_pirex.pdf");
                startActivity(new Intent(getActivity(), OpenPDFActivity.class).putExtras(bundle));
                break;
            case R.id.tv_pirex_ms_sds:
                bundle.putString("check_pdf", "tds_pirex_ms.pdf");
                startActivity(new Intent(getActivity(), OpenPDFActivity.class).putExtras(bundle));
                break;
        }
    }

    private void openBoosterDropDown(){
        ivBooster.setImageDrawable(getResources().getDrawable(R.drawable.up));
        layoutBoosterOne.setVisibility(View.VISIBLE);
        layoutBoosterTwo.setVisibility(View.VISIBLE);

        layoutBulk.setVisibility(View.GONE);
        layoutDitonation.setVisibility(View.GONE);
        layoutElectronic.setVisibility(View.GONE);
        layoutNonElectric.setVisibility(View.GONE);
        layoutThermoTube.setVisibility(View.GONE);

        ivBulk.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
        ivCord.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
        ivElectronics.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
        ivNonElectronics.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
        ivThermo.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
    }

}
