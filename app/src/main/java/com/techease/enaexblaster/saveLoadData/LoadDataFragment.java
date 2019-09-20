package com.techease.enaexblaster.saveLoadData;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.techease.enaexblaster.R;
import com.techease.enaexblaster.adapters.ExplosiveWeightAdapter;
import com.techease.enaexblaster.adapters.HoleAdapter;
import com.techease.enaexblaster.adapters.PowderFactorAdapter;
import com.techease.enaexblaster.adapters.SDOBAdapter;
import com.techease.enaexblaster.adapters.ScaledDistanceAdapter;
import com.techease.enaexblaster.adapters.ShotAdapter;
import com.techease.enaexblaster.adapters.VibrationAdapter;
import com.techease.enaexblaster.adapters.VolumeAdapter;
import com.techease.enaexblaster.models.ExplosiveWeightModel;
import com.techease.enaexblaster.models.HoleModel;
import com.techease.enaexblaster.models.PowderFactorModel;
import com.techease.enaexblaster.models.ScaledDistanceModel;
import com.techease.enaexblaster.models.SdobModel;
import com.techease.enaexblaster.models.ShotModel;
import com.techease.enaexblaster.models.VibrationModel;
import com.techease.enaexblaster.models.VolumeModel;
import com.techease.enaexblaster.sqliteDatabase.ShotCrud;
import com.techease.enaexblaster.utilities.GeneralUtils;
import com.techease.enaexblaster.views.calculators.CalculatorByHoleFragment;
import com.techease.enaexblaster.views.calculators.CalculatorByShotFragment;
import com.techease.enaexblaster.views.calculators.ExplosiveWeightFragment;
import com.techease.enaexblaster.views.calculators.PFCalculatorFragment;
import com.techease.enaexblaster.views.calculators.SDOBCalculatorFragment;
import com.techease.enaexblaster.views.calculators.ScaledDistanceFragment;
import com.techease.enaexblaster.views.calculators.VibrationCalculatorFragment;
import com.techease.enaexblaster.views.calculators.VolumeCalculatorFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoadDataFragment extends Fragment {
    View view;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    ShotCrud enaexCrud;
    LinearLayoutManager layoutManager;
    Bundle bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_load_data, container, false);
        ButterKnife.bind(this, view);


        bundle = this.getArguments();
        if (bundle != null) {
            String strCheckScreen = bundle.getString("checkingScreen");
            laodConcernData(strCheckScreen);

        }


        return view;
    }

    private void laodConcernData(String strScreen) {
        switch (strScreen) {
            case "hole":
                showHoleList();
                break;
            case "scaledDistance":
                showScaledDitanceList();
                break;
            case "vibration":
                showVibrationList();
                break;
            case "sdob":
                showSDOBList();
                break;
            case "pf":
                showPowderFactorList();
                break;
            case "volume":
                showVolumeList();
                break;
            case "explosive_weight":
                showExplosiveWeightList();
                break;
            case "shot":
                showShotList();
                break;
        }
    }

    public void showScaledDitanceList() {
        ArrayList<ScaledDistanceModel> scaledDistanceModelArrayList;
        ScaledDistanceAdapter todoListAdapter;
        enaexCrud = new ShotCrud(getActivity());
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvList.setLayoutManager(layoutManager);
        scaledDistanceModelArrayList = new ArrayList<>();

        Cursor cursor = enaexCrud.getProducts();
        while (cursor.moveToNext()) {
            String time = cursor.getString(1).trim();
            String date = cursor.getString(2).trim();
            String note = cursor.getString(3).trim();

            ScaledDistanceModel model = new ScaledDistanceModel();
            model.setDistance(time);
            model.setMic(date);
            model.setRow_name(note);

            scaledDistanceModelArrayList.add(model);
            todoListAdapter = new ScaledDistanceAdapter(getActivity(), scaledDistanceModelArrayList);
            rvList.setAdapter(todoListAdapter);
            todoListAdapter.notifyDataSetChanged();
        }

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.connectFragmentWithBack(getActivity(),new ScaledDistanceFragment());
            }
        });
    }

    public void showVibrationList() {
        ArrayList<VibrationModel> vibrationModelArrayList;
        VibrationAdapter todoListAdapter;
        enaexCrud = new ShotCrud(getActivity());
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvList.setLayoutManager(layoutManager);
        vibrationModelArrayList = new ArrayList<>();

        Cursor cursor = enaexCrud.getVibraionData();
        while (cursor.moveToNext()) {
            String distance = cursor.getString(1).trim();
            String mic = cursor.getString(2).trim();
            String scallingFactor = cursor.getString(3).trim();
            String attenuation = cursor.getString(4).trim();
            String rowName = cursor.getString(5).trim();

            VibrationModel model = new VibrationModel();
            model.setDistance(distance);
            model.setMic(mic);
            model.setScallingFactor(scallingFactor);
            model.setAttenuation(attenuation);
            model.setRow_name(rowName);

            vibrationModelArrayList.add(model);
            todoListAdapter = new VibrationAdapter(getActivity(), vibrationModelArrayList);
            rvList.setAdapter(todoListAdapter);
            todoListAdapter.notifyDataSetChanged();
        }

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.connectFragmentWithBack(getActivity(),new VibrationCalculatorFragment());
            }
        });
    }

    public void showSDOBList() {
        ArrayList<SdobModel> sdobModelArrayList;
        SDOBAdapter todoListAdapter;
        enaexCrud = new ShotCrud(getActivity());
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvList.setLayoutManager(layoutManager);
        sdobModelArrayList = new ArrayList<>();

        Cursor cursor = enaexCrud.getSDOBData();
        while (cursor.moveToNext()) {
            String diameter = cursor.getString(1).trim();
            String density = cursor.getString(2).trim();
            String holeLength = cursor.getString(3).trim();
            String stemLength = cursor.getString(4).trim();
            String rowName = cursor.getString(5).trim();

            SdobModel model = new SdobModel();
            model.setDiameter(diameter);
            model.setDensity(density);
            model.setHoleLength(holeLength);
            model.setStemLength(stemLength);
            model.setRow_name(rowName);

            sdobModelArrayList.add(model);
            todoListAdapter = new SDOBAdapter(getActivity(), sdobModelArrayList);
            rvList.setAdapter(todoListAdapter);
            todoListAdapter.notifyDataSetChanged();
        }

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.connectFragmentWithBack(getActivity(),new SDOBCalculatorFragment());
            }
        });
    }


    public void showPowderFactorList() {
        ArrayList<PowderFactorModel> powderFactorModelArrayList;
        PowderFactorAdapter powderFactorAdapter;
        enaexCrud = new ShotCrud(getActivity());
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvList.setLayoutManager(layoutManager);
        powderFactorModelArrayList = new ArrayList<>();

        Cursor cursor = enaexCrud.getPowderFactorData();
        while (cursor.moveToNext()) {
            String diameter = cursor.getString(1).trim();
            String density = cursor.getString(2).trim();
            String burden = cursor.getString(3).trim();
            String spacing = cursor.getString(4).trim();
            String holeLength = cursor.getString(5).trim();
            String stemLength = cursor.getString(6).trim();
            String rockDensity = cursor.getString(7).trim();
            String strAirDeck = cursor.getString(8).trim();
            String rowName = cursor.getString(9).trim();

            PowderFactorModel model = new PowderFactorModel();
            model.setDiamter(diameter);
            model.setDensity(density);
            model.setBurden(burden);
            model.setSpacing(spacing);
            model.setHoleLength(holeLength);
            model.setStemLength(stemLength);
            model.setRockDensity(rockDensity);
            model.setAirDeck(strAirDeck);
            model.setRowName(rowName);

            powderFactorModelArrayList.add(model);
            powderFactorAdapter = new PowderFactorAdapter(getActivity(), powderFactorModelArrayList);
            rvList.setAdapter(powderFactorAdapter);
            powderFactorAdapter.notifyDataSetChanged();
        }

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.connectFragmentWithBack(getActivity(),new PFCalculatorFragment());
            }
        });
    }

    public void showExplosiveWeightList() {
        ArrayList<ExplosiveWeightModel> explosiveWeightModelArrayList;
        ExplosiveWeightAdapter todoListAdapter;
        enaexCrud = new ShotCrud(getActivity());
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvList.setLayoutManager(layoutManager);
        explosiveWeightModelArrayList = new ArrayList<>();

        Cursor cursor = enaexCrud.getExplosiveWeightData();
        while (cursor.moveToNext()) {
            String diameter = cursor.getString(1).trim();
            String density = cursor.getString(2).trim();
            String holeLength = cursor.getString(3).trim();
            String stemLength = cursor.getString(4).trim();
            String rowName = cursor.getString(5).trim();

            ExplosiveWeightModel model = new ExplosiveWeightModel();
            model.setDiameter(diameter);
            model.setDensity(density);
            model.setHoleLength(holeLength);
            model.setStemLength(stemLength);
            model.setRow_name(rowName);

            explosiveWeightModelArrayList.add(model);
            todoListAdapter = new ExplosiveWeightAdapter(getActivity(), explosiveWeightModelArrayList);
            rvList.setAdapter(todoListAdapter);
            todoListAdapter.notifyDataSetChanged();
        }

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.connectFragmentWithBack(getActivity(),new ExplosiveWeightFragment());
            }
        });
    }

    public void showVolumeList() {
        ArrayList<VolumeModel> powderFactorModelArrayList;
        VolumeAdapter powderFactorAdapter;
        enaexCrud = new ShotCrud(getActivity());
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvList.setLayoutManager(layoutManager);
        powderFactorModelArrayList = new ArrayList<>();

        Cursor cursor = enaexCrud.getVolumeData();
        while (cursor.moveToNext()) {
            String burden = cursor.getString(1).trim();
            String spacing = cursor.getString(2).trim();
            String average_depth = cursor.getString(3).trim();
            String noHole = cursor.getString(4).trim();
            String rockDensity = cursor.getString(5).trim();
            String rowName = cursor.getString(6).trim();

            VolumeModel model = new VolumeModel();
            model.setBurden(burden);
            model.setSpacing(spacing);
            model.setAverage_depth(average_depth);
            model.setHole(noHole);
            model.setRockDensity(rockDensity);
            model.setRowName(rowName);

            powderFactorModelArrayList.add(model);
            powderFactorAdapter = new VolumeAdapter(getActivity(), powderFactorModelArrayList);
            rvList.setAdapter(powderFactorAdapter);
            powderFactorAdapter.notifyDataSetChanged();
        }

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.connectFragmentWithBack(getActivity(),new VolumeCalculatorFragment());
            }
        });
    }

    public void showShotList() {
        ArrayList<ShotModel> powderFactorModelArrayList;
        ShotAdapter powderFactorAdapter;
        enaexCrud = new ShotCrud(getActivity());
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvList.setLayoutManager(layoutManager);
        powderFactorModelArrayList = new ArrayList<>();

        Cursor cursor = enaexCrud.getShotData();
        while (cursor.moveToNext()) {
            String noRows = cursor.getString(1).trim();
            String noHoles = cursor.getString(2).trim();
            String diameter = cursor.getString(3).trim();
            String density = cursor.getString(4).trim();
            String burden = cursor.getString(5).trim();
            String spacing = cursor.getString(6).trim();
            String bench_height = cursor.getString(7).trim();
            String subDrill = cursor.getString(8).trim();
            String stemLenght = cursor.getString(9).trim();
            String rockDensity = cursor.getString(10).trim();
            String holeMS = cursor.getString(11).trim();
            String distance = cursor.getString(12).trim();
            String scaling = cursor.getString(13).trim();
            String attenuation = cursor.getString(14).trim();

            String calculator = cursor.getString(15).trim();
            String volume = cursor.getString(16).trim();
            String standoff = cursor.getString(17).trim();
            String checkHoles = cursor.getString(18).trim();
            String checkVibration = cursor.getString(19).trim();

            String rowName = cursor.getString(20).trim();

            ShotModel model = new ShotModel();



            model.setRows(noRows);
            model.setHoles(noHoles);
            model.setDiameter(diameter);
            model.setDensity(density);
            model.setBurden(burden);
            model.setSpacing(spacing);
            model.setBenchHeigh(bench_height);
            model.setSubDrill(subDrill);
            model.setStemLength(stemLenght);
            model.setRockDensity(rockDensity);
            model.setHoleMs(holeMS);
            model.setDistance(distance);
            model.setScaling(scaling);
            model.setAttenuation(attenuation);

            model.setCheckCalculator(calculator);
            model.setCheckVolume(volume);
            model.setCheckSubdrill(standoff);
            model.setCheckHole(checkHoles);
            model.setCheckVibration(checkVibration);

            model.setRowName(rowName);

            powderFactorModelArrayList.add(model);
            powderFactorAdapter = new ShotAdapter(getActivity(), powderFactorModelArrayList);
            rvList.setAdapter(powderFactorAdapter);
            powderFactorAdapter.notifyDataSetChanged();
        }

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.connectFragmentWithBack(getActivity(),new CalculatorByShotFragment());
            }
        });
    }

    public void showHoleList() {
        ArrayList<HoleModel> powderFactorModelArrayList;
        HoleAdapter powderFactorAdapter;
        enaexCrud = new ShotCrud(getActivity());
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvList.setLayoutManager(layoutManager);
        powderFactorModelArrayList = new ArrayList<>();

        Cursor cursor = enaexCrud.getHoleData();
        while (cursor.moveToNext()) {
            String diameter = cursor.getString(1).trim();
            String density = cursor.getString(2).trim();
            String burden = cursor.getString(3).trim();
            String spacing = cursor.getString(4).trim();
            String holeLength = cursor.getString(5).trim();
            String stemLength = cursor.getString(6).trim();
            String rockDensity = cursor.getString(7).trim();
            String distance = cursor.getString(8).trim();
            String scaling = cursor.getString(9).trim();
            String attenuation = cursor.getString(10).trim();
            String checkCalcultor = cursor.getString(11).trim();
            String checkVolume = cursor.getString(12).trim();
            String checkVibration = cursor.getString(13).trim();
            String rowName = cursor.getString(14).trim();

            HoleModel model = new HoleModel();

            model.setDiameter(diameter);
            model.setDensity(density);
            model.setBurden(burden);
            model.setSpacing(spacing);
            model.setHoleLength(holeLength);
            model.setStemLength(stemLength);
            model.setRockDensity(rockDensity);
            model.setDistance(distance);
            model.setScaling(scaling);
            model.setAttenuation(attenuation);
            model.setCheckCalculator(checkCalcultor);
            model.setCheckVolume(checkVolume);
            model.setCheckVibration(checkVibration);
            model.setRowName(rowName);

            powderFactorModelArrayList.add(model);
            powderFactorAdapter = new HoleAdapter(getActivity(), powderFactorModelArrayList);
            rvList.setAdapter(powderFactorAdapter);
            powderFactorAdapter.notifyDataSetChanged();
        }

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.connectFragmentWithBack(getActivity(),new CalculatorByHoleFragment());
            }
        });
    }

}
