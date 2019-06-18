package com.techease.enaexblaster.views.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.techease.enaexblaster.R;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CalculatorByHoleFragment extends Fragment {
    View view;
    @BindView(R.id.et_diameter)
    EditText etDiameter;
    @BindView(R.id.et_density)
    EditText etDensity;
    @BindView(R.id.et_burden)
    EditText etBurden;
    @BindView(R.id.et_spacing)
    EditText etSpacing;
    @BindView(R.id.et_bench_height)
    EditText etBenchHeight;
    @BindView(R.id.et_subdrill)
    EditText etSubDrill;
    @BindView(R.id.et_stemming)
    EditText etStemming;
    @BindView(R.id.et_distance)
    EditText etDistance;
    @BindView(R.id.et_scalling)
    EditText etScallingFactor;
    @BindView(R.id.et_attenuation)
    EditText etAttenuationFactor;
    @BindView(R.id.tv_hole_lenght)
    TextView tvHoleLenght;
    @BindView(R.id.tv_volume_result)
    TextView tvVolume;
    @BindView(R.id.tv_lbs_hole)
    TextView tvLBSHole;
    @BindView(R.id.tv_pf)
    TextView tvPF;
    @BindView(R.id.tv_sdob)
    TextView tvSDOB;
    @BindView(R.id.tv_mic)
    TextView tvMic;
    @BindView(R.id.tv_sd)
    TextView tvSD;
    @BindView(R.id.tv_ppv)
    TextView tvPPV;
    @BindView(R.id.layout_option)
    RelativeLayout layoutOption;
    @BindView(R.id.calculator_layout)
    LinearLayout layoutMetricImperial;
    @BindView(R.id.btn_metric)
    Button btnMetric;
    @BindView(R.id.btn_imperail)
    Button btnImperial;

    private double density=0,diameter=0,burden=0,spacing=0,benchHeight=0,subDrill=0,stemming=0,
            distance=0,scallingFactor=0,attenuation=0;

    private double holeLenght,volume,LBSPerHole,PF,metricSDOB,imperialSDOB,MIC,SD,PPV,kgsPerHole;
    private boolean check = true;
    private boolean checkCalculator = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_calculator_by_hole, container, false);
        ButterKnife.bind(this,view);
        initViews();

        layoutOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check){
                    layoutMetricImperial.setVisibility(View.VISIBLE);
                    check = false;
                }
                else {
                    layoutMetricImperial.setVisibility(View.GONE);
                    check = true;
                }
            }
        });

        btnMetric.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                checkCalculator = true;
                tvLBSHole.setText("Kgs per Hole");
                btnImperial.setBackgroundColor(getActivity().getColor(R.color.grey));
                btnMetric.setBackgroundColor(getActivity().getColor(R.color.silver));
            }
        });

        btnImperial.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                checkCalculator = false;
                tvLBSHole.setText("Lbs per Hole");
                btnImperial.setBackgroundColor(getActivity().getColor(R.color.silver));
                btnMetric.setBackgroundColor(getActivity().getColor(R.color.grey));
            }
        });

        return view;
    }

    private void initViews(){

        etDiameter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().equals("") || s == null){
                   diameter = 0;
                }
                else {
                    try {
                        diameter = Double.parseDouble(s.toString().replace(',', '.'));
                        if(checkCalculator){
                            metricCalculation();
                        }
                        else {
                            calculation();
                        }
                    } catch (NumberFormatException e) {
                        //Error
                    }

                }

            }
        });

        etDensity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(s.toString().equals("")){
                    density = 0;
                }
                else {
                    try {
                        density = Double.parseDouble(s.toString().replace(',', '.'));
                        if(checkCalculator){
                            metricCalculation();
                        }
                        else {
                            calculation();
                        }
                    } catch (NumberFormatException e) {
                        //Error
                    }
                }
            }
        });

        etBurden.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().equals("")){
                    burden = 0;
                }
                else {
                    try {
                        burden = Double.parseDouble(s.toString().replace(',', '.'));
                        if(checkCalculator){
                            metricCalculation();
                        }
                        else {
                            calculation();
                        }
                    } catch (NumberFormatException e) {
                        //Error
                    }
                }
            }
        });

        etSpacing.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().equals("")){
                    spacing = 0;
                }
                else {
                    try {
                        spacing = Double.parseDouble(s.toString().replace(',', '.'));
                        if(checkCalculator){
                            metricCalculation();
                        }
                        else {
                            calculation();
                        }
                    } catch (NumberFormatException e) {
                        //Error
                    }
                }
            }
        });

        etBenchHeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().equals("")){
                    benchHeight = 0;
                }
                else {
                    try {
                        benchHeight = Double.parseDouble(s.toString().replace(',', '.'));
                        if(checkCalculator){
                            metricCalculation();
                        }
                        else {
                            calculation();
                        }
                    } catch (NumberFormatException e) {
                        //Error
                    }
                }
            }
        });

        etSubDrill.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(s.toString().equals("")){
                    subDrill = 0;
                }
                else {
                    try {
                        subDrill = Double.parseDouble(s.toString().replace(',', '.'));
                        if(checkCalculator){
                            metricCalculation();
                        }
                        else {
                            calculation();
                        }
                    } catch (NumberFormatException e) {
                        //Error
                    }
                }
            }
        });

        etStemming.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().equals("")){
                    stemming = 0;
                }
                else {
                    try {
                        stemming = Double.parseDouble(s.toString().replace(',', '.'));
                        if(checkCalculator){
                            metricCalculation();
                        }
                        else {
                            calculation();
                        }
                    } catch (NumberFormatException e) {
                        //Error
                    }
                }
            }
        });

        etDistance.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().equals("")){
                    distance = 0;
                }
                else {
                    try {
                        distance = Double.parseDouble(s.toString().replace(',', '.'));
                        if(checkCalculator){
                            metricCalculation();
                        }
                        else {
                            calculation();
                        }
                    } catch (NumberFormatException e) {
                        //Error
                    }
                }
            }
        });

        etScallingFactor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().equals("")){
                    scallingFactor = 0;
                }
                else {
                    try {
                        scallingFactor = Double.parseDouble(s.toString().replace(',', '.'));
                        if(checkCalculator){
                            metricCalculation();
                        }
                        else {
                            calculation();
                        }
                    } catch (NumberFormatException e) {
                        //Error
                    }
                }
            }
        });

        etAttenuationFactor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().equals("")){
                    attenuation = 0;
                }
                else {
                    try {
                        attenuation = Double.parseDouble(s.toString().replace(',', '.'));
                        if(checkCalculator){
                            metricCalculation();
                        }
                        else {
                            calculation();
                        }
                    } catch (NumberFormatException e) {
                        //Error
                    }
                }
            }
        });



    }

    private void calculation() {

        holeLenght = benchHeight + subDrill;

        volume = (burden*spacing*benchHeight)/27;


        LBSPerHole = ((density*62.4) * Math.PI * (Math.pow(diameter/24,2)) * benchHeight+subDrill-stemming);

        PF = LBSPerHole/volume;

        metricSDOB = ((stemming+((5*diameter)/12))) / ((density*62.4) * Math.PI * Math.pow((diameter/24),2)
                * Math.pow((10*diameter)/12,0.3333));

        SD = distance / Math.pow(LBSPerHole,0.5);

        PPV = scallingFactor * Math.pow(SD,attenuation);


        tvHoleLenght.setText(String.format("%.0f", Double.valueOf(holeLenght)));
        tvVolume.setText(String.format("%.2f", Double.valueOf(volume)));
        tvLBSHole.setText(String.format("%.0f", Double.valueOf(LBSPerHole)));
        tvPF.setText(String.format("%.2f", Double.valueOf(PF)));
        tvSDOB.setText(String.format("%.2f", Double.valueOf(metricSDOB)));
        tvMic.setText(String.format("%.0f", Double.valueOf(LBSPerHole)));
        tvSD.setText(String.format("%.1f", Double.valueOf(SD)));
        tvPPV.setText(String.format("%.2f", Double.valueOf(PPV)));

    }

    private void metricCalculation() {

        holeLenght = benchHeight + subDrill;

        volume = (burden*spacing*benchHeight);


        kgsPerHole = ((density/1000) * Math.PI * (Math.pow(diameter/2,2)) * benchHeight+subDrill-stemming);

        PF = kgsPerHole/volume;

        imperialSDOB = ((stemming+((5*(diameter/1000))))) / ((density/1000) * Math.PI * Math.pow((diameter/2),2))*
                Math.pow((10*(diameter/1000)),0.3333);

        SD = distance / Math.pow(kgsPerHole,0.5);

        PPV = scallingFactor * Math.pow(SD,attenuation);


        tvHoleLenght.setText(String.format("%.0f", Double.valueOf(holeLenght)));
        tvVolume.setText(String.format("%.2f", Double.valueOf(volume)));
        tvLBSHole.setText(String.format("%.0f", Double.valueOf(kgsPerHole)));
        tvPF.setText(String.format("%.2f", Double.valueOf(PF)));
        tvSDOB.setText(String.format("%.2f", Double.valueOf(imperialSDOB)));
        tvMic.setText(String.format("%.0f", Double.valueOf(kgsPerHole)));
        tvSD.setText(String.format("%.1f", Double.valueOf(SD)));
        tvPPV.setText(String.format("%.2f", Double.valueOf(PPV)));

    }

}
