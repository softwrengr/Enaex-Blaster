package com.techease.enaexblaster.helpers;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.techease.enaexblaster.R;
import com.techease.enaexblaster.sqliteDatabase.ShotCrud;
import com.techease.enaexblaster.sqliteDatabase.ExplosiveCrud;
import com.techease.enaexblaster.sqliteDatabase.HoleCrud;
import com.techease.enaexblaster.sqliteDatabase.PowderFactorCrud;
import com.techease.enaexblaster.sqliteDatabase.ScaledDistanceCrud;
import com.techease.enaexblaster.sqliteDatabase.SdobCrud;
import com.techease.enaexblaster.sqliteDatabase.VibrationCrud;
import com.techease.enaexblaster.sqliteDatabase.VolumeCrud;

public class SavingLoadingData {
    public static HoleCrud holeCrud;
    public static ShotCrud enaexCrud;
    public static ScaledDistanceCrud scaledDistanceCrud;
    public static VibrationCrud vibrationCrud;
    public static SdobCrud sdobCrud;
    public static PowderFactorCrud factorCrud;
    public static ExplosiveCrud explosiveCrud;
    public static VolumeCrud volumeCrud;
    public static String strChecking;


    //saving the shot calculator data
    public static void showHoleDialog(final Context context, final double diameter, final double density,
                                      final double burden, final double spacing, final double hole_length,
                                      final double stemLenght, final double rockDensity,
                                      final double distance, final double scaling, final double attnuation,
                                      final boolean checkCalculator, final boolean checkVolume, final boolean checkVibration) {

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom_dialog);
        final EditText etSaveAs = dialog.findViewById(R.id.et_save_as);
        final TextView tvSave = dialog.findViewById(R.id.tv_save);
        TextView tvCancel = dialog.findViewById(R.id.tv_cancel);
        final TextView tvUpdate = dialog.findViewById(R.id.tv_update);
        final TextView tvMessage = dialog.findViewById(R.id.tv_error);

