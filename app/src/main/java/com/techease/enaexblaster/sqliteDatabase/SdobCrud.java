package com.techease.enaexblaster.sqliteDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class SdobCrud {

    private static SQLiteDatabase sqLiteDatabase;
    private Context context;
    private String checkExistData = "";

    public SdobCrud(Context context) {
        EnaexDatabase database = new EnaexDatabase(context);
        sqLiteDatabase = database.getWritableDatabase();
        this.context = context;
    }


    //inserting sdob data
    public String insertSDOBData(String diameter,String density,String holeLength,
                                 String stemLength,String row_name,String checkCal) {

        if (!checkData(row_name)) {
            ContentValues values = new ContentValues();
            values.put("DIAMETER", diameter);
            values.put("DENSITY",density);
            values.put("HOLE_LENGHT",holeLength);
            values.put("STEM_LENGHT",stemLength);
            values.put("ROW_NAME",row_name);
            values.put("CALCULATOR",checkCal);
            sqLiteDatabase.insert("SDOB", null, values);
            checkExistData = "Saved";
        } else {
            checkExistData = "Already Exist";
        }

        return checkExistData;
    }

    //inserting scaled distance data
    public  void  updateSDOBData(String diameter,String density,String holeLength,
                                 String stemLength,String row_name,String checkCal) {

        ContentValues values = new ContentValues();
        values.put("DIAMETER", diameter);
        values.put("DENSITY",density);
        values.put("HOLE_LENGHT",holeLength);
        values.put("STEM_LENGHT",stemLength);
        values.put("ROW_NAME",row_name);
        values.put("CALCULATOR",checkCal);
        String whereClause = "ROW_NAME = '" + row_name + "'";
        sqLiteDatabase.update("SDOB",  values,whereClause,null);
    }

    //check for single row
    public boolean checkData(String row_name) {
        Cursor cursor = this.sqLiteDatabase.rawQuery("SELECT * FROM SDOB WHERE ROW_NAME = '" + row_name + "' ", null);
        boolean isItemAddChart = false;
        if (cursor.moveToFirst()) {
            isItemAddChart = true;
        }
        return isItemAddChart;

    }
}

