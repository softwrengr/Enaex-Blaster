package com.techease.enaexblaster.sqliteDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class VolumeCrud {


    private static SQLiteDatabase sqLiteDatabase;
    private Context context;
    private String checkExistData = "";

    public VolumeCrud(Context context) {
        EnaexDatabase database = new EnaexDatabase(context);
        sqLiteDatabase = database.getWritableDatabase();
        this.context = context;
    }

    //inserting volume data
    public String insertVolumeData(String burden,String spacing,String averageDepth,
                                 String noHole,String rockDensity,String row_name,String checkCal,String checkVol) {

        if (!checkData(row_name)) {
            ContentValues values = new ContentValues();
            values.put("BURDEN", burden);
            values.put("SPACING",spacing);
            values.put("AVERAGE_DEPTH",averageDepth);
            values.put("HOLES",noHole);
            values.put("ROCK_DENSITY",rockDensity);
            values.put("ROW_NAME",row_name);
            values.put("CALCULATOR",checkCal);
            values.put("VOLUME",checkVol);
            sqLiteDatabase.insert("VOlUME", null, values);
            checkExistData = "Saved";

        } else {
            checkExistData = "Already Exist";
        }

        return checkExistData;
    }




    //inserting volume data
    public void updateVolumeData(String burden,String spacing,String averageDepth,
                                 String noHole,String rockDensity,String row_name,String checkCal,String checkVol) {
        ContentValues values = new ContentValues();
        values.put("BURDEN", burden);
        values.put("SPACING",spacing);
        values.put("AVERAGE_DEPTH",averageDepth);
        values.put("HOLES",noHole);
        values.put("ROCK_DENSITY",rockDensity);
        values.put("ROW_NAME",row_name);
        values.put("CALCULATOR",checkCal);
        values.put("VOLUME",checkVol);
        String whereClause = "ROW_NAME = '" + row_name + "'";
        sqLiteDatabase.update("VOlUME",values,  whereClause, null);

    }

    //check for single row
    public boolean checkData(String row_name) {
        Cursor cursor = this.sqLiteDatabase.rawQuery("SELECT * FROM VOlUME WHERE ROW_NAME = '" + row_name + "' ", null);
        boolean isItemAddChart = false;
        if (cursor.moveToFirst()) {
            isItemAddChart = true;
        }
        return isItemAddChart;

    }
}
