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
import java.text.ParseException;

import butterknife.BindView;
import butterknife.ButterKnife;


public class VibrationCalculatorFragment extends Fragment implements View.OnClickListener {
    View view;
    @BindView(R.id.iv_arrow)
    ImageView ivArrow;
    @BindView(R.id.et_distance)
    EditText etDistance;
    @BindView(R.id.et_mic)
    EditText etMIC;
    @BindView(R.id.et_scalling_factor)
    EditText etScallingFactor;
    @BindView(R.id.et_attenuation_factor)
    EditText etAttenuationFactor;
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
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_menu)
    ImageView ivMenu;

    private double distance = 0, mic = 0, scallingFactor = 0, attenuationFactor = -1.6;
    private boolean check = true;
    private boolean checkCalculator = true;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_vibration_calculator, container, false);
        ButterKnife.bind(this, view);

        checkCalculator = GeneralUtils.getSharedPreferences(getActivity()).getBoolean("check_unit", false);


        if (checkCalculator) {
            btnImperial.setBackgroundColor(getActivity().getColor(R.color.grey));
            btnMetric.setBackgroundColor(getActivity().getColor(R.color.silver));
            scallingFactor = 1140;
            etScallingFactor.setText("1140");
            tvDistanceUnit.setText("m");
            tvMicUnit.setText("kg");
        } else {
            btnImperial.setBackgroundColor(getActivity().getColor(R.color.silver));
            btnMetric.setBackgroundColor(getActivity().getColor(R.color.grey));
            scallingFactor = 160;
            etScallingFactor.setText("160");
            tvDistanceUnit.setText("ft");
            tvMicUnit.setText("lb");
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
                switchToImperial();


                btnImperial.setClickable(false);
                btnMetric.setClickable(true);
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
                            imperialCalculation();
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
                    attenuationFactor = 0;
                } else {
                    try {
                        attenuationFactor = Double.parseDouble(s.toString().replace(',', '.'));
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
        double SD, PPV;

        if (distance == 0) {
            SD = 0;
        } else {
            SD = (distance / Math.sqrt(mic));
        }

        PPV = scallingFactor * (Math.pow(SD, attenuationFactor));

        tvResult.setText(String.format("%.2f", PPV) + "  in/s");

    }

    private void metricCalculation() {
        double SD, PPV;

        if (distance == 0) {
            SD = 0;
        } else {
            SD = (distance / Math.sqrt(mic));
        }

        PPV = scallingFactor * (Math.pow(SD, attenuationFactor));

        tvResult.setText(String.format("%.1f", PPV) + "  mm/s");

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
                        SavingLoadingData.showVibrationDialog(getActivity(), distance, mic, scallingFactor, attenuationFactor);
                        break;
                    case R.id.load:
                        Bundle bundle = new Bundle();
                        bundle.putString("checkingScreen", "vibration");
                        GeneralUtils.connectFragmentWithBack(getActivity(), new LoadDataFragment()).setArguments(bundle);
                        break;
                    case R.id.email:
                        NetworkUtilities.sendMail(getActivity(),
                                "www.enaexusa.com/vibration?distance=" + distance
                                        + "&mic="+ mic
                                        + "&scaling="+ scallingFactor
                                        + "&attenuation="+ attenuationFactor
                                        + "&unit="+ String.valueOf(checkCalculator));
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void switchToMetric() {
        distance = distance / 3.28084;
        mic = mic * 0.453592;
        scallingFactor = 1140;

        etDistance.setText(String.format("%.0f", Double.valueOf(distance)));
        etMIC.setText(String.format("%.0f", Double.valueOf(mic)));
        etScallingFactor.setText("1140");

        tvDistanceUnit.setText("m");
        tvMicUnit.setText("kg");


        metricCalculation();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void switchToImperial() {
        distance = distance * 3.28084;
        mic = mic / 0.453592;
        scallingFactor = 160;


        etDistance.setText(String.format("%.0f", Double.valueOf(distance)));
        etMIC.setText(String.format("%.0f", Double.valueOf(mic)));
        etScallingFactor.setText("160");

        tvDistanceUnit.setText("ft");
        tvMicUnit.setText("lb");

        imperialCalculation();


    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void showSaveData() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String strDistance = bundle.getString("distance");
            String strMic = bundle.getString("mic");
            String strScalingFactor = bundle.getString("scaling");
            String strAttenuation = bundle.getString("attenuation");
            checkCalculator = Boolean.parseBoolean(bundle.getString("unit"));

            etDistance.setText(strDistance);
            etMIC.setText(strMic);
            etScallingFactor.setText(strScalingFactor);
            etAttenuationFactor.setText(strAttenuation);

            distance = Double.parseDouble(strDistance);
            mic = Double.parseDouble(strMic);
            scallingFactor = Double.parseDouble(strScalingFactor);
            attenuationFactor = Double.parseDouble(strAttenuation);

            if(checkCalculator){
                btnImperial.setBackgroundColor(getActivity().getColor(R.color.grey));
                btnMetric.setBackgroundColor(getActivity().getColor(R.color.silver));
                scallingFactor = 1140;
                etScallingFactor.setText("1140");
                tvDistanceUnit.setText("m");
                tvMicUnit.setText("kg");
                metricCalculation();
            }
            else {
                btnImperial.setBackgroundColor(getActivity().getColor(R.color.silver));
                btnMetric.setBackgroundColor(getActivity().getColor(R.color.grey));
                scallingFactor = 160;
                etScallingFactor.setText("160");
                tvDistanceUnit.setText("ft");
                tvMicUnit.setText("lb");
                imperialCalculation();
            }
        }
    }
}
