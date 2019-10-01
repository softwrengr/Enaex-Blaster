package com.techease.enaexblaster.views.fragments;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.techease.enaexblaster.R;
import com.techease.enaexblaster.utilities.GeneralUtils;
import com.techease.enaexblaster.views.calculators.CalculatorByHoleFragment;
import com.techease.enaexblaster.views.calculators.CalculatorByShotFragment;
import com.techease.enaexblaster.views.calculators.ExplosiveWeightFragment;
import com.techease.enaexblaster.views.calculators.PFCalculatorFragment;
import com.techease.enaexblaster.views.calculators.SDOBCalculatorFragment;
import com.techease.enaexblaster.views.calculators.ScaledDistanceFragment;
import com.techease.enaexblaster.views.calculators.VibrationCalculatorFragment;
import com.techease.enaexblaster.views.calculators.VolumeCalculatorFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeFragment extends Fragment {
    View view;
    @BindView(R.id.calculator_layout)
    LinearLayout layoutCalculator;
    @BindView(R.id.contact_layout)
    LinearLayout layoutContact;
    @BindView(R.id.guidline_layout)
    LinearLayout layoutGuideLine;
    @BindView(R.id.product_layout)
    LinearLayout layoutProductInfo;
    @BindView(R.id.setting_layout)
    LinearLayout layoutSetting;
    @BindView(R.id.about_layout)
    LinearLayout layoutAbout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,view);
        onback(view);
        checkDeepLink();

        layoutCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.connectFragmentWithBack(getActivity(),new CalculatorsHomeFragment());
            }
        });

        layoutContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.connectFragmentWithBack(getActivity(),new ContactUsFragment());
            }
        });

        layoutGuideLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.connectFragmentWithBack(getActivity(),new GuideLineFragment());
            }
        });

        layoutProductInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.connectFragmentWithBack(getActivity(),new ProductInfoFragment());
            }
        });

        layoutSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.connectFragmentWithBack(getActivity(),new SettingFragment());
            }
        });

        layoutAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.connectFragmentWithBack(getActivity(),new AboutUsFragment());
            }
        });

        if(!GeneralUtils.getSharedPreferences(getActivity()).getBoolean("disclaimer",false)){
            showDisclaimerDialog();
        }

        return view;
    }

    private void onback(View view) {
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    getActivity().finishAffinity();
                    return true;
                }
                return false;
            }
        });

    }

    private void checkDeepLink(){
        Bundle bundle = new Bundle();
        Uri uri = getActivity().getIntent().getData();
        if(uri != null){
            List<String> params = uri.getPathSegments();
            String id = params.get(params.size()-1);

            switch (id){
                case "hole":
                    GeneralUtils.connectFragment(getActivity(),new CalculatorByHoleFragment());
                    break;
                case "shot":
                    GeneralUtils.connectFragment(getActivity(),new CalculatorByShotFragment());
                    break;
                case "powder_factor":
                    bundle.putString("diameter",uri.getQueryParameter("diameter"));
                    bundle.putString("density",uri.getQueryParameter("density"));
                    bundle.putString("burden",uri.getQueryParameter("burden"));
                    bundle.putString("spacing",uri.getQueryParameter("spacing"));
                    bundle.putString("holeLength",uri.getQueryParameter("holeLength"));
                    bundle.putString("stemLength",uri.getQueryParameter("stemLength"));
                    bundle.putString("rockDensity",uri.getQueryParameter("rockDensity"));
                    bundle.putString("airDeck",uri.getQueryParameter("airDeck"));
                    bundle.putString("checkWeight",uri.getQueryParameter("checkWeight"));
                    bundle.putString("unit",uri.getQueryParameter("unit"));
                    GeneralUtils.connectFragment(getActivity(),new PFCalculatorFragment()).setArguments(bundle);
                    break;
                case "sdob":
                    bundle.putString("diameter",uri.getQueryParameter("diameter"));
                    bundle.putString("density",uri.getQueryParameter("density"));
                    bundle.putString("holeLength",uri.getQueryParameter("holeLength"));
                    bundle.putString("stemLength",uri.getQueryParameter("stemLength"));
                    bundle.putString("unit",uri.getQueryParameter("unit"));
                    GeneralUtils.connectFragment(getActivity(),new SDOBCalculatorFragment()).setArguments(bundle);
                    break;
                case "explosive_weight":
                    bundle.putString("diameter",uri.getQueryParameter("diameter"));
                    bundle.putString("density",uri.getQueryParameter("density"));
                    bundle.putString("holeLength",uri.getQueryParameter("holeLength"));
                    bundle.putString("stemLength",uri.getQueryParameter("stemLength"));
                    bundle.putString("unit",uri.getQueryParameter("unit"));
                    GeneralUtils.connectFragment(getActivity(),new ExplosiveWeightFragment()).setArguments(bundle);
                    break;
                case "vibration":
                    bundle.putString("distance",uri.getQueryParameter("distance"));
                    bundle.putString("mic",uri.getQueryParameter("mic"));
                    bundle.putString("scaling",uri.getQueryParameter("scaling"));
                    bundle.putString("attenuation",uri.getQueryParameter("attenuation"));
                    bundle.putString("unit",uri.getQueryParameter("unit"));
                    GeneralUtils.connectFragment(getActivity(),new VibrationCalculatorFragment()).setArguments(bundle);
                    break;
                case "volume":
                    bundle.putString("burden",uri.getQueryParameter("burden"));
                    bundle.putString("spacing",uri.getQueryParameter("spacing"));
                    bundle.putString("average_depth",uri.getQueryParameter("average_depth"));
                    bundle.putString("holes",uri.getQueryParameter("holes"));
                    bundle.putString("rockDensity",uri.getQueryParameter("rockDensity"));
                    bundle.putString("checkWeight",uri.getQueryParameter("checkWeight"));
                    bundle.putString("unit",uri.getQueryParameter("unit"));
                    GeneralUtils.connectFragment(getActivity(),new VolumeCalculatorFragment()).setArguments(bundle);
                    break;
                case "scaled_distance":
                    bundle.putString("distance",uri.getQueryParameter("distance"));
                    bundle.putString("mic",uri.getQueryParameter("mic"));
                    bundle.putString("unit",uri.getQueryParameter("unit"));
                    GeneralUtils.connectFragmentWithBack(getActivity(),new ScaledDistanceFragment()).setArguments(bundle);
                    break;
            }
        }
    }

    private void showDisclaimerDialog(){
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.disclaimer_dialog);
        dialog.setCancelable(false);
        Button btnOk = dialog.findViewById(R.id.btn_discliamer);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.putBooleanValueInEditor(getActivity(),"disclaimer",true);
                dialog.dismiss();
            }
        });

        dialog.show();
    }

}
