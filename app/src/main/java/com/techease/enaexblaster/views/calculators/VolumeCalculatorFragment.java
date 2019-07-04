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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.techease.enaexblaster.R;
import com.techease.enaexblaster.utilities.GeneralUtils;
import com.techease.enaexblaster.views.fragments.CalculatorsHomeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


public class VolumeCalculatorFragment extends Fragment {
    View view;
    @BindView(R.id.iv_arrow)
    ImageView ivArrow;
    @BindView(R.id.et_volume_burden)
    EditText etBurden;
    @BindView(R.id.et_volume_spacing)
    EditText etSpacing;
    @BindView(R.id.et_average_depth)
    EditText etAverageDepth;
    @BindView(R.id.et_rock_density)
    EditText etRockDensity;
    @BindView(R.id.et_no_holes)
    EditText etNoHoles;


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
    @BindView(R.id.layout_rock_density)
    RelativeLayout layoutRockDensity;
    @BindView(R.id.btn_weight)
    Button btnWeight;
    @BindView(R.id.btn_volume)
    Button btnVolume;

    @BindView(R.id.tv_burden_unit)
    TextView tvBurdenUnit;
    @BindView(R.id.tv_spacing_unit)
    TextView tvSpacingUnit;
    @BindView(R.id.tv_depth_unit)
    TextView tvDepthUnit;
    @BindView(R.id.tv_density_unit)
    TextView tvDensityUnit;

    private double burden = 0, spacing = 0, averageDepth = 0,rockDensity=0,noOfHOles=0;
    private boolean check = true;
    private boolean checkCalculator = true,checkWeight = false;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_volume_calculator, container, false);
        ButterKnife.bind(this,view);

        checkCalculator  = GeneralUtils.getSharedPreferences(getActivity()).getBoolean("check_unit",true);


        if(checkCalculator){
            btnImperial.setBackgroundColor(getActivity().getColor(R.color.grey));
            btnMetric.setBackgroundColor(getActivity().getColor(R.color.silver));
            metricUnits();
        }
        else {
            btnImperial.setBackgroundColor(getActivity().getColor(R.color.silver));
            btnMetric.setBackgroundColor(getActivity().getColor(R.color.grey));
            imperialUnits();
        }
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

        btnVolume.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                checkWeight = false;
                layoutRockDensity.setVisibility(View.GONE);
                btnVolume.setBackgroundColor(getActivity().getColor(R.color.silver));
                btnWeight.setBackgroundColor(getActivity().getColor(R.color.grey));
                metricCalculation();
                imperialCalculation();
            }
        });

        btnWeight.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                checkWeight = true;
                layoutRockDensity.setVisibility(View.VISIBLE);
                btnVolume.setBackgroundColor(getActivity().getColor(R.color.grey));
                btnWeight.setBackgroundColor(getActivity().getColor(R.color.silver));
                metricCalculation();
                imperialCalculation();
            }
        });

        layoutOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check) {
                    ivArrow.setImageDrawable(getResources().getDrawable(R.drawable.up));
                    layoutMetricImperial.setVisibility(View.VISIBLE);
                    check = false;
                } else {
                    ivArrow.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
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
                btnImperial.setBackgroundColor(getActivity().getColor(R.color.grey));
                btnMetric.setBackgroundColor(getActivity().getColor(R.color.silver));
                metricCalculation();
                metricUnits();
            }
        });

        btnImperial.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                checkCalculator = false;
                btnImperial.setBackgroundColor(getActivity().getColor(R.color.silver));
                btnMetric.setBackgroundColor(getActivity().getColor(R.color.grey));
                imperialCalculation();
                imperialUnits();
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

        etAverageDepth.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("")) {
                    averageDepth = 0;
                } else {
                    try {
                        averageDepth = Double.parseDouble(s.toString().replace(',', '.'));
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

        etRockDensity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("")) {
                    rockDensity = 0;
                } else {
                    try {
                        rockDensity = Double.parseDouble(s.toString().replace(',', '.'));
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

        etNoHoles.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("")) {
                    noOfHOles = 0;
                } else {
                    try {
                        noOfHOles = Double.parseDouble(s.toString().replace(',', '.'));
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

        if(checkWeight){ //if weight is selected
            double volume = ((burden * spacing * averageDepth) /27) * rockDensity * 0.841 * noOfHOles;
            tvVolume.setText(String.format("%.0f", Double.valueOf(volume)) +" tons");
        }
        else {  //if volume if selected
            double volume = ((burden * spacing * averageDepth) /27) * noOfHOles;
            tvVolume.setText(String.format("%.0f", Double.valueOf(volume)) + " yd3");
        }


    }

    private void metricCalculation() {
        if(checkWeight){
            double volume = (burden * spacing * averageDepth) * rockDensity * noOfHOles;
            tvVolume.setText(String.format("%.0f", Double.valueOf(volume)) + " tonnes");
        }
        else {
            double volume = (burden * spacing * averageDepth) * noOfHOles;
            tvVolume.setText(String.format("%.0f", Double.valueOf(volume)) + " m3");
        }
    }

    private void metricUnits(){
        tvBurdenUnit.setText("m");
        tvSpacingUnit.setText("m");
        tvDepthUnit.setText("m");
        tvDensityUnit.setText("g/cc");
    }

    private void imperialUnits(){
        tvBurdenUnit.setText("ft");
        tvSpacingUnit.setText("ft");
        tvDepthUnit.setText("ft");
        tvDensityUnit.setText("g/cc");
    }
}
