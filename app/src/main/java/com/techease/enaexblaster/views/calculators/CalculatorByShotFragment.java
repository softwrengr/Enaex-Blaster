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


public class CalculatorByShotFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {
    View view;

    @BindView(R.id.iv_arrow)
    ImageView ivArrow;
    @BindView(R.id.et_shot_rows)
    EditText etNoOfRows;
    @BindView(R.id.et_shot_holes)
    EditText etShotHoles;
    @BindView(R.id.et_holePerRow)
    EditText etHolePerRow;
    @BindView(R.id.et_shot_diameter)
    EditText etDiameter;
    @BindView(R.id.et_shot_density)
    EditText etDensity;
    @BindView(R.id.et_shot_burden)
    EditText etBurden;
    @BindView(R.id.et_shot_spacing)
    EditText etSpacing;
    @BindView(R.id.et_shot_bench_height)
    EditText etBenchHeight;
    @BindView(R.id.et_shot_subdrill)
    EditText etSubDrill;
    @BindView(R.id.et_shot_stemming)
    EditText etStemming;
    @BindView(R.id.et_rock_density)
    EditText etRockDensity;
    @BindView(R.id.et_shot_hole_ms)
    EditText etShotHoleMs;
    @BindView(R.id.et_shot_distance)
    EditText etDistance;
    @BindView(R.id.et_shot_scalling)
    EditText etScallingFactor;
    @BindView(R.id.et_shot_attenuation)
    EditText etAttenuationFactor;
    @BindView(R.id.tv_shot_lenght)
    TextView tvShotLenght;
    @BindView(R.id.tv_shot_drill_lenght)
    TextView tvShotDrillLenght;
    @BindView(R.id.tv_total_holes)
    TextView tvTotalHoles;
    @BindView(R.id.tv_shot_volume_per_hole)
    TextView tvVolumePerHole;
    @BindView(R.id.tv_shot_volume)
    TextView tvVolume;
    @BindView(R.id.tv_explosive_per_hole)
    TextView tvExplosivePerHole;
    @BindView(R.id.tv_total_explosive)
    TextView tvTotalExplosive;
    @BindView(R.id.tv_shot_pf)
    TextView tvPF;
    @BindView(R.id.tv_shot_sdob)
    TextView tvSDOB;
    @BindView(R.id.tv_shot_mic)
    TextView tvMic;
    @BindView(R.id.tv_shot_sd)
    TextView tvSD;
    @BindView(R.id.tv_shot_ppv)
    TextView tvPPV;

    @BindView(R.id.volume)
    LinearLayout layoutVolume;
    @BindView(R.id.layout_option)
    RelativeLayout layoutOption;
    @BindView(R.id.calculator_shot_layout)
    LinearLayout layoutMetricImperial;

    @BindView(R.id.btn_shot_metric)
    Button btnMetric;
    @BindView(R.id.btn_shot_imperail)
    Button btnImperial;
    @BindView(R.id.switch_vibration)
    Switch switchVibration;
    @BindView(R.id.btn_volume)
    Button btnVolume;
    @BindView(R.id.btn_weight)
    Button btnWeight;
    @BindView(R.id.btn_subDrill)
    Button btnSubDrill;
    @BindView(R.id.btn_standOFf)
    Button btnStandOff;
    @BindView(R.id.btn_no_of_holes)
    Button btnHoles;
    @BindView(R.id.btn_hole_row_count)
    Button btnHoleRowCount;
    @BindView(R.id.subdrill)
    TextView tvSubDrill;

    @BindView(R.id.layoutRock)
    RelativeLayout layoutRock;
    @BindView(R.id.layoutRow)
    RelativeLayout layoutRow;
    @BindView(R.id.layoutHole)
    RelativeLayout layoutHole;
    @BindView(R.id.layoutDirectHole)
    RelativeLayout layoutDirectHole;
    @BindView(R.id.layout_distance)
    RelativeLayout layoutDistance;
    @BindView(R.id.layout_attenuation)
    RelativeLayout layoutAttenuation;
    @BindView(R.id.layout_scaling_factor)
    RelativeLayout layoutScalingFactor;
    @BindView(R.id.layout_for_vibration)
    LinearLayout layoutVibrationOnOff;

    @BindView(R.id.iv_back)
    ImageView ivBack;

