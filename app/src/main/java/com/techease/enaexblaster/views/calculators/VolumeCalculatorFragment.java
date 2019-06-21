package com.techease.enaexblaster.views.calculators;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.techease.enaexblaster.R;
import com.techease.enaexblaster.utilities.GeneralUtils;
import com.techease.enaexblaster.views.fragments.CalculatorsHomeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


public class VolumeCalculatorFragment extends Fragment {
    View view;
    @BindView(R.id.et_volume_burden)
    EditText etBurden;
    @BindView(R.id.et_volume_spacing)
    EditText etSpacing;
    @BindView(R.id.et_volume_bench_height)
    EditText etBenchHeight;
    @BindView(R.id.tv_burden_unit)
    TextView tvBurdenUnit;
    @BindView(R.id.tv_height_unit)
    TextView tvHeightUnit;
    @BindView(R.id.tv_spacing_unit)
    TextView tvSpacingUnit;

    @BindView(R.id.layout_volume_option)
    RelativeLayout layoutOption;
    @BindView(R.id.volume_layout)
    LinearLayout layoutMetricImperial;
    @BindView(R.id.btn_volume_metric)
    Button btnMetric;
    @BindView(R.id.btn_volume_imperial)
    Button btnImperial;
    @BindView(R.id.tv_volume_result)
    TextView tvVolume;
    @BindView(R.id.iv_back)
    ImageView ivBack;

    private double burden = 0, spacing = 0, benchHeight = 0;
    private boolean check = true;
    private boolean checkCalculator = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_volume_calculator, container, false);
        ButterKnife.bind(this,view);
        initViews();
        return view;
    }

    private void initViews() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.connectFragment(getActivity(),new CalculatorsHomeFragment());
            }
        });


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
                tvBurdenUnit.setText("m");
                tvSpacingUnit.setText("m");
                tvHeightUnit.setText("m");


                checkCalculator = true;
                btnImperial.setBackgroundColor(getActivity().getColor(R.color.grey));
                btnMetric.setBackgroundColor(getActivity().getColor(R.color.silver));
            }
        });

        btnImperial.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                tvBurdenUnit.setText("ft");
                tvSpacingUnit.setText("ft");
                tvHeightUnit.setText("ft");
                checkCalculator = false;
                btnImperial.setBackgroundColor(getActivity().getColor(R.color.silver));
                btnMetric.setBackgroundColor(getActivity().getColor(R.color.grey));
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
                if (s.toString().equals("")) {
                    burden = 0;
                } else {
                    try {
                        burden = Double.parseDouble(s.toString().replace(',', '.'));
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

        etSpacing.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("")) {
                    spacing = 0;
                } else {
                    try {
                        spacing = Double.parseDouble(s.toString().replace(',', '.'));
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

        etBenchHeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("")) {
                    benchHeight = 0;
                } else {
                    try {
                        benchHeight = Double.parseDouble(s.toString().replace(',', '.'));
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
        double volume = (burden * spacing * benchHeight) / 27;

        tvVolume.setText(String.format("%.2f", Double.valueOf(volume)));


    }

    private void metricCalculation() {
        double volume = (burden * spacing * benchHeight);

        tvVolume.setText(String.format("%.2f", Double.valueOf(volume)));

    }

}
