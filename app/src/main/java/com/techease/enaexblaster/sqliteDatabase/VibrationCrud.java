package com.techease.enaexblaster.sqliteDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class VibrationCrud {

    private static SQLiteDatabase sqLiteDatabase;
    private Context context;
    private String checkExistData = "";

    public VibrationCrud(Context context) {
        EnaexDatabase database = new EnaexDatabase(context);
        sqLiteDatabase = database.getWritableDatabase();
        this.context = context;
    }

    //inserting scaled distance data
    public String  insertVibrationData(String distance,String mic,String scaling_factor,String attenuation,String row_name) {

        if (!checkData(row_name)) {
            ContentValues values = new ContentValues();
            values.put("DISTANCE", distance);
            values.put("MIC",mic);
            values.put("SCALING_FACTOR",scaling_factor);
            values.put("ATTENUATION",attenuation);
            values.put("ROW_NAME",row_name);
            sqLiteDatabase.insert("VIBRATION", null, values);
            checkExistData = "Saved";

        } else {
            checkExistData = "Already Exist";
        }
        return checkExistData;
    }

    //inserting scaled distance data
    public  void  updateVibrationData(String distance,String mic,String scaling_factor,String attenuation,String row_name) {

        ContentValues values = new ContentValues();
        values.put("DISTANCE", distance);
        values.put("MIC",mic);
        values.put("SCALING_FACTOR",scaling_factor);
        values.put("ATTENUATION",attenuation);
        values.put("ROW_NAME",row_name);
        String whereClause = "ROW_NAME = '" + row_name + "'";
        sqLiteDatabase.update("VIBRATION",  values,whereClause,null);
    }

    //check for single row
    public boolean checkData(String row_name) {
        Cursor cursor = this.sqLiteDatabase.rawQuery("SELECT * FROM VIBRATION WHERE ROW_NAME = '" + row_name + "' ", null);
        boolean isItemAddChart = false;
        if (cursor.moveToFirst()) {
            isItemAddChart = true;
        }
        return isItemAddChart;

    }

}
