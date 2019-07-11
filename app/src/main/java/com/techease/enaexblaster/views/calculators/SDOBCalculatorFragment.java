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
import android.widget.Toast;

import com.techease.enaexblaster.R;
import com.techease.enaexblaster.helpers.SavingLoadingData;
import com.techease.enaexblaster.saveLoadData.LoadDataFragment;
import com.techease.enaexblaster.utilities.GeneralUtils;
import com.techease.enaexblaster.views.fragments.CalculatorsHomeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SDOBCalculatorFragment extends Fragment implements View.OnClickListener {
    View view;
    @BindView(R.id.iv_arrow)
    ImageView ivArrow;
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
    @BindView(R.id.iv_graphics)
    ImageView ivGraphics;
    @BindView(R.id.iv_menu)
    ImageView ivMenu;

    double diameter = 0, density = 0, holeLenght = 0, stemLenght = 0, metricResult, imperailResult;
    private boolean check = true;
    private boolean checkCalculator = true;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_sdobcalculator, container, false);
        ButterKnife.bind(this, view);

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
                metricCalculator();
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
                imperialCalculator();
                imperialUnits();
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
        double d, C, Wc, SDOB;

        d = stemLenght + (5 * (diameter / 1000));
        C = (density / 1000) * (Math.PI * Math.pow((diameter / 2), 2));
        Wc = C * (10 * (diameter / 1000));

        SDOB = d / Math.pow(Wc, 0.3333);

        tvResult.setText(String.format("%.2f", SDOB) + " m/∛kg");
        checkGraphics(SDOB);
    }


    private void imperialCalculator() {
        double d, C, Wc, SDOB;

        d = stemLenght + (5 * diameter / 12);

        C = (density * 62.4) * (Math.PI * Math.pow((diameter / 24), 2));

        Wc = C * (10 * diameter / 12);

        SDOB = d / Math.pow(Wc, 0.3333);

        tvResult.setText(String.format("%.1f", SDOB) + " ft/∛lb");
        checkGraphics(SDOB);
    }

    private void imperialUnits() {
        tvDiameterUnit.setText("in");
        tvExplosiveUnit.setText("g/cc");
        tvLenghtUnit.setText("ft");
        tvStemUnit.setText("ft");
    }

    private void metricUnits() {
        tvDiameterUnit.setText("mm");
        tvExplosiveUnit.setText("g/cc");
        tvLenghtUnit.setText("m");
        tvStemUnit.setText("m");
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
                        SavingLoadingData.showSdobDialog(getActivity(),diameter,density,holeLenght,stemLenght);
                        break;
                    case R.id.load:
                        Bundle bundle = new Bundle();
                        bundle.putString("checkingScreen","sdob");
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



    private void showSaveData() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String strDiameter = bundle.getString("diameter");
            String strDensity = bundle.getString("density");
            String strHoleLength = bundle.getString("holeLength");
            String strStemLength = bundle.getString("stemLength");

            etDiameter.setText(strDiameter);
            etDensity.setText(strDensity);
            etHoleLenght.setText(strHoleLength);
            etStemLenght.setText(strStemLength);

            diameter = Double.parseDouble(strDiameter);
            density = Double.parseDouble(strDensity);
            holeLenght = Double.parseDouble(strHoleLength);
            stemLenght = Double.parseDouble(strStemLength);

        }
    }
}
