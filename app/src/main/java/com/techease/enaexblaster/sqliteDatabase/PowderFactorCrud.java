package com.techease.enaexblaster.sqliteDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class PowderFactorCrud {


    private static SQLiteDatabase sqLiteDatabase;
    private Context context;
    private String checkExistData = "";

    public PowderFactorCrud(Context context) {
        EnaexDatabase database = new EnaexDatabase(context);
        sqLiteDatabase = database.getWritableDatabase();
        this.context = context;
    }


    //inserting powder factor data
    public String insertPowderFactorData(String diameter,String density,String burden,String spacing,
                                       String holeLength,String stemLength,String rockDensity,
                                       String airDeck,String row_name) {

        if (!checkData(row_name)) {
            ContentValues values = new ContentValues();
            values.put("DIAMETER", diameter);
            values.put("DENSITY",density);
            values.put("BURDEN",burden);
            values.put("SPACING",spacing);
            values.put("HOLE_LENGHT",holeLength);
            values.put("STEM_LENGHT",stemLength);
            values.put("ROCK_DENSITY",rockDensity);
            values.put("AIR_DECK",airDeck);
            values.put("ROW_NAME",row_name);
            sqLiteDatabase.insert("Powder_Factor", null, values);
            checkExistData = "Saved";

        } else {
            checkExistData = "Already Exist";
        }

        return checkExistData;
    }

    //inserting scaled distance data
    public void updatePowderFactorData(String diameter,String density,String burden,String spacing,
                                       String holeLength,String stemLength,String rockDensity,
                                       String airDeck,String row_name) {

        ContentValues values = new ContentValues();
        values.put("DIAMETER", diameter);
        values.put("DENSITY",density);
        values.put("BURDEN",burden);
        values.put("SPACING",spacing);
        values.put("HOLE_LENGHT",holeLength);
        values.put("STEM_LENGHT",stemLength);
        values.put("ROCK_DENSITY",rockDensity);
        values.put("AIR_DECK",airDeck);
        values.put("ROW_NAME",row_name);
        String whereClause = "ROW_NAME = '" + row_name + "'";
        sqLiteDatabase.update("Powder_Factor",  values,whereClause,null);
    }

    //check for single row
    public boolean checkData(String row_name) {
        Cursor cursor = this.sqLiteDatabase.rawQuery("SELECT * FROM Powder_Factor WHERE ROW_NAME = '" + row_name + "' ", null);
        boolean isItemAddChart = false;
        if (cursor.moveToFirst()) {
            isItemAddChart = true;
        }
        return isItemAddChart;

    }
}
