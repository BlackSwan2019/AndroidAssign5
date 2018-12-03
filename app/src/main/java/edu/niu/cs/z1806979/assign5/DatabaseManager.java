package edu.niu.cs.z1806979.assign5;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {
    private static final String DATABASENAME = "birdDB";
    private static final int VERSION = 5;
    private static final String TBLNAME = "bird";
    private static final String ID = "id";
    private static final String NAME = "type";
    private static final String PRICE = "quantity";
    private static final String TIME = "time";
    private static final String DATE = "date";
    private static final String WEATHER = "weather";

    public DatabaseManager(Context ctx) {
        super(ctx, DATABASENAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate = "create table " +
                TBLNAME + " (" + ID;
        sqlCreate += " integer primary key autoincrement, " + NAME;
        sqlCreate += " text, " + PRICE + " real, " + TIME + " text, " + DATE + " text, " + WEATHER + " text )";

        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TBLNAME);
        onCreate(db);
    }

    public void insert(Bird c) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into " + TBLNAME;
        sqlInsert += " values (null, '" + c.getType();
        sqlInsert += "', '" + c.getQuantity() + "', '" + c.getTime() + "', '" + c.getDate() + "', '" + c.getWeather() + "')";

        db.execSQL(sqlInsert);
        db.close();         // Close database.
    }

    public ArrayList<Bird> selectAll() {
        SQLiteDatabase db = this.getWritableDatabase();

        String sqlQuery = "select * from " + TBLNAME;

        Cursor c = db.rawQuery(sqlQuery, null);

        ArrayList<Bird> birdies = new ArrayList<>();

        while (c.moveToNext()) {
            Bird currentBird = new Bird(Integer.parseInt(c.getString(0)), c.getString(1), c.getDouble(2), c.getString(3), c.getString(4), c.getString(5));
            birdies.add(currentBird);
        } // End while

        db.close();

        return birdies;
    } // end selectAll

    public Bird selectById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlQuery = "select * from " + TBLNAME;
        sqlQuery += " where " + ID + " =  " + id;
        Cursor c = db.rawQuery(sqlQuery, null);
        Bird bird = null;
        if (c.moveToFirst()) {
            bird = new Bird(Integer.parseInt(c.getString(0)), c.getString(1), c.getDouble(2), c.getString(3), c.getString(4), c.getString(5));
        }

        return bird;
    } // End select by id

    public void deleteById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlQuery = "delete from " + TBLNAME;
        sqlQuery += " where " + ID + " =  " + id;
        db.execSQL(sqlQuery);
        db.close();
    } // End deleteById

    public void update(int id, String name, double price, String weather) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlQuery = "update " + TBLNAME;
        sqlQuery += " set " + NAME + " = '" + name + "', ";
        sqlQuery += PRICE + " = '" + price + "', " + WEATHER + " = '" + weather + "'";
        sqlQuery += " where " + ID + " =  " + id;
        db.execSQL(sqlQuery);
        db.close();
    }
}
