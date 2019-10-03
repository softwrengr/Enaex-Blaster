package com.techease.enaexblaster.sqliteDatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EnaexDatabase extends SQLiteOpenHelper {

    private static String DB_NAME = "Enaex";
    public static int DB_VERSION = 1;

    public EnaexDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String byHoleQuery = "CREATE TABLE BY_HOLE (ID INTEGER PRIMARY KEY AUTOINCREMENT,DIAMETER,DENSITY,BURDEN,SPACING,HOLE_LENGTH," +
                "STEM_LENGTH,ROCK_DENSITY,DISTANCE,SCALING,ATTENUATION,CALCULATOR,VOLUME,VIBRATION,ROW_NAME)";

        String byShotQuery = "CREATE TABLE BY_SHOT (ID INTEGER PRIMARY KEY AUTOINCREMENT,HOLES,ROWS,DIAMETER,DENSITY,BURDEN,SPACING,BENCH_HEIGHT," +
                "SUB_DRILL,STEM_LENGTH,ROCK_DENSITY,HOLE_MS,DISTANCE,SCALING,ATTENUATION,CALCULATOR,VOLUME,CHECK_SUBDRILL,CHECK_HOLES,CHECK_VIBRATION,ROW_NAME)";

        String vibrationQuery = "CREATE TABLE VIBRATION (ID INTEGER PRIMARY KEY AUTOINCREMENT,DISTANCE,MIC,SCALING_FACTOR,ATTENUATION,ROW_NAME,CALCULATOR)";

        String SDOBQuery = "CREATE TABLE SDOB (ID INTEGER PRIMARY KEY AUTOINCREMENT,DIAMETER,DENSITY,HOLE_LENGHT,STEM_LENGHT,ROW_NAME,CALCULATOR)";

        String PowderFactorQuery = "CREATE TABLE Powder_Factor (ID INTEGER PRIMARY KEY AUTOINCREMENT,DIAMETER,DENSITY,BURDEN,SPACING," +
                                                                "HOLE_LENGHT,STEM_LENGHT,ROCK_DENSITY,AIR_DECK,ROW_NAME,CALCULATOR,VOLUME,CHECK_AIRDECK)";

        String ExplosiveWeightQuery = "CREATE TABLE EXPLOSIVE_WEIGHT (ID INTEGER PRIMARY KEY AUTOINCREMENT,DIAMETER,DENSITY,HOLE_LENGHT,STEM_LENGHT,ROW_NAME,CALCULATOR)";

        String VolumeQuery = "CREATE TABLE VOlUME (ID INTEGER PRIMARY KEY AUTOINCREMENT,BURDEN,SPACING,AVERAGE_DEPTH,HOLES,ROCK_DENSITY,ROW_NAME,CALCULATOR,VOLUME)";

        String sclaedDistanceqQuery = "CREATE TABLE SCALED_DISTANCE (ID INTEGER PRIMARY KEY AUTOINCREMENT,DISTANCE,MIC,ROW_NAME,CALCULATOR)";

        sqLiteDatabase.execSQL(byHoleQuery);
        sqLiteDatabase.execSQL(byShotQuery);
        sqLiteDatabase.execSQL(sclaedDistanceqQuery);
        sqLiteDatabase.execSQL(vibrationQuery);
        sqLiteDatabase.execSQL(SDOBQuery);
        sqLiteDatabase.execSQL(PowderFactorQuery);
        sqLiteDatabase.execSQL(ExplosiveWeightQuery);
        sqLiteDatabase.execSQL(VolumeQuery);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS byHoleQuery");
        db.execSQL("DROP TABLE IF EXISTS byShotQuery");
        db.execSQL("DROP TABLE IF EXISTS SCALED_DISTANCE");
        db.execSQL("DROP TABLE IF EXISTS VIBRATION");
        db.execSQL("DROP TABLE IF EXISTS SDOBQuery");
        db.execSQL("DROP TABLE IF EXISTS PowderFactorQuery");
        db.execSQL("DROP TABLE IF EXISTS ExplosiveWeightQuery");
        db.execSQL("DROP TABLE IF EXISTS VolumeQuery");
        onCreate(db);
    }
}