        holeCrud = new HoleCrud(context);

        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strSaveAs = etSaveAs.getText().toString();
                if (strSaveAs.isEmpty() || strSaveAs == null || strSaveAs.equals("")) {
                    etSaveAs.setError("Please enter Name");
                } else {
                    strChecking = holeCrud.insertByHoleData(
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
                            String.valueOf(checkCalculator),
                            String.valueOf(checkVolume),
                            String.valueOf(checkVibration),
                            strSaveAs);

                    if (strChecking.equals("Already Exist")) {
                        tvMessage.setVisibility(View.VISIBLE);
                        tvSave.setVisibility(View.GONE);
                        tvUpdate.setVisibility(View.VISIBLE);
                    } else {
                        dialog.dismiss();
                    }
                }
            }
        });


        tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strSaveAs = etSaveAs.getText().toString();
                holeCrud.updateByHoleData(
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
                        String.valueOf(checkCalculator),
                        String.valueOf(checkVolume),
                        String.valueOf(checkVibration),
                        strSaveAs);

                dialog.dismiss();
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
    public static void showShotDialog(final Context context, final double holes, final double rows, final double diameter, final double density,
                                      final double burden, final double spacing, final double bench_height,
                                      final double subDril, final double stemLenght, final double rockDensity, final double hole_ms,
                                      final double distance, final double scaling, final double attnuation,
                                      final boolean checkCalculator, final boolean checkVolume, final boolean checkSubDrillStandOFF, final boolean checkHoleRowCount, final boolean checkVibration) {

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom_dialog);
        final EditText etSaveAs = dialog.findViewById(R.id.et_save_as);
        final TextView tvSave = dialog.findViewById(R.id.tv_save);
        TextView tvCancel = dialog.findViewById(R.id.tv_cancel);
        final TextView tvUpdate = dialog.findViewById(R.id.tv_update);
        final TextView tvMessage = dialog.findViewById(R.id.tv_error);

        enaexCrud = new ShotCrud(context);

        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strSaveAs = etSaveAs.getText().toString();
                if (strSaveAs.isEmpty() || strSaveAs == null || strSaveAs.equals("")) {
                    etSaveAs.setError("Please enter Name");
                } else {
                    strChecking = enaexCrud.insertByShotData(
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

                            String.valueOf(checkCalculator),
                            String.valueOf(checkVolume),
                            String.valueOf(checkSubDrillStandOFF),
                            String.valueOf(checkHoleRowCount),
                            String.valueOf(checkVibration),

                            strSaveAs);

                    if (strChecking.equals("Already Exist")) {
                        tvMessage.setVisibility(View.VISIBLE);
                        tvSave.setVisibility(View.GONE);
                        tvUpdate.setVisibility(View.VISIBLE);
                    } else {
                        dialog.dismiss();
                    }

                }
            }
        });

        tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strSaveAs = etSaveAs.getText().toString();
                enaexCrud.updateByShotData(
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
    public static void showScaledDistanceDialog(final Context context, final double distance,
                                                final double mic, final boolean checkCalculator) {

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom_dialog);
        final EditText etSaveAs = dialog.findViewById(R.id.et_save_as);
        final TextView tvSave = dialog.findViewById(R.id.tv_save);
        TextView tvCancel = dialog.findViewById(R.id.tv_cancel);
        final TextView tvUpdate = dialog.findViewById(R.id.tv_update);
        final TextView tvMessage = dialog.findViewById(R.id.tv_error);

        scaledDistanceCrud = new ScaledDistanceCrud(context);

        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strSaveAs = etSaveAs.getText().toString();
                if (strSaveAs.isEmpty() || strSaveAs == null || strSaveAs.equals("")) {
                    etSaveAs.setError("Please enter Name");
                } else {
                    strChecking = scaledDistanceCrud.insertScaledDitanceData(
                            String.valueOf(distance),
                            String.valueOf(mic),
                            strSaveAs,
                            String.valueOf(checkCalculator));

                    if (strChecking.equals("Already Exist")) {
                        tvMessage.setVisibility(View.VISIBLE);
                        tvSave.setVisibility(View.GONE);
                        tvUpdate.setVisibility(View.VISIBLE);
                    } else {
                        dialog.dismiss();
                    }
                }
            }
        });


        tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strSaveAs = etSaveAs.getText().toString();
                scaledDistanceCrud.updateScaledDitanceData(
                        String.valueOf(distance),
                        String.valueOf(mic),
                        strSaveAs,
                        String.valueOf(checkCalculator));
                dialog.dismiss();
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
    public static void showVibrationDialog(final Context context, final double distance,
                                           final double mic, final double scalingFactor,
                                           final double attenuation, final boolean checkCalculator) {

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom_dialog);
        final EditText etSaveAs = dialog.findViewById(R.id.et_save_as);
        final TextView tvSave = dialog.findViewById(R.id.tv_save);
        final TextView tvCancel = dialog.findViewById(R.id.tv_cancel);
        final TextView tvUpdate = dialog.findViewById(R.id.tv_update);
        final TextView tvMessage = dialog.findViewById(R.id.tv_error);

        vibrationCrud = new VibrationCrud(context);
        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strSaveAs = etSaveAs.getText().toString();
                if (strSaveAs.isEmpty() || strSaveAs == null || strSaveAs.equals("")) {
                    etSaveAs.setError("Please enter Name");
                } else {
                    strChecking = vibrationCrud.insertVibrationData(
                            String.valueOf(distance),
                            String.valueOf(mic),
                            String.valueOf(scalingFactor),
                            String.valueOf(attenuation),
                            strSaveAs,
                            String.valueOf(checkCalculator));

                    if (strChecking.equals("Already Exist")) {
                        tvMessage.setVisibility(View.VISIBLE);
                        tvSave.setVisibility(View.GONE);
                        tvUpdate.setVisibility(View.VISIBLE);
                    } else {
                        dialog.dismiss();
                    }
                }
            }
        });

        tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strSaveAs = etSaveAs.getText().toString();
                vibrationCrud.updateVibrationData(
                        String.valueOf(distance),
                        String.valueOf(mic),
                        String.valueOf(scalingFactor),
                        String.valueOf(attenuation),
                        strSaveAs,
                        String.valueOf(checkCalculator));
                dialog.dismiss();
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
    public static void showSdobDialog(final Context context, final double diameter, final double density, final double holeLength,
                                      final double stemLength, final boolean checkCalculator) {

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom_dialog);
        final EditText etSaveAs = dialog.findViewById(R.id.et_save_as);
        final TextView tvSave = dialog.findViewById(R.id.tv_save);
        TextView tvCancel = dialog.findViewById(R.id.tv_cancel);
        final TextView tvUpdate = dialog.findViewById(R.id.tv_update);
        final TextView tvMessage = dialog.findViewById(R.id.tv_error);

        sdobCrud = new SdobCrud(context);

        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strSaveAs = etSaveAs.getText().toString();
                if (strSaveAs.isEmpty() || strSaveAs == null || strSaveAs.equals("")) {
                    etSaveAs.setError("Please enter Name");
                } else {
                    strChecking = sdobCrud.insertSDOBData(
                            String.valueOf(diameter),
                            String.valueOf(density),
                            String.valueOf(holeLength),
                            String.valueOf(stemLength),
                            strSaveAs,
                            String.valueOf(checkCalculator));

                    if (strChecking.equals("Already Exist")) {
                        tvMessage.setVisibility(View.VISIBLE);
                        tvSave.setVisibility(View.GONE);
                        tvUpdate.setVisibility(View.VISIBLE);
                    } else {
                        dialog.dismiss();
                    }
                }
            }
        });

        tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strSaveAs = etSaveAs.getText().toString();
                sdobCrud.updateSDOBData(
                        String.valueOf(diameter),
                        String.valueOf(density),
                        String.valueOf(holeLength),
                        String.valueOf(stemLength),
                        strSaveAs,
                        String.valueOf(checkCalculator));
                dialog.dismiss();
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
    public static void showExplosiveWeightDialog(final Context context, final double diameter,
                                                 final double density, final double holeLength,
                                                 final double stemLength,final boolean checkCal) {

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom_dialog);
        final EditText etSaveAs = dialog.findViewById(R.id.et_save_as);
        final TextView tvSave = dialog.findViewById(R.id.tv_save);
        TextView tvCancel = dialog.findViewById(R.id.tv_cancel);
        final TextView tvUpdate = dialog.findViewById(R.id.tv_update);
        final TextView tvMessage = dialog.findViewById(R.id.tv_error);

        explosiveCrud = new ExplosiveCrud(context);

        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strSaveAs = etSaveAs.getText().toString();
                if (strSaveAs.isEmpty() || strSaveAs == null || strSaveAs.equals("")) {
                    etSaveAs.setError("Please enter Name");
                } else {
                    strChecking = explosiveCrud.insertExplosiveData(
                            String.valueOf(diameter),
                            String.valueOf(density),
                            String.valueOf(holeLength),
                            String.valueOf(stemLength),
                            strSaveAs,
                            String.valueOf(checkCal));

                    if (strChecking.equals("Already Exist")) {
                        tvMessage.setVisibility(View.VISIBLE);
                        tvSave.setVisibility(View.GONE);
                        tvUpdate.setVisibility(View.VISIBLE);
                    } else {
                        dialog.dismiss();
                    }
                }
            }
        });

        tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strSaveAs = etSaveAs.getText().toString();
                explosiveCrud.updateExplosiveData(
                        String.valueOf(diameter),
                        String.valueOf(density),
                        String.valueOf(holeLength),
                        String.valueOf(stemLength),
                        strSaveAs,
                        String.valueOf(checkCal));
                dialog.dismiss();
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
    public static void showPowderFactorDialog(final Context context, final double diameter, final double density, final double burden,
                                              final double spacing, final double holeLength, final double stemLength,
                                              final double rockDensity, final double airDeck,
                                              final boolean checkCalculator, final boolean checkVolumeWeight, final boolean checkAirDeck) {

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom_dialog);
        final EditText etSaveAs = dialog.findViewById(R.id.et_save_as);
        final TextView tvSave = dialog.findViewById(R.id.tv_save);
        TextView tvCancel = dialog.findViewById(R.id.tv_cancel);
        final TextView tvUpdate = dialog.findViewById(R.id.tv_update);
        final TextView tvMessage = dialog.findViewById(R.id.tv_error);

        factorCrud = new PowderFactorCrud(context);

        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strSaveAs = etSaveAs.getText().toString();
                if (strSaveAs.isEmpty() || strSaveAs == null || strSaveAs.equals("")) {
                    etSaveAs.setError("Please enter Name");
                } else {
                    strChecking = factorCrud.insertPowderFactorData(
                            String.valueOf(diameter),
                            String.valueOf(density),
                            String.valueOf(burden),
                            String.valueOf(spacing),
                            String.valueOf(holeLength),
                            String.valueOf(stemLength),
                            String.valueOf(rockDensity),
                            String.valueOf(airDeck),
                            strSaveAs,
                            String.valueOf(checkCalculator),
                            String.valueOf(checkVolumeWeight),
                            String.valueOf(checkAirDeck));

                    if (strChecking.equals("Already Exist")) {
                        tvMessage.setVisibility(View.VISIBLE);
                        tvSave.setVisibility(View.GONE);
                        tvUpdate.setVisibility(View.VISIBLE);
                    } else {
                        dialog.dismiss();
                    }
                }
            }
        });

        tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strSaveAs = etSaveAs.getText().toString();
                factorCrud.updatePowderFactorData(
                        String.valueOf(diameter),
                        String.valueOf(density),
                        String.valueOf(burden),
                        String.valueOf(spacing),
                        String.valueOf(holeLength),
                        String.valueOf(stemLength),
                        String.valueOf(rockDensity),
                        String.valueOf(airDeck),
                        strSaveAs,
                        String.valueOf(checkCalculator),
                        String.valueOf(checkVolumeWeight),
                        String.valueOf(checkAirDeck));
                dialog.dismiss();
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
    public static void showVolumeDialog(final Context context, final double burden,
                                        final double spacing, final double average_depth,
                                        final double noHole, final double rockDensity,
                                        final boolean checkCal, final boolean checkVolumeWeight) {

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom_dialog);
        final EditText etSaveAs = dialog.findViewById(R.id.et_save_as);
        final TextView tvSave = dialog.findViewById(R.id.tv_save);
        TextView tvCancel = dialog.findViewById(R.id.tv_cancel);
        final TextView tvUpdate = dialog.findViewById(R.id.tv_update);
        final TextView tvMessage = dialog.findViewById(R.id.tv_error);

        volumeCrud = new VolumeCrud(context);

        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strSaveAs = etSaveAs.getText().toString();
                if (strSaveAs.isEmpty() || strSaveAs == null || strSaveAs.equals("")) {
                    etSaveAs.setError("Please enter Name");
                } else {
                    strChecking = volumeCrud.insertVolumeData(
                            String.valueOf(burden),
                            String.valueOf(spacing),
                            String.valueOf(average_depth),
                            String.valueOf(noHole),
                            String.valueOf(rockDensity),
                            strSaveAs,
                            String.valueOf(checkCal),
                            String.valueOf(checkVolumeWeight)
                            );

                    if (strChecking.equals("Already Exist")) {
                        tvMessage.setVisibility(View.VISIBLE);
                        tvSave.setVisibility(View.GONE);
                        tvUpdate.setVisibility(View.VISIBLE);
                    } else {
                        dialog.dismiss();
                    }
                }
            }
        });

        tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strSaveAs = etSaveAs.getText().toString();
                volumeCrud.updateVolumeData(
                        String.valueOf(burden),
                        String.valueOf(spacing),
                        String.valueOf(average_depth),
                        String.valueOf(noHole),
                        String.valueOf(rockDensity),
                        strSaveAs,
                        String.valueOf(checkCal),
                        String.valueOf(checkVolumeWeight));
                dialog.dismiss();
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
