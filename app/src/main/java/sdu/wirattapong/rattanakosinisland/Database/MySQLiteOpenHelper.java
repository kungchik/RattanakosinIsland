package sdu.wirattapong.rattanakosinisland.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by khowoatt on 16/7/2560.
 */

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private final String TAG = getClass().getSimpleName();
    private SQLiteDatabase sqLiteDatabase;

    public static final String DataBase_Name = "travelguide.db";
    public static final int DataBase_Version = 1;

//    public static final String membertable = "membertable";
//    public static final String UserID = BaseColumns._ID;
//    public static final String USERName = "user";
//    public static final String PASSWord = "pass";
//    public static final String Status = "type";
//    public String CREATE_MEMBER_TABLE ="create table "+membertable+" ("+UserID+" integer primary key," + " "+USERName+" text, "+PASSWord+" text, "+Status+" text);";

    public static final String ordertable = "ordertable";
    public static final String or_id = "id_order";
    public static final String or_Name = "name_order";
    public static final String or_detel = "detail_order";
    public static final String or_long= "long_order";
    public static final String or_lat = "lat_order";
    public static final String or_image = "image_order";
    public static final String or_price = "price_order";
    public static final String tor_id = "id_type";
    public static final String or_type = "or_type";
    private static final String CREATE_MENU_TABLE = "create table "+ordertable+" ("+or_id+" integer primary key," +
            " "+or_Name+" text,"+or_detel+" text, "+or_long+" text, "+or_lat+" text, "+or_image+" text,"+or_price+" text,"+tor_id+"text,"+or_type+"text);";

    public static final String traveltable = "traveltable";
    public static final String tvl_ID = "tvl_ID";
    public static final String tvl_Travel = "tvl_Travel";
    public static final String tvl_Source = "tvl_Source";
    public static final String tvl_opentime = "tvl_opentime";
    public static final String tvl_Image = "tvl_Image";
    public static final String tvl_lat = "tvl_lat";
    public static final String tvl_long = "tvl_long";
    public static final String time_edit = "time_edit";
    private static final String CREATE_ORDER_TABLE = "create table "+traveltable+" ("+tvl_ID+" integer primary key," +
            " "+tvl_Travel+" text,"+tvl_Source+" text, "+tvl_opentime+" text, "+tvl_Image+" text, "+tvl_lat+" text,"+tvl_long+"text,"+time_edit+"text);";
    public MySQLiteOpenHelper(Context context) {
        super(context, DataBase_Name, null, DataBase_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, CREATE_MENU_TABLE);
        Log.i(TAG, CREATE_ORDER_TABLE);
        db.execSQL(CREATE_MENU_TABLE);
        db.execSQL(CREATE_ORDER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_MEMBER_TABLE = "DROP TABLE IF EXISTS " + ordertable;

        db.execSQL(DROP_MEMBER_TABLE);

        Log.i(TAG, "Upgrade Database from " + oldVersion + " to " + newVersion);

        onCreate(db);

    }
}
