package sdu.wirattapong.rattanakosinisland.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by SaNoMan on 14/8/2560.
 */

public class TravelTabel {
    private MySQLiteOpenHelper objMySQLiteOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase;
    private SQLiteDatabase readSqLiteDatabase;

    public static final String traveltable = "traveltable";
    public static final String tvl_ID = "tvl_ID";
    public static final String tvl_Travel = "tvl_Travel";
    public static final String tvl_Source = "tvl_Source";
    public static final String tvl_opentime = "tvl_opentime";
    public static final String tvl_Image = "tvl_Image";
    public static final String tvl_lat = "tvl_lat";
    public static final String tvl_long = "tvl_long";
    public static final String time_edit = "time_edit";

    public TravelTabel(Context context) {
        objMySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        writeSqLiteDatabase = objMySQLiteOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMySQLiteOpenHelper.getReadableDatabase();
    }
    public long AddNewOrderTable(String order_ID, String order_MENU, String or_Detel,String or_Long,String or_Lat,String or_Image,String or_price) {
        ContentValues objContentValues = new ContentValues();

        objContentValues.put(objMySQLiteOpenHelper.tvl_ID, tvl_ID);
        objContentValues.put(objMySQLiteOpenHelper.tvl_Travel, tvl_Travel);
        objContentValues.put(objMySQLiteOpenHelper.tvl_Source, tvl_Source);
        objContentValues.put(objMySQLiteOpenHelper.tvl_opentime, tvl_opentime);
        objContentValues.put(objMySQLiteOpenHelper.tvl_Image, tvl_Image);
        objContentValues.put(objMySQLiteOpenHelper.tvl_lat, tvl_lat);
        objContentValues.put(objMySQLiteOpenHelper.tvl_long, tvl_long);
        objContentValues.put(objMySQLiteOpenHelper.time_edit, time_edit);

        return readSqLiteDatabase.insert(objMySQLiteOpenHelper.ordertable, null, objContentValues);
    }

    public String[] readALLTravelTable(int intColume){
        try {
            String[] strResult = null;
            Cursor objCursor = readSqLiteDatabase.query(traveltable, new String[]{tvl_ID,tvl_Travel,tvl_Source,tvl_opentime,tvl_Image,tvl_lat,tvl_long},null,null,null,null,null,null);
            if(objCursor != null){
                if(objCursor.moveToFirst()){
                    strResult = new String[10];
                    // strResult[0] = objCursor.getString(0);
                    // strResult[1] = objCursor.getString(1);
                    // strResult[2] = objCursor.getString(2);
                    // strResult[3] = objCursor.getString(3);
                    for(int i =0;i<10;i++){
                        strResult[i] = objCursor.getString(intColume);
                        objCursor.moveToNext();
                    }
                }
            }
            objCursor.close();
            return strResult;
        }catch (Exception e){
            return null;
        }
        //return new String[0];
    }
}
