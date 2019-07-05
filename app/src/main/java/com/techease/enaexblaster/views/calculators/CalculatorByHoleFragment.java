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
import android.widget.Toast;

import com.techease.enaexblaster.R;
import com.techease.enaexblaster.utilities.GeneralUtils;
import com.techease.enaexblaster.views.fragments.CalculatorsHomeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CalculatorByHoleFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {
    View view;
    @BindView(R.id.et_diameter)
    EditText etDiameter;
    @BindView(R.id.et_density)
    EditText etDensity;
    @BindView(R.id.et_burden)
    EditText etBurden;
    @BindView(R.id.et_spacing)
    EditText etSpacing;
    @BindView(R.id.et_hole_height)
    EditText etHoleLenght;
    @BindView(R.id.et_rock_density)
    EditText etRockDensity;
    @BindView(R.id.et_stemming)
    EditText etStemming;
    @BindView(R.id.et_distance)
    EditText etDistance;
    @BindView(R.id.et_scalling)
    EditText etScallingFactor;
    @BindView(R.id.et_attenuation)
    EditText etAttenuationFactor;
    @BindView(R.id.tv_volume_result)
    TextView tvVolume;
    @BindView(R.id.tv_lbs_hole)
    TextView tvLBSHole;
    @BindView(R.id.tv_lbs)
    TextView tvLBS;
    @BindView(R.id.tv_pf)
    TextView tvPF;
    @BindView(R.id.tv_sdob)
    TextView tvSDOB;
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
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.switch_vibration)
    Switch switchHole;
    @BindView(R.id.btn_volume)
    Button btnVolume;
    @BindView(R.id.btn_weight)
    Button btnWeight;
    @BindView(R.id.tv_volume)
    TextView tvVolumeWeight;

    @BindView(R.id.layout_distance)
    RelativeLayout layoutDistance;
    @BindView(R.id.layout_scal)
    RelativeLayout layoutScaling;
    @BindView(R.id.layout_atten)
    RelativeLayout layoutAttenuation;
    @BindView(R.id.layout_ppv)
    LinearLayout layoutPPV;
    @BindView(R.id.layout_sd)
    LinearLayout layoutSD;
    @BindView(R.id.rock_layout)
    RelativeLayout layoutRock;
    @BindView(R.id.iv_arrow)
    ImageView ivArrow;

    @BindView(R.id.iv_graphics)
    ImageView ivGraphics;

    @BindView(R.id.hole_diameter_unit)
    TextView tvDiameterUnit;
    @BindView(R.id.hole_exp_density_unit)
    TextView tvExplosiveDensityUnit;
    @BindView(R.id.hole_burden_unit)
    TextView tvBurdenrUnit;
    @BindView(R.id.hole_spacing_unit)
    TextView tvSpacingUnit;
    @BindView(R.id.hole_length_unit)
    TextView tvHoleLengthUnit;
    @BindView(R.id.hole_stem_unit)
    TextView tvStemLengthrUnit;
    @BindView(R.id.hole_rock_unit)
    TextView tvRockDensityUnit;
    @BindView(R.id.hole_distance_unit)
    TextView tvDistanceUnit;




    private double density = 0, diameter = 270, burden = 0, spacing = 0, holeLenght = 0, rockDensity = 0, stemming = 0,
            distance = 0, scallingFactor = 160, attenuation = -1.6;

    private double metricResult,imperailResult;

    private boolean check = true;
    private boolean checkCalculator = true;
    private boolean checkVolume = true;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_calculator_by_hole, container, false);
        ButterKnife.bind(this, view);

        checkCalculator  = GeneralUtils.getSharedPreferences(getActivity()).getBoolean("check_unit",true);

        if(checkCalculator){
            tvLBS.setText("Kgs per Hole");
            etDiameter.setText("270");
            diameter = 270;
            btnImperial.setBackgroundColor(getActivity().getColor(R.color.grey));
            btnMetric.setBackgroundColor(getActivity().getColor(R.color.silver));
            metrciUnits();
        }
        else {
            tvLBS.setText("Lbs per Hole");
            etDiameter.setText("10.625");
            diameter = 10.625;
            btnImperial.setBackgroundColor(getActivity().getColor(R.color.silver));
            btnMetric.setBackgroundColor(getActivity().getColor(R.color.grey));
            imperialUnits();
        }

        initViews();

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
                tvLBS.setText("Kgs per Hole");
                etDiameter.setText("270");
                diameter = 270;
                btnImperial.setBackgroundColor(getActivity().getColor(R.color.grey));
                btnMetric.setBackgroundColor(getActivity().getColor(R.color.silver));
                metricCalculation();
                metrciUnits();
            }
        });

        btnImperial.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                checkCalculator = false;
                tvLBS.setText("Lbs per Hole");
                etDiameter.setText("10.625");
                diameter = 10.625;
                btnImperial.setBackgroundColor(getActivity().getColor(R.color.silver));
                btnMetric.setBackgroundColor(getActivity().getColor(R.color.grey));
                calculation();
                imperialUnits();


            }
        });

        btnVolume.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                checkVolume = true;
                tvVolumeWeight.setText("Volume");
                layoutRock.setVisibility(View.GONE);
                btnWeight.setBackgroundColor(getActivity().getColor(R.color.grey));
                btnVolume.setBackgroundColor(getActivity().getColor(R.color.silver));

                calculation();
                metricCalculation();
            }
        });

        btnWeight.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                checkVolume = false;
                tvVolumeWeight.setText("Weight");
                layoutRock.setVisibility(View.VISIBLE);
                btnWeight.setBackgroundColor(getActivity().getColor(R.color.silver));
                btnVolume.setBackgroundColor(getActivity().getColor(R.color.grey));

                calculation();
                metricCalculation();
            }
        });

        switchHole.setOnCheckedChangeListener(this);

        return view;
    }

    private void initViews() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.connectFragment(getActivity(), new CalculatorsHomeFragment());
            }
        });

        etDiameter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("") || s == null) {
                    diameter = 0;
                } else {
                    try {
                        diameter = Double.parseDouble(s.toString().replace(',', '.'));
                        if (checkCalculator) {
                            metricCalculation();
                        } else {
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

                if (s.toString().equals("")) {
                    density = 0;
                } else {
                    try {
                        density = Double.parseDouble(s.toString().replace(',', '.'));
                        if (checkCalculator) {
                            metricCalculation();
                        } else {
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
                if (s.toString().equals("")) {
                    burden = 0;
                } else {
                    try {
                        burden = Double.parseDouble(s.toString().replace(',', '.'));
                        if (checkCalculator) {
                            metricCalculation();
                        } else {
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
                if (s.toString().equals("")) {
                    spacing = 0;
                } else {
                    try {
                        spacing = Double.parseDouble(s.toString().replace(',', '.'));
                        if (checkCalculator) {
                            metricCalculation();
                        } else {
                            calculation();
                        }
                    } catch (NumberFormatException e) {
                        //Error
                    }
                }
            }
        });

        etHoleLenght.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("")) {
                    holeLenght = 0;
                } else {
                    try {
                        holeLenght = Double.parseDouble(s.toString().replace(',', '.'));
                        if (checkCalculator) {
                            metricCalculation();
                        } else {
                            calculation();
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
                if (s.toString().equals("")) {
                    stemming = 0;
                } else {
                    try {
                        stemming = Double.parseDouble(s.toString().replace(',', '.'));
                        if (checkCalculator) {
                            metricCalculation();
                        } else {
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
                if (s.toString().equals("")) {
                    distance = 0;
                } else {
                    try {
                        distance = Double.parseDouble(s.toString().replace(',', '.'));
                        if (checkCalculator) {
                            metricCalculation();
                        } else {
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
                if (s.toString().equals("")) {
                    scallingFactor = 0;
                } else {
                    try {
                        scallingFactor = Double.parseDouble(s.toString().replace(',', '.'));
                        if (checkCalculator) {
                            metricCalculation();
                        } else {
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
                if (s.toString().equals("")) {
                    attenuation = 0;
                } else {
                    try {
                        attenuation = Double.parseDouble(s.toString().replace(',', '.'));
                        if (checkCalculator) {
                            metricCalculation();
                        } else {
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
        double volume,weight,explosivePerHole,PFVolume,PFWeight,sdob,SD,PPV,d,Wc,chargeUnit;

        volume = (burden * spacing * holeLenght) / 27;
        weight = ((burden * spacing * holeLenght) / 27) * rockDensity * 0.841;
        explosivePerHole = (density * 62.4) * (Math.PI * (Math.pow((diameter / 24), 2))) * (holeLenght - stemming);

        PFVolume = explosivePerHole / volume;
        PFWeight = explosivePerHole / weight;

        d = stemming + (5 * (diameter/12));
        chargeUnit = (density * 62.4) * (Math.PI * Math.pow((diameter / 24), 2));
        Wc =  chargeUnit * (10 *  (diameter/12));
        sdob = d / Math.pow(Wc,0.3333);
        SD = distance / Math.sqrt(explosivePerHole);
        PPV =  scallingFactor * (Math.pow(SD, attenuation));

        imperailResult = sdob;
        checkGraphics();

        if(checkVolume){
            tvVolume.setText(String.format("%.0f", Double.valueOf(volume)) + " yd³");
            tvLBSHole.setText(String.format("%.0f", Double.valueOf(explosivePerHole))+ " lb");
            tvPF.setText(String.format("%.2f", Double.valueOf(PFVolume))+ " lb/yd³");
            tvSDOB.setText(String.format("%.1f", Double.valueOf(sdob))+ " ft∛lb");
            tvSD.setText(String.format("%.1f", Double.valueOf(SD))+ " ft√lb");
            tvPPV.setText(String.format("%.2f", Double.valueOf(PPV))+ " in/s");
        }
        else {
            tvVolume.setText(String.format("%.0f", Double.valueOf(weight))+ " ton");
            tvLBSHole.setText(String.format("%.0f", Double.valueOf(explosivePerHole))+ " lb");
            tvPF.setText(String.format("%.2f", Double.valueOf(PFWeight))+ " lb/ton");
            tvSDOB.setText(String.format("%.1f", Double.valueOf(sdob))+ " ft∛lb");
            tvSD.setText(String.format("%.1f", Double.valueOf(SD))+ " ft√lb");
            tvPPV.setText(String.format("%.2f", Double.valueOf(PPV))+ " in/s");
        }

    }

    private void metricCalculation() {
        double volume,weight,explosivePerHole,PFVolume,PFWeight,sdob,SD,PPV,d,Wc,chargeUnit;

        volume = (burden * spacing * holeLenght);
        weight = (burden * spacing * holeLenght) * rockDensity;
        explosivePerHole = (density / 1000) * (Math.PI * (Math.pow((diameter / 2), 2))) * (holeLenght - stemming);

        PFVolume = explosivePerHole / volume;
        PFWeight = explosivePerHole / weight;

        d = stemming + (5 * (diameter/1000));
        chargeUnit = (density /1000) * (Math.PI * Math.pow((diameter / 2), 2));
        Wc =  chargeUnit * (10 *  (diameter/1000));
        sdob = d / Math.pow(Wc,0.3333);
        SD = distance / Math.sqrt(explosivePerHole);
        PPV =  scallingFactor * (Math.pow(SD, attenuation));

        metricResult = sdob;
        checkGraphics();

        if(checkVolume){
            tvVolume.setText(String.format("%.0f", Double.valueOf(volume)) + " m³");
            tvLBSHole.setText(String.format("%.0f", Double.valueOf(explosivePerHole))+ " kg");
            tvPF.setText(String.format("%.2f", Double.valueOf(PFVolume)) + " kg/m³");
            tvSDOB.setText(String.format("%.2f", Double.valueOf(sdob)) + " m∛kg");
            tvSD.setText(String.format("%.1f", Double.valueOf(SD)) + " m/√kg");
            tvPPV.setText(String.format("%.2f", Double.valueOf(PPV)) + " mm/s");
        }
        else {
            tvVolume.setText(String.format("%.0f", Double.valueOf(weight))+ " tonne");
            tvLBSHole.setText(String.format("%.0f", Double.valueOf(explosivePerHole))+ " kg");
            tvPF.setText(String.format("%.2f", Double.valueOf(PFWeight))+ " kg/tonne");
            tvSDOB.setText(String.format("%.2f", Double.valueOf(sdob))+ " m∛kg");
            tvSD.setText(String.format("%.1f", Double.valueOf(SD))+ " m/√kg");
            tvPPV.setText(String.format("%.2f", Double.valueOf(PPV))+ " mm/s");
        }
    }

    private void  metrciUnits(){
        tvDiameterUnit.setText("mm");
        tvExplosiveDensityUnit.setText("g/cc");
        tvBurdenrUnit.setText("m");
        tvSpacingUnit.setText("m");
        tvHoleLengthUnit.setText("m");
        tvStemLengthrUnit.setText("m");
        tvRockDensityUnit.setText("g/cc");
        tvDistanceUnit.setText("m");
    }

    private void imperialUnits(){
        tvDiameterUnit.setText("in");
        tvExplosiveDensityUnit.setText("g/cc");
        tvBurdenrUnit.setText("ft");
        tvSpacingUnit.setText("ft");
        tvHoleLengthUnit.setText("ft");
        tvStemLengthrUnit.setText("ft");
        tvRockDensityUnit.setText("g/cc");
        tvDistanceUnit.setText("ft");
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            layoutDistance.setVisibility(View.VISIBLE);
            layoutScaling.setVisibility(View.VISIBLE);
            layoutAttenuation.setVisibility(View.VISIBLE);
            layoutPPV.setVisibility(View.VISIBLE);
            layoutSD.setVisibility(View.VISIBLE);
        } else {
            layoutDistance.setVisibility(View.GONE);
            layoutScaling.setVisibility(View.GONE);
            layoutAttenuation.setVisibility(View.GONE);
            layoutPPV.setVisibility(View.GONE);
            layoutSD.setVisibility(View.GONE);
        }
    }

    private void checkGraphics() {

        if(checkCalculator){    //checking graphics for metric calculator
            if (metricResult >= 0 && metricResult <= 0.6) {
                ivGraphics.setImageDrawable(getResources().getDrawable(R.drawable.graphic1));
            } else if (metricResult >= 0.6 && metricResult <= 0.9) {
                ivGraphics.setImageDrawable(getResources().getDrawable(R.drawable.graphics2));
            }
            else if (metricResult >= 0.91 && metricResult <= 1.42) {
                ivGraphics.setImageDrawable(getResources().getDrawable(R.drawable.graphics3));
            }
            else if (metricResult >= 1.43 && metricResult <= 1.82) {
                ivGraphics.setImageDrawable(getResources().getDrawable(R.drawable.graphics4));
            }
            else if (metricResult >= 1.83 && metricResult <= 2.40) {
                ivGraphics.setImageDrawable(getResources().getDrawable(R.drawable.graphics5));
            }
            else if (metricResult >= 2.41) {
                ivGraphics.setImageDrawable(getResources().getDrawable(R.drawable.graphics6));
            }
        }
        else {     //checking graphics for imperial calculator
            if (imperailResult >= 0 && imperailResult <= 1.5) {
                ivGraphics.setImageDrawable(getResources().getDrawable(R.drawable.graphic1));
            } else if (imperailResult >= 1.6 && imperailResult <= 2.2) {
                ivGraphics.setImageDrawable(getResources().getDrawable(R.drawable.graphics2));
            }
            else if (imperailResult >= 2.3 && imperailResult <= 3.5) {
                ivGraphics.setImageDrawable(getResources().getDrawable(R.drawable.graphics3));
            }
            else if (imperailResult >= 3.6 && imperailResult <= 4.5) {
                ivGraphics.setImageDrawable(getResources().getDrawable(R.drawable.graphics4));
            }
            else if (imperailResult >= 4.6 && imperailResult <= 6.0) {
                ivGraphics.setImageDrawable(getResources().getDrawable(R.drawable.graphics5));
            }
            else if (imperailResult >= 6.1) {
                ivGraphics.setImageDrawable(getResources().getDrawable(R.drawable.graphics6));
            }
        }
    }
}
