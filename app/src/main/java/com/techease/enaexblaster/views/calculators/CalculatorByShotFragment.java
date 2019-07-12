package com.techease.enaexblaster.views.calculators;

import android.annotation.TargetApi;
import android.icu.util.Measure;
import android.icu.util.MeasureUnit;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.techease.enaexblaster.R;
import com.techease.enaexblaster.helpers.SavingLoadingData;
import com.techease.enaexblaster.saveLoadData.LoadDataFragment;
import com.techease.enaexblaster.utilities.GeneralUtils;
import com.techease.enaexblaster.utilities.NetworkUtilities;
import com.techease.enaexblaster.views.fragments.CalculatorsHomeFragment;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CalculatorByShotFragment extends Fragment implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {
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

    @BindView(R.id.iv_graphics)
    ImageView ivGraphics;

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
    @BindView(R.id.hole8ms_layout)
    RelativeLayout layoutHole8ms;

    @BindView(R.id.shot_diameter_unit)
    TextView tvDiameterUnit;
    @BindView(R.id.shot_explosive_density_unit)
    TextView tvExplosiveDensityUnit;
    @BindView(R.id.shot_burden_unit)
    TextView tvBurdenrUnit;
    @BindView(R.id.shot_spacing_unit)
    TextView tvSpacingUnit;
    @BindView(R.id.shot_stem_unit)
    TextView tvStemLengthrUnit;
    @BindView(R.id.shot_rock_unit)
    TextView tvRockDensityUnit;
    @BindView(R.id.shot_subdrill_unit)
    TextView tvShotDrillUnit;
    @BindView(R.id.shot_bench_unit)
    TextView tvShotBenchUnit;
    @BindView(R.id.shot_distance_unit)
    TextView tvDistanceUnit;
    @BindView(R.id.shot_ms_unit)
    TextView tvShotMsUnit;

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_menu)
    ImageView ivMenu;

    private double explosiveDensity = 0, diameter = 0, burden = 0, spacing = 0, benchHeight = 0, subDrill = 0, stemming = 0, noOfRows = 0, holePerRows = 0,
            holePerMs = 0, distance = 0, scallingFactor = 160, attenuation = -1.6, numberOfHole = 0, rockDensity = 0;

    private boolean check = true;
    private boolean checkCalculator = true;
    private boolean checkVolume = true;
    private boolean checkSubDrillStandOFF = true;
    private boolean checkHoleRowCount = true;
    DecimalFormat formatter;

    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_calculator_by_shot, container, false);
        ButterKnife.bind(this, view);
        formatter = new DecimalFormat("#,###,###");
        checkCalculator = GeneralUtils.getSharedPreferences(getActivity()).getBoolean("check_unit", true);

        if (checkCalculator) {
            btnImperial.setBackgroundColor(getActivity().getColor(R.color.grey));
            btnMetric.setBackgroundColor(getActivity().getColor(R.color.silver));
            metricUnits();
        } else {
            btnImperial.setBackgroundColor(getActivity().getColor(R.color.silver));
            btnMetric.setBackgroundColor(getActivity().getColor(R.color.grey));
            imperialUnits();
        }
        intiViews();
        showSaveData();

        return view;
    }

    private void intiViews() {
        ivMenu.setOnClickListener(this);
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
                switchToMetric();
            }
        });

        btnImperial.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                checkCalculator = false;
                switchToImperial();
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
                if (checkCalculator) {
                    metricCalculator();
                } else {
                    imperialCalculator();
                }

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
                if (checkCalculator) {
                    metricCalculator();
                } else {
                    imperialCalculator();
                }

            }
        });

        btnStandOff.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                checkSubDrillStandOFF = false;
                tvSubDrill.setText("StandOff");
                btnStandOff.setBackgroundColor(getActivity().getColor(R.color.silver));
                btnSubDrill.setBackgroundColor(getActivity().getColor(R.color.grey));
                imperialCalculator();
                metricCalculator();
            }
        });

        btnSubDrill.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                checkSubDrillStandOFF = true;
                tvSubDrill.setText("SubDrill");
                btnStandOff.setBackgroundColor(getActivity().getColor(R.color.grey));
                btnSubDrill.setBackgroundColor(getActivity().getColor(R.color.silver));
                imperialCalculator();
                metricCalculator();
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
        double NoOfHole, holeLength, totalDrill, volumePerHole = 0, weightPerHole, totalWeight = 0, totalVolume = 0,
                explosivePerHole, totalExplosive, powderFactor, d, chargeUnit, Wc, sdob, SD, MIC, PPV;

        if (checkHoleRowCount) {
            NoOfHole = numberOfHole;
        } else {
            NoOfHole = holePerRows * noOfRows;
        }


        if (checkSubDrillStandOFF) {  //subdrill selected
            holeLength = benchHeight + subDrill;

        } else {   //standoff selected
            holeLength = benchHeight - subDrill;
        }

        totalDrill = holeLength * NoOfHole;


        if (checkVolume) {
            volumePerHole = burden * spacing * benchHeight;
            totalVolume = volumePerHole * NoOfHole;

        } else {
            volumePerHole = (burden * spacing * benchHeight) * rockDensity;
            totalVolume = volumePerHole * NoOfHole;
        }

        explosivePerHole = (explosiveDensity / 1000) * (Math.PI * (Math.pow((diameter / 2), 2))) * (holeLength - stemming);
        totalExplosive = explosivePerHole * NoOfHole;


        if (checkVolume) {
            powderFactor = totalExplosive / totalVolume;
        } else {
            powderFactor = totalExplosive / totalWeight;
        }

        d = stemming + (5 * (diameter / 1000));
        chargeUnit = (explosiveDensity / 1000) * (Math.PI * Math.pow((diameter / 2), 2));
        Wc = chargeUnit * (10 * (diameter / 1000));

        sdob = d / Math.pow(Wc, 0.3333);

        MIC = holePerMs * explosivePerHole;

        SD = distance / Math.sqrt(MIC);

        PPV = scallingFactor * (Math.pow(SD, attenuation));

        checkGraphics(sdob);


        tvTotalHoles.setText(formatter.format(NoOfHole));
        tvShotLenght.setText(String.format("%.1f", holeLength) + " m");
        tvShotDrillLenght.setText(formatter.format(totalDrill) + " m");
        tvVolumePerHole.setText(formatter.format(volumePerHole) + " m³");
        tvVolume.setText(formatter.format(totalVolume) + " m³");
        tvExplosivePerHole.setText(formatter.format(explosivePerHole) + " kg");
        tvTotalExplosive.setText(formatter.format(totalExplosive) + " kg");
        tvSDOB.setText(String.format("%.2f", sdob) + " m∛kg");
        tvPF.setText(String.format("%.2f", powderFactor) + " kg/m³");
        tvSD.setText(String.format("%.1f", SD) + " m/√kg");
        tvMic.setText(String.format("%.0f", MIC) + " kg");
        tvPPV.setText(String.format("%.1f", PPV) + " mm/s");


    }

    private void imperialCalculator() {
        double NoOfHole, holeLength, totalDrill, volumePerHole = 0, weightPerHole, totalWeight, totalVolume = 0,
                explosivePerHole, totalExplosive, powderFactor, d, chargeUnit, Wc, sdob, SD, MIC, PPV;

        if (checkHoleRowCount) {
            NoOfHole = numberOfHole;
        } else {
            NoOfHole = holePerRows * noOfRows;
        }

        if (checkSubDrillStandOFF) {  //subdrill selected
            holeLength = benchHeight + subDrill;
        } else {   //standoff selected
            holeLength = benchHeight - subDrill;
        }


        totalDrill = holeLength * NoOfHole;

        explosivePerHole = (explosiveDensity * 62.4) * (Math.PI * (Math.pow((diameter / 24), 2))) * (holeLength - stemming);
        totalExplosive = explosivePerHole * NoOfHole;

        if (checkVolume) {
            volumePerHole = ((burden * spacing * benchHeight) / 27);
            totalVolume = volumePerHole * NoOfHole;
            powderFactor = totalExplosive / totalVolume;
        } else {
            volumePerHole = ((burden * spacing * benchHeight) / 27) * rockDensity * 0.841;
            totalVolume = volumePerHole * NoOfHole;
            powderFactor = totalExplosive / totalVolume;
        }

        d = stemming + (5 * (diameter / 12));
        chargeUnit = (explosiveDensity * 62.4) * (Math.PI * Math.pow((diameter / 24), 2));
        Wc = chargeUnit * (10 * (diameter / 12));

        sdob = d / Math.pow(Wc, 0.3333);
        MIC = holePerMs * explosivePerHole;
        SD = distance / Math.sqrt(MIC);
        PPV = scallingFactor * (Math.pow(SD, attenuation));

        checkGraphics(sdob);


        tvTotalHoles.setText(formatter.format(NoOfHole));
        tvShotLenght.setText(String.format("%.1f", holeLength) + " ft");
        tvShotDrillLenght.setText(formatter.format(totalDrill) + " ft");
        tvVolumePerHole.setText(formatter.format(volumePerHole) + " yd³");
        tvVolume.setText(formatter.format(totalVolume) + " yd³");
        tvExplosivePerHole.setText(formatter.format(explosivePerHole) + " lb");
        tvTotalExplosive.setText(formatter.format(totalExplosive) + " lb");
        tvSDOB.setText(String.format("%.2f", sdob) + " ft/∛lb");
        tvPF.setText(String.format("%.2f", powderFactor) + " lb/yd³");
        tvSD.setText(String.format("%.1f", SD) + " ft/√lb");
        tvMic.setText(String.format("%.2f", MIC) + "kg");
        tvPPV.setText(String.format("%.2f", PPV) + " in/s");
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            layoutHole8ms.setVisibility(View.VISIBLE);
            layoutDistance.setVisibility(View.VISIBLE);
            layoutScalingFactor.setVisibility(View.VISIBLE);
            layoutAttenuation.setVisibility(View.VISIBLE);
            layoutVibrationOnOff.setVisibility(View.VISIBLE);
        } else {
            layoutHole8ms.setVisibility(View.GONE);
            layoutDistance.setVisibility(View.GONE);
            layoutScalingFactor.setVisibility(View.GONE);
            layoutAttenuation.setVisibility(View.GONE);
            layoutVibrationOnOff.setVisibility(View.GONE);
        }
    }

    private void metricUnits() {
        tvDiameterUnit.setText("mm");
        tvExplosiveDensityUnit.setText("g/cc");
        tvBurdenrUnit.setText("m");
        tvSpacingUnit.setText("m");
        tvBurdenrUnit.setText("m");
        tvStemLengthrUnit.setText("m");
        tvShotBenchUnit.setText("m");
        tvShotDrillUnit.setText("m");
        tvRockDensityUnit.setText("g/cc");
        tvDistanceUnit.setText("m");
    }

    private void imperialUnits() {
        tvDiameterUnit.setText("in");
        tvExplosiveDensityUnit.setText("g/cc");
        tvBurdenrUnit.setText("ft");
        tvSpacingUnit.setText("ft");
        tvBurdenrUnit.setText("ft");
        tvStemLengthrUnit.setText("ft");
        tvShotDrillUnit.setText("ft");
        tvShotBenchUnit.setText("ft");
        tvRockDensityUnit.setText("g/cc");
        tvDistanceUnit.setText("ft");
    }


    private void checkGraphics(double result) {

        if (checkCalculator) {    //checking graphics for metric calculator
            if (result >= 0 && result <= 0.6) {
                ivGraphics.setImageDrawable(getResources().getDrawable(R.drawable.graphic1));
            } else if (result >= 0.61 && result <= 0.9) {
                ivGraphics.setImageDrawable(getResources().getDrawable(R.drawable.graphics2));
            } else if (result >= 0.91 && result <= 1.42) {
                ivGraphics.setImageDrawable(getResources().getDrawable(R.drawable.graphics3));
            } else if (result >= 1.43 && result <= 1.82) {
                ivGraphics.setImageDrawable(getResources().getDrawable(R.drawable.graphics4));
            } else if (result >= 1.83 && result <= 2.40) {
                ivGraphics.setImageDrawable(getResources().getDrawable(R.drawable.graphics5));
            } else if (result >= 2.41) {
                ivGraphics.setImageDrawable(getResources().getDrawable(R.drawable.graphics6));
            }
        } else {     //checking graphics for imperial calculator

            if (result >= 0 && result <= 1.5) {
                ivGraphics.setImageDrawable(getResources().getDrawable(R.drawable.graphic1));
            } else if (result >= 1.51 && result <= 2.2) {
                ivGraphics.setImageDrawable(getResources().getDrawable(R.drawable.graphics2));
            } else if (result >= 2.21 && result <= 3.5) {
                ivGraphics.setImageDrawable(getResources().getDrawable(R.drawable.graphics3));
            } else if (result >= 3.51 && result <= 4.5) {
                ivGraphics.setImageDrawable(getResources().getDrawable(R.drawable.graphics4));
            } else if (result >= 4.51 && result <= 6.0) {
                ivGraphics.setImageDrawable(getResources().getDrawable(R.drawable.graphics5));
            } else if (result >= 6.01) {
                ivGraphics.setImageDrawable(getResources().getDrawable(R.drawable.graphics6));
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void switchToMetric() {
        btnImperial.setBackgroundColor(getActivity().getColor(R.color.grey));
        btnMetric.setBackgroundColor(getActivity().getColor(R.color.silver));


        burden = burden / 3.281;
        spacing = spacing / 3.281;
        benchHeight = benchHeight / 3.281;
        stemming = stemming / 3.281;
        distance = distance / 3.281;
        subDrill = subDrill / 3.281;
        stemming = stemming / 3.281;

        scallingFactor = 1140;

        etBurden.setText(String.format("%.1f", Double.valueOf(burden)));
        etSpacing.setText(String.format("%.1f", Double.valueOf(spacing)));
        etBenchHeight.setText(String.format("%.1f", Double.valueOf(benchHeight)));
        etSubDrill.setText(String.format("%.1f", Double.valueOf(subDrill)));
        etStemming.setText(String.format("%.1f", Double.valueOf(stemming)));
        etDistance.setText(String.format("%.1f", Double.valueOf(distance)));
        etScallingFactor.setText(String.format("%.0f", Double.valueOf(scallingFactor)));

        metricCalculator();
        metricUnits();

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void switchToImperial() {
        btnImperial.setBackgroundColor(getActivity().getColor(R.color.silver));
        btnMetric.setBackgroundColor(getActivity().getColor(R.color.grey));


        burden = burden * 3.281;
        spacing = spacing * 3.281;
        benchHeight = benchHeight * 3.281;
        stemming = stemming * 3.281;
        distance = distance * 3.281;
        subDrill = subDrill * 3.281;
        stemming = stemming * 3.281;

        scallingFactor = 160;


        etBurden.setText(String.format("%.0f", Double.valueOf(burden)));
        etSpacing.setText(String.format("%.0f", Double.valueOf(spacing)));
        etBenchHeight.setText(String.format("%.0f", Double.valueOf(benchHeight)));
        etSubDrill.setText(String.format("%.0f", Double.valueOf(subDrill)));
        etStemming.setText(String.format("%.0f", Double.valueOf(stemming)));
        etDistance.setText(String.format("%.0f", Double.valueOf(distance)));
        etScallingFactor.setText(String.format("%.0f", Double.valueOf(scallingFactor)));

        imperialCalculator();
        imperialUnits();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_menu:
                showMenu();
                break;
        }
    }
    private void showMenu() {
        PopupMenu popup = new PopupMenu(getActivity(), ivMenu);
        popup.getMenuInflater().inflate(R.menu.menu,
                popup.getMenu());
        popup.show();
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.save:
                        SavingLoadingData.showShotDialog(getActivity(),numberOfHole,holePerRows,diameter,explosiveDensity,burden,
                                spacing,benchHeight,subDrill,stemming,rockDensity,holePerMs,distance,scallingFactor,attenuation);
                        break;
                    case R.id.load:
                        Bundle bundle = new Bundle();
                        bundle.putString("checkingScreen","shot");
                        GeneralUtils.connectFragmentWithBack(getActivity(),new LoadDataFragment()).setArguments(bundle);
                        break;
                    case R.id.email:
                        NetworkUtilities.sendMail(getActivity(),"www.enaex.com/shot");
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    private void showSaveData(){
        Bundle bundle = this.getArguments();
        if (bundle != null) {

            String noRows = bundle.getString("rows");
            String holes = bundle.getString("holes");
            String strDiameter = bundle.getString("diameter");
            String strDensity = bundle.getString("density");
            String strBurden = bundle.getString("burden");
            String strSpacing = bundle.getString("spacing");
            String strBenchHeight = bundle.getString("benchHeight");
            String strSubDrill = bundle.getString("subDrill");
            String strStemLength = bundle.getString("stemLength");
            String strRockDensity = bundle.getString("rockDensity");
            String strHoleMS = bundle.getString("hole_ms");
            String strDistance = bundle.getString("distance");
            String strScaling = bundle.getString("scaling");
            String strAttenuation = bundle.getString("attenuation");


            etShotHoles.setText(noRows);
            etHolePerRow.setText(holes);
            etDiameter.setText(strDiameter);
            etDensity.setText(strDensity);
            etBurden.setText(strBurden);
            etSpacing.setText(strSpacing);
            etBenchHeight.setText(strBenchHeight);
            etSubDrill.setText(strSubDrill);
            etStemming.setText(strStemLength);
            etRockDensity.setText(strRockDensity);
            etShotHoleMs.setText(strHoleMS);
            etDistance.setText(strDistance);
            etScallingFactor.setText(strScaling);
            etAttenuationFactor.setText(strAttenuation);

            numberOfHole = Double.parseDouble(noRows);
            holePerRows = Double.parseDouble(holes);
            diameter = Double.parseDouble(strDiameter);
            explosiveDensity = Double.parseDouble(strDensity);
            burden = Double.parseDouble(strBurden);
            spacing = Double.parseDouble(strSpacing);
            benchHeight = Double.parseDouble(strBenchHeight);
            subDrill = Double.parseDouble(strSubDrill);
            stemming = Double.parseDouble(strStemLength);
            rockDensity = Double.parseDouble(strRockDensity);
            holePerMs = Double.parseDouble(strHoleMS);
            distance = Double.parseDouble(strDistance);
            scallingFactor = Double.parseDouble(strScaling);
            attenuation = Double.parseDouble(strAttenuation);
        }
    }
}
