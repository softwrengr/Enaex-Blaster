package com.techease.enaexblaster.helpers;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.techease.enaexblaster.R;
import com.techease.enaexblaster.sqliteDatabase.EnaexCrud;

public class SavingLoadingData {
    public static EnaexCrud enaexCrud;



    //saving the shot calculator data
    public static  void showHoleDialog(final Context context, final double diameter, final double density,
                                       final double burden, final double spacing, final double hole_length,
                                        final double stemLenght, final double rockDensity,
                                       final double distance, final double scaling, final double attnuation) {

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom_dialog);
        final EditText etSaveAs = dialog.findViewById(R.id.et_save_as);
        TextView tvSave = dialog.findViewById(R.id.tv_save);
        TextView tvCancel = dialog.findViewById(R.id.tv_cancel);

        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strSaveAs = etSaveAs.getText().toString();
                if (strSaveAs.isEmpty() || strSaveAs == null || strSaveAs.equals("")) {
                    etSaveAs.setError("Please enter Name");
                } else {
                    enaexCrud = new EnaexCrud(context);
                    enaexCrud.insertByHoleData(
                            String.valueOf(diameter),
                            String.valueOf(density),
                            String.valueOf(burden),
                            String.valueOf(spacing),
                            String.valueOf(hole_length),
                            String.valueOf(stemLenght),
                            String.valueOf(rockDensity),
                            String.valueOf(distance),
                            String.valueOf(scaling),
                            String.valueOf(attnuation),
                            strSaveAs);

                    dialog.dismiss();
                }
            }
        });
        dialog.show();

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }



    //saving the shot calculator data
    public static  void showShotDialog(final Context context, final double holes, final double rows, final double diameter, final double density,
                                       final double burden, final double spacing, final double bench_height,
                                       final double subDril, final double stemLenght, final double rockDensity, final double hole_ms,
                                       final double distance, final double scaling, final double attnuation) {

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom_dialog);
        final EditText etSaveAs = dialog.findViewById(R.id.et_save_as);
        TextView tvSave = dialog.findViewById(R.id.tv_save);
        TextView tvCancel = dialog.findViewById(R.id.tv_cancel);

        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strSaveAs = etSaveAs.getText().toString();
                if (strSaveAs.isEmpty() || strSaveAs == null || strSaveAs.equals("")) {
                    etSaveAs.setError("Please enter Name");
                } else {
                    enaexCrud = new EnaexCrud(context);
                    enaexCrud.insertByShotData(
                            String.valueOf(holes),
                            String.valueOf(rows),
                            String.valueOf(diameter),
                            String.valueOf(density),
                            String.valueOf(burden),
                            String.valueOf(spacing),
                            String.valueOf(bench_height),
                            String.valueOf(subDril),
                            String.valueOf(stemLenght),
                            String.valueOf(rockDensity),
                            String.valueOf(hole_ms),
                            String.valueOf(distance),
                            String.valueOf(scaling),
                            String.valueOf(attnuation),
                            strSaveAs);

                    dialog.dismiss();
                }
            }
        });
        dialog.show();

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }



    //saving the scaled distance data
    public static  void showScaledDistanceDialog(final Context context, final double distance, final double mic) {

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom_dialog);
        final EditText etSaveAs = dialog.findViewById(R.id.et_save_as);
        TextView tvSave = dialog.findViewById(R.id.tv_save);
        TextView tvCancel = dialog.findViewById(R.id.tv_cancel);

        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strSaveAs = etSaveAs.getText().toString();
                if (strSaveAs.isEmpty() || strSaveAs == null || strSaveAs.equals("")) {
                    etSaveAs.setError("Please enter Name");
                } else {
                    enaexCrud = new EnaexCrud(context);
                    enaexCrud.insertScaledDitanceData(String.valueOf(distance), String.valueOf(mic), strSaveAs);
                    dialog.dismiss();
                }
            }
        });
        dialog.show();

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    //saving the vibration data
    public static  void showVibrationDialog(final Context context, final double distance, final double mic, final double scalingFactor, final double attenuation) {

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom_dialog);
        final EditText etSaveAs = dialog.findViewById(R.id.et_save_as);
        TextView tvSave = dialog.findViewById(R.id.tv_save);
        TextView tvCancel = dialog.findViewById(R.id.tv_cancel);

        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strSaveAs = etSaveAs.getText().toString();
                if (strSaveAs.isEmpty() || strSaveAs == null || strSaveAs.equals("")) {
                    etSaveAs.setError("Please enter Name");
                } else {
                    enaexCrud = new EnaexCrud(context);
                    enaexCrud.insertVibrationData(
                            String.valueOf(distance),
                            String.valueOf(mic),
                            String.valueOf(scalingFactor),
                            String.valueOf(attenuation),strSaveAs);

                    dialog.dismiss();
                }
            }
        });
        dialog.show();

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    //saving the vibration data
    public static  void showSdobDialog(final Context context, final double diameter, final double density, final double holeLength, final double stemLength) {

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom_dialog);
        final EditText etSaveAs = dialog.findViewById(R.id.et_save_as);
        TextView tvSave = dialog.findViewById(R.id.tv_save);
        TextView tvCancel = dialog.findViewById(R.id.tv_cancel);

        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strSaveAs = etSaveAs.getText().toString();
                if (strSaveAs.isEmpty() || strSaveAs == null || strSaveAs.equals("")) {
                    etSaveAs.setError("Please enter Name");
                } else {
                    enaexCrud = new EnaexCrud(context);
                    enaexCrud.insertSDOBData(
                            String.valueOf(diameter),
                            String.valueOf(density),
                            String.valueOf(holeLength),
                            String.valueOf(stemLength),
                            strSaveAs);

                    dialog.dismiss();
                }
            }
        });
        dialog.show();

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    //saving the vibration data
    public static  void showExplosiveWeightDialog(final Context context, final double diameter, final double density, final double holeLength, final double stemLength) {

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom_dialog);
        final EditText etSaveAs = dialog.findViewById(R.id.et_save_as);
        TextView tvSave = dialog.findViewById(R.id.tv_save);
        TextView tvCancel = dialog.findViewById(R.id.tv_cancel);

        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strSaveAs = etSaveAs.getText().toString();
                if (strSaveAs.isEmpty() || strSaveAs == null || strSaveAs.equals("")) {
                    etSaveAs.setError("Please enter Name");
                } else {
                    enaexCrud = new EnaexCrud(context);
                    enaexCrud.insertExplosiveData(
                            String.valueOf(diameter),
                            String.valueOf(density),
                            String.valueOf(holeLength),
                            String.valueOf(stemLength),
                            strSaveAs);

                    dialog.dismiss();
                }
            }
        });
        dialog.show();

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }


    //saving the powder factor data
    public static  void showPowderFactorDialog(final Context context, final double diameter, final double density,final double burden,
                                               final double spacing, final double holeLength, final double stemLength,
                                               final double rockDensity,final double airDeck) {

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom_dialog);
        final EditText etSaveAs = dialog.findViewById(R.id.et_save_as);
        TextView tvSave = dialog.findViewById(R.id.tv_save);
        TextView tvCancel = dialog.findViewById(R.id.tv_cancel);

        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strSaveAs = etSaveAs.getText().toString();
                if (strSaveAs.isEmpty() || strSaveAs == null || strSaveAs.equals("")) {
                    etSaveAs.setError("Please enter Name");
                } else {
                    enaexCrud = new EnaexCrud(context);
                    enaexCrud.insertPowderFactorData(
                            String.valueOf(diameter),
                            String.valueOf(density),
                            String.valueOf(burden),
                            String.valueOf(spacing),
                            String.valueOf(holeLength),
                            String.valueOf(stemLength),
                            String.valueOf(rockDensity),
                            String.valueOf(airDeck),
                            strSaveAs);

                    dialog.dismiss();
                }
            }
        });
        dialog.show();

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    //saving the volume data
    public static  void showVolumeDialog(final Context context, final double burden, final double spacing, final double average_depth, final double noHole,final double rockDensity) {

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom_dialog);
        final EditText etSaveAs = dialog.findViewById(R.id.et_save_as);
        TextView tvSave = dialog.findViewById(R.id.tv_save);
        TextView tvCancel = dialog.findViewById(R.id.tv_cancel);

        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strSaveAs = etSaveAs.getText().toString();
                if (strSaveAs.isEmpty() || strSaveAs == null || strSaveAs.equals("")) {
                    etSaveAs.setError("Please enter Name");
                } else {
                    enaexCrud = new EnaexCrud(context);
                    enaexCrud.insertVolumeData(
                            String.valueOf(burden),
                            String.valueOf(spacing),
                            String.valueOf(average_depth),
                            String.valueOf(noHole),
                            String.valueOf(rockDensity),
                            strSaveAs);

                    dialog.dismiss();
                }
            }
        });
        dialog.show();

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }



}
