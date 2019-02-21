package myapp.anilandroid.com.retrofitwithjsondb.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import myapp.anilandroid.com.retrofitwithjsondb.model.Address;
import myapp.anilandroid.com.retrofitwithjsondb.model.Company;
import myapp.anilandroid.com.retrofitwithjsondb.model.Geo;
import myapp.anilandroid.com.retrofitwithjsondb.model.Resultdata;

public class Databasehelper extends SQLiteOpenHelper {

    //Database name
    public static final String DATABASE_NAME = "Employee.db";

    //Table name
    public static final String TABLE_NAME = "employee_table";
    //columns name
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "USERNAME";
    public static final String COL_4 = "EMAIL";
    public static final String COL_5 = "STREET";
    public static final String COL_6 = "SUITE";
    public static final String COL_7 = "CITY";
    public static final String COL_8 = "ZIPCODE";
    public static final String COL_9 = "LAT";
    public static final String COL_10 = "LNG";
    public static final String COL_11 = "PHONE";
    public static final String COL_12 = "WEBSITE";
    public static final String COL_13 = "COMPANYNAME";
    public static final String COL_14 = "CATCHPHRASE";
    public static final String COL_15 = "BS";


    public Databasehelper(Context context) {
        super( context, DATABASE_NAME, null, 1 );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( "create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,USERNAME TEXT,EMAIL TEXT,STREET TEXT,SUITE TEXT,CITY TEXT,ZIPCODE TEXT,LAT TEXT,LNG TEXT,PHONE TEXT,WEBSITE TEXT,COMPANYNAME TEXT,CATCHPHRASE TEXT,BS TEXT)" );
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL( "DROP TABLE IF EXISTS " + TABLE_NAME );
        onCreate( db );
    }

    public int getCountData() {
        String countQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery( countQuery, null );
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public boolean insertData(String name, String username, String email, String street, String suite, String city, String zipcode, String lat, String lng, String phone, String website, String companyname, String catchphrase, String bs) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put( COL_2, name );
        contentValues.put( COL_3, username );
        contentValues.put( COL_4, email );
        contentValues.put( COL_5, street );
        contentValues.put( COL_6, suite );
        contentValues.put( COL_7, city );
        contentValues.put( COL_8, zipcode );
        contentValues.put( COL_9, lat );
        contentValues.put( COL_10, lng );
        contentValues.put( COL_11, phone );
        contentValues.put( COL_12, website );
        contentValues.put( COL_13, companyname );
        contentValues.put( COL_14, catchphrase );
        contentValues.put( COL_15, bs );
        long result = db.insert( TABLE_NAME, null, contentValues );
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery( "select * from " + TABLE_NAME, null );
        return res;
    }


    public List<Resultdata> getAllEmployeeData() {
        List<Resultdata> resultdataList = new ArrayList<>();
        String selectquery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery( selectquery, null );
        if (cursor.moveToFirst()) {
            do {
               // for (int i = 0; i < resultdataList.size(); i++) {
                    Resultdata resultdata = new Resultdata();
                    resultdata.setId( Integer.valueOf( cursor.getString( cursor.getColumnIndex( COL_1 ) ) ) );
                    resultdata.setName( cursor.getString( cursor.getColumnIndex( COL_2 ) ) );
                    resultdata.setUsername( cursor.getString( cursor.getColumnIndex( COL_3 ) ) );
                    resultdata.setEmail( cursor.getString( cursor.getColumnIndex( COL_4 ) ) );
                    Address address = new Address();
                    address.setStreet( cursor.getString( cursor.getColumnIndex( COL_5 ) ) );
                    address.setSuite( cursor.getString( cursor.getColumnIndex( COL_6 ) ) );
                    address.setCity( cursor.getString( cursor.getColumnIndex( COL_7 ) ) );
                    address.setZipcode( cursor.getString( cursor.getColumnIndex( COL_8 ) ) );
                    Geo geo = new Geo();
                    geo.setLat( cursor.getString( cursor.getColumnIndex( COL_9 ) ) );
                    geo.setLng( cursor.getString( cursor.getColumnIndex( COL_10 ) ) );
                    address.setGeo( geo );
                    resultdata.setAddress( address );
                    resultdata.setPhone( cursor.getString( cursor.getColumnIndex( COL_11 ) ) );
                    resultdata.setWebsite( cursor.getString( cursor.getColumnIndex( COL_12 ) ) );
                    Company company = new Company();
                    company.setName( cursor.getString( cursor.getColumnIndex( COL_13 ) ) );
                    company.setCatchPhrase( cursor.getString( cursor.getColumnIndex( COL_14 ) ) );
                    company.setBs( cursor.getString( cursor.getColumnIndex( COL_15 ) ) );
                    resultdata.setCompany( company );


              //  }


            } while (cursor.moveToNext());
        }
        db.close();
        return resultdataList;
    }

    public boolean updateData(String id, String name, String username, String email, String street, String suite, String city, String zipcode, String lat, String lng, String phone, String website, String companyname, String catchphrase, String bs) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put( COL_1, id );
        contentValues.put( COL_2, name );
        contentValues.put( COL_3, username );
        contentValues.put( COL_4, email );
        contentValues.put( COL_5, street );
        contentValues.put( COL_6, suite );
        contentValues.put( COL_7, city );
        contentValues.put( COL_8, zipcode );
        contentValues.put( COL_9, lat );
        contentValues.put( COL_10, lng );
        contentValues.put( COL_11, phone );
        contentValues.put( COL_12, website );
        contentValues.put( COL_13, companyname );
        contentValues.put( COL_14, catchphrase );
        contentValues.put( COL_15, bs );

        db.update( TABLE_NAME, contentValues, "ID = ?", new String[]{id} );
        return true;
    }

}
