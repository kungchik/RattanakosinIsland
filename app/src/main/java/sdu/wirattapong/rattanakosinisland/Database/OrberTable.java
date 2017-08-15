package sdu.wirattapong.rattanakosinisland.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by khowoatt on 17/7/2560.
 */
 public class OrberTable {
    private MySQLiteOpenHelper objMySQLiteOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase;
    private SQLiteDatabase readSqLiteDatabase;

    public static final String ordertable = "ordertable";
    public static final String or_id = "id_order";
    public static final String or_Name = "name_order";
    public static final String or_detel = "detail_order";
    public static final String or_long = "long_order";
    public static final String or_lat = "lat_order";
    public static final String or_image = "image_order";
    public static final String or_price = "price_order";

    public OrberTable(Context context) {
        objMySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        writeSqLiteDatabase = objMySQLiteOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMySQLiteOpenHelper.getReadableDatabase();
    }

    public long AddNewOrderTable(String order_ID, String order_MENU, String or_Detel,String or_Long,String or_Lat,String or_Image,String or_price) {
        ContentValues objContentValues = new ContentValues();

        objContentValues.put(objMySQLiteOpenHelper.or_id, order_ID);
        objContentValues.put(objMySQLiteOpenHelper.or_Name, order_MENU);
        objContentValues.put(objMySQLiteOpenHelper.or_detel, or_Detel);
        objContentValues.put(objMySQLiteOpenHelper.or_long, or_Long);
        objContentValues.put(objMySQLiteOpenHelper.or_lat, or_Lat);
        objContentValues.put(objMySQLiteOpenHelper.or_image, or_Image);
        objContentValues.put(objMySQLiteOpenHelper.or_price, or_price);

        return readSqLiteDatabase.insert(objMySQLiteOpenHelper.ordertable, null, objContentValues);
    }

    public String[] readALLOrderTable(int intColume){
        try {
            String[] strResult = null;
            Cursor objCursor = readSqLiteDatabase.query(ordertable, new String[]{or_id,or_Name,or_detel,or_long,or_lat,or_image,or_price},null,null,null,null,null,null);
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
