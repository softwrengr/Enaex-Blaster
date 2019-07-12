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

import butterknife.BindView;
import butterknife.ButterKnife;


public class PFCalculatorFragment extends Fragment implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    View view;
    @BindView(R.id.iv_arrow)
    ImageView ivArrow;
    @BindView(R.id.layout_option)
    RelativeLayout layoutOption;
    @BindView(R.id.layout_below_option)
    LinearLayout layoutMetricImperial;
    @BindView(R.id.btn_metric)
    Button btnMetric;
    @BindView(R.id.btn_imperail)
    Button btnImperial;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.btn_weight)
    Button btnWeight;
    @BindView(R.id.btn_volume)
    Button btnVolume;
    @BindView(R.id.switch_airdeck)
    Switch switchAirDeck;

    @BindView(R.id.et_diameter)
    EditText etDiameter;
    @BindView(R.id.et_density)
    EditText etDensity;
    @BindView(R.id.et_burden)
    EditText etBurden;
    @BindView(R.id.et_spacing)
    EditText etSpacing;
    @BindView(R.id.et_hole_lenght)
    EditText etHoleLenght;
    @BindView(R.id.et_stemming_lenght)
    EditText etStemmingLenght;
    @BindView(R.id.et_rock_density)
    EditText etRockDensity;
    @BindView(R.id.et_airdeck_lenght)
    EditText etAirDeck;

    @BindView(R.id.layout_density)
    RelativeLayout layoutDensity;
    @BindView(R.id.layout_airdeck)
    RelativeLayout layoutAirDeck;


    @BindView(R.id.pf_diameter_unit)
    TextView tvDiameterUnit;
    @BindView(R.id.pf_explosive_unit)
    TextView tvExplosiveDensityUnit;
    @BindView(R.id.pf_burden_unit)
    TextView tvBurdenrUnit;
    @BindView(R.id.pf_spacing_unit)
    TextView tvSpacingUnit;
    @BindView(R.id.pf_stem_unit)
    TextView tvStemLengthrUnit;
    @BindView(R.id.pf_rock_unit)
    TextView tvRockDensityUnit;
    @BindView(R.id.pf_holelength_unit)
    TextView tvHoleLengthUnit;
    @BindView(R.id.pf_airdeck_unit)
    TextView tvAirDeckUnit;
    @BindView(R.id.iv_menu)
    ImageView ivMenu;
    
    @BindView(R.id.tv_result)
    TextView tvResult;

    private boolean check = true, checkVolumeWeight = true,checkAirDeck=true;
    private boolean checkCalculator = true;
    private double density = 0.00, diameter = 0, burden = 0, spacing = 0, stemmingLenght = 0,
            holeLenght = 0, rockDensity = 0.00, airDeck = 0,airDeckOld=0;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_pf, container, false);
        ButterKnife.bind(this, view);

        checkCalculator  = GeneralUtils.getSharedPreferences(getActivity()).getBoolean("check_unit",true);

        if(checkCalculator){
            btnImperial.setBackgroundColor(getActivity().getColor(R.color.grey));
            btnMetric.setBackgroundColor(getActivity().getColor(R.color.silver));
            metricUnits();
        }
        else {
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
                checkVolumeWeight = true;
                layoutDensity.setVisibility(View.GONE);
                btnVolume.setBackgroundColor(getActivity().getColor(R.color.silver));
                btnWeight.setBackgroundColor(getActivity().getColor(R.color.grey));

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
                checkVolumeWeight = false;
                layoutDensity.setVisibility(View.VISIBLE);
                btnVolume.setBackgroundColor(getActivity().getColor(R.color.grey));
                btnWeight.setBackgroundColor(getActivity().getColor(R.color.silver));

                if (checkCalculator) {
                    metricCalculator();
                } else {
                    imperialCalculator();
                }
            }
        });

        switchAirDeck.setOnCheckedChangeListener(this);

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

        etSpacing.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("") || s == null) {
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

        etStemmingLenght.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("") || s == null) {
                    stemmingLenght = 0;
                } else {
                    try {
                        stemmingLenght = Double.parseDouble(s.toString().replace(',', '.'));
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

        etAirDeck.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("") || s == null) {
                    airDeck = 0;
                } else {
                    try {
                        airDeck = Double.parseDouble(s.toString().replace(',', '.'));
                        airDeckOld = Double.parseDouble(s.toString().replace(',', '.'));

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

    private void imperialUnits() {
        tvDiameterUnit.setText("in");
        tvExplosiveDensityUnit.setText("g/cc");
        tvBurdenrUnit.setText("ft");
        tvSpacingUnit.setText("ft");
        tvHoleLengthUnit.setText("ft");
        tvStemLengthrUnit.setText("ft");
        tvRockDensityUnit.setText("g/cc");
        tvAirDeckUnit.setText("ft");
    }

    private void metricUnits() {
        tvDiameterUnit.setText("mm");
        tvExplosiveDensityUnit.setText("g/cc");
        tvBurdenrUnit.setText("m");
        tvSpacingUnit.setText("m");
        tvHoleLengthUnit.setText("m");
        tvStemLengthrUnit.setText("m");
        tvRockDensityUnit.setText("g/cc");
        tvAirDeckUnit.setText("m");
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void switchToMetric() {
        btnImperial.setBackgroundColor(getActivity().getColor(R.color.grey));
        btnMetric.setBackgroundColor(getActivity().getColor(R.color.silver));


        burden = burden / 3.281;
        spacing = spacing / 3.281;
        holeLenght = holeLenght / 3.281;
        stemmingLenght = stemmingLenght / 3.281;
        airDeck = airDeck / 3.281;


        etBurden.setText(String.format("%.1f", Double.valueOf(burden)));
        etSpacing.setText(String.format("%.1f", Double.valueOf(spacing)));
        etHoleLenght.setText(String.format("%.1f", Double.valueOf(holeLenght)));
        etStemmingLenght.setText(String.format("%.1f", Double.valueOf(stemmingLenght)));
        etAirDeck.setText(String.format("%.0f", Double.valueOf(airDeck)));

        metricCalculator();
        metricUnits();

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void switchToImperial() {
        btnImperial.setBackgroundColor(getActivity().getColor(R.color.silver));
        btnMetric.setBackgroundColor(getActivity().getColor(R.color.grey));


        burden = burden * 3.281;
        spacing = spacing * 3.281;
        holeLenght = holeLenght * 3.281;
        stemmingLenght = stemmingLenght * 3.281;
        airDeck = airDeck * 3.281;


        etBurden.setText(String.format("%.0f", Double.valueOf(burden)));
        etSpacing.setText(String.format("%.0f", Double.valueOf(spacing)));
        etHoleLenght.setText(String.format("%.0f", Double.valueOf(holeLenght)));
        etStemmingLenght.setText(String.format("%.0f", Double.valueOf(stemmingLenght)));
        etAirDeck.setText(String.format("%.0f", Double.valueOf(airDeck)));

        imperialCalculator();
        imperialUnits();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            checkAirDeck = true;
            layoutAirDeck.setVisibility(View.VISIBLE);
            airDeck = airDeckOld;
            if (checkCalculator) {
                metricCalculator();
            } else {
                imperialCalculator();
            }
        } else {
            checkAirDeck = false;
            layoutAirDeck.setVisibility(View.GONE);
            airDeck = 0;
            if (checkCalculator) {
                metricCalculator();
            } else {
                imperialCalculator();
            }
        }
    }

    private void metricCalculator(){
        double value1,value2,value3,powderFactor;


        value1 = ((density/1000)*(Math.PI*Math.pow(diameter/2,2)));
        value2 = holeLenght - stemmingLenght;
        value3 = burden * spacing * holeLenght;



        if(checkVolumeWeight){   //if volume is selected
            powderFactor = value1 * value2 / value3 ;
            tvResult.setText(String.format("%.2f",powderFactor) + " kg/m³");
        }

        if(!checkVolumeWeight){    //if weight is selected
            powderFactor = (value1 * value2) / (value3 * rockDensity);
            tvResult.setText(String.format("%.2f",powderFactor) + " kg/tonne");
        }

        if(checkVolumeWeight && checkAirDeck){   //if volume is selected and airdeck is selected
            value2 = holeLenght - stemmingLenght - airDeck;
            powderFactor = (value1 * value2) / value3;
            tvResult.setText(String.format("%.2f",powderFactor)+ " kg/m³");
        }

        if(!checkVolumeWeight && checkAirDeck){   //if weight is selected and airdeck is  selected
            value2 = holeLenght - stemmingLenght - airDeck;
            powderFactor = (value1 * value2) / (value3 * rockDensity);
            tvResult.setText(String.format("%.2f",powderFactor) + " kg/tonne");
        }
    }

    private void imperialCalculator(){
        double value1,value2,value3,powderFactor;

        value1 = ((density*62.4)*(Math.PI*Math.pow(diameter/24,2)));
        value2 = holeLenght - stemmingLenght;
        value3 = (burden * spacing * holeLenght) / 27;


        if(checkVolumeWeight){   //if volume is selected
            powderFactor = (value1 * value2) / value3 ;
            tvResult.setText(String.format("%.2f",powderFactor) + " lb/yd³");
        }

        if(!checkVolumeWeight){    //if weight is selected
            powderFactor = (value1 * value2) / (value3 * rockDensity * 0.841);
            tvResult.setText(String.format("%.2f",powderFactor)  + " lb/ton");
        }

        if(checkVolumeWeight && checkAirDeck){   //if volume is selected and airdeck is selected
            value2 = holeLenght - stemmingLenght - airDeck;
            powderFactor = (value1 * value2) / value3;
            tvResult.setText(String.format("%.2f",powderFactor) + "  lb/yd³");
        }

        if(!checkVolumeWeight && checkAirDeck){   //if weight is selected and airdeck is selected
            value2 = holeLenght - stemmingLenght - airDeck;
            powderFactor = (value1 * value2) / (value3 * rockDensity * 0.841);
            tvResult.setText(String.format("%.2f",powderFactor)+ " lb/ton");
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
                        SavingLoadingData.showPowderFactorDialog(getActivity(),diameter,density,burden,spacing,
                                holeLenght,stemmingLenght,rockDensity,airDeck);
                        break;
                    case R.id.load:
                        Bundle bundle = new Bundle();
                        bundle.putString("checkingScreen","pf");
                        GeneralUtils.connectFragmentWithBack(getActivity(),new LoadDataFragment()).setArguments(bundle);
                        break;
                    case R.id.email:
                        NetworkUtilities.sendMail(getActivity(),"www.enaex.com/powder_factor");
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
            String strAirDeck = bundle.getString("airDeck");

            etDiameter.setText(strDiameter);
            etDensity.setText(strDensity);
            etBurden.setText(strBurden);
            etSpacing.setText(strSpacing);
            etHoleLenght.setText(strHoleLength);
            etStemmingLenght.setText(strStemLength);
            etRockDensity.setText(strRockDensity);
            etAirDeck.setText(strAirDeck);

            diameter = Double.parseDouble(strDiameter);
            density = Double.parseDouble(strDensity);
            burden = Double.parseDouble(strBurden);
            spacing = Double.parseDouble(strSpacing);
            holeLenght = Double.parseDouble(strHoleLength);
            stemmingLenght = Double.parseDouble(strStemLength);
            rockDensity = Double.parseDouble(strRockDensity);
            airDeck = Double.parseDouble(strRockDensity);
        }
    }
}