    private double explosiveDensity = 0, diameter = 0, burden = 0, spacing = 0, benchHeight = 0, subDrill = 0, stemming = 0, noOfRows = 0, holePerRows = 0,
            holePerMs = 0, distance = 0, scallingFactor = 0, attenuation = 0, numberOfHole = 0, rockDensity = 0;

    private boolean check = true;
    private boolean checkCalculator = true;
    private boolean checkVolume = true;
    private boolean checkSubDrillStandOFF = true;
    private boolean checkHoleRowCount = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_calculator_by_shot, container, false);
        ButterKnife.bind(this, view);
        intiViews();
        return view;
    }

    private void intiViews() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.connectFragment(getActivity(), new CalculatorsHomeFragment());
            }
        });

        switchVibration.setOnCheckedChangeListener(this);

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
                metricCalculator();
            }
        });

        btnImperial.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                checkCalculator = false;
                btnImperial.setBackgroundColor(getActivity().getColor(R.color.silver));
                btnMetric.setBackgroundColor(getActivity().getColor(R.color.grey));
                imperialCalculator();
            }
        });

        btnVolume.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                checkVolume = true;
                layoutRock.setVisibility(View.GONE);
                btnWeight.setBackgroundColor(getActivity().getColor(R.color.grey));
                btnVolume.setBackgroundColor(getActivity().getColor(R.color.silver));
                metricCalculator();
                imperialCalculator();

            }
        });

        btnWeight.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                checkVolume = false;
                layoutRock.setVisibility(View.VISIBLE);
                btnWeight.setBackgroundColor(getActivity().getColor(R.color.silver));
                btnVolume.setBackgroundColor(getActivity().getColor(R.color.grey));
                metricCalculator();
                imperialCalculator();

            }
        });

        btnStandOff.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                tvSubDrill.setText("StandOff");
                btnStandOff.setBackgroundColor(getActivity().getColor(R.color.silver));
                btnSubDrill.setBackgroundColor(getActivity().getColor(R.color.grey));
            }
        });

        btnSubDrill.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                tvSubDrill.setText("SubDrill");
                btnStandOff.setBackgroundColor(getActivity().getColor(R.color.grey));
                btnSubDrill.setBackgroundColor(getActivity().getColor(R.color.silver));
            }
        });

        btnHoles.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                checkHoleRowCount = true;   //count direct holes
                layoutDirectHole.setVisibility(View.VISIBLE);
                layoutRow.setVisibility(View.GONE);
                layoutHole.setVisibility(View.GONE);
                btnHoles.setBackgroundColor(getActivity().getColor(R.color.silver));
                btnHoleRowCount.setBackgroundColor(getActivity().getColor(R.color.grey));
            }
        });

        btnHoleRowCount.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                checkHoleRowCount = false;    //count holes from no of hole and row
                layoutDirectHole.setVisibility(View.GONE);
                layoutRow.setVisibility(View.VISIBLE);
                layoutHole.setVisibility(View.VISIBLE);
                btnHoles.setBackgroundColor(getActivity().getColor(R.color.grey));
                btnHoleRowCount.setBackgroundColor(getActivity().getColor(R.color.silver));
            }
        });


        etNoOfRows.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("") || s == null) {
                    noOfRows = 0;
                } else {
                    try {
                        noOfRows = Double.parseDouble(s.toString().replace(',', '.'));
                        if (checkCalculator) {
                            metricCalculator();
                        } else {
                            imperialCalculator();
                        }
                    } catch (NumberFormatException e) {
                        //Error
                    }

                }

            }
        });

        etShotHoles.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("") || s == null) {
                    numberOfHole = 0;
                } else {
                    try {
                        numberOfHole = Double.parseDouble(s.toString().replace(',', '.'));
                        if (checkCalculator) {
                            metricCalculator();
                        } else {
                            imperialCalculator();
                        }
                    } catch (NumberFormatException e) {
                        //Error
                    }

                }

            }
        });

        etHolePerRow.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("") || s == null) {
                    holePerRows = 0;
                } else {
                    try {
                        holePerRows = Double.parseDouble(s.toString().replace(',', '.'));
                        if (checkCalculator) {
                            metricCalculator();
                        } else {
                            imperialCalculator();
                        }
                    } catch (NumberFormatException e) {
                        //Error
                    }

                }

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
                            metricCalculator();
                        } else {
                            imperialCalculator();
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
                if (s.toString().equals("") || s == null) {
                    explosiveDensity = 0;
                } else {
                    try {
                        explosiveDensity = Double.parseDouble(s.toString().replace(',', '.'));
                        if (checkCalculator) {
                            metricCalculator();
                        } else {
                            imperialCalculator();
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
                            metricCalculator();
                        } else {
                            imperialCalculator();
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
                            metricCalculator();
                        } else {
                            imperialCalculator();
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
                            metricCalculator();
                        } else {
                            imperialCalculator();
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

                if (s.toString().equals("")) {
                    subDrill = 0;
                } else {
                    try {
                        subDrill = Double.parseDouble(s.toString().replace(',', '.'));
                        if (checkCalculator) {
                            metricCalculator();
                        } else {
                            imperialCalculator();
                        }
                    } catch (NumberFormatException e) {
                        //Error
                    }
                }
            }
        });

        etShotHoleMs.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("") || s == null) {
                    holePerMs = 0;
                } else {
                    try {
                        holePerMs = Double.parseDouble(s.toString().replace(',', '.'));
                        if (checkCalculator) {
                            metricCalculator();
                        } else {
                            imperialCalculator();
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
                if (s.toString().equals("") || s == null) {
                    burden = 0;
                } else {
                    try {
                        burden = Double.parseDouble(s.toString().replace(',', '.'));
                        if (checkCalculator) {
                            metricCalculator();
                        } else {
                            imperialCalculator();
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
                if (s.toString().equals("") || s == null) {
                    distance = 0;
                } else {
                    try {
                        distance = Double.parseDouble(s.toString().replace(',', '.'));
                        if (checkCalculator) {
                            metricCalculator();
                        } else {
                            imperialCalculator();
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
                if (s.toString().equals("") || s == null) {
                    stemming = 0;
                } else {
                    try {
                        stemming = Double.parseDouble(s.toString().replace(',', '.'));
                        if (checkCalculator) {
                            metricCalculator();
                        } else {
                            imperialCalculator();
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
                if (s.toString().equals("") || s == null) {
                    rockDensity = 0;
                } else {
                    try {
                        rockDensity = Double.parseDouble(s.toString().replace(',', '.'));
                        if (checkCalculator) {
                            metricCalculator();
                        } else {
                            imperialCalculator();
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
                if (s.toString().equals("") || s == null) {
                    scallingFactor = 0;
                } else {
                    try {
                        scallingFactor = Double.parseDouble(s.toString().replace(',', '.'));
                        if (checkCalculator) {
                            metricCalculator();
                        } else {
                            imperialCalculator();
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
                if (s.toString().equals("") || s == null) {
                    attenuation = 0;
                } else {
                    try {
                        attenuation = Double.parseDouble(s.toString().replace(',', '.'));
                        if (checkCalculator) {
                            metricCalculator();
                        } else {
                            imperialCalculator();
                        }
                    } catch (NumberFormatException e) {
                        //Error
                    }

                }

            }
        });
    }

    private void metricCalculator() {
        double NoOfHole, holeLength, totalDrill, volumePerHole = 0, weightPerHole, totalWeight, totalVolume = 0,
                explosivePerHole, totalExplosive, powderFactor, d, chargeUnit, Wc, sdob, SD, MIC, PPV;

        if (checkHoleRowCount) {
            NoOfHole = numberOfHole;
        } else {
            NoOfHole = holePerRows * noOfRows;
        }

        holeLength = benchHeight + subDrill;
        totalDrill = holeLength * NoOfHole;

        explosivePerHole = (explosiveDensity / 1000) * (Math.PI * (Math.pow((diameter / 2), 2))) * (holeLength - stemming);
        totalExplosive = explosivePerHole * NoOfHole;

        if (checkVolume) {
            volumePerHole = burden * spacing * benchHeight;
            totalVolume = volumePerHole * NoOfHole;

            powderFactor = totalExplosive / totalVolume;
        } else {
            weightPerHole = (burden * spacing * benchHeight) * rockDensity;
            totalWeight = weightPerHole * NoOfHole;

            powderFactor = totalExplosive / totalWeight;
        }

        d = stemming + (5 * (diameter / 1000));
        chargeUnit = (explosiveDensity / 1000) * (Math.PI * Math.pow((diameter / 2), 2));
        Wc = chargeUnit * (10 * (diameter / 1000));

        sdob = d / Math.pow(Wc, 0.3333);
        SD = distance / Math.sqrt(explosivePerHole);

        MIC = holePerMs * explosivePerHole;

        PPV = scallingFactor * (Math.pow(SD, attenuation));


        tvTotalHoles.setText(String.format("%.0f", NoOfHole));
        tvShotLenght.setText(String.format("%.2f", holeLength));
        tvShotDrillLenght.setText(String.format("%.0f", totalDrill));
        tvVolumePerHole.setText(String.format("%.0f", volumePerHole));
        tvVolume.setText(String.format("%.0f", totalVolume));
        tvExplosivePerHole.setText(String.format("%.0f", explosivePerHole));
        tvTotalExplosive.setText(String.format("%.0f", totalExplosive));
        tvSDOB.setText(String.format("%.2f", sdob));
        tvPF.setText(String.format("%.2f", powderFactor));
        tvSD.setText(String.format("%.2f", SD));
        tvMic.setText(String.format("%.2f", MIC));
        tvPPV.setText(String.format("%.2f", PPV));


    }

    private void imperialCalculator() {
        double NoOfHole, holeLength, totalDrill, volumePerHole = 0, weightPerHole, totalWeight, totalVolume = 0,
                explosivePerHole, totalExplosive, powderFactor, d, chargeUnit, Wc, sdob, SD, MIC, PPV;

        if (checkHoleRowCount) {
            NoOfHole = numberOfHole;
        } else {
            NoOfHole = holePerRows * noOfRows;
        }

        holeLength = benchHeight + subDrill;
        totalDrill = holeLength * NoOfHole;

        explosivePerHole = (explosiveDensity * 62.4) * (Math.PI * (Math.pow((diameter / 24), 2))) * (holeLength - stemming);
        totalExplosive = explosivePerHole * NoOfHole;

        if (checkVolume) {
            volumePerHole = ((burden * spacing * benchHeight) / 27);
            totalVolume = volumePerHole * NoOfHole;
            powderFactor = totalExplosive / totalVolume;
        } else {
            weightPerHole = ((burden * spacing * benchHeight) / 27) * rockDensity * 0.841;
            totalWeight = weightPerHole * NoOfHole;
            powderFactor = totalExplosive / totalWeight;
        }

        d = stemming + (5 * (diameter / 12));
        chargeUnit = (explosiveDensity * 62.4) * (Math.PI * Math.pow((diameter / 24), 2));
        Wc = chargeUnit * (10 * (diameter / 12));

        sdob = d / Math.pow(Wc, 0.3333);
        SD = distance / Math.sqrt(explosivePerHole);

        MIC = holePerMs * explosivePerHole;
        PPV = scallingFactor * (Math.pow(SD, attenuation));


        tvTotalHoles.setText(String.format("%.0f", NoOfHole));
        tvShotLenght.setText(String.format("%.2f", holeLength));
        tvShotDrillLenght.setText(String.format("%.0f", totalDrill));
        tvVolumePerHole.setText(String.format("%.0f", volumePerHole));
        tvVolume.setText(String.format("%.0f", totalVolume));
        tvExplosivePerHole.setText(String.format("%.0f", explosivePerHole));
        tvTotalExplosive.setText(String.format("%.0f", totalExplosive));
        tvSDOB.setText(String.format("%.2f", sdob));
        tvPF.setText(String.format("%.2f", powderFactor));
        tvSD.setText(String.format("%.2f", SD));
        tvMic.setText(String.format("%.2f", MIC));
        tvPPV.setText(String.format("%.2f", PPV));
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            layoutDistance.setVisibility(View.VISIBLE);
            layoutScalingFactor.setVisibility(View.VISIBLE);
            layoutAttenuation.setVisibility(View.VISIBLE);
            layoutVibrationOnOff.setVisibility(View.VISIBLE);
        } else {
            layoutDistance.setVisibility(View.GONE);
            layoutScalingFactor.setVisibility(View.GONE);
            layoutAttenuation.setVisibility(View.GONE);
            layoutVibrationOnOff.setVisibility(View.GONE);
        }
    }


    //\u221a
}
