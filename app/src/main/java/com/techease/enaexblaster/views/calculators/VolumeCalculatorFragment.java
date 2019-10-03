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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.techease.enaexblaster.R;
import com.techease.enaexblaster.helpers.SavingLoadingData;
import com.techease.enaexblaster.saveLoadData.LoadDataFragment;
import com.techease.enaexblaster.utilities.GeneralUtils;
import com.techease.enaexblaster.utilities.NetworkUtilities;
import com.techease.enaexblaster.views.fragments.CalculatorsHomeFragment;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;


public class VolumeCalculatorFragment extends Fragment implements View.OnClickListener {
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
    @BindView(R.id.iv_menu)
    ImageView ivMenu;

    private double burden = 0, spacing = 0, averageDepth = 0, rockDensity = 0, noOfHOles = 1;
    private boolean check = true;
    private boolean checkCalculator = true, checkWeight = false;
    DecimalFormat formatter;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_volume_calculator, container, false);
        ButterKnife.bind(this, view);
        formatter = new DecimalFormat("#,###,###");

        checkCalculator = GeneralUtils.getSharedPreferences(getActivity()).getBoolean("check_unit", false);


        if (checkCalculator) {
            btnImperial.setBackgroundColor(getActivity().getResources().getColor(R.color.grey));
            btnMetric.setBackgroundColor(getActivity().getResources().getColor(R.color.silver));
            metricUnits();
        } else {
            btnImperial.setBackgroundColor(getActivity().getResources().getColor(R.color.silver));
            btnMetric.setBackgroundColor(getActivity().getResources().getColor(R.color.grey));
            imperialUnits();
        }
        initViews();
        showSaveData();
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

        btnVolume.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                checkWeight = false;
                layoutRockDensity.setVisibility(View.GONE);
                btnVolume.setBackgroundColor(getActivity().getColor(R.color.silver));
                btnWeight.setBackgroundColor(getActivity().getColor(R.color.grey));
                if (checkCalculator) {
                    metricCalculation();
                } else {
                    imperialCalculation();
                }
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
                if (checkCalculator) {
                    metricCalculation();
                } else {
                    imperialCalculation();
                }
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
                btnImperial.setBackgroundColor(getActivity().getColor(R.color.silver));
                btnMetric.setBackgroundColor(getActivity().getColor(R.color.grey));
                switchToImperial();  //all values converting to imperial calculator

                btnImperial.setClickable(false);
                btnMetric.setClickable(true);
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
                    noOfHOles = 1;
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

        if (checkWeight) { //if weight is selected
            double volume = ((burden * spacing * averageDepth) / 27) * rockDensity * 0.841 * noOfHOles;
            String yourFormattedResult = formatter.format(volume);
            tvVolume.setText(yourFormattedResult + " tons");
        } else {  //if volume if selected
            double volume = ((burden * spacing * averageDepth) / 27) * noOfHOles;
            String yourFormattedResult = formatter.format(volume);
            tvVolume.setText(yourFormattedResult + " yd³");
        }


    }

    private void metricCalculation() {

        if (checkWeight) { //if weight is selected
            double volume = (burden * spacing * averageDepth) * rockDensity * noOfHOles;
            String yourFormattedResult = formatter.format(volume);
            tvVolume.setText(yourFormattedResult + " tonnes");
        } else { //if volume if selected
            double volume = (burden * spacing * averageDepth) * noOfHOles;
            String yourFormattedResult = formatter.format(volume);
            tvVolume.setText(yourFormattedResult + " m³");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void switchToMetric() {
        burden = burden * 0.3048;
        spacing = spacing * 0.3048;
        averageDepth = averageDepth * 0.3048;

        etBurden.setText(String.format("%.1f", Double.valueOf(burden)));
        etSpacing.setText(String.format("%.1f", Double.valueOf(spacing)));
        etAverageDepth.setText(String.format("%.1f", Double.valueOf(averageDepth)));
        etNoHoles.setText(String.format("%.1f", Double.valueOf(noOfHOles)));
        etRockDensity.setText(String.format("%.2f", Double.valueOf(rockDensity)));


        metricCalculation();
        metricUnits();

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void switchToImperial() {
        burden = burden / 0.3048;
        spacing = spacing / 0.3048;
        averageDepth = averageDepth / 0.3048;

        etBurden.setText(String.format("%.1f", Double.valueOf(burden)));
        etSpacing.setText(String.format("%.1f", Double.valueOf(spacing)));
        etAverageDepth.setText(String.format("%.1f", Double.valueOf(averageDepth)));
        etNoHoles.setText(String.format("%.1f", Double.valueOf(noOfHOles)));
        etRockDensity.setText(String.format("%.2f", Double.valueOf(rockDensity)));


        imperialCalculation();
        imperialUnits();
    }

    private void metricUnits() {
        tvBurdenUnit.setText("m");
        tvSpacingUnit.setText("m");
        tvDepthUnit.setText("m");
        tvDensityUnit.setText("g/cc");
    }

    private void imperialUnits() {
        tvBurdenUnit.setText("ft");
        tvSpacingUnit.setText("ft");
        tvDepthUnit.setText("ft");
        tvDensityUnit.setText("g/cc");
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
                        SavingLoadingData.showVolumeDialog(getActivity(), burden, spacing, averageDepth,
                                noOfHOles, rockDensity, checkCalculator, checkWeight);
                        break;
                    case R.id.load:
                        Bundle bundle = new Bundle();
                        bundle.putString("checkingScreen", "volume");
                        GeneralUtils.connectFragmentWithBack(getActivity(), new LoadDataFragment()).setArguments(bundle);
                        break;
                    case R.id.email:
                        NetworkUtilities.sendMail(getActivity(),
                                "www.enaexusa.com/volume?burden=" + burden
                                        + "&spacing=" + spacing
                                        + "&average_depth=" + averageDepth
                                        + "&holes=" + noOfHOles
                                        + "&rockDensity=" + rockDensity
                                        + "&checkWeight=" + checkWeight
                                        + "&unit=" + String.valueOf(checkCalculator));
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void showSaveData() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String strBurden = bundle.getString("burden");
            String strSpacing = bundle.getString("spacing");
            String strAverageDepth = bundle.getString("average_depth");
            String strHoles = bundle.getString("holes");
            String strRockDensity = bundle.getString("rockDensity");
            checkCalculator = Boolean.parseBoolean(bundle.getString("unit"));
            checkWeight = Boolean.parseBoolean(bundle.getString("checkWeight"));

            etBurden.setText(strBurden);
            etSpacing.setText(strSpacing);
            etAverageDepth.setText(strAverageDepth);
            etNoHoles.setText(strHoles);
            etRockDensity.setText(strRockDensity);

            burden = Double.parseDouble(strBurden);
            spacing = Double.parseDouble(strSpacing);
            averageDepth = Double.parseDouble(strAverageDepth);
            noOfHOles = Double.parseDouble(strHoles);
            rockDensity = Double.parseDouble(strRockDensity);


            if (checkWeight) {
                layoutRockDensity.setVisibility(View.VISIBLE);
                btnVolume.setBackgroundColor(getActivity().getColor(R.color.grey));
                btnWeight.setBackgroundColor(getActivity().getColor(R.color.silver));
                if (checkCalculator) {
                    btnImperial.setBackgroundColor(getActivity().getResources().getColor(R.color.grey));
                    btnMetric.setBackgroundColor(getActivity().getResources().getColor(R.color.silver));
                    metricCalculation();
                    metricUnits();
                } else {
                    btnImperial.setBackgroundColor(getActivity().getResources().getColor(R.color.silver));
                    btnMetric.setBackgroundColor(getActivity().getResources().getColor(R.color.grey));
                    imperialCalculation();
                    imperialUnits();
                }
            } else {
                layoutRockDensity.setVisibility(View.GONE);
                btnVolume.setBackgroundColor(getActivity().getColor(R.color.silver));
                btnWeight.setBackgroundColor(getActivity().getColor(R.color.grey));
                if (checkCalculator) {
                    btnImperial.setBackgroundColor(getActivity().getResources().getColor(R.color.grey));
                    btnMetric.setBackgroundColor(getActivity().getResources().getColor(R.color.silver));
                    metricCalculation();
                    metricUnits();
                } else {
                    btnImperial.setBackgroundColor(getActivity().getResources().getColor(R.color.silver));
                    btnMetric.setBackgroundColor(getActivity().getResources().getColor(R.color.grey));
                    imperialCalculation();
                    imperialUnits();
                }
            }

        }
    }
}
