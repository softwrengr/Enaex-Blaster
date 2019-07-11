package com.techease.enaexblaster.views.calculators;

import android.app.Dialog;
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

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ScaledDistanceFragment extends Fragment implements View.OnClickListener {
    View view;
    @BindView(R.id.iv_arrow)
    ImageView ivArrow;
    @BindView(R.id.et_distance)
    EditText etDistance;
    @BindView(R.id.et_mic)
    EditText etMIC;
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

    private double distance = 0, mic = 0;
    private boolean check = true;
    private boolean checkCalculator = true;
    DecimalFormat formatter, dfnd;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_scaled_distance, container, false);
        ButterKnife.bind(this, view);
        checkCalculator = GeneralUtils.getSharedPreferences(getActivity()).getBoolean("check_unit", true);
        formatter = new DecimalFormat("#,###,###.#");
        dfnd = new DecimalFormat("#,###,###");

        if (checkCalculator) {
            btnImperial.setBackgroundColor(getActivity().getColor(R.color.grey));
            btnMetric.setBackgroundColor(getActivity().getColor(R.color.silver));
            tvDistanceUnit.setText("m");
            tvMicUnit.setText("kg");
            tvResult.setText(String.format("%.1f", 0.0) + " m/√kg");
        } else {
            btnImperial.setBackgroundColor(getActivity().getColor(R.color.silver));
            btnMetric.setBackgroundColor(getActivity().getColor(R.color.grey));
            tvDistanceUnit.setText("ft");
            tvMicUnit.setText("lb");
            tvResult.setText(String.format("%.1f", 0.0) + " ft/√lb");
        }
        initViews();
        showSaveData();
        return view;
    }

    private void initViews() {

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
                tvDistanceUnit.setText("m");
                tvMicUnit.setText("kg");


                checkCalculator = true;
                btnImperial.setBackgroundColor(getActivity().getColor(R.color.grey));
                btnMetric.setBackgroundColor(getActivity().getColor(R.color.silver));

                metricCalculation();
            }
        });

        btnImperial.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                tvDistanceUnit.setText("ft");
                tvMicUnit.setText("lb");
                checkCalculator = false;
                btnImperial.setBackgroundColor(getActivity().getColor(R.color.silver));
                btnMetric.setBackgroundColor(getActivity().getColor(R.color.grey));

                imperialCalculation();
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
                    etDistance.removeTextChangedListener(this);

                    try {
                        distance = Double.parseDouble(s.toString());
//                        etDistance.setText(dfnd.format(distance));
//                        etDistance.setSelection(etDistance.getText().length());
                        if (checkCalculator) {
                            metricCalculation();
                        } else {
                            imperialCalculation();
                        }
                    } catch (NumberFormatException e) {
                        //Error
                    }

                    etDistance.addTextChangedListener(this);
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

        ivMenu.setOnClickListener(this);


    }

    private void metricCalculation() {
        double SD;
        if (distance == 0) {
            SD = 0;
        } else {
            SD = (distance / Math.sqrt(mic));
        }

        String yourFormattedResult = formatter.format(SD);
        tvResult.setText(yourFormattedResult + " m/√kg");

    }

    private void imperialCalculation() {
        double SD;
        if (distance == 0) {
            SD = 0;
        } else {
            SD = (distance / Math.sqrt(mic));
        }

        tvResult.setText(String.format("%.1f", SD) + " ft/√lb");
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
                        SavingLoadingData.showScaledDistanceDialog(getActivity(),distance,mic);
                        break;
                    case R.id.load:
                        Bundle bundle = new Bundle();
                        bundle.putString("checkingScreen","scaledDistance");
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
            String strDistance = bundle.getString("distance");
            String strMic = bundle.getString("mic");
            etDistance.setText(strDistance);
            etMIC.setText(strMic);
            distance = Double.parseDouble(strDistance);
            mic = Double.parseDouble(strMic);
        }
    }

}
