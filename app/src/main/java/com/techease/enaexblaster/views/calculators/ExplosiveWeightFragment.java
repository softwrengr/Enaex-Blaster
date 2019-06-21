package com.techease.enaexblaster.views.calculators;

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
import android.widget.ImageView;
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
    @BindView(R.id.et_total_holes)
    EditText etTotlaHoles;
    @BindView(R.id.tv_result)
    TextView tvResult;
    @BindView(R.id.iv_back)
    ImageView ivBack;

    double diameter = 0, density = 0, holeLenght = 0, stemLenght = 0,totalHoles=0;

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

                        imperialCalculator();

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

                        imperialCalculator();

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

                        imperialCalculator();

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

                        imperialCalculator();

                    } catch (NumberFormatException e) {
                        //Error
                    }

                }

            }
        });

        etTotlaHoles.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("") || s == null) {
                    totalHoles = 0;
                } else {
                    try {
                        totalHoles = Double.parseDouble(s.toString().replace(',', '.'));

                        imperialCalculator();

                    } catch (NumberFormatException e) {
                        //Error
                    }

                }

            }
        });
    }


    private void imperialCalculator() {

        double lbsPerHole = (density * 62.4) * (Math.PI * Math.pow((diameter / 24), 2)) * (holeLenght - stemLenght);

        double totalExplosive = lbsPerHole * totalHoles;


        tvResult.setText(String.format("%.1f", totalExplosive));
    }
}
