package com.techease.enaexblaster.views.calculators;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.techease.enaexblaster.views.fragments.CalculatorsHomeFragment;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CalculatorByHoleFragment extends Fragment implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {
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
    @BindView(R.id.iv_menu)
    ImageView ivMenu;


    private double density = 0, diameter = 0, burden = 0, spacing = 0, holeLenght = 0, rockDensity = 0, stemming = 0,
            distance = 0, scallingFactor = 160, attenuation = -1.6;

    private boolean check = true;
    private boolean checkCalculator = true;
    private boolean checkVolume = true;
    DecimalFormat formatter;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_calculator_by_hole, container, false);
        ButterKnife.bind(this, view);
        formatter = new DecimalFormat("#,###,###");

        checkCalculator = GeneralUtils.getSharedPreferences(getActivity()).getBoolean("check_unit", true);

        if (checkCalculator) {
            tvLBS.setText("Kgs per Hole");
            btnImperial.setBackgroundColor(getActivity().getColor(R.color.grey));
            btnMetric.setBackgroundColor(getActivity().getColor(R.color.silver));
            metrciUnits();
        } else {
            tvLBS.setText("Lbs per Hole");
            btnImperial.setBackgroundColor(getActivity().getColor(R.color.silver));
            btnMetric.setBackgroundColor(getActivity().getColor(R.color.grey));
            imperialUnits();
        }

        initViews();
        showSaveData();

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
                switchToMetric(); //all values converting to metric calculator
            }
        });

        btnImperial.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                checkCalculator = false;
                switchToImperial();  //all values converting to imperial calculator
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

                if (checkCalculator) {
                    metricCalculation();
                } else {
                    calculation();
                }
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

                if (checkCalculator) {
                    metricCalculation();
                } else {
                    calculation();
                }
            }
        });

        switchHole.setOnCheckedChangeListener(this);

        return view;
    }

    private void initViews() {
        ivMenu.setOnClickListener(this);
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
        double volume, weight, explosivePerHole, PFVolume, PFWeight, sdob, SD, PPV, d, Wc, chargeUnit;

        volume = (burden * spacing * holeLenght) / 27;
        weight = ((burden * spacing * holeLenght) / 27) * rockDensity * 0.841;
        explosivePerHole = (density * 62.4) * (Math.PI * (Math.pow((diameter / 24), 2))) * (holeLenght - stemming);

        PFVolume = explosivePerHole / volume;
        PFWeight = explosivePerHole / weight;

        d = stemming + (5 * (diameter / 12));
        chargeUnit = (density * 62.4) * (Math.PI * Math.pow((diameter / 24), 2));
        Wc = chargeUnit * (10 * (diameter / 12));
        sdob = d / Math.pow(Wc, 0.3333);
        SD = distance / Math.sqrt(explosivePerHole);
        PPV = scallingFactor * (Math.pow(SD, attenuation));


        checkGraphics(sdob);

        if (checkVolume) {
            tvVolume.setText(formatter.format(volume) + " yd³");
            tvLBSHole.setText(formatter.format(explosivePerHole) + " lb");
            tvPF.setText(String.format("%.2f", Double.valueOf(PFVolume)) + " lb/yd³");
            tvSDOB.setText(String.format("%.1f", Double.valueOf(sdob)) + " ft∛lb");
            tvSD.setText(String.format("%.1f", Double.valueOf(SD)) + " ft√lb");
            tvPPV.setText(String.format("%.2f", Double.valueOf(PPV)) + " in/s");
        } else {
            tvVolume.setText(formatter.format(weight) + " ton");
            tvLBSHole.setText(formatter.format(explosivePerHole) + " lb");
            tvPF.setText(String.format("%.2f", Double.valueOf(PFWeight)) + " lb/ton");
            tvSDOB.setText(String.format("%.1f", Double.valueOf(sdob)) + " ft∛lb");
            tvSD.setText(String.format("%.1f", Double.valueOf(SD)) + " ft√lb");
            tvPPV.setText(String.format("%.2f", Double.valueOf(PPV)) + " in/s");
        }

    }

    private void metricCalculation() {
        double volume, weight, explosivePerHole, PFVolume, PFWeight, sdob, SD, PPV, d, Wc, chargeUnit;

        volume = (burden * spacing * holeLenght);
        weight = (burden * spacing * holeLenght) * rockDensity;
        explosivePerHole = (density / 1000) * (Math.PI * (Math.pow((diameter / 2), 2))) * (holeLenght - stemming);

        PFVolume = explosivePerHole / volume;
        PFWeight = explosivePerHole / weight;

        d = stemming + (5 * (diameter / 1000));
        chargeUnit = (density / 1000) * (Math.PI * Math.pow((diameter / 2), 2));
        Wc = chargeUnit * (10 * (diameter / 1000));
        sdob = d / Math.pow(Wc, 0.3333);
        SD = distance / Math.sqrt(explosivePerHole);
        PPV = scallingFactor * (Math.pow(SD, attenuation));

        checkGraphics(sdob);

        if (checkVolume) {
            tvVolume.setText(formatter.format(volume) + " m³");
            tvLBSHole.setText(formatter.format(explosivePerHole) + " kg");
            tvPF.setText(String.format("%.2f", Double.valueOf(PFVolume)) + " kg/m³");
            tvSDOB.setText(String.format("%.2f", Double.valueOf(sdob)) + " m∛kg");
            tvSD.setText(String.format("%.1f", Double.valueOf(SD)) + " m/√kg");
            tvPPV.setText(String.format("%.2f", Double.valueOf(PPV)) + " mm/s");
        } else {
            tvVolume.setText(formatter.format(weight) + " tonne");
            tvLBSHole.setText(formatter.format(explosivePerHole) + " kg");
            tvPF.setText(String.format("%.2f", Double.valueOf(PFWeight)) + " kg/tonne");
            tvSDOB.setText(String.format("%.2f", Double.valueOf(sdob)) + " m∛kg");
            tvSD.setText(String.format("%.1f", Double.valueOf(SD)) + " m/√kg");
            tvPPV.setText(String.format("%.2f", Double.valueOf(PPV)) + " mm/s");
        }
    }

    private void metrciUnits() {
        tvDiameterUnit.setText("mm");
        tvExplosiveDensityUnit.setText("g/cc");
        tvBurdenrUnit.setText("m");
        tvSpacingUnit.setText("m");
        tvHoleLengthUnit.setText("m");
        tvStemLengthrUnit.setText("m");
        tvRockDensityUnit.setText("g/cc");
        tvDistanceUnit.setText("m");
    }

    private void imperialUnits() {
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
        tvLBS.setText("Kgs per Hole");
        btnImperial.setBackgroundColor(getActivity().getColor(R.color.grey));
        btnMetric.setBackgroundColor(getActivity().getColor(R.color.silver));

        burden = burden / 3.281;
        spacing = spacing / 3.281;
        holeLenght = holeLenght / 3.281;
        stemming = stemming / 3.281;
        distance = distance / 3.281;

        scallingFactor = 1140;

        etBurden.setText(String.format("%.1f", Double.valueOf(burden)));
        etSpacing.setText(String.format("%.1f", Double.valueOf(spacing)));
        etHoleLenght.setText(String.format("%.1f", Double.valueOf(holeLenght)));
        etStemming.setText(String.format("%.1f", Double.valueOf(stemming)));
        etDistance.setText(String.format("%.1f", Double.valueOf(distance)));
        etScallingFactor.setText(String.format("%.0f", Double.valueOf(scallingFactor)));

        metricCalculation();
        metrciUnits();

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void switchToImperial() {
        tvLBS.setText("Lbs per Hole");
        btnImperial.setBackgroundColor(getActivity().getColor(R.color.silver));
        btnMetric.setBackgroundColor(getActivity().getColor(R.color.grey));

        burden = burden * 3.281;
        spacing = spacing * 3.281;
        holeLenght = holeLenght * 3.281;
        stemming = stemming * 3.281;
        distance = distance * 3.281;

        scallingFactor = 160;


        etBurden.setText(String.format("%.0f", Double.valueOf(burden)));
        etSpacing.setText(String.format("%.0f", Double.valueOf(spacing)));
        etHoleLenght.setText(String.format("%.0f", Double.valueOf(holeLenght)));
        etStemming.setText(String.format("%.0f", Double.valueOf(stemming)));
        etDistance.setText(String.format("%.0f", Double.valueOf(distance)));
        etScallingFactor.setText(String.format("%.0f", Double.valueOf(scallingFactor)));

        calculation();
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
                        SavingLoadingData.showHoleDialog(getActivity(),diameter,density,burden,
                                spacing,holeLenght,stemming,rockDensity,distance,scallingFactor,attenuation);
                        break;
                    case R.id.load:
                        Bundle bundle = new Bundle();
                        bundle.putString("checkingScreen","hole");
                        GeneralUtils.connectFragmentWithBack(getActivity(),new LoadDataFragment()).setArguments(bundle);
                        break;
                    case R.id.email:

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

            String strDiameter = bundle.getString("diameter");
            String strDensity = bundle.getString("density");
            String strBurden = bundle.getString("burden");
            String strSpacing = bundle.getString("spacing");
            String strHoleLength = bundle.getString("holeLength");
            String strStemLength = bundle.getString("stemLength");
            String strRockDensity = bundle.getString("rockDensity");
            String strDistance = bundle.getString("distance");
            String strScaling = bundle.getString("scaling");
            String strAttenuation = bundle.getString("attenuation");


            etDiameter.setText(strDiameter);
            etDensity.setText(strDensity);
            etBurden.setText(strBurden);
            etSpacing.setText(strSpacing);
            etHoleLenght.setText(strHoleLength);
            etStemming.setText(strStemLength);
            etRockDensity.setText(strRockDensity);
            etDistance.setText(strDistance);
            etScallingFactor.setText(strScaling);
            etAttenuationFactor.setText(strAttenuation);

            diameter = Double.parseDouble(strDiameter);
            density = Double.parseDouble(strDensity);
            burden = Double.parseDouble(strBurden);
            spacing = Double.parseDouble(strSpacing);
            holeLenght = Double.parseDouble(strHoleLength);
            stemming = Double.parseDouble(strStemLength);
            rockDensity = Double.parseDouble(strRockDensity);
            distance = Double.parseDouble(strDistance);
            scallingFactor = Double.parseDouble(strScaling);
            attenuation = Double.parseDouble(strAttenuation);
        }
    }

}
