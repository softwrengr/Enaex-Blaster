package com.techease.enaexblaster.sqliteDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

public class EnaexCrud {

    private static SQLiteDatabase sqLiteDatabase;
    private Context context;

    public EnaexCrud(Context context) {
        EnaexDatabase database = new EnaexDatabase(context);
        sqLiteDatabase = database.getWritableDatabase();
        this.context = context;
    }


    //inserting by shot data
    public void insertByHoleData(String diameter,String density,String burden,String spacing,String holeLength,
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
            Toast.makeText(context, "saved", Toast.LENGTH_SHORT).show();

        } else {
            Log.d("message","already saved");
        }
    }


    //inserting by shot data
    public void insertByShotData(String holes,String rows,String diameter,String density,String burden,String spacing,String bench_height,
                                 String subDril,String stemLenght,String rockDensity,String hole_ms,String distance,String scaling,String attnuation,String row_name) {

        if (!checkData(row_name)) {
            ContentValues values = new ContentValues();
            values.put("HOLES", holes);
            values.put("ROWS",rows);
            values.put("DIAMETER",diameter);
            values.put("DENSITY",density);
            values.put("BURDEN",burden);

            values.put("SPACING", spacing);
            values.put("BENCH_HEIGHT",bench_height);
            values.put("SUB_DRILL",subDril);
            values.put("STEM_LENGTH",stemLenght);
            values.put("ROCK_DENSITY",rockDensity);

            values.put("HOLE_MS",hole_ms);
            values.put("DISTANCE",distance);
            values.put("SCALING",scaling);
            values.put("ATTENUATION",attnuation);
            values.put("ROW_NAME",row_name);

            sqLiteDatabase.insert("BY_SHOT", null, values);
            Log.d("message","saved");

        } else {
            Log.d("message","already saved");
        }
    }

    //inserting scaled distance data
    public void insertScaledDitanceData(String distance,String mic,String row_name) {

        if (!checkData(row_name)) {
            ContentValues values = new ContentValues();
            values.put("DISTANCE", distance);
            values.put("MIC",mic);
            values.put("ROW_NAME",row_name);
            sqLiteDatabase.insert("SCALED_DISTANCE", null, values);
            Log.d("message","saved");

        } else {
            Log.d("message","already saved");
        }
    }

    //inserting scaled distance data
    public void insertVibrationData(String distance,String mic,String scaling_factor,String attenuation,String row_name) {

        if (!checkData(row_name)) {
            ContentValues values = new ContentValues();
            values.put("DISTANCE", distance);
            values.put("MIC",mic);
            values.put("SCALING_FACTOR",scaling_factor);
            values.put("ATTENUATION",attenuation);
            values.put("ROW_NAME",row_name);
            sqLiteDatabase.insert("VIBRATION", null, values);
            Log.d("message","saved");

        } else {
            Log.d("message","already saved");
        }
    }

    //inserting sdob data
    public void insertSDOBData(String diameter,String density,String holeLength,String stemLength,String row_name) {

        if (!checkData(row_name)) {
            ContentValues values = new ContentValues();
            values.put("DIAMETER", diameter);
            values.put("DENSITY",density);
            values.put("HOLE_LENGHT",holeLength);
            values.put("STEM_LENGHT",stemLength);
            values.put("ROW_NAME",row_name);
            sqLiteDatabase.insert("SDOB", null, values);
            Log.d("message","saved");

        } else {
            Log.d("message","already saved");
        }
    }

    //inserting powder factor data
    public void insertPowderFactorData(String diameter,String density,String burden,String spacing,
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
            Log.d("message","saved");

        } else {
            Log.d("message","already saved");
        }
    }

    //inserting explosive weight data
    public void insertExplosiveData(String diameter,String density,String holeLength,
                                    String stemLength,String row_name) {

        if (!checkData(row_name)) {
            ContentValues values = new ContentValues();
            values.put("DIAMETER", diameter);
            values.put("DENSITY",density);
            values.put("HOLE_LENGHT",holeLength);
            values.put("STEM_LENGHT",stemLength);
            values.put("ROW_NAME",row_name);
            sqLiteDatabase.insert("EXPLOSIVE_WEIGHT", null, values);
            Log.d("message","saved");

        } else {
            Log.d("message","already saved");
        }
    }

    //inserting volume data
    public void insertVolumeData(String burden,String spacing,String averageDepth,
                                    String noHole,String rockDensity,String row_name) {

        if (!checkData(row_name)) {
            ContentValues values = new ContentValues();
            values.put("BURDEN", burden);
            values.put("SPACING",spacing);
            values.put("AVERAGE_DEPTH",averageDepth);
            values.put("HOLES",noHole);
            values.put("ROCK_DENSITY",rockDensity);
            values.put("ROW_NAME",row_name);
            sqLiteDatabase.insert("VOlUME", null, values);
            Log.d("message","saved");

        } else {
            Log.d("message","already saved");
        }
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

    //fetching all data
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

    //fetching all vibration data
    public Cursor getSDOBData() {
        String query = "SELECT * FROM SDOB";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        return cursor;
    }

    //fetching all vibration data
    public Cursor getPowderFactorData() {
        String query = "SELECT * FROM Powder_Factor";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        return cursor;
    }

    //fetching all vibration data
    public Cursor getExplosiveWeightData() {
        String query = "SELECT * FROM Explosive_Weight";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        return cursor;
    }

    //fetching all vibration data
    public Cursor getVolumeData() {
        String query = "SELECT * FROM VOlUME";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        return cursor;
    }

    public Cursor getShotData() {
        String query = "SELECT * FROM BY_SHOT";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        return cursor;
    }

    public Cursor getHoleData() {
        String query = "SELECT * FROM BY_HOLE";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        return cursor;
    }

    //deleting item from cart table
    public void delete(String row_name) {
        this.sqLiteDatabase.delete("SCALED_DISTANCE", "ROW_NAME= '" + row_name + "'", null);
        Toast.makeText(context, "task deleted successful", Toast.LENGTH_SHORT).show();
    }


}
