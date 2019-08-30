package com.techease.enaexblaster.sqliteDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class ScaledDistanceCrud {

    private static SQLiteDatabase sqLiteDatabase;
    private Context context;
    private String checkExistData = "";

    public ScaledDistanceCrud(Context context) {
        EnaexDatabase database = new EnaexDatabase(context);
        sqLiteDatabase = database.getWritableDatabase();
        this.context = context;
    }


    //inserting scaled distance data
    public String insertScaledDitanceData(String distance,String mic,String row_name) {

        if (!checkData(row_name)) {
            ContentValues values = new ContentValues();
            values.put("DISTANCE", distance);
            values.put("MIC",mic);
            values.put("ROW_NAME",row_name);
            sqLiteDatabase.insert("SCALED_DISTANCE", null, values);
            checkExistData = "Saved";
        } else {
            checkExistData = "Already Exist";
        }

        return checkExistData;
    }

    //inserting scaled distance data
    public  void  updateScaledDitanceData(String distance,String mic,String row_name) {

        ContentValues values = new ContentValues();
        values.put("DISTANCE", distance);
        values.put("MIC",mic);
        values.put("ROW_NAME",row_name);
        String whereClause = "ROW_NAME = '" + row_name + "'";
        sqLiteDatabase.update("SCALED_DISTANCE",  values,whereClause,null);
    }

    //check for single row
    public boolean checkData(String row_name) {
        Cursor cursor = this.sqLiteDatabase.rawQuery("SELECT * FROM SCALED_DISTANCE WHERE ROW_NAME = '" + row_name + "' ", null);
        boolean isItemAddChart = false;
        if (cursor.moveToFirst()) {
            isItemAddChart = true;
        }
        return isItemAddChart;

    }

}
