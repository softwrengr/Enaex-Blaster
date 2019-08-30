package com.techease.enaexblaster.sqliteDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

public class HoleCrud {

    private static SQLiteDatabase sqLiteDatabase;
    private Context context;
    private String checkExistData = "";

    public HoleCrud(Context context) {
        EnaexDatabase database = new EnaexDatabase(context);
        sqLiteDatabase = database.getWritableDatabase();
        this.context = context;
    }


    //inserting by shot data
    public String insertByHoleData(String diameter,String density,String burden,String spacing,String holeLength,
                                 String stemLenght,String rockDensity,String distance,String scaling,String attnuation,String row_name) {

        if (!checkData(row_name)) {
            ContentValues values = new ContentValues();
            values.put("DIAMETER",diameter);
            values.put("DENSITY",density);
            values.put("BURDEN",burden);

            values.put("SPACING", spacing);
            values.put("HOLE_LENGTH",holeLength);
            values.put("STEM_LENGTH",stemLenght);
            values.put("ROCK_DENSITY",rockDensity);

            values.put("DISTANCE",distance);
            values.put("SCALING",scaling);
            values.put("ATTENUATION",attnuation);
            values.put("ROW_NAME",row_name);

            sqLiteDatabase.insert("BY_HOLE", null, values);
            checkExistData = "Saved";

        } else {
            checkExistData = "Already Exist";
        }

        return checkExistData;
    }


    //inserting by shot data
    public void updateByHoleData(String diameter,String density,String burden,String spacing,String holeLength,
                                   String stemLenght,String rockDensity,String distance,String scaling,String attnuation,String row_name) {
        ContentValues values = new ContentValues();
        values.put("DIAMETER",diameter);
        values.put("DENSITY",density);
        values.put("BURDEN",burden);

        values.put("SPACING", spacing);
        values.put("HOLE_LENGTH",holeLength);
        values.put("STEM_LENGTH",stemLenght);
        values.put("ROCK_DENSITY",rockDensity);

        values.put("DISTANCE",distance);
        values.put("SCALING",scaling);
        values.put("ATTENUATION",attnuation);
        values.put("ROW_NAME",row_name);
        String whereClause = "ROW_NAME = '" + row_name + "'";
        sqLiteDatabase.update("BY_HOLE",values,  whereClause, null);

    }

    //check for single row
    public boolean checkData(String row_name) {
        Cursor cursor = this.sqLiteDatabase.rawQuery("SELECT * FROM BY_HOLE WHERE ROW_NAME = '" + row_name + "' ", null);
        boolean isItemAddChart = false;
        if (cursor.moveToFirst()) {
            isItemAddChart = true;
        }
        return isItemAddChart;

    }
}
