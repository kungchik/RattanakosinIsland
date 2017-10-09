package sdu.wirattapong.rattanakosinisland.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by SaNoMan on 14/8/2560.
 */

public class TravelTable {
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
  //  public static final String time_edit = "time_edit";

    public TravelTable(Context context) {
        objMySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        writeSqLiteDatabase = objMySQLiteOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMySQLiteOpenHelper.getReadableDatabase();
    }
    public long AddNewTravelTable(String tvl_Travel,String tvl_Source,String tvl_opentime,String tvl_Image,String tvl_lat,String tvl_long) {
        ContentValues objContentValues = new ContentValues();

        objContentValues.put(objMySQLiteOpenHelper.tvl_Travel, tvl_Travel);
        objContentValues.put(objMySQLiteOpenHelper.tvl_Source, tvl_Source);
        objContentValues.put(objMySQLiteOpenHelper.tvl_opentime, tvl_opentime);
        objContentValues.put(objMySQLiteOpenHelper.tvl_Image, tvl_Image);
        objContentValues.put(objMySQLiteOpenHelper.tvl_lat, tvl_lat);
        objContentValues.put(objMySQLiteOpenHelper.tvl_long, tvl_long);

        return readSqLiteDatabase.insert(objMySQLiteOpenHelper.traveltable, null, objContentValues);
    }

    public String[] readALLTravelTable(int intColume){
        try {
            String[] strResult = null;
            Cursor objCursor = readSqLiteDatabase.query(traveltable, new String[]{tvl_ID,tvl_Travel,tvl_Source,tvl_opentime,tvl_Image,tvl_lat,tvl_long},null,null,null,null,null,null);
            if(objCursor != null){
                if(objCursor.moveToFirst()){
                    int num = objCursor.getCount();
                    strResult = new String[num];
                    for(int i =0;i<num;i++){
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
