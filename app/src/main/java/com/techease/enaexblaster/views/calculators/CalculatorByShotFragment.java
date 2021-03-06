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
    @BindView(R.id.tv_volumePerHole)
    TextView tvVolumeHole;
    @BindView(R.id.tvTotalVolume)
    TextView tvTotlaVolume;

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
            holePerMs = 1, distance = 0, scallingFactor = 160, attenuation = -1.6, numberOfHole = 0, rockDensity = 0;

    private boolean check = true;
    private boolean checkCalculator = true;
    private boolean checkVolume = true;
    private boolean checkSubDrillStandOFF = true;
    private boolean checkHoleRowCount = true;
    private boolean checkVibration = false;
    DecimalFormat formatter;

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_calculator_by_shot, container, false);
        ButterKnife.bind(this, view);
        formatter = new DecimalFormat("#,###,###");
        checkCalculator = GeneralUtils.getSharedPreferences(getActivity()).getBoolean("check_unit", false);

        if (checkCalculator) { // metric caluculator
            loadMetricCalculator();
        } else {              //imperial calculator
           loadImperailCalculator();
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

                btnMetric.setClickable(false);
                btnImperial.setClickable(true);
            }
        });

        btnImperial.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                checkCalculator = false;
                switchToImperial();

                btnMetric.setClickable(true);
                btnImperial.setClickable(false);
            }
        });

        btnVolume.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                checkVolume = true;
                calculateWithVolume();

            }
        });

        btnWeight.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                checkVolume = false;
                calculateWithWeight();

            }
        });

        btnStandOff.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                checkSubDrillStandOFF = false;
                selectStandOFF();
            }
        });

        btnSubDrill.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                checkSubDrillStandOFF = true;
                selectSubDrill();

            }
        });

        btnHoles.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                checkHoleRowCount = true;   //count direct holes
                selectBtnHoles();

            }
        });

        btnHoleRowCount.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                checkHoleRowCount = false;    //count holes from no of hole and row
                selectBtnHolesRow();
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
                        numberOfHole = noOfRows;
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

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void loadMetricCalculator(){
        btnImperial.setBackgroundColor(getActivity().getColor(R.color.grey));
        btnMetric.setBackgroundColor(getActivity().getColor(R.color.silver));
        metricCalculator();
        metricUnits();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void loadImperailCalculator(){
        btnImperial.setBackgroundColor(getActivity().getColor(R.color.silver));
        btnMetric.setBackgroundColor(getActivity().getColor(R.color.grey));
        imperialCalculator();
        imperialUnits();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void calculateWithVolume(){
        layoutRock.setVisibility(View.GONE);
        btnWeight.setBackgroundColor(getActivity().getColor(R.color.grey));
        btnVolume.setBackgroundColor(getActivity().getColor(R.color.silver));
        if (checkCalculator) {
            metricCalculator();
        } else {
            imperialCalculator();
        }

        tvVolumeHole.setText("Volume per Hole ");
        tvTotlaVolume.setText("Total Volume");
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void calculateWithWeight(){
        layoutRock.setVisibility(View.VISIBLE);
        btnWeight.setBackgroundColor(getActivity().getColor(R.color.silver));
        btnVolume.setBackgroundColor(getActivity().getColor(R.color.grey));
        if (checkCalculator) {
            metricCalculator();
        } else {
            imperialCalculator();
        }

        tvVolumeHole.setText("Weight per Hole");
        tvTotlaVolume.setText("Total Weight");

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void selectStandOFF(){
        tvSubDrill.setText("Standoff");
        btnStandOff.setBackgroundColor(getActivity().getColor(R.color.silver));
        btnSubDrill.setBackgroundColor(getActivity().getColor(R.color.grey));
        if (checkCalculator) { // metric caluculator
            loadMetricCalculator();
        } else {              //imperial calculator
            loadImperailCalculator();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void selectSubDrill(){
        tvSubDrill.setText("Subdrill");
        btnStandOff.setBackgroundColor(getActivity().getColor(R.color.grey));
        btnSubDrill.setBackgroundColor(getActivity().getColor(R.color.silver));
        if (checkCalculator) { // metric caluculator
            loadMetricCalculator();
        } else {              //imperial calculator
            loadImperailCalculator();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void selectBtnHoles(){
        layoutDirectHole.setVisibility(View.VISIBLE);
        layoutRow.setVisibility(View.GONE);
        layoutHole.setVisibility(View.GONE);
        btnHoles.setBackgroundColor(getActivity().getColor(R.color.silver));
        btnHoleRowCount.setBackgroundColor(getActivity().getColor(R.color.grey));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void selectBtnHolesRow(){
        layoutDirectHole.setVisibility(View.GONE);
        layoutRow.setVisibility(View.VISIBLE);
        layoutHole.setVisibility(View.VISIBLE);
        btnHoles.setBackgroundColor(getActivity().getColor(R.color.grey));
        btnHoleRowCount.setBackgroundColor(getActivity().getColor(R.color.silver));
    }

    private void metricCalculator() {
        double NoOfHole, holeLength, totalDrill, volumePerHole = 0, totalWeight = 0, totalVolume = 0,
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
            tvVolume.setText(formatter.format(totalVolume) + " m³");

        } else {
            volumePerHole = (burden * spacing * benchHeight) * rockDensity;
            totalWeight = volumePerHole * NoOfHole;
            tvVolume.setText(formatter.format(totalWeight) + " m³");
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
        tvExplosivePerHole.setText(formatter.format(explosivePerHole) + " kg");
        tvTotalExplosive.setText(formatter.format(totalExplosive) + " kg");
        tvSDOB.setText(String.format("%.2f", sdob) + " m/∛kg");
        tvPF.setText(String.format("%.2f", powderFactor) + " kg/m³");
        tvSD.setText(String.format("%.1f", SD) + " m/√kg");
        tvMic.setText(formatter.format(MIC) + " kg");
        tvPPV.setText(String.format("%.1f", PPV) + " mm/s");


    }

    private void imperialCalculator() {
        double NoOfHole, holeLength, totalDrill, volumePerHole = 0, totalVolume = 0,
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


        if(!checkVolume){   //weight selected
            tvVolume.setText(formatter.format(totalVolume) + " tons");
            tvVolumePerHole.setText(formatter.format(volumePerHole) + " tons");
            tvPF.setText(String.format("%.2f", powderFactor) + " lb/ton");
        }
        else {   //volume selected
            tvVolume.setText(formatter.format(totalVolume) + " yd³");
            tvVolumePerHole.setText(formatter.format(volumePerHole) + " yd³");
            tvPF.setText(String.format("%.2f", powderFactor) + " lb/yd³");
        }


        tvTotalHoles.setText(formatter.format(NoOfHole));
        tvShotLenght.setText(String.format("%.1f", holeLength) + " ft");
        tvShotDrillLenght.setText(formatter.format(totalDrill) + " ft");
        tvExplosivePerHole.setText(formatter.format(explosivePerHole) + " lb");
        tvTotalExplosive.setText(formatter.format(totalExplosive) + " lb");
        tvSDOB.setText(String.format("%.2f", sdob) + " ft/∛lb");
        tvSD.setText(String.format("%.1f", SD) + " ft/√lb");
        tvMic.setText(formatter.format(MIC) + " lb");
        tvPPV.setText(String.format("%.2f", PPV) + " in/s");
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        checkVibration = isChecked;
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
            if (result >= 0 && result <= 0.600) {
                ivGraphics.setImageDrawable(getResources().getDrawable(R.drawable.metric_graphic1));
            } else if (result >= 0.609 && result <= 0.900) {
                ivGraphics.setImageDrawable(getResources().getDrawable(R.drawable.metric_graphic2));
            } else if (result >= 0.909 && result <= 1.4249) {
                ivGraphics.setImageDrawable(getResources().getDrawable(R.drawable.metric_graphic3));
            } else if (result >= 1.4250 && result <= 1.8249) {
                ivGraphics.setImageDrawable(getResources().getDrawable(R.drawable.metric_graphic4));
            } else if (result >= 1.8250 && result <= 2.400) {
                ivGraphics.setImageDrawable(getResources().getDrawable(R.drawable.metric_graphic5));
            } else if (result >= 2.41) {
                ivGraphics.setImageDrawable(getResources().getDrawable(R.drawable.metric_graphic6));
            }
        } else {     //checking graphics for imperial calculator

            if (result >= 0 && result <= 1.549) {
                ivGraphics.setImageDrawable(getResources().getDrawable(R.drawable.imperial_graphic1));
            } else if (result >= 1.500 && result <= 2.249) {
                ivGraphics.setImageDrawable(getResources().getDrawable(R.drawable.imperial_graphic2));
            } else if (result >= 2.250 && result <= 3.549) {
                ivGraphics.setImageDrawable(getResources().getDrawable(R.drawable.imperial_graphic3));
            } else if (result >= 3.500 && result <= 4.549) {
                ivGraphics.setImageDrawable(getResources().getDrawable(R.drawable.imperial_graphic4));
            } else if (result >= 4.500 && result <= 6.049) {
                ivGraphics.setImageDrawable(getResources().getDrawable(R.drawable.imperial_graphic5));
            } else if (result >= 6.050) {
                ivGraphics.setImageDrawable(getResources().getDrawable(R.drawable.imperial_graphic6));
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void switchToMetric() {
        btnImperial.setBackgroundColor(getActivity().getColor(R.color.grey));
        btnMetric.setBackgroundColor(getActivity().getColor(R.color.silver));

        diameter = diameter * 25.4000008128;
        burden = burden / 3.280844;
        spacing = spacing / 3.280844;
        benchHeight = benchHeight / 3.280844;
        stemming = stemming / 3.280844;
        distance = distance / 3.280844;
        subDrill = subDrill / 3.280844;

        scallingFactor = 1140;

        etDiameter.setText(String.format("%.0f", Double.valueOf(diameter)));
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

        diameter = diameter / 25.4000008128;
        burden = burden * 3.280844;
        spacing = spacing * 3.280844;
        benchHeight = benchHeight * 3.280844;
        stemming = stemming * 3.280844;
        distance = distance * 3.280844;
        subDrill = subDrill * 3.280844;

        scallingFactor = 160;

        etDiameter.setText(String.format("%.3f", Double.valueOf(diameter)));
        etBurden.setText(String.format("%.1f", Double.valueOf(burden)));
        etSpacing.setText(String.format("%.1f", Double.valueOf(spacing)));
        etBenchHeight.setText(String.format("%.1f", Double.valueOf(benchHeight)));
        etSubDrill.setText(String.format("%.1f", Double.valueOf(subDrill)));
        etStemming.setText(String.format("%.1f", Double.valueOf(stemming)));
        etDistance.setText(String.format("%.1f", Double.valueOf(distance)));
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
                        //saving data in sqlite database
                        SavingLoadingData.showShotDialog(
                                getActivity(),
                                numberOfHole,holePerRows,diameter,explosiveDensity,burden,
                                spacing,benchHeight,subDrill,stemming,rockDensity,holePerMs, distance,scallingFactor,
                                attenuation,checkCalculator,checkVolume,checkSubDrillStandOFF,checkHoleRowCount,
                                checkVibration);
                        break;
                    case R.id.load:
                        Bundle bundle = new Bundle();
                        bundle.putString("checkingScreen","shot");
                        GeneralUtils.connectFragmentWithBack(getActivity(),new LoadDataFragment()).setArguments(bundle);
                        break;
                    case R.id.email:
                        NetworkUtilities.sendMail(getActivity(),
                                "www.enaexusa.com/shot?rows=" + numberOfHole
                                        + "&holes="+ holePerRows
                                        + "&diameter="+ diameter
                                        + "&density="+ explosiveDensity
                                        + "&burden="+ burden
                                        + "&spacing="+ spacing
                                        + "&benchHeight="+ benchHeight
                                        + "&subDrill="+ subDrill
                                        + "&stemLength="+ stemming
                                        + "&rockDensity="+ rockDensity
                                        + "&hole_ms="+ holePerMs
                                        + "&distance="+ distance
                                        + "&scaling="+ scallingFactor
                                        + "&attenuation="+ attenuation
                                        + "&calculator="+ checkCalculator
                                        + "&volume="+ checkVolume
                                        + "&subdrill="+ checkSubDrillStandOFF
                                        + "&check_holes="+ checkHoleRowCount
                                        + "&vibration="+ checkVibration);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
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

            boolean calculator = Boolean.valueOf(bundle.getString("calculator"));
            boolean volume = Boolean.valueOf(bundle.getString("volume"));
            boolean subdrill = Boolean.valueOf(bundle.getString("subdrill"));
            boolean checkHoles = Boolean.valueOf(bundle.getString("check_holes"));
            boolean checkVibration = Boolean.valueOf(bundle.getString("vibration"));


            etNoOfRows.setText(noRows);
            etHolePerRow.setText(holes);
            etShotHoles.setText(holes);
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


            if(checkHoles){
                checkHoleRowCount = true;
                layoutDirectHole.setVisibility(View.VISIBLE);
                layoutRow.setVisibility(View.GONE);
                layoutHole.setVisibility(View.GONE);
                btnHoles.setBackgroundColor(getActivity().getColor(R.color.silver));
                btnHoleRowCount.setBackgroundColor(getActivity().getColor(R.color.grey));
            }
            else {
                checkHoleRowCount = false;
                layoutDirectHole.setVisibility(View.GONE);
                layoutRow.setVisibility(View.VISIBLE);
                layoutHole.setVisibility(View.VISIBLE);
                btnHoles.setBackgroundColor(getActivity().getColor(R.color.grey));
                btnHoleRowCount.setBackgroundColor(getActivity().getColor(R.color.silver));
            }

            if(volume){
                checkVolume = true;
                layoutRock.setVisibility(View.GONE);
                btnWeight.setBackgroundColor(getActivity().getColor(R.color.grey));
                btnVolume.setBackgroundColor(getActivity().getColor(R.color.silver));
                tvVolumeHole.setText("Volume per Hole ");
                tvTotlaVolume.setText("Total Volume");
            }
            else {
                checkVolume = false;
                layoutRock.setVisibility(View.VISIBLE);
                btnWeight.setBackgroundColor(getActivity().getColor(R.color.silver));
                btnVolume.setBackgroundColor(getActivity().getColor(R.color.grey));
                tvVolumeHole.setText("Weight per Hole");
                tvTotlaVolume.setText("Total Weight");
            }

            if(subdrill){
                checkSubDrillStandOFF = true;
                tvSubDrill.setText("Subdrill");
                btnStandOff.setBackgroundColor(getActivity().getColor(R.color.grey));
                btnSubDrill.setBackgroundColor(getActivity().getColor(R.color.silver));
            }
            else {
                checkSubDrillStandOFF = false;
                tvSubDrill.setText("Standoff");
                btnStandOff.setBackgroundColor(getActivity().getColor(R.color.silver));
                btnSubDrill.setBackgroundColor(getActivity().getColor(R.color.grey));
            }

            if(checkVibration){
                switchVibration.setChecked(true);
                layoutHole8ms.setVisibility(View.VISIBLE);
                layoutDistance.setVisibility(View.VISIBLE);
                layoutScalingFactor.setVisibility(View.VISIBLE);
                layoutAttenuation.setVisibility(View.VISIBLE);
                layoutVibrationOnOff.setVisibility(View.VISIBLE);
            }
            else {
                switchVibration.setChecked(false);
                layoutHole8ms.setVisibility(View.GONE);
                layoutDistance.setVisibility(View.GONE);
                layoutScalingFactor.setVisibility(View.GONE);
                layoutAttenuation.setVisibility(View.GONE);
                layoutVibrationOnOff.setVisibility(View.GONE);
            }



            if (calculator) {
                checkCalculator = true;
                loadMetricCalculator();
            } else {
                checkCalculator = false;
                loadImperailCalculator();
            }


        }
    }
}
