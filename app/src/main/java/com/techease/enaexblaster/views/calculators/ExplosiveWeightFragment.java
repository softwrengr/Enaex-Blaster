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
import com.techease.enaexblaster.utilities.NetworkUtilities;
import com.techease.enaexblaster.views.fragments.CalculatorsHomeFragment;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ExplosiveWeightFragment extends Fragment implements View.OnClickListener {
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
    @BindView(R.id.iv_menu)
    ImageView ivMenu;

    double diameter = 0, density = 0, holeLenght = 0, stemLenght =0;
    private boolean check = true;
    private boolean checkCalculator = true;
    DecimalFormat formatter;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_explosive_weight, container, false);
        ButterKnife.bind(this,view);
        formatter = new DecimalFormat("#,###,###.#");

        checkCalculator  = GeneralUtils.getSharedPreferences(getActivity()).getBoolean("check_unit",false);

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
        intiViews();
        showSaveData();
        return view;
    }

    private void intiViews() {
        ivMenu.setOnClickListener(this);

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

        String yourFormattedResult = formatter.format(toalExplosive);

        tvResult.setText(yourFormattedResult + " kg");
    }


    private void imperialCalculator() {
        double value1,value2,value3,toalExplosive;

        value1 = (density * 62.4);
        value2 = (Math.PI * Math.pow((diameter / 24), 2));
        value3 = (holeLenght - stemLenght);

        toalExplosive = (value1 * value2) * value3;

        String yourFormattedResult = formatter.format(toalExplosive);
        tvResult.setText(yourFormattedResult + " lbs");
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void switchToMetric() {
        btnImperial.setBackgroundColor(getActivity().getColor(R.color.grey));
        btnMetric.setBackgroundColor(getActivity().getColor(R.color.silver));


        diameter = diameter * 25.4;
        holeLenght = holeLenght / 3.28084;
        stemLenght = stemLenght / 3.28084;

        etDiameter.setText(String.format("%.0f", Double.valueOf(diameter)));
        etHoleLenght.setText(String.format("%.1f", Double.valueOf(holeLenght)));
        etStemLenght.setText(String.format("%.1f", Double.valueOf(stemLenght)));

        metricCalculator();
        metricUnits();

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void switchToImperial() {
        btnImperial.setBackgroundColor(getActivity().getColor(R.color.silver));
        btnMetric.setBackgroundColor(getActivity().getColor(R.color.grey));



        diameter = diameter / 25.4;
        holeLenght = holeLenght * 3.28084;
        stemLenght = stemLenght * 3.28084;

        etDiameter.setText(String.format("%.3f", Double.valueOf(diameter)));
        etHoleLenght.setText(String.format("%.1f", Double.valueOf(holeLenght)));
        etStemLenght.setText(String.format("%.1f", Double.valueOf(stemLenght)));

        imperialCalculator();
        imperialUnits();
    }

    private void metricUnits(){
        tvDiameterUnit.setText("mm");
        tvExplosiveUnit.setText("g/cc");
        tvLenghtUnit.setText("m");
        tvStemUnit.setText("m");
    }

    private void imperialUnits(){
        tvDiameterUnit.setText("in");
        tvExplosiveUnit.setText("g/cc");
        tvLenghtUnit.setText("ft");
        tvStemUnit.setText("ft");
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
                        SavingLoadingData.showExplosiveWeightDialog(getActivity(),diameter,density,holeLenght,stemLenght,checkCalculator);
                        break;
                    case R.id.load:
                        Bundle bundle = new Bundle();
                        bundle.putString("checkingScreen","explosive_weight");
                        GeneralUtils.connectFragmentWithBack(getActivity(),new LoadDataFragment()).setArguments(bundle);
                        break;
                    case R.id.email:
                        NetworkUtilities.sendMail(getActivity(),
                                "www.enaexusa.com/explosive_weight?diameter="+diameter
                                        + "&density="+ density
                                        + "&holeLength="+ holeLenght
                                        + "&stemLength="+ stemLenght
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
    private void showSaveData() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String strDiameter = bundle.getString("diameter");
            String strDensity = bundle.getString("density");
            String strHoleLength = bundle.getString("holeLength");
            String strStemLength = bundle.getString("stemLength");
            checkCalculator = Boolean.parseBoolean(bundle.getString("unit"));

            etDiameter.setText(strDiameter);
            etDensity.setText(strDensity);
            etHoleLenght.setText(strHoleLength);
            etStemLenght.setText(strStemLength);

            diameter = Double.parseDouble(strDiameter);
            density = Double.parseDouble(strDensity);
            holeLenght = Double.parseDouble(strHoleLength);
            stemLenght = Double.parseDouble(strStemLength);

            if(checkCalculator){
                btnImperial.setBackgroundColor(getActivity().getColor(R.color.grey));
                btnMetric.setBackgroundColor(getActivity().getColor(R.color.silver));
                metricUnits();
                metricCalculator();
            }
            else {
                btnImperial.setBackgroundColor(getActivity().getColor(R.color.silver));
                btnMetric.setBackgroundColor(getActivity().getColor(R.color.grey));
                imperialUnits();
                imperialCalculator();
            }

        }
    }
}
