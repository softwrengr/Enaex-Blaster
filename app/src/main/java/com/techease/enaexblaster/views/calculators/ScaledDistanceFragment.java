package com.techease.enaexblaster.views.calculators;

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

import com.techease.enaexblaster.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ScaledDistanceFragment extends Fragment {
    View view;
    @BindView(R.id.et_distance)
    EditText etDistance;
    @BindView(R.id.et_mic)
    EditText etMIC;
    @BindView(R.id.tv_distance_unit)
    TextView tvDistanceUnit;
    @BindView(R.id.tv_mic_unit)
    TextView tvMicUnit;

    @BindView(R.id.layout_sclaed_option)
    RelativeLayout layoutOption;
    @BindView(R.id.scaled_layout)
    LinearLayout layoutMetricImperial;
    @BindView(R.id.btn_scale_metric)
    Button btnMetric;
    @BindView(R.id.btn_scale_imperial)
    Button btnImperial;
    @BindView(R.id.tv_result)
    TextView tvResult;

    private double distance = 0, mic = 0;
    private boolean check = true;
    private boolean checkCalculator = true;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_scaled_distance, container, false);
        ButterKnife.bind(this,view);
        initViews();
        return  view;
    }

    private void  initViews(){
        layoutOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check) {
                    layoutMetricImperial.setVisibility(View.VISIBLE);
                    check = false;
                } else {
                    layoutMetricImperial.setVisibility(View.GONE);
                    check = true;
                }
            }
        });

        btnMetric.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                tvDistanceUnit.setText("m");
                tvMicUnit.setText("m");


                checkCalculator = true;
                btnImperial.setBackgroundColor(getActivity().getColor(R.color.grey));
                btnMetric.setBackgroundColor(getActivity().getColor(R.color.silver));
            }
        });

        btnImperial.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                tvDistanceUnit.setText("m");
                tvMicUnit.setText("m");
                checkCalculator = false;
                btnImperial.setBackgroundColor(getActivity().getColor(R.color.silver));
                btnMetric.setBackgroundColor(getActivity().getColor(R.color.grey));
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
                if (s.toString().equals("")) {
                    distance = 0;
                } else {
                    try {
                        distance = Double.parseDouble(s.toString().replace(',', '.'));
                        if (checkCalculator) {
                            metricCalculation();
                        } else {
                            imperialCalculation();
                        }
                    } catch (NumberFormatException e) {
                        //Error
                    }
                }
            }
        });

        etMIC.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("")) {
                    mic = 0;
                } else {
                    try {
                        mic = Double.parseDouble(s.toString().replace(',', '.'));
                        if (checkCalculator) {
                            metricCalculation();
                        } else {
                            imperialCalculation();
                        }
                    } catch (NumberFormatException e) {
                        //Error
                    }
                }
            }
        });


    }

    private void imperialCalculation() {
        double SD;
        if (distance == 0) {
            SD = 0;
        } else {
            SD = (distance / Math.pow(mic, 0.5));
        }

        tvResult.setText(String.format("%.2f", SD));


    }

    private void metricCalculation() {
        double SD;
        if (distance == 0) {
            SD = 0;
        } else {
            SD = (distance / Math.pow(mic, 0.5));
        }

        tvResult.setText(String.format("%.2f", SD));

    }
}