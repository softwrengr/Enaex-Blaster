package com.techease.enaexblaster.sqliteDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

public class ShotCrud {

    private static SQLiteDatabase sqLiteDatabase;
    private Context context;
    private String checkExistData = "";

    public ShotCrud(Context context) {
        EnaexDatabase database = new EnaexDatabase(context);
        sqLiteDatabase = database.getWritableDatabase();
        this.context = context;
    }

    //inserting by shot data
    public String insertByShotData(String holes,
                                   String rows,
                                   String diameter,
                                   String density,
                                   String burden,
                                   String spacing,
                                   String bench_height,
                                   String subDril,
                                   String stemLenght,
                                   String rockDensity,
                                   String hole_ms,
                                   String distance,
                                   String scaling,
                                   String attnuation,
                                   String checkCalculator,
                                   String checkVolume,
                                   String checkSubDrillStandOFF,
                                   String checkHoleRowCount,
                                   String checkVibration,
                                   String row_name) {


        if (!checkData(row_name)) {
            ContentValues values = new ContentValues();
            values.put("HOLES", holes);
            values.put("ROWS", rows);
            values.put("DIAMETER", diameter);
            values.put("DENSITY", density);
            values.put("BURDEN", burden);

            values.put("SPACING", spacing);
            values.put("BENCH_HEIGHT", bench_height);
            values.put("SUB_DRILL", subDril);
            values.put("STEM_LENGTH", stemLenght);
            values.put("ROCK_DENSITY", rockDensity);

            values.put("HOLE_MS", hole_ms);
            values.put("DISTANCE", distance);
            values.put("SCALING", scaling);
            values.put("ATTENUATION", attnuation);

            values.put("CALCULATOR", checkCalculator);
            values.put("VOLUME", checkVolume);
            values.put("CHECK_SUBDRILL", checkSubDrillStandOFF);
            values.put("CHECK_HOLES", checkHoleRowCount);
            values.put("CHECK_VIBRATION", checkVibration);
            values.put("ROW_NAME", row_name);

            sqLiteDatabase.insert("BY_SHOT", null, values);
            checkExistData = "Saved";

        } else {
            checkExistData = "Already Exist";
        }

        return checkExistData;
    }

    //inserting by shot data
    public void updateByShotData(String holes,
                                 String rows,
                                 String diameter,
                                 String density,
                                 String burden,
                                 String spacing,
                                 String bench_height,
                                 String subDril,
                                 String stemLenght,
                                 String rockDensity,
                                 String hole_ms,
                                 String distance,
                                 String scaling,
                                 String attnuation,
                                 String row_name) {

        ContentValues values = new ContentValues();
        values.put("HOLES", holes);
        values.put("ROWS", rows);
        values.put("DIAMETER", diameter);
        values.put("DENSITY", density);
        values.put("BURDEN", burden);

        values.put("SPACING", spacing);
        values.put("BENCH_HEIGHT", bench_height);
        values.put("SUB_DRILL", subDril);
        values.put("STEM_LENGTH", stemLenght);
        values.put("ROCK_DENSITY", rockDensity);

        values.put("HOLE_MS", hole_ms);
        values.put("DISTANCE", distance);
        values.put("SCALING", scaling);
        values.put("ATTENUATION", attnuation);
        values.put("ROW_NAME", row_name);
        String whereClause = "ROW_NAME = '" + row_name + "'";
        sqLiteDatabase.update("BY_SHOT", values, whereClause, null);

    }


    //check for single row
    public boolean checkData(String row_name) {
        Cursor cursor = this.sqLiteDatabase.rawQuery("SELECT * FROM BY_SHOT WHERE ROW_NAME = '" + row_name + "' ", null);
        boolean isItemAddChart = false;
        if (cursor.moveToFirst()) {
            isItemAddChart = true;
        }
        return isItemAddChart;

    }

    //fetching all scaled distance data
    public Cursor getProducts() {
        String query = "SELECT * FROM SCALED_DISTANCE";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        return cursor;
    }

    //fetching all vibration data
    public Cursor getVibraionData() {
        String query = "SELECT * FROM VIBRATION";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        return cursor;
    }

    //fetching all sdob data
    public Cursor getSDOBData() {
        String query = "SELECT * FROM SDOB";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        return cursor;
    }

    //fetching all pf data
    public Cursor getPowderFactorData() {
        String query = "SELECT * FROM Powder_Factor";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        return cursor;
    }

    //fetching all explosive weight data
    public Cursor getExplosiveWeightData() {
        String query = "SELECT * FROM Explosive_Weight";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        return cursor;
    }

    //fetching all volume data
    public Cursor getVolumeData() {
        String query = "SELECT * FROM VOlUME";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        return cursor;
    }

    //fetching all shot data
    public Cursor getShotData() {
        String query = "SELECT * FROM BY_SHOT";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        return cursor;
    }

    //fetching all hole data
    public Cursor getHoleData() {
        String query = "SELECT * FROM BY_HOLE";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        return cursor;
    }
}
