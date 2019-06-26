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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.techease.enaexblaster.R;
import com.techease.enaexblaster.utilities.GeneralUtils;
import com.techease.enaexblaster.views.fragments.CalculatorsHomeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ExplosiveWeightFragment extends Fragment {
    View view;
    @BindView(R.id.et_diameter)
    EditText etDiameter;
    @BindView(R.id.et_density)
    EditText etDensity;
    @BindView(R.id.et_hole_lenght)
    EditText etHoleLenght;
    @BindView(R.id.et_stem_lenght)
    EditText etStemLenght;
    @BindView(R.id.tv_result)
    TextView tvResult;
    @BindView(R.id.iv_back)
    ImageView ivBack;

    @BindView(R.id.layout_option)
    RelativeLayout layoutOption;
    @BindView(R.id.layout_below_option)
    LinearLayout layoutMetricImperial;
    @BindView(R.id.btn_metric)
    Button btnMetric;
    @BindView(R.id.btn_imperail)
    Button btnImperial;

    @BindView(R.id.tv_diameter_unit)
    TextView tvDiameterUnit;
    @BindView(R.id.tv_explosive_unit)
    TextView tvExplosiveUnit;
    @BindView(R.id.tv_lenght_unit)
    TextView tvLenghtUnit;
    @BindView(R.id.tv_stem_unit)
    TextView tvStemUnit;

    double diameter = 0, density = 0, holeLenght = 0, stemLenght =0;
    private boolean check = true;
    private boolean checkCalculator = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_explosive_weight, container, false);
        ButterKnife.bind(this,view);
        intiViews();
        return view;
    }

    private void intiViews() {
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
                checkCalculator = true;
                btnImperial.setBackgroundColor(getActivity().getColor(R.color.grey));
                btnMetric.setBackgroundColor(getActivity().getColor(R.color.silver));
                metricCalculator();

                tvDiameterUnit.setText("mm");
                tvExplosiveUnit.setText("g/cc");
                tvLenghtUnit.setText("m");
                tvStemUnit.setText("m");

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

                tvDiameterUnit.setText("in");
                tvExplosiveUnit.setText("g/cc");
                tvLenghtUnit.setText("ft");
                tvStemUnit.setText("ft");
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
                    density = 0;
                } else {
                    try {
                        density = Double.parseDouble(s.toString().replace(',', '.'));

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

        etHoleLenght.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("") || s == null) {
                    holeLenght = 0;
                } else {
                    try {
                        holeLenght = Double.parseDouble(s.toString().replace(',', '.'));

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

        etStemLenght.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("") || s == null) {
                    stemLenght = 0;
                } else {
                    try {
                        stemLenght = Double.parseDouble(s.toString().replace(',', '.'));

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
        double value1,value2,value3,toalExplosive;

        value1 = (density / 1000);
        value2 = (Math.PI * Math.pow((diameter / 2), 2));
        value3 = (holeLenght - stemLenght);

        toalExplosive = (value1 * value2) * value3;

        tvResult.setText(String.format("%.1f", toalExplosive));
    }


    private void imperialCalculator() {
        double value1,value2,value3,toalExplosive;

        value1 = (density * 62.4);
        value2 = (Math.PI * Math.pow((diameter / 24), 2));
        value3 = (holeLenght - stemLenght);

        toalExplosive = (value1 * value2) * value3;

        tvResult.setText(String.format("%.1f", toalExplosive));
    }
}
