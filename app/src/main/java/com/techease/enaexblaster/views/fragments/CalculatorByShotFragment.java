package com.techease.enaexblaster.views.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.techease.enaexblaster.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CalculatorByShotFragment extends Fragment {
    View view;
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
    @BindView(R.id.et_shot_rows)
    EditText etNoOfRows;
    @BindView(R.id.et_shot_holes)
    EditText etShotHoles;
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
    @BindView(R.id.tv_shot_lbs_hole)
    TextView tvLBSHole;
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

    private double density = 0, diameter = 0, burden = 0, spacing = 0, benchHeight = 0, subDrill = 0, stemming = 0, noOfRows = 0, holePerRows = 0,
            holePerMs = 0, distance = 0, scallingFactor = 0, attenuation = 0;

    private double holeLenght, volume, LBSPerHole, PF, metricSDOB, imperialSDOB, MIC, SD, PPV, kgsPerHole;
    private boolean check = true;
    private boolean checkCalculator = true;

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
        layoutVolume.setVisibility(View.GONE);

        double totalHoles = noOfRows * holePerRows;

        double holeLenght = benchHeight + subDrill;

        double drillLenght = totalHoles * holeLenght;

        double shotVolume = burden * spacing * benchHeight;

        double lbsPerHole = (density / 1000) * (Math.PI * Math.pow((diameter / 2), 2)) * (benchHeight + subDrill - stemming);

        double PF = lbsPerHole / shotVolume;

        double mic = lbsPerHole * holePerMs;

        double value1 = ((stemming + ((5 * (diameter / 1000)))));
        double value2 = (density / 1000) * (Math.PI * Math.pow((diameter / 2), 2)) * ((10 * (diameter / 1000)));
        double sdob = value1 / Math.pow(value2, 0.3333);
        double SD, PPV;

        if (distance == 0) {
            SD = 0;
        } else {
            SD = (distance / Math.pow(mic, 0.5));
        }

        PPV = scallingFactor * (Math.pow(SD, attenuation));


        tvTotalHoles.setText(String.format("%.0f", totalHoles));
        tvShotLenght.setText(String.format("%.2f", holeLenght));
        tvShotDrillLenght.setText(String.format("%.0f", drillLenght));
        tvVolume.setText(String.format("%.2f", shotVolume));
        tvLBSHole.setText(String.format("%.2f", lbsPerHole));
        tvSDOB.setText(String.format("%.2f", sdob));
        tvPF.setText(String.format("%.2f", PF));
        tvMic.setText(String.format("%.2f", mic));
        tvSD.setText(String.format("%.2f", SD));
        tvPPV.setText(String.format("%.2f", PPV));


    }

    private void imperialCalculator() {

    }
}
